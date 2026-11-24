public class Carro extends Vehiculo {
    private static final int TARIFA_CARRO = 15000;

    public Carro(String placa, String marca) {
            super(placa, marca);
    }

    @Override
    public int getTarifaPorHora() {
        return TARIFA_CARRO;
    }

}