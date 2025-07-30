package dev.estv.mapAereo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(
<<<<<<< HEAD
        @NotNull Integer code,
        @NotBlank String name,
        @NotNull Integer quantity,
        String expiration,
=======

        @Schema(description = "Product code with 6 digits", example = "123456")
        @NotNull Integer code,

        @Schema(description = "Product name (No special characters allowed)", example = "Coca-cola")
        @NotBlank String name,

        @Schema(description = "Product quantity (Only integer numbers)", example = "123456")
        @NotNull Integer quantity,

        @Schema(description = "Product expiration date (dd/mm/yyyy)", example = "12/05/2027")
        @NotBlank String expiration,

        @Schema(description = "Product address (Check README for more info)", example = "15A-R5")
>>>>>>> 47f8455bdf0949cf008261cea416dc6c67ff9ebe
        @NotBlank String address) {
}