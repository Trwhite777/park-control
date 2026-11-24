public class Microbus extends Vehiculo {
    private static final int TARIFA_MICROBUS = 22000;

    public Microbus(String placa, String marca) {
        super(placa, marca);
    }

    @Override
    public int getTarifaPorHora() {
        return TARIFA_MICROBUS;
    }

}