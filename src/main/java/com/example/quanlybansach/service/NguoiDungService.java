package com.example.quanlybansach.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quanlybansach.domain.model.NguoiDung;
import com.example.quanlybansach.repository.NguoiDungRepository;

@Service
public class NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    // thêm mới người dùng

    public NguoiDung save(NguoiDung nguoiDung) {
        return this.nguoiDungRepository.save(nguoiDung);
    }

    // hiện thị tất cả người dùng
    public List<NguoiDung> getTatCaNguoiDung() {
        return this.nguoiDungRepository.findAll();
    }

    // hiển thị thông tin người dùng bằng id
    public NguoiDung getNguoiDungById(String id) {
        return this.nguoiDungRepository.findById(id).orElse(null);
    }

    // xóa người dùng
    public void xoaNguoiDung(String id) {
        this.nguoiDungRepository.deleteById(id);
    }
    // sửa thông tin người dùng

    public NguoiDung suaNguoiDung(String id, NguoiDung nguoiDung) {
        NguoiDung nguoiDungCu = this.getNguoiDungById(id);
        if (nguoiDungCu.getId() != null) {
            return this.nguoiDungRepository.save(nguoiDung);
        }
        return this.nguoiDungRepository.save(nguoiDung);
    }

    // tìm kiếm người dùng
    public List<NguoiDung> timKiemNguoiDung(String keyword) {
        return this.nguoiDungRepository.timKiemTheoTen(keyword);
    }

}
