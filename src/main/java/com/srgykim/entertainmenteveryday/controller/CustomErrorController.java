package com.srgykim.entertainmenteveryday.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class is responsible for redirecting to 404 page
 * whenever user tries to visit the URI that doesn't exist.
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "page-not-found";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
