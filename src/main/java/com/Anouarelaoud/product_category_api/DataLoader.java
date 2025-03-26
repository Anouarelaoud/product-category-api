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
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<Category> subCategories = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                Category subCategory = new Category(
                        null,
                        faker.commerce().department() + " Subcategory " + (i * 3 + j + 1),
                        null,
                        new ArrayList<>());
                subCategories.add(subCategory);
            }

            Category mainCategory = new Category(
                    null,
                    faker.commerce().department() + " Category " + (i + 1),
                    null,
                    subCategories);

            for (Category subCategory : subCategories) {
                subCategory.setParentCategory(mainCategory);
            }

            categories.add(mainCategory);
        }

        categoryRepository.saveAll(categories);

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

            for (Category subCategory : category.getSubcategories()) {
                for (int i = 0; i < 2; i++) {
                    products.add(new Product(
                            null,
                            faker.commerce().productName(),
                            faker.commerce().material(),
                            Double.parseDouble(faker.commerce().price()),
                            "USD",
                            subCategory));
                }
            }
        }

        productRepository.saveAll(products);

        System.out.println("Random demo data with categories, subcategories, and products created successfully!");
    }
}
