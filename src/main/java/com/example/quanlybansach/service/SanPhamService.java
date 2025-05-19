package com.example.quanlybansach.service;

import com.example.quanlybansach.domain.model.SanPham;
import com.example.quanlybansach.repository.SanPhamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {

    private final SanPhamRepository sanPhamRepository;

    public SanPhamService(SanPhamRepository sanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
    }

    // Thêm mới sản phẩm
    public SanPham save(SanPham sanPham) {
        return this.sanPhamRepository.save(sanPham);
    }

    // Lấy tất cả sản phẩm
    public List<SanPham> getTatCaSanPham() {
        return this.sanPhamRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    public SanPham getSanPhamById(String id) {
        return this.sanPhamRepository.findById(id).orElse(null);
    }

    // Xóa sản phẩm theo ID
    public void xoaSanPham(String id) {
        this.sanPhamRepository.deleteById(id);
    }

    // Sửa sản phẩm
    public SanPham suaSanPham(String id, SanPham sanPhamMoi) {
        SanPham sanPhamCu = this.getSanPhamById(id);
        if (sanPhamCu != null) {
            sanPhamMoi.setId(id); // đảm bảo ID không bị thay đổi
            return this.sanPhamRepository.save(sanPhamMoi);
        }
        return null; // hoặc throw exception nếu muốn thông báo lỗi
    }

    // Tìm kiếm sản phẩm theo tên (nếu bạn có custom query trong Repository)
    public List<SanPham> timKiemSanPham(String keyword) {
        return this.sanPhamRepository.timKiemToanBo(keyword); // cần định nghĩa trong repository
    }
}
