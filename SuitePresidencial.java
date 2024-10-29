public class SuitePresidencial implements Habitacion {
    private static final double tarifaBase = 250;
    private static final double costoServicios = 50;
    private static final double accesoVIP = 30;

    @Override
    public double calcularCosto(int noches) {
        double costo = tarifaBase * noches;
        costo += (costoServicios * noches);
        costo += accesoVIP; 
        return costo;
    }
}
