public class HabitacionEstandar implements Habitacion {
    private static final double TARIFA_BASE = 50;
    @Override
    public double calcularCosto(int noches) {
        return TARIFA_BASE * noches;
    }
}