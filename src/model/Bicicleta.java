public class Bicicleta extends Vehiculo {
    private static final int TARIFA_BICICLETA = 600;

    public Bicicleta(String placa, String marca) {
        super(placa, marca);
    }

    @Override
    public int getTarifaPorHora() {
        return TARIFA_BICICLETA;
    }

}