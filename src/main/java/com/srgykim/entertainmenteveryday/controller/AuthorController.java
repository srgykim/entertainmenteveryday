package com.srgykim.entertainmenteveryday.controller;

import com.srgykim.entertainmenteveryday.data.ArticleDAO;
import com.srgykim.entertainmenteveryday.data.AuthorDAO;
import com.srgykim.entertainmenteveryday.model.Article;
import com.srgykim.entertainmenteveryday.model.Author;
import com.srgykim.entertainmenteveryday.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class AuthorController {

    @Autowired
    private ArticleDAO articleDao;

    @Autowired
    private AuthorDAO authorDao;

    @Autowired
    ArticleService articleService;

    @RequestMapping("/author/login")
    public String authorLogin() {

        return "author-login";
    }

    @RequestMapping(value="/author", method= RequestMethod.GET)
    public String authorPage(Model model, Principal principal) {
        
        Author author = authorDao.getAuthorById(principal.getName());
        model.addAttribute("author", author);
        model.addAttribute("article", new Article());
        return "author";
    }

    @RequestMapping(value="/author", method= RequestMethod.POST)
    public String articleSubmit(@ModelAttribute Article article, Model model,
                                Principal principal) {

        article.setShortTitledId(articleService.formatShortTitledId(article));
        article.setAuthorId(principal.getName());
        articleDao.publishArticle(article);
        model.addAttribute("article", article);
        return "just-published";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "access-denied";
    }
}
