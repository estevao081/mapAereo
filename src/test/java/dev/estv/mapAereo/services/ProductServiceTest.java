package dev.estv.mapAereo.services;

import dev.estv.mapAereo.dtos.ProductRecordDto;
import dev.estv.mapAereo.exceptions.InvalidAddressException;
import dev.estv.mapAereo.exceptions.InvalidCodeException;
import dev.estv.mapAereo.exceptions.InvalidDateException;
import dev.estv.mapAereo.exceptions.InvalidNameException;
import dev.estv.mapAereo.models.ProductModel;
import dev.estv.mapAereo.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @Test
    @DisplayName("Should return an exception when the product code length is different than 6")
    public void shouldReturnAnExceptionWhenTheProductCodeLengthIsDifferentThan6() {
        ProductRecordDto productDto = new ProductRecordDto(12345, "Coca-cola", 57, "28/08/2027", "17A-R5");
        Assertions.assertThrows(InvalidCodeException.class, () -> service.createProduct(productDto));
    }

    @Test
    @DisplayName("Should return an exception when the product name contains special characters")
    public void shouldReturnAnExceptionWhenTheProductNameContainsSpecialCharacters() {
        ProductRecordDto productDto = new ProductRecordDto(123456, "Coc@-cola", 57, "28/08/2027", "17A-R5");
        Assertions.assertThrows(InvalidNameException.class, () -> service.createProduct(productDto));
    }

    @Test
    @DisplayName("Should return an exception when the product expiration date isn't a valid date (dd/mm/yyyy) and when the expiration date isn't on future")
    public void shouldReturnAnExceptionWhenTheProductExpirationDateIsNotAValidDateAndWhenIsNotOnFuture() {
        ProductRecordDto productDto = new ProductRecordDto(123456, "Coca-cola", 57, "12/25/2024", "17A-R5");
        Assertions.assertThrows(InvalidDateException.class, () -> service.createProduct(productDto));
    }

    @Test
    @DisplayName("Should return an exception when the product address doensn't match with the pattern (Check README for more info)")
    public void shouldReturnAnExceptionWhenTheProductAddressDoesNotMatchWithThePattern() {
        ProductRecordDto productDto = new ProductRecordDto(123456, "Coca-cola", 57, "28/08/2027", "00A-R0");
        Assertions.assertThrows(InvalidAddressException.class, () -> service.createProduct(productDto));
    }
}