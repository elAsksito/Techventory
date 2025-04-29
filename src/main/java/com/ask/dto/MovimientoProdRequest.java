package com.ask.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MovimientoProdRequest {
    private UUID idMovimiento;
    private UUID idProducto;
    private int cantidad;
}
