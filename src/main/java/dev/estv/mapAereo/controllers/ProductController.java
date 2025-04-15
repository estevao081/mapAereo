package dev.estv.mapAereo.controllers;

import dev.estv.mapAereo.dtos.ProductRecordDto;
import dev.estv.mapAereo.models.ProductModel;
import dev.estv.mapAereo.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productsList = productRepository.findAll();
        if (!productsList.isEmpty()) {
            for (ProductModel product : productsList) {
                Integer codigo = product.getCodigo();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(codigo)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/products/{codigo}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "codigo") Integer codigo) {
        List<ProductModel> productsList = productRepository.findAll();
        Optional<ProductModel> productO = Optional.empty();
        for (ProductModel product : productsList) {
            if (product.getCodigo().equals(codigo)) {
                productO = Optional.of(product);
            }
        }
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productO.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @DeleteMapping("/products/{codigo}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "codigo") Integer codigo) {
        List<ProductModel> productsList = productRepository.findAll();
        Optional<ProductModel> productO = Optional.empty();
        for (ProductModel product : productsList) {
            if (product.getCodigo().equals(codigo)) {
                productO = Optional.of(product);
            }
        }
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @PutMapping("/products/{codigo}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "codigo") Integer codigo,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        List<ProductModel> productsList = productRepository.findAll();
        Optional<ProductModel> productO = Optional.empty();
        for (ProductModel product : productsList) {
            if (product.getCodigo().equals(codigo)) {
                productO = Optional.of(product);
            }
        }
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = productO.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }
}