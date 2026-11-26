package model;
public abstract class  Vehiculo {
    private String placa;
    private String marca;



    public Vehiculo(String placa , String marca) {
        this.setPlaca(placa);
        this.setString(marca);
    }


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getString() {
        return marca;
    }

    public void setString(String marca) {
        this.marca = marca;
    }

    public abstract int getTarifaPorHora();
}