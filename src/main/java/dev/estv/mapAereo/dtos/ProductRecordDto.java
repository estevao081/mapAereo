package dev.estv.mapAereo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(
        @NotNull Integer code,
        @NotBlank String name,
        @NotNull Integer quantity,
        String expiration,
        @NotBlank String address) {
}