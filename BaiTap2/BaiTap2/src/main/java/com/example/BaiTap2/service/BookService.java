package com.example.BaiTap2.service;

import jakarta.annotation.PostConstruct;
import com.example.BaiTap2.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    @PostConstruct
    public void initBooks() {
        books.add(new Book(1, "Lập trình Spring boot", "Võ Tuấn Anh"));
        books.add(new Book(2, "Lập trình hướng đối tượng", "Võ Tuấn Anh"));

    }
    public List<Book> getAllBooks() {
        return books;
    }
    public Book getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().get();
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public void updateBook(int id, Book updatedBook) {
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                });
    }
    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}