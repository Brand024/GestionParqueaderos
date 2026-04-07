package com.GestorParcking.GestionParqueaderos.models;

public class Vehiculo {
    private String placa;
    private int idTipo;
    private String modelo;
    private String color;

public Vehiculo(){
}

public Vehiculo(String placa, int idTipo, String modelo, String color) {
        this.placa = placa;
        this.idTipo = idTipo;
        this.modelo = modelo;
        this.color = color;
}

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}