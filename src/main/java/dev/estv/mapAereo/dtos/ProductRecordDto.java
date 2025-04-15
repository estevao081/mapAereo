package dev.estv.mapAereo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(@NotNull Integer codigo,
                               @NotBlank String nome,
                               @NotNull Integer quantidade,
                               @NotBlank String validade,
                               @NotBlank String endereco) {
}