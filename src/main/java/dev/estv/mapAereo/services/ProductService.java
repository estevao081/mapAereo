package dev.estv.mapAereo.services;

import dev.estv.mapAereo.dtos.ProductRecordDto;
import dev.estv.mapAereo.models.ProductModel;
import dev.estv.mapAereo.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    @Autowired
    ProductValidationService validation;

    public List<ProductModel> getAllProducts() {
        return repository.findAll();
    }

    public Optional<ProductModel> findById(UUID id) {
        return repository.findById(id);
    }

    public ProductModel createProduct(ProductRecordDto dto) {
        validation.validate(dto);
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(dto, productModel);
        return repository.save(productModel);
    }

    public void deleteProduct(UUID id) {
        Optional<ProductModel> productOpt = findById(id);
        productOpt.ifPresent(productModel -> repository.delete(productModel));
    }

    public ProductModel updateProduct(UUID id, ProductRecordDto dto) {
        Optional<ProductModel> productOpt = repository.findById(id);
        var productModel = productOpt.get();
        BeanUtils.copyProperties(dto, productModel);
        validation.validate(dto);
        return repository.save(productModel);
    }
}
