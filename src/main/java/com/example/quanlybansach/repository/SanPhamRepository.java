package com.example.quanlybansach.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.quanlybansach.domain.model.SanPham;

public interface SanPhamRepository extends MongoRepository<SanPham, String> {
    @Query("""
            {
              "$or": [
                { "id": { "$regex": ?0, "$options": "i" } },
                { "tieuDe": { "$regex": ?0, "$options": "i" } },
                { "tacGia": { "$regex": ?0, "$options": "i" } },
                { "moTa": { "$regex": ?0, "$options": "i" } },
                { "theLoai": { "$regex": ?0, "$options": "i" } }
              ]
            }
            """)
    List<SanPham> timKiemToanBo(String keyword);

}
