package com.ask.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductoRequestDTO {
    private String nombre;
    private Integer stock;
    private Double precio;
    private String sku;
    private String descripcion;
    private String codigoBarras;
    private UUID idCategoria;
    private UUID idEstado;
}