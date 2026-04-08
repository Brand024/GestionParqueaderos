package com.GestorParcking.GestionParqueaderos.models;

public class Ticket {

    public class Ticket{
        private int id_ticket ;
        private String placa;
        private int id_espacio;
        private String hora_entrada;
        private String hora_salida;
        private float valor_total;

        public Ticket() {
        }

        public Ticket(int id_ticket,String placa,int id_espacio,String hora_entrada,String hora_salida,float valor_total) {
            this.id_ticket = id_ticket;
            this.placa = placa;
            this.id_espacio = id_espacio;
            this.hora_entrada = hora_entrada;
            this.hora_salida = hora_salida;
            this.valor_total= valor_total;
        }

        public int getId_ticket() {
            return id_ticket;
        }

        public void setId_ticket(int id_ticket) {
            this.id_ticket = id_ticket;
        }

        public String getPlaca() {
            return placa;
        }

        public void setPlaca(String placa) {
            this.placa = placa;
        }

        public int getId_espacio() {
            return id_espacio;
        }

        public void setId_espacio(int id_espacio) {
            this.id_espacio = id_espacio;
        }

        public String getHora_entrada() {
            return hora_entrada;
        }

        public void setHora_entrada(String hora_entrada) {
            this.hora_entrada = hora_entrada;
        }

        public String getHora_salida() {
            return hora_salida;
        }

        public void setHora_salida(String hora_salida) {
            this.hora_salida = hora_salida;
        }

        public float getValor_total() {
            return valor_total;
        }

        public void setValor_total(float valor_total) {
            this.valor_total = valor_total;
        }
    }
}
