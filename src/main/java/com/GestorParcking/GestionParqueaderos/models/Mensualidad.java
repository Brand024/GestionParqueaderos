package com.GestorParcking.GestionParqueaderos.models;
import java.sql.Date;

public class Mensualidad {
    private int id_mensualidad;
    private String placa;
    private Date fecha_inicio;
    private Date fecha_fin;
    private float valor;
    private boolean pagado;

        public Mensualidad() {
        }

        public Mensualidad(int id_mensualidad, String placa, Date fecha_inicio, Date fecha_fin, boolean pagado) {
            this.id_mensualidad = id_mensualidad;
            this.placa = placa;
            this.fecha_inicio = fecha_inicio;
            this.fecha_fin = fecha_fin;
            this.pagado = pagado;
        }

        public int getId_mensualidad() {
            return id_mensualidad;
        }

        public void setId_mensualidad(int id_mensualidad) {
            this.id_mensualidad = id_mensualidad;
        }

        public String getPlaca() {
            return placa;
        }

        public void setPlaca(String placa) {
            this.placa = placa;
        }

        public Date getFecha_inicio() {
            return fecha_inicio;
        }

        public void setFecha_inicio(Date fecha_inicio) {
            this.fecha_inicio = fecha_inicio;
        }

        public Date getFecha_fin() {
            return fecha_fin;
        }

        public void setFecha_fin(Date fecha_fin) {
            this.fecha_fin = fecha_fin;
        }

        public boolean isPagado() {
            return pagado;
        }

        public void setPagado(boolean pagado) {
            this.pagado = pagado;
        }
        public float getValor() {
            return valor;
        }

        public void setValor(float valor) {
            this.valor = valor;
        }
    }
