public class Reserva {
    private int tipoHabitacion;
    private int noches;
    private int personas;

    public Reserva(int tipoHabitacion, int noches, int personas) {
        this.tipoHabitacion = tipoHabitacion;
        this.noches = noches;
        this.personas = personas;
    }

    // Calcular el costo total según el tipo de habitación y servicios adicionales
    public double calcularCostoTotal() {
        double costoTotal = 0;

        switch (tipoHabitacion) {
            case 1: //  Estándar
                costoTotal = 50 * noches;
                break;
            case 2: //  Ejecutiva
                costoTotal = (100 * noches) + (15 * personas * noches) + (10 * noches);
                break;
            case 3: // Suite 
                costoTotal = (250 * noches) + (50 * noches) + 30;
                break;
            default:
                System.out.println("Tipo de habitación no válido.");
        }

        return costoTotal;
    }

    public int obtenerTipoHabitacion() {
        return tipoHabitacion;
    }
}
