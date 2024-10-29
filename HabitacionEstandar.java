public class HabitacionEstandar implements Habitacion {
    private static final double tarifaBase = 50;
    @Override
    public double calcularCosto(int noches) {
        return tarifaBase * noches;
    }
}