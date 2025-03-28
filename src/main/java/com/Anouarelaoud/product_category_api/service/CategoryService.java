package com.Anouarelaoud.product_category_api.service;

import com.Anouarelaoud.product_category_api.exception.ResourceNotFoundException;
import com.Anouarelaoud.product_category_api.model.Category;
import com.Anouarelaoud.product_category_api.model.Product;
import com.Anouarelaoud.product_category_api.repository.CategoryRepository;
import com.Anouarelaoud.product_category_api.repository.ProductRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatedCategory.getName());
            category.setParentCategory(updatedCategory.getParentCategory());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
    }

    public void deleteCategory(Long id) {
        List<Product> products = productRepository.findByCategoryId(id);
        productRepository.deleteAll(products);
        categoryRepository.deleteById(id);
    }
}
