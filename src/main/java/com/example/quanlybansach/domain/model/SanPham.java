package com.example.quanlybansach.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "sanpham")
public class SanPham {
    @Id
    private String id;
    private String tieuDe; // tên Sách
    private String tacGia;
    private String moTa;
    private double gia;
    private int soLuong;
    private String theLoai;
    private String hinhAnh;

    @CreatedDate
    private LocalDateTime ngayThem;

    @LastModifiedDate
    private LocalDateTime ngayCapNhat;
}
