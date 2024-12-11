package com.pm_app.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {

    @RequestMapping(value = "/")
    private String showReact(){
        return "showing front!";
    }
}
