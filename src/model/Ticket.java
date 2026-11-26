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
    boolean estadoTicket;

    public Ticket() {

    }

    


}