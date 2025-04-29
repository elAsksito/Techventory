package com.ask.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_movimiento_prod")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoProd {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "id_movimiento_prod")
    private String id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_movimiento")
    private Movimiento movimiento;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
}