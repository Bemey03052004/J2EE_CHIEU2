package com.example.BaiTap2.service;

import com.example.BaiTap2.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    /* ================= GET ================= */
    public List<Product> getAll() {
        return products;
    }

    public Product get(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /* ================= ADD ================= */
    public void add(Product product) {
        int maxId = products.stream()
                .mapToInt(Product::getId)
                .max()
                .orElse(0);
        product.setId(maxId + 1);
        products.add(product);
    }

    /* ================= UPDATE ================= */
    public void update(Product product) {
        Product old = get(product.getId());
        if (old != null) {
            old.setName(product.getName());
            old.setPrice(product.getPrice());
            old.setCategory(product.getCategory());

            if (product.getImage() != null) {
                old.setImage(product.getImage());
            }
        }
    }

    /* ================= DELETE ================= */
    public void delete(int id) {
        Product product = get(id);
        if (product == null) return;

        // xoá ảnh nếu có
        if (product.getImage() != null) {
            deleteImage(product.getImage());
        }

        products.remove(product);
    }

    private void deleteImage(String imageName) {
        try {
            Path imagePath = Paths.get("src/main/resources/static/images")
                    .resolve(imageName);

            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            throw new RuntimeException("Không thể xoá ảnh: " + imageName, e);
        }
    }

    /* ================= IMAGE ================= */
    public void updateImage(Product product, MultipartFile file) {
        if (file == null || file.isEmpty()) return;

        if (file.getContentType() == null || !file.getContentType().startsWith("image")) {
            throw new IllegalArgumentException("File không phải hình ảnh");
        }

        try {
            Path uploadDir = Paths.get("src/main/resources/static/images");
            Files.createDirectories(uploadDir);

            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), uploadDir.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            product.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
