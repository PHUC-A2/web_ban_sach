package com.example.quanlybansach.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.quanlybansach.domain.model.NguoiDung;

public interface NguoiDungRepository extends MongoRepository<NguoiDung, String> {
    @Query("{ 'tenNguoiDung': { $regex: ?0, $options: 'i' } }")
    List<NguoiDung> timKiemTheoTen(String keyword);
}
