package com.example.quanlybansach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {
    @GetMapping("/")
    public String trangChu() {
        return "trang_chu";
    }
}
