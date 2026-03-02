package com.example.BaiTap2.controller;

import com.example.BaiTap2.model.Book;
import com.example.BaiTap2.model.Category;
import com.example.BaiTap2.repository.BookRepository;
import com.example.BaiTap2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    private final String UPLOAD_DIR = "src/main/resources/static/images/";

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll()); // 🔥 thêm dòng này
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book,
                          @RequestParam("imageFile") MultipartFile imageFile,
                          @RequestParam("categoryId") Integer categoryId) {

        // 🔥 Lấy category từ DB
        Category category = categoryRepository.findById(categoryId).orElse(null);
        book.setCategory(category);

        if (!imageFile.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                Files.createDirectories(uploadPath);

                String fileName = UUID.randomUUID() + "-" + imageFile.getOriginalFilename();

                Files.copy(imageFile.getInputStream(),
                        uploadPath.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);

                book.setImage(fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bookRepository.save(book);
        return "redirect:/books";
    }

}