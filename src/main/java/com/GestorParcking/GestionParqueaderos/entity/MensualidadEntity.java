package com.GestorParcking.GestionParqueaderos.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Mensualidad")
public class MensualidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ← AGREGAR ESTO
    @Column(name = "id_mensualidad")
    private Integer idMensualidad;

    @ManyToOne
    @JoinColumn(name = "placa", referencedColumnName = "placa", nullable = false)
    private VehiculoEntity vehiculo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "pagado", nullable = false)
    private Boolean pagado;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    // Constructores
    public MensualidadEntity() {}

    public MensualidadEntity(Integer idMensualidad, VehiculoEntity vehiculo, LocalDate fechaInicio,
                             LocalDate fechaFin, Boolean pagado, BigDecimal valor) {
        this.idMensualidad = idMensualidad;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pagado = pagado;
        this.valor = valor;
    }

    // Getters y Setters
    public Integer getIdMensualidad() { return idMensualidad; }
    public void setIdMensualidad(Integer idMensualidad) { this.idMensualidad = idMensualidad; }

    public VehiculoEntity getVehiculo() { return vehiculo; }
    public void setVehiculo(VehiculoEntity vehiculo) { this.vehiculo = vehiculo; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public Boolean getPagado() { return pagado; }
    public void setPagado(Boolean pagado) { this.pagado = pagado; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}