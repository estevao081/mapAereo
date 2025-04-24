package dev.estv.mapAereo.controllers;

import dev.estv.mapAereo.dtos.ProductRecordDto;
import dev.estv.mapAereo.models.ProductModel;
import dev.estv.mapAereo.repositories.ProductRepository;
import dev.estv.mapAereo.services.DateValidationService;
import dev.estv.mapAereo.services.ProductValidationService;
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
    @Autowired
    ProductValidationService productValidationService;

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productsList = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        List<ProductModel> productsList = productRepository.findAll();
        Optional<ProductModel> productO = Optional.empty();
        for (ProductModel product : productsList) {
            if (product.getId().equals(id)) {
                productO = Optional.of(product);
            }
        }
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productValidationService.validateCode(productModel.getCodigo());
        productValidationService.validateName(productModel.getNome());
        productValidationService.validateDate(productModel.getValidade());
        productValidationService.validateAddress(productModel.getEndereco());
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        List<ProductModel> productsList = productRepository.findAll();
        Optional<ProductModel> productO = Optional.empty();
        for (ProductModel product : productsList) {
            if (product.getId().equals(id)) {
                productO = Optional.of(product);
            }
        }
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        List<ProductModel> productsList = productRepository.findAll();
        Optional<ProductModel> productO = Optional.empty();
        for (ProductModel product : productsList) {
            if (product.getId().equals(id)) {
                productO = Optional.of(product);
            }
        }
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        var productModel = productO.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productValidationService.validateCode(productModel.getCodigo());
        productValidationService.validateName(productModel.getNome());
        productValidationService.validateDate(productModel.getValidade());
        productValidationService.validateAddress(productModel.getEndereco());
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }
}