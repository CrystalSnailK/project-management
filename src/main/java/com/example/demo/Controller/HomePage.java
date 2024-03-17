package com.example.demo.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePage {
    @RequestMapping("/")
    public String homePage() {
        return "redirect:/templates/index.html";
    }
}
