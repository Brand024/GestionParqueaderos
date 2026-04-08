package com.GestorParcking.GestionParqueaderos.models;

public class TipoVehiculo {

    public class TipoVehiculo {
        private int id_tipo;
        private  String nombre;
        private float tarifa_minuto;

        public TipoVehiculo() {
        }

        public TipoVehiculo(int id_tipo, String nombre, float tarifa_minuto) {
            this.id_tipo = id_tipo;
            this.nombre = nombre;
            this.tarifa_minuto = tarifa_minuto;
        }

        public int getId_tipo() {
            return id_tipo;
        }

        public void setId_tipo(int id_tipo) {
            this.id_tipo = id_tipo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public float getTarifa_minuto() {
            return tarifa_minuto;
        }

        public void setTarifa_minuto(float tarifa_minuto) {
            this.tarifa_minuto = tarifa_minuto;
        }
    }

    public class TipoVehiculoImpl extends TipoVehiculo { }
}
