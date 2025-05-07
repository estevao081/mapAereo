package dev.estv.mapAereo.controllers;

import dev.estv.mapAereo.dtos.ProductRecordDto;
import dev.estv.mapAereo.models.ProductModel;
import dev.estv.mapAereo.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Return a list of all products"
    )
    @ApiResponse(responseCode = "200", description = "Products found")
    @ApiResponse(responseCode = "204", description = "No content")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = service.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get one product",
            description = "Return a product registered in the database searching by ID"
    )
    @ApiResponse(responseCode = "200", description = "Product found")
    public ResponseEntity<ProductModel> getOneProduct(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Save product",
            description = "Save a new product in the database"
    )
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto dto) {
        ProductModel created = service.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete product",
            description = "Deletes the product specified by ID"
    )
    @ApiResponse(responseCode = "200", description = "Deleted successfully")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") UUID id) {
        if(service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update product",
            description = "Updates the product specified by ID"
    )
    @ApiResponse(responseCode = "200", description = "Updated")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable(value = "id") UUID id,
                                                      @RequestBody @Valid ProductRecordDto dto) {
        if(service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ProductModel updated = service.updateProduct(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}