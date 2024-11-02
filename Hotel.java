import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Habitacion> habitaciones;
    private List<Integer> nochesxReserva;

    public Hotel() {
        this.habitaciones = new ArrayList<>();
        this.nochesxReserva = new ArrayList<>();
    }

    // Registrar una nueva reserva
    public void registrarReserva(Habitacion habitacion, int noches) {
        habitaciones.add(habitacion);
        nochesxReserva.add(noches);
    }

    // Mostrar todas las reservas 
    public String mostrarReservas() {
        StringBuilder resultadoDeReservas = new StringBuilder();
        for (int i = 0; i < habitaciones.size(); i++) {
            Habitacion habitacion = habitaciones.get(i);
            int noches = nochesxReserva.get(i);
            resultadoDeReservas.append("Habitacion: ").append(habitacion.getClass().getSimpleName()).append(", costo total: ").append(habitacion.calcularCosto(noches)).append("\n");
        }
        
        return resultadoDeReservas.toString();
    }

    // Calcular los ingresos segun el tipo de habitacion
    public double calcularIngresos(Class<? extends Habitacion> tipo) {
        double total = 0;
        for (int i = 0; i < habitaciones.size(); i++) {
            Habitacion habitacion = habitaciones.get(i);
            int noches = nochesxReserva.get(i);

            if (habitacion.getClass().equals(tipo)) {
                total += habitacion.calcularCosto(noches);
            }
        }
        return total;
    }

    // Determinar los ingresos segun la habtacion
    public String habitacionMasRentable() {
        double ingresosEstandar = calcularIngresos(HabitacionEstandar.class);
        double ingresosEjecutiva = calcularIngresos(HabitacionEjecutiva.class);
        double ingresosSuite = calcularIngresos(SuitePresidencial.class);

        if (ingresosEstandar > ingresosEjecutiva && ingresosEstandar > ingresosSuite) {
            return "Habitación Estándar";
        } 
        else if (ingresosEjecutiva > ingresosEstandar && ingresosEjecutiva > ingresosSuite) {
            return "Habitación Ejecutiva";
        } 
        else {
            return "Suite Presidencial";
        }
    }

    // Obtener el numero de reservas
    public int getNumeroDeReservas() {
        return habitaciones.size();
    }

    // Obtener habitacion y noches de una reserva
    public Habitacion getHabitacion(int index) {
        return habitaciones.get(index);
    }

    public int getNoches(int index) {
        return nochesxReserva.get(index);
    }
}