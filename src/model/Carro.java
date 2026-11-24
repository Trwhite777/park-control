public class Carro extends Vehiculo {
    private static final double TARIFA_CARRO = 15000;

    public Carro(String placa, String marca) {
            super(placa, marca);
    }

    @Override
    public double getTarifaPorHora() {
        return TARIFA_CARRO;
    }

}