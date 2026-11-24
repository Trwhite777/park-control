
public class Moto extends Vehiculo {
    private static final double TARIFA_MOTO = 10000;

    public Moto(String placa, String marca) {
        super(placa, marca);
    }

    @Override
    public double getTarifaPorHora() {
        return TARIFA_MOTO;
    }

}