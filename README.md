A Spring Boot application that allows managing product categories and products

# 🛠 Features
* ✅ Manage categories (list, create, update, delete)
* ✅ Manage products (list, create, update, delete)
* ✅ Swagger API documentation for easy testing
* ✅ Convert product prices to different currencies (TODO)
* ✅ Uses Fixer.io API for real-time currency exchange rates (TODO)

# 📂 Project Structure

📦 product-category-api
 * ┣ 📂 src/main/java/com/Anouarelaoud/product_category_api
 * ┃ ┣ 📂 controller  # REST controllers
 * ┃ ┣ 📂 model       # Entity classes
 * ┃ ┣ 📂 repository  # Database access layer
 * ┃ ┣ 📂 service     # Business logic
 * ┃ ┗ 📜 ProductCategoryApiApplication.java  # Main entry point
 * ┣ 📜 pom.xml       # Maven dependencies
 * ┣ 📜 application.properties  # Configuration (database, API keys)

# 🚀 How to Run the Backend

1️⃣ Prerequisites
* Java 17+
* Maven

2️⃣ Run the Backend
* `mvn spring-boot:run`
