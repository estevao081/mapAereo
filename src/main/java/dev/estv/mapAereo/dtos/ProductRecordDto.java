package dev.estv.mapAereo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(@NotNull(message = "Código é obrigatório") Integer codigo,
                               @NotBlank(message = "Nome é obrigatório") String nome,
                               @NotNull(message = "Quantidade é obrigatória") Integer quantidade,
                               @NotBlank(message = "Validade é obrigatória") String validade,
                               @NotBlank(message = "Endereço é obrigatório") String endereco) {
}