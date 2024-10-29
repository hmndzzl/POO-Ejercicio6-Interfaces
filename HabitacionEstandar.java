public class HabitacionEstandar implements Habitacion {
<<<<<<< HEAD
<<<<<<< HEAD
    private static final double TARIFABASE = 50;
    
=======

    private static final double TARIFABASE = 50;

>>>>>>> 6f8e3eea698c2b32c6c162064a9c8aaace343569
    @Override
    public double calcularCosto(int noches) {
        return TARIFABASE * noches;
    }
}