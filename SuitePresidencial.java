public class SuitePresidencial implements Habitacion {
    private static final double TARIFABASE = 250;
    private static final double COSTOSERVICIOS = 50;
    private static final double ACCESOVIP = 30;

    @Override
    public double calcularCosto(int noches) {
        double costo = TARIFABASE * noches;
        costo += (COSTOSERVICIOS * noches);
        costo += ACCESOVIP; 
        return costo;
    }
}