package com.GestorParcking.GestionParqueaderos.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TipoVehiculo")
public class TipoVehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "tarifa_minuto", nullable = false)
    private BigDecimal tarifaMinuto;

    // Constructores
    public TipoVehiculoEntity() {}

    public TipoVehiculoEntity(String nombre, BigDecimal tarifaMinuto) {
        this.nombre = nombre;
        this.tarifaMinuto = tarifaMinuto;
    }

    // Getters y Setters
    public Integer getIdTipo() { return idTipo; }
    public void setIdTipo(Integer idTipo) { this.idTipo = idTipo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getTarifaMinuto() { return tarifaMinuto; }
    public void setTarifaMinuto(BigDecimal tarifaMinuto) { this.tarifaMinuto = tarifaMinuto; }
}