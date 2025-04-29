package com.ask.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MovimientoProdDTO {
    private UUID idProducto;
    private Integer cantidad;
}