public class Reserva {
    private int tipoHabitacion; // 1 = Estandar, 2 = Ejecutiva, 3 = Suite Presidencial
    private int noches;
    private int personas;

    public Reserva(int tipoHabitacion, int noches, int personas) {
        this.tipoHabitacion = tipoHabitacion;
        this.noches = noches;
        this.personas = personas;
    }

    public double calcularCostoTotal() {
        Habitacion habitacion;
        switch (tipoHabitacion) {
            case 1:
                habitacion = new HabitacionEstandar();
                break;
            case 2:
                // Se requiere el número de días de lavandería para la habitación ejecutiva
                int diasLavanderia = 1; // Por defecto, puedes cambiarlo según la lógica deseada
                habitacion = new HabitacionEjecutiva(personas, diasLavanderia);
                break;
            case 3:
                habitacion = new SuitePresidencial();
                break;
            default:
                throw new IllegalArgumentException("Tipo de habitación no válido");
        }
        return habitacion.calcularCosto(noches);
    }

    public String obtenerTipoHabitacion() {
        switch (tipoHabitacion) {
            case 1: return "Habitación Estándar";
            case 2: return "Habitación Ejecutiva";
            case 3: return "Suite Presidencial";
            default: return "Desconocido";
        }
    }

    public int getNoches() {
        return noches;
    }

    public int getPersonas() {
        return personas;
    }
}
