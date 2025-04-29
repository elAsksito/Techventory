package com.ask.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class MovimientoProdRequest {

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    @NotNull(message = "El ID del movimiento es obligatorio")
    private UUID idMovimiento;

    @NotNull(message = "El ID del producto es obligatorio")
    private UUID idProducto;
}