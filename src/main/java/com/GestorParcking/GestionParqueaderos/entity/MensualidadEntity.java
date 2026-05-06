package com.GestorParcking.GestionParqueaderos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "Mensualidad")
public class MensualidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensualidad")
    private Integer idMensualidad;

    @ManyToOne
    @JoinColumn(name = "placa", nullable = false)
    private VehiculoEntity vehiculo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "pagado", nullable = false)
    private Boolean pagado = false;

    // Constructores
    public MensualidadEntity() {
    }

    // Getters y Setters
    public Integer getIdMensualidad() {
        return idMensualidad;
    }

    public void setIdMensualidad(Integer idMensualidad) {
        this.idMensualidad = idMensualidad;
    }

    public VehiculoEntity getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoEntity vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }
}