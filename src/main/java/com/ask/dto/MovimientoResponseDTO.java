package com.ask.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class MovimientoResponseDTO {
    private UUID id;
    private String tipo;
    private LocalDateTime fecha;
    private String observaciones;
    private String nombreUsuario;
    private List<MovimientoProdDetalleDTO> productos;
}