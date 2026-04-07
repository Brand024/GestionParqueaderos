public class vehiculo {private String placa;
    private String tipo; // carro, moto, camioneta
    private String horaEntrada;

    public vehiculo
            (String placa, String tipo, String horaEntrada) {
        this.placa = placa;
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
    }

    // Métodos getters
    public String getPlaca() { return placa; }
    public String getTipo() { return tipo; }
    public String getHoraEntrada() { return horaEntrada; }
}
