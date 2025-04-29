package com.ask.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MovimientoRequestDTO {
    private UUID idUsuario;
    private String tipo;
    private String observaciones;
    private List<MovimientoProdDTO> productos;
}