package com.ask.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductoResponseDTO {
    private UUID id;
    private String nombre;
    private Integer stock;
    private Double precio;
    private String sku;
    private String descripcion;
    private String codigoBarras;
    private CategoriaDTO categoria;
    private EstadoDTO estado;
}