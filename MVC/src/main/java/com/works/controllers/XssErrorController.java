package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XssErrorController {

    @GetMapping("/xssError")
    public String xssError() {
        return "xssError";
    }

}
