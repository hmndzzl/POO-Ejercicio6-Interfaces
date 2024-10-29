public class HabitacionEjecutiva implements Habitacion {
    private static final double tarifaBase = 100;
    private static final double costoDesayuno = 15;
    private static final double costoLavanderia = 10;

    private int personas;
    private int diasLavanderia;

    public HabitacionEjecutiva(int personas, int diasLavanderia) {
        this.personas = personas;
        this.diasLavanderia = diasLavanderia;
    }

    @Override
    public double calcularCosto(int noches) {
        double costo = tarifaBase * noches;
        costo += (costoDesayuno * personas * noches);
        costo += (costoLavanderia * diasLavanderia);
        return costo;
    }
}
