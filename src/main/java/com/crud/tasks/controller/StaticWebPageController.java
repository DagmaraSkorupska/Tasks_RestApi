package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model){
        model.put("variable", "My Thymeleaf variable");
        model.put("multi", "*");
        model.put("equal", "=");
        model.put("add", "+");
        model.put("minus", "-");

        model.put("two", 2);
        return "index";
    }
}
