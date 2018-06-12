package uestc.articlewebserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import nlplib.commonclasses.Article;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author uestc
 */
@RestController
public class ArticleWebserverController {

    CorpusManager corpusManager = new CorpusManager();

    @RequestMapping("/")
    public String index() {
        System.out.println("@RequestMapping(\"/\")");
        return "Greetings from article web server!";
    }

    @RequestMapping("/listCorpus")
    public Set<String> listCorpus() {
        System.out.println("@RequestMapping(\"/listCorpus\")");
        return corpusManager.getCorpusMap().keySet();
    }

    @RequestMapping("/getCorpus")
    public List<Article> getCorpus(@RequestParam("corpus") String corpus) {
        System.out.println("@RequestMapping(\"/getCorpus\")");
        if (corpusManager.getCorpusMap().get(corpus) != null) {
            return corpusManager.getCorpusMap().get(corpus).getArticles();
        }
        return new ArrayList<Article>();
    }

    @RequestMapping("/test")
    public String test() {
        System.out.println("@RequestMapping(\"/test\")");
        return "什么鬼...";
    }

}
