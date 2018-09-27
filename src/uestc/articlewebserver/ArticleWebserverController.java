package uestc.articlewebserver;

import java.util.ArrayList;
import java.util.List;
import nlplib.commonclasses.Article;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
 * @author uestc1010
 */
@RestController
@EnableAutoConfiguration
public class ArticleWebserverController {

    CorpusManager corpusManager = new CorpusManager();

    @RequestMapping("/")
    public String index() {
        System.out.println("@RequestMapping(\"/\")");
        return "Greetings from article web server!";
    }

    @RequestMapping("/listCorpus")
    public List<String> listCorpus() {
        System.out.println("@RequestMapping(\"/listCorpus\")");
        return corpusManager.getCorpusList();
    }

    @RequestMapping("/getCorpus")
    public List<Article> getCorpus(@RequestParam("corpus") String corpus) {
        System.out.println("@RequestMapping(\"/getCorpus\")");
        if (corpusManager.getCorpusList().contains(corpus)) {
            return corpusManager.getCorpus(corpus).getArticles();
        }
        return new ArrayList<Article>();
    }

    @RequestMapping("/test")
    public String test() {
        System.out.println("@RequestMapping(\"/test\")");
        return "什么鬼...";
    }

}
