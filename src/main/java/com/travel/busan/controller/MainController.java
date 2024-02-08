package com.travel.busan.controller;

import ch.qos.logback.core.model.Model;
import com.travel.busan.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/")
    public String home(Model model){

        return "layout/MainLayout";
    }
}
