package com.example.BaiTap2.repository;

import com.example.BaiTap2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}