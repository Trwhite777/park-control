package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration; 
public class Ticket {
    static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String numero;
    Vehiculo vehiculo;
    LocalDateTime horaEntrada;
    LocalDateTime horaSalida;
    Duration duration;
    double valorTotal;
    boolean estadoTicket = true;

    public static DateTimeFormatter getFormato() {
        return formato;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(LocalDateTime horaEntrada , LocalDateTime horaSalida) {
        this.duration = Duration.between(horaEntrada,horaSalida);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isEstadoTicket() {
        return estadoTicket;
    }

    public void setEstadoTicket(boolean estadoTicket) {
        this.estadoTicket = estadoTicket;
    }

    public long calcularDuracion() { 
        // cerramos la hora de entrada y usamos el metodo de duration para calcular la duracion
        setHoraSalida(LocalDateTime.now());
        setDuration(horaEntrada,horaSalida);
        calcularValorTotal();
        setEstadoTicket(false);
    }

    public void calcularValorTotal () {
        // recibe la duracion por minutos y calcula
        final int MINUTO_A_HORA = 60;
        if (duration.toMinutes()>MINUTO_A_HORA) {
            setValorTotal(duration.toMinutes()*vehiculo.getTarifaPorHora());
        } else {
            // recivbe un cero por que segun las horas de negocio son cero
            setValorTotal(0);
        }
    }

    
        //long dias = getDuration().toDaysPart();
        //int horas = getDuration().toHoursPart();
        //int minutos = getDuration().toMinutesPart();
        //int segundos = getDuration().toSecondsPart();
        //System.out.printf("Tiempo total: %d días, %d horas, %d minutos y %d segundos%n",
        //dias, horas, minutos, segundos);


}