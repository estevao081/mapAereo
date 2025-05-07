# mapAereo API

A RESTful API built with Spring Boot for managing product inventory and operations in a store environment.

## 📦 Features

- Register, update, delete and list products
- Data validation using custom exception handling
- H2 in-memory database support
- Swagger documentation via OpenAPI 3

## 🚀 Technologies

- Java 19
- Spring Boot 3.2.5
- Spring Data JPA
- Spring Validation
- H2 Database
- OpenAPI / Swagger UI

## 🧪 Running Tests

Execute the following command to run unit tests:

```bash
./mvnw test
```

## 🧭 API Endpoints

| Method | Endpoint           | Description                          |
|--------|--------------------|--------------------------------------|
| GET    | `/products`        | Get all products                     |
| GET    | `/products/{id}`   | Get a single product by ID           |
| POST   | `/products`        | Create a new product                 |
| PUT    | `/products/{id}`   | Update an existing product by ID     |
| DELETE | `/products/{id}`   | Delete a product by ID               |

## 🧠 Validations
The API includes custom validation rules:

code: Must be exactly 6 integer digits

name: Only letters, numbers, spaces, and hyphens allowed

expiration date: Must match DD/MM/YYYY format and be valid (dates prior to the current day are not allowed)

address: We got two store address patterns: 17A-R5 and PAR-123 where:

On "17A-R5" example:
- __17__ is the street number where the product is stored (can go from 1 to 20)
- __A__ is the street side where the product is stored (can be A or B)
- __Hyphen__ split the street info and the stored product position
- __R__ points to the stored product position
- __5__ it's the stored product position (can go from 1 to 6)

and "PAR-123":

- __PAR__ means the product is stored in other stock, and it can go from 1 to ?

Custom exceptions are thrown if validations fail.

## ⚙️ Setup and Run

1. Clone the repository:
```bash
git clone https://github.com/estevao081/mapAereo.git
cd mapAereo-api
```

2. Build and run the project using Maven:
```bash
./mvnw spring-boot:run
```

3. Access Swagger UI for API documentation:
```
http://localhost:8080/swagger-ui/index.html
```

## 📄 License

This project is licensed under the MIT License.