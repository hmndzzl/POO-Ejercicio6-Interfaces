import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Reserva> reservas;

    public Hotel() {
        this.reservas = new ArrayList<>();
    }

    // Registrar nueva reserva
    public void nuevaReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    // Mostrar todas las reservas activas
    public String mostrarReservas() {
        StringBuilder resultado = new StringBuilder(); // se construye la cadena
        
        for (Reserva reserva : reservas) {
            resultado.append("Tipo de habitación: ")
                     .append(reserva.obtenerTipoHabitacion())
                     .append(", Costo total: ")
                     .append(reserva.calcularCostoTotal())
                     .append("\n"); 
        }
        
        return resultado.toString(); // devuleve la cadena 
    }
    

    // Calcular los ingresos totales de una sola habitacion
    public double ingresosPorHabitacion(Class<? extends Habitacion> tipo) {
        double total = 0;
        for (Reserva reserva : reservas) {
            if (reserva.obtenerTipoHabitacion().equals(tipo.getSimpleName())) {
                total += reserva.calcularCostoTotal();
            }
        }
        return total;
    }

    // mostrar la habitacion que mas ingresos  genere
    public String habitacionMasRentable() {
        double ingresosEstandar = ingresosPorHabitacion(HabitacionEstandar.class);
        double ingresosEjecutiva = ingresosPorHabitacion(HabitacionEjecutiva.class);
        double ingresosSuite = ingresosPorHabitacion(SuitePresidencial.class);
        
        if (ingresosEstandar > ingresosEjecutiva && ingresosEstandar > ingresosSuite) {
            return "Habitacion Estandar";
        } else if (ingresosEjecutiva > ingresosEstandar && ingresosEjecutiva > ingresosSuite) {
            return "Habitacion Ejecutiva";
        } else {
            return "Suite Presidencial";
        }
    }
}
