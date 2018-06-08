package uestc.articlewebserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return corpusManager.getCorpusMap().keySet();
    }

    @RequestMapping("/getCorpus")
    public List<Article> getCorpus(@RequestParam("corpus") String corpus) {
        return corpusManager.getCorpusMap().get(corpus).getArticles();
    }

    @RequestMapping("/test")
    public String test() {
        return "什么鬼...";
    }

}
