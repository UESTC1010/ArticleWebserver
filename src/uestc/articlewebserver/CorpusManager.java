/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uestc.articlewebserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import nlpevaluation.corpus.CategorizedCorpus;
import nlpevaluation.corpus.Corpus;
import nlpevaluation.corpus.CorpusType;
import nlpevaluation.corpus.DependencyCorpus;
import nlpevaluation.corpus.EntityCorpus;
import nlpevaluation.corpus.EventCorpus;
import nlpevaluation.corpus.InfoRetrievalCorpus;
import nlpevaluation.corpus.KeywordCorpus;
import nlpevaluation.corpus.RelationCorpus;
import nlpevaluation.corpus.SegCorpus;
import nlpevaluation.corpus.SentimentCorpus;

/**
 *
 * @author UESTC1010
 */
public class CorpusManager {

    private String corpusRoot;
//    private Map<String, Corpus> corpusMap = new TreeMap<>();
    private List<String> corpusList = new ArrayList<>();

    public CorpusManager() {
        corpusRoot = Properties.getProperties().getProperty("corpusRoot");
        loadCorpusList();
    }

    public List<String> getCorpusList() {
        if (corpusList.isEmpty()) {
            loadCorpusList();
        }
        return corpusList;
    }

    public Corpus getCorpus(String CorpusName) {
        File corpusRootDir = new File(corpusRoot);
        if (corpusRootDir.isDirectory()) {
            for (File corpusFile : corpusRootDir.listFiles()) {
                if (corpusFile.isFile()
                        && (corpusFile.getName().endsWith(".json") || corpusFile.getName().endsWith(".Json") || corpusFile.getName().endsWith(".JSON"))) {
//                    corpusMap.put(corpusFile.getName().substring(0, corpusFile.getName().length() - 5), loadCorpus(corpusFile));
                    if (CorpusName.equalsIgnoreCase(corpusFile.getName().substring(0, corpusFile.getName().length() - 5))) {
                        return loadCorpus(corpusFile);
                    }
                }
            }
        }

        return null;
    }

    private void loadCorpusList() {
        corpusList.clear();
        File corpusRootDir = new File(corpusRoot);
        if (corpusRootDir.isDirectory()) {
            for (File corpusFile : corpusRootDir.listFiles()) {
                if (corpusFile.isFile()
                        && (corpusFile.getName().endsWith(".json") || corpusFile.getName().endsWith(".Json") || corpusFile.getName().endsWith(".JSON"))) {
//                    corpusMap.put(corpusFile.getName().substring(0, corpusFile.getName().length() - 5), loadCorpus(corpusFile));
                    corpusList.add(corpusFile.getName().substring(0, corpusFile.getName().length() - 5));
                }
            }
        }
    }

    private Corpus loadCorpus(File corpusFile) {
        String fileName = corpusFile.getName();
        for (CorpusType type : CorpusType.values()) {
            String prefix = Properties.getProperties().getProperty(type.name());
            if (fileName.startsWith(prefix)) {
                switch (type) {
                    case 分词和词性标注语料:
                        return SegCorpus.loadCorpusFromJson(corpusFile);
                    case 依存句法语料:
                        return DependencyCorpus.loadCorpusFromJson(corpusFile);
                    case 命名实体语料:
                        return EntityCorpus.loadCorpusFromJson(corpusFile);
                    case 实体关系语料:
                        return RelationCorpus.loadCorpusFromJson(corpusFile);
                    case 事件抽取语料:
                        return EventCorpus.loadCorpusFromJson(corpusFile);
                    case 文本关键词语料:
                        return KeywordCorpus.loadCorpusFromJson(corpusFile);
                    case 文本类别语料:
                        return CategorizedCorpus.loadCorpusFromJson(corpusFile);
                    case 情感分析语料:
                        return SentimentCorpus.loadCorpusFromJson(corpusFile);
                    case 信息检索语料:
                        return InfoRetrievalCorpus.loadCorpusFromJson(corpusFile);
                }
            }
        }
        return null;
    }

}
