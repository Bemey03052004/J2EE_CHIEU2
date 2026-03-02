package com.example.BaiTap2.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String author;

    private String image;

    // Quan hệ nhiều sách thuộc 1 danh mục
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}