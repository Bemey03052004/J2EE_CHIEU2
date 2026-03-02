package com.example.BaiTap2.repository;

import com.example.BaiTap2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}