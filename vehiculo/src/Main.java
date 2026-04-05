//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        vehiculo carro = new vehiculo("ABC123", "Carro", "10:00 AM");
        System.out.println("Placa: " + carro.getPlaca());
        System.out.println("Tipo: " + carro.getTipo());
        System.out.println("Hora de entrada: " + carro.getHoraEntrada());
    }
}
