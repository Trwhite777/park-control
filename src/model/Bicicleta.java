public class Bicicleta extends Vehiculo {
    private static final double TARIFA_BICICLETA = 600;

    public Bicicleta(String placa, String marca) {
        super(placa, marca);
    }

    @Override
    public double getTarifaPorHora() {
        return TARIFA_BICICLETA;
    }

}