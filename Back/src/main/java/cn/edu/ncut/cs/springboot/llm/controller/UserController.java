package cn.edu.ncut.cs.springboot.llm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/check")
    public String check() {
        return "1";
    }
}