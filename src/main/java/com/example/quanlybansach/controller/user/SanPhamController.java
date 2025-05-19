package com.example.quanlybansach.controller.user;

import com.example.quanlybansach.domain.model.SanPham;
import com.example.quanlybansach.service.SanPhamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/sanpham") // 👈 Đường dẫn của người dùng bắt đầu từ đây
public class SanPhamController {

    private final SanPhamService sanPhamService;

    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    // ✅ GET: Hiển thị tất cả sản phẩm cho user
    @GetMapping("/tatca")
    public String danhSachSanPham(Model model) {
        List<SanPham> danhSach = sanPhamService.getTatCaSanPham();
        model.addAttribute("sanPhams", danhSach);
        return "user/danhsach_sanpham"; // Tạo file trong templates/user/
    }

    // ✅ GET: Chi tiết sản phẩm
    @GetMapping("/chitiet/{id}")
    public String chiTietSanPham(@PathVariable String id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        if (sanPham == null) {
            return "redirect:/user/sanpham/tatca?error=notfound";
        }
        model.addAttribute("sanPham", sanPham);
        return "user/chitiet_sanpham"; // Tạo file trong templates/user/
    }

    // ✅ GET: Tìm kiếm sản phẩm theo từ khóa
    @GetMapping("/timkiem")
    public String timKiem(@RequestParam("keyword") String keyword, Model model) {
        List<SanPham> ketQua = sanPhamService.timKiemSanPham(keyword);
        model.addAttribute("sanPhams", ketQua);
        model.addAttribute("keyword", keyword);
        return "user/danhsach_sanpham";
    }
}
