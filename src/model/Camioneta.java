public class Camioneta extends Vehiculo {
    private static final double TARIFA_CAMIONETA = 20000;

    public Camioneta(String placa, String marca) {
        super(placa, marca);
    }

    @Override
    public double getTarifaPorHora() {
        return TARIFA_CAMIONETA;
    }

}