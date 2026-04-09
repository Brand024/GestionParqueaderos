package com.GestorParcking.GestionParqueaderos.models;

public class EspacioParqueadero {
//atributos
        private int id_espacio;
        private int numero;
        private int piso;
        private String tipo_celda;
        private boolean estado;

        public EspacioParqueadero() { //constructor vacio y con parametros
        }

        public EspacioParqueadero(int id_espacio,int numero,int piso,String tipo_celda,boolean estado) {
            this.id_espacio = id_espacio;
            this.numero = numero;
            this.piso = piso;
            this.tipo_celda = tipo_celda;
            this.estado = estado;
        }

        //getters y setters
        public int getId_espacio() {
            return id_espacio;
        }

        public void setId_espacio(int id_espacio) {
            this.id_espacio = id_espacio;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public int getPiso() {
            return piso;
        }

        public void setPiso(int piso) {
            this.piso = piso;
        }

        public String getTipo_celda() {
            return tipo_celda;
        }

        public void setTipo_celda(String tipo_celda) {
            this.tipo_celda = tipo_celda;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }
        public boolean isEstado() { // Para los booleanos, el "get" suele llamarse "is"
            return estado;
        }

    }

