package com.example.BaiTap2.service;

import com.example.BaiTap2.model.Category;
import com.example.BaiTap2.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /* ================= GET ================= */

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /* ================= SAVE ================= */

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    /* ================= DELETE ================= */

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}