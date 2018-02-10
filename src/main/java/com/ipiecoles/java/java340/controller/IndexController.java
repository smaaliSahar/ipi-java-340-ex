package com.ipiecoles.java.java340.controller;

import com.ipiecoles.java.java340.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private EmployeService employeService;

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("nbEmployes", employeService.countAllEmploye());
        return "index";
    }

}