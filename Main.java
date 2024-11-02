import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        String csv = "reservas.csv"; // URL al archivo CSV
        boolean menu = true; // Variable para el bucle while

        // Leer reservas desde el CSV y agregarlas al hotel
        ArrayList<String[]> reservas_csv = leerCsv(csv);
        for (String[] fila : reservas_csv) {
            int tipoHabitacion = Integer.parseInt(fila[0]);
            int noches = Integer.parseInt(fila[1]);
            int personas = Integer.parseInt(fila[2]);

            // Crear objeto Reserva
            Reserva reserva = new Reserva(tipoHabitacion, noches, personas);
            hotel.nuevaReserva(reserva);
        }

        // Menú
        while (menu) {
            System.out.println("1. Mostrar reservas activas");
            System.out.println("2. Consultar habitación más rentable");
            System.out.println("3. Agregar nueva reserva");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(hotel.mostrarReservas());
                    break;
                case 2:
                    System.out.println("La habitación más rentable es: " + hotel.habitacionMasRentable());
                    break;
                case 3:
                    // Preguntar detalles de la nueva reserva
                    System.out.print("Ingrese el tipo de habitación (1: Estándar, 2: Ejecutiva, 3: Suite): ");
                    int nuevoTipo = scanner.nextInt();
                    System.out.print("Ingrese el número de noches: ");
                    int nuevoNoches = scanner.nextInt();
                    System.out.print("Ingrese el número de personas: ");
                    int nuevoPersonas = scanner.nextInt();

                    // Crear nueva reserva
                    Reserva nuevaReserva = new Reserva(nuevoTipo, nuevoNoches, nuevoPersonas);
                    double nuevoCostoTotal = nuevaReserva.calcularCostoTotal();
                    hotel.nuevaReserva(nuevaReserva);

                    // Escribir nueva reserva en el CSV
                    escribirCsv(csv, new String[] { String.valueOf(nuevoTipo), String.valueOf(nuevoNoches), String.valueOf(nuevoPersonas), String.valueOf(nuevoCostoTotal) });
                    System.out.println("Reserva agregada exitosamente.");
                    break;
                case 4:
                    menu = false;
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }

    // Se encarga de leer el CSV y almacenar las filas en ArrayList de Arrays de Strings
    private static ArrayList<String[]> leerCsv(String urlCSV) {
        String fila_completa;
        String[] fila;
        ArrayList<String[]> filas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(urlCSV))) {
            // Omitir cabecera
            br.readLine();
            while ((fila_completa = br.readLine()) != null) {
                fila = fila_completa.split(",");
                filas.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filas;
    }

    // Función para escribir los cambios en el CSV de la base de datos
    private static void escribirCsv(String urlCSV, String[] fila) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(urlCSV, true))) {
            writer.write(String.join(",", fila));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
