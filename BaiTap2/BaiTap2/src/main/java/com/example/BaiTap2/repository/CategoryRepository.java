package com.example.BaiTap2.repository;

import com.example.BaiTap2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}