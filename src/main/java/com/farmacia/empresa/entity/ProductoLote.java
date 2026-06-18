package com.farmacia.empresa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "producto_lotes")
public class ProductoLote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  ///Muchos lotes pertenecen a un producto
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "numero_lote", nullable = false)
    private String numerolote;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechavencimiento;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaingreso;

    @Column(name = "esta_activo", nullable = false)
    private Boolean estado = true;

    @PrePersist
    protected void onCreate() {
        if (this.fechaingreso == null) {
            this.fechaingreso = LocalDateTime.now();
        }
    }

}
