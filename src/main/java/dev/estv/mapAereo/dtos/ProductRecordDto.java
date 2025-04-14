package dev.estv.mapAereo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(@NotNull Long id,
                               @NotBlank String nome,
                               @NotNull Integer quantidade,
                               @NotBlank String validade) {
}