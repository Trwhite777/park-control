public class Camioneta extends Vehiculo {
    private static final int TARIFA_CAMIONETA = 20000;

    public Camioneta(String placa, String marca) {
        super(placa, marca);
    }

    @Override
    public int getTarifaPorHora() {
        return TARIFA_CAMIONETA;
    }

}