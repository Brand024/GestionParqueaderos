package com.GestorParcking.GestionParqueaderos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehiculo")  // ← ESPECIFICA EL NOMBRE EXACTO DE LA TABLA
public class VehiculoEntity {

    @Id
    @Column(name = "placa", length = 20)
    private String placa;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @ManyToOne
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo", nullable = false)
    private TipoVehiculoEntity tipoVehiculo;

    // Constructores
    public VehiculoEntity() {}

    public VehiculoEntity(String placa, String modelo, String color, TipoVehiculoEntity tipoVehiculo) {
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.tipoVehiculo = tipoVehiculo;
    }

    // Getters y Setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public TipoVehiculoEntity getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(TipoVehiculoEntity tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }
}