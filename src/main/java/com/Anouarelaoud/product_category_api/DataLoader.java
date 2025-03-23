package com.Anouarelaoud.product_category_api;

import com.Anouarelaoud.product_category_api.model.Category;
import com.Anouarelaoud.product_category_api.model.Product;
import com.Anouarelaoud.product_category_api.repository.CategoryRepository;
import com.Anouarelaoud.product_category_api.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final Faker faker = new Faker();

    public DataLoader(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Generate some random categories
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            categories.add(new Category(
                    null,
                    faker.commerce().department(),
                    null, // Parent category can be null for top-level categories
                    new ArrayList<>()));
        }
        categoryRepository.saveAll(categories);

        // Generate some random products
        List<Product> products = new ArrayList<>();
        for (Category category : categories) {
            for (int i = 0; i < 3; i++) {
                products.add(new Product(
                        null,
                        faker.commerce().productName(),
                        faker.commerce().material(),
                        Double.parseDouble(faker.commerce().price()),
                        "USD",
                        category));
            }
        }

        productRepository.saveAll(products);

        System.out.println("Random demo data created successfully!");
    }
}
