package dev.estv.mapAereo.services;

import dev.estv.mapAereo.dtos.ProductRecordDto;
import dev.estv.mapAereo.exceptions.InvalidAddressException;
import dev.estv.mapAereo.exceptions.InvalidCodeException;
import dev.estv.mapAereo.exceptions.InvalidDateException;
import dev.estv.mapAereo.exceptions.InvalidNameException;
import dev.estv.mapAereo.models.ProductModel;
import dev.estv.mapAereo.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductModel> getAllProducts() {
        return repository.findAll();
    }

    public Optional<ProductModel> findById(UUID id) {
        return repository.findById(id);
    }

    public List<ProductModel> findByName(String name) {
        List<ProductModel> products = getAllProducts();
        List<ProductModel> productsFound = null;
        for (ProductModel product: products) {
            if(product.getName().contains(name)) {
                productsFound.add(product);
            }
        }
        return productsFound;
    }

    public ProductModel createProduct(ProductRecordDto dto) {
        validate(dto);
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(dto, productModel);
        if (dto.expiration() != null && dto.expiration().isBlank()) {
            productModel.setExpiration(null);
        }
        return repository.save(productModel);
    }

    public void deleteProduct(UUID id) {
        Optional<ProductModel> productOpt = findById(id);
        productOpt.ifPresent(productModel -> repository.delete(productModel));
    }

    public ProductModel updateProduct(UUID id, ProductRecordDto dto) {
        Optional<ProductModel> productOpt = repository.findById(id);
        validate(dto);
        var productModel = productOpt.get();
        BeanUtils.copyProperties(dto, productModel);
        if (dto.expiration() != null && dto.expiration().isBlank()) {
            productModel.setExpiration(null);
        }
        return repository.save(productModel);
    }

    public void validateCode(Integer code) {
        if (code.toString().length() != 6) {
            throw new InvalidCodeException();
        }
    }

    public void validateName(String name) {
        if (!name.matches("^[\\p{L}\\p{N}\\s-]+$")) {
            throw new InvalidNameException();
        }
    }

    public void validateDate(String date) {
        if (date == null || date.isBlank()) {
            return;
        }

        if (!date.matches("^(?:(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4})$") || !isValidDate(date)) {
            throw new InvalidDateException();
        }
    }


    public void validateAddress(String address) {
        if (!address.matches("^(?:(?:[1-9]|1\\d|20)[AB]-R[1-7]|PAR-\\d+)$")) {
            throw new InvalidAddressException();
        }
    }

    public void validate(ProductRecordDto dto) {
        validateCode(dto.code());
        validateName(dto.name());
        validateDate(dto.expiration());
        validateAddress(dto.address());
    }

    public boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date parsedDate = sdf.parse(date);
            Date today = new Date();

            if (parsedDate.after(removeTime(today))) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    private Date removeTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            return date;
        }
    }
}