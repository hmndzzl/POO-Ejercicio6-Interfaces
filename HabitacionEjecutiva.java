public class HabitacionEjecutiva implements Habitacion {
    private static final double TARIFABASE = 100;
    private static final double COSTODESAYUNO = 15;
    private static final double COSTOLAVANDERIA = 10;

    private int personas;
    private int diasLavanderia;

    public HabitacionEjecutiva(int personas, int diasLavanderia) {
        this.personas = personas;
        this.diasLavanderia = diasLavanderia;
    }

    @Override
    public double calcularCosto(int noches) {
        double costo = TARIFABASE * noches;
        costo += (COSTODESAYUNO * personas * noches);
        costo += (COSTOLAVANDERIA * diasLavanderia);
        return costo;
    }
}
