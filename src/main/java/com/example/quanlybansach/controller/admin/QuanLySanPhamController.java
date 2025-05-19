package com.example.quanlybansach.controller.admin;

import com.example.quanlybansach.domain.model.SanPham;
import com.example.quanlybansach.service.SanPhamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class QuanLySanPhamController {

    private final SanPhamService sanPhamService;

    public QuanLySanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    // ✅ GET: Hiển thị form thêm mới sản phẩm
    @GetMapping("/sanpham/them")
    public String themSanPhamForm(Model model) {
        model.addAttribute("sanPham", new SanPham());
        return "admin/them_sanpham"; // Tạo file them_sanpham.html trong templates/admin
    }

    // ✅ POST: Xử lý thêm mới sản phẩm
    @PostMapping("/sanpham/them")
    public String themSanPham(@ModelAttribute SanPham sanPham) {
        this.sanPhamService.save(sanPham);
        return "redirect:/admin/sanpham/tatca";
    }

    // ✅ GET: Danh sách tất cả sản phẩm
    @GetMapping("/sanpham/tatca")
    public String getTatCaSanPham(Model model) {
        List<SanPham> danhSachSanPham = this.sanPhamService.getTatCaSanPham();
        model.addAttribute("sanPhams", danhSachSanPham);
        return "admin/danhsach_sanpham"; // Tạo file danhsach_sanpham.html
    }

    // ✅ GET: Chi tiết sản phẩm
    @GetMapping("/sanpham/chitiet/{id}")
    public String getSanPhamById(@PathVariable String id, Model model) {
        SanPham sanPham = this.sanPhamService.getSanPhamById(id);
        if (sanPham == null) {
            return "redirect:/admin/sanpham/tatca?error=notfound";
        }
        model.addAttribute("sanPham", sanPham);
        return "admin/chitiet_sanpham"; // Tạo file chitiet_sanpham.html
    }

    // ✅ POST: Xóa sản phẩm
    @PostMapping("/sanpham/xoa/{id}")
    public String xoaSanPham(@PathVariable String id) {
        this.sanPhamService.xoaSanPham(id);
        return "redirect:/admin/sanpham/tatca";
    }

    // ✅ GET: Hiển thị form sửa sản phẩm
    @GetMapping("/sanpham/sua/{id}")
    public String suaSanPhamForm(@PathVariable String id, Model model) {
        SanPham sanPham = this.sanPhamService.getSanPhamById(id);
        if (sanPham == null) {
            return "redirect:/admin/sanpham/tatca?error=notfound";
        }
        model.addAttribute("sanPham", sanPham);
        return "admin/sua_sanpham"; // Tạo file sua_sanpham.html
    }

    // ✅ POST: Xử lý sửa sản phẩm
    @PostMapping("/sanpham/sua/{id}")
    public String suaSanPham(@PathVariable String id, @ModelAttribute SanPham request) {
        SanPham sanPhamHienTai = this.sanPhamService.getSanPhamById(id);
        if (sanPhamHienTai == null) {
            return "redirect:/admin/sanpham/tatca?error=notfound";
        }
        if (sanPhamHienTai != null) {
            sanPhamHienTai.setTieuDe(request.getTieuDe());
            sanPhamHienTai.setTacGia(request.getTacGia());
            sanPhamHienTai.setMoTa(request.getMoTa());
            sanPhamHienTai.setGia(request.getGia());
            sanPhamHienTai.setSoLuong(request.getSoLuong());
            sanPhamHienTai.setTheLoai(request.getTheLoai());
            sanPhamHienTai.setHinhAnh(request.getHinhAnh());
            this.sanPhamService.suaSanPham(id, sanPhamHienTai);

        }
        return "redirect:/admin/sanpham/tatca";
    }

    // ✅ GET: Tìm kiếm sản phẩm
    @GetMapping("/sanpham/timkiem")
    public String timKiemSanPham(@RequestParam("keyword") String keyword, Model model) {
        List<SanPham> ketQua = this.sanPhamService.timKiemSanPham(keyword);
        model.addAttribute("sanPhams", ketQua);
        return "admin/danhsach_sanpham"; // dùng lại giao diện danh sách
    }
}
