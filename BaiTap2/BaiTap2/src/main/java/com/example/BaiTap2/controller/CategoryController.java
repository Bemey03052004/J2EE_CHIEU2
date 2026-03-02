package com.example.BaiTap2.controller;

import com.example.BaiTap2.model.Category;
import com.example.BaiTap2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }

    @PostMapping("/save")
    public String save(@RequestParam String name) {
        Category c = new Category();
        c.setName(name);
        categoryRepository.save(c);
        return "redirect:/categories";
    }
}