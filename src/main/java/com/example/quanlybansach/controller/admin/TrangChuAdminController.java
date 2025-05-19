package com.example.quanlybansach.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TrangChuAdminController {
    @GetMapping("/admin")
    public String trangChu() {
        return "admin/trangchu_admin";
    }
}
