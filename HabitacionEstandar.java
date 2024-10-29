public class HabitacionEstandar implements Habitacion {

    private static final double TARIFABASE = 50;


    @Override
    public double calcularCosto(int noches) {
        return TARIFABASE * noches;
    }
}