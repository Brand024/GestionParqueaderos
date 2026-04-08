package com.GestorParcking.GestionParqueaderos.models;

//TIPO VEHICULO
public class TipoVehiculo{
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

//---VEHICULO
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
//ESPACIO PARQUEADERO

public class EspacioParqueadero{
    private int id_espacio;
    private int numero;
    private int piso;
    private String tipo_celda;
    private boolean estado;

    public EspacioParqueadero() {
    }

    public EspacioParqueadero(int id_espacio,int numero,int piso,String tipo_celda,boolean estado) {
        this.id_espacio = id_espacio;
        this.numero = numero;
        this.piso = piso;
        this.tipo_celda = tipo_celda;
        this.estado = estado;
    }

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

//---- MENSULAIDAD
public class Mensualidad{
    private int id_mensualidad;
    private String placa;
    private date fecha_inicio;
    private date fecha_fin;
    private boolean pagado;

    public Mensualidad() {
    }

    public Mensualidad(int id_mensualidad, String placa, date fecha_inicio, date fecha_fin, boolean pagado) {
        this.id_mensualidad = id_mensualidad;
        this.placa = placa;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.pagado = pagado
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

    public date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}

//-----TICKET
public class Ticket{
    private int id_ticket ;
    private String placa;
    private int id_espacio;
    private String hora_entrada;
    private Sting hora_salida;
    private float valor_total;

    public Ticket() {
    }

    public Ticket(int id_ticket,String placa,int id_espacio,String hora_entrada,Sting hora_salida,float valor_total) {
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

    public Sting getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Sting hora_salida) {
        this.hora_salida = hora_salida;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }
}