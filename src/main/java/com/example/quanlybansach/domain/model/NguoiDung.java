package com.example.quanlybansach.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.quanlybansach.domain.enums.VaiTro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "nguoidung") // tên bảng/ document csld
public class NguoiDung {

    @Id // ObjectID bên MongoDB
    private String id;
    private String tenNguoiDung;
    private String email;
    private String matKhau;
    private VaiTro vaiTro = VaiTro.USER; // admin, user

    @CreatedDate
    private LocalDateTime ngayThem;
}
