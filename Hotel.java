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
    public double ingresosPorHabitacion(int tipoHabitacion) {
        double total = 0;
        for (Reserva reserva : reservas) {
            if (reserva.obtenerTipoHabitacion() == tipoHabitacion) { // Compara con el número en lugar de nombre
                total += reserva.calcularCostoTotal();
            }
        }
        return total;
    }


    // mostrar la habitacion que mas ingresos  genere
    public String habitacionMasRentable() {
        int[] ingresosPorTipo = new int[3]; // Array para almacenar ingresos de cada tipo
        
        for (Reserva reserva : reservas) {
            int tipo = reserva.obtenerTipoHabitacion() - 1; // ajustar a índices 0, 1 y 2
            ingresosPorTipo[tipo] += reserva.calcularCostoTotal();
        }
    
        int maxTipo = 0;
        for (int i = 1; i < ingresosPorTipo.length; i++) {
            if (ingresosPorTipo[i] > ingresosPorTipo[maxTipo]) {
                maxTipo = i;
            }
        }
    
        // Asigna nombres de tipos a los índices
        String[] nombresTipo = {"Estándar", "Ejecutiva", "Suite"};
        return nombresTipo[maxTipo];
    }
    
}
