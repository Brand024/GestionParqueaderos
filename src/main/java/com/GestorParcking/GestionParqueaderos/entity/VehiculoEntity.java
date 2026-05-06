package com.GestorParcking.GestionParqueaderos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehiculo")
public class VehiculoEntity {

    @Id
    @Column(name = "placa", length = 20)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoVehiculoEntity tipoVehiculo;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    // Constructores
    public VehiculoEntity() {}

    public VehiculoEntity(String placa, TipoVehiculoEntity tipoVehiculo, String modelo, String color) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.modelo = modelo;
        this.color = color;
    }

    // Getters y Setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public TipoVehiculoEntity getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(TipoVehiculoEntity tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}