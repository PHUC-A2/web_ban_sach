package com.example.quanlybansach.controller.admin;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quanlybansach.domain.model.NguoiDung;
import com.example.quanlybansach.service.NguoiDungService;

@Controller
@RequestMapping("/admin")
public class QuanLyNguoiDungController {

    private final NguoiDungService nguoiDungService;

    public QuanLyNguoiDungController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    // thêm mới người dùng
    @GetMapping("/nguoidung/them")
    public String themNguoiDung(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "admin/them_nguoidung";
    }

    // Post thêm
    @PostMapping("/nguoidung/them")
    public String themNguoiDung(@ModelAttribute NguoiDung nguoiDung) {
        this.nguoiDungService.save(nguoiDung);
        return "redirect:/admin/nguoidung/tatca";

    }

    // hiện thị tất cả người dùng
    @GetMapping("/nguoidung/tatca")
    public String getTatCaNguoiDung(Model model) {
        List<NguoiDung> nguoiDungs = this.nguoiDungService.getTatCaNguoiDung();
        model.addAttribute("nguoiDungs", nguoiDungs);
        return "admin/danhsach_nguoidung";
    }

    // hiển thị thông tin người dùng bằng id
    @GetMapping("/nguoidung/chitiet/{id}")
    public String getNguoiDungById(Model model, @PathVariable String id) {
        NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungById(id);
        model.addAttribute("nguoiDung", nguoiDung);
        return "admin/chitiet_nguoidung";
    }

    // xóa người dùng
    @PostMapping("/nguoidung/xoa/{id}")
    public String xoaNguoiDung(@PathVariable String id) {
        this.nguoiDungService.xoaNguoiDung(id);
        return "redirect:/admin/nguoidung/tatca";
    }

    // Get sửa thông tin người dùng
    @GetMapping("/nguoidung/sua/{id}")
    public String suaNguoiDung(Model model, @PathVariable String id) {
        NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungById(id);
        model.addAttribute("nguoiDung", nguoiDung);
        return "admin/sua_nguoidung";
    }

    // sửa thông tin người dùng
    @PostMapping("/nguoidung/sua/{id}")
    public String suaNguoiDung(Model model, @PathVariable String id,
            @ModelAttribute NguoiDung request) {
        NguoiDung nguoiDungHienTai = this.nguoiDungService.getNguoiDungById(id);
        if (nguoiDungHienTai == null) {
            return "redirect:/admin/nguoidung?error=notfound";
        }
        if (nguoiDungHienTai != null) {
            nguoiDungHienTai.setTenNguoiDung(request.getTenNguoiDung());
            nguoiDungHienTai.setEmail(request.getEmail());
            nguoiDungHienTai.setMatKhau(request.getMatKhau());
            nguoiDungHienTai.setVaiTro(request.getVaiTro());
            nguoiDungHienTai.setNgayThem(LocalDateTime.now());
            this.nguoiDungService.suaNguoiDung(id, nguoiDungHienTai);

        }
        return "redirect:/admin/nguoidung/tatca";

    }

    // tìm kiếm người dùng
    @GetMapping("/nguoidung/timkiem")
    public String timKiemNguoiDung(Model model, @RequestParam("keyword") String keyword) {
        List<NguoiDung> nguoiDungs = this.nguoiDungService.timKiemNguoiDung(keyword);
        model.addAttribute("nguoiDungs", nguoiDungs);
        return "admin/danhsach_nguoidung";
    }

}
