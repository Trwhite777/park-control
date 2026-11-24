
public class Moto extends Vehiculo {
    private static final int TARIFA_MOTO = 10000;

    public Moto(String placa, String marca) {
        super(placa, marca);
    }

    @Override
    public int getTarifaPorHora() {
        return TARIFA_MOTO;
    }

}