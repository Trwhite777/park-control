public class Microbus extends Vehiculo {
    private static final double TARIFA_MICROBUS = 22000;

    public Microbus(String placa, String marca) {
        super(placa, marca);
    }

    @Override
    public double getTarifaPorHora() {
        return TARIFA_MICROBUS;
    }

}