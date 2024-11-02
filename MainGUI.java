import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class MainGUI extends JFrame {
    private Hotel hotel;
    private String csvFile = "reservas.csv";

    public MainGUI() {
        hotel = new Hotel();
        cargarReservasDesdeCSV();

        // Para la ventana principal
        setTitle("Sistema de Reservas de Hotel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Botones de opciones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnMostrarReservas = new JButton("Reservas activas");
        JButton btnHabitacionRentable = new JButton("Consultar habitación más rentable");
        JButton btnAgregarReserva = new JButton("Agregar una nueva reserva");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnMostrarReservas);
        panelBotones.add(btnHabitacionRentable);
        panelBotones.add(btnAgregarReserva);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        // Acción de cada boton
        btnMostrarReservas.addActionListener(e -> mostrarReservas());
        btnHabitacionRentable.addActionListener(e -> consultarHabitacionRentable());
        btnAgregarReserva.addActionListener(e -> agregarNuevaReserva());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void cargarReservasDesdeCSV() {
        ArrayList<String[]> reservasCsv = leerCsv(csvFile);
        for (String[] fila : reservasCsv) {
            int tipoHabitacion = Integer.parseInt(fila[0]);
            int noches = Integer.parseInt(fila[1]);
            int personas = Integer.parseInt(fila[2]);
            Reserva reserva = new Reserva(tipoHabitacion, noches, personas);
            hotel.nuevaReserva(reserva);
        }
    }

    private void mostrarReservas() {
        String reservas = hotel.mostrarReservas();
        JOptionPane.showMessageDialog(this, reservas, "Reservas Activas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void consultarHabitacionRentable() {
        String rentable = "La habitación más rentable es: " + hotel.habitacionMasRentable();
        JOptionPane.showMessageDialog(this, rentable, "Habitación Más Rentable", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarNuevaReserva() {
        try {
            int tipoHabitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo de habitación: 1. Estándar, 2. Ejecutiva, 3. Suite Presidencial):"));
            int noches = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de noches:"));
            int personas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de personas:"));

            Reserva nuevaReserva = new Reserva(tipoHabitacion, noches, personas);
            hotel.nuevaReserva(nuevaReserva);

            escribirCsv(csvFile, new String[]{
                    String.valueOf(tipoHabitacion),
                    String.valueOf(noches),
                    String.valueOf(personas),
                    String.valueOf(nuevaReserva.calcularCostoTotal())
            });

            JOptionPane.showMessageDialog(this, "Reserva agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al ingresar datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI app = new MainGUI();
            app.setVisible(true);
        });
    }

    // lectura/escritura CSV
    private ArrayList<String[]> leerCsv(String urlCSV) {
        String filaCompleta;
        ArrayList<String[]> filas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(urlCSV))) {
            br.readLine();  // Omitir cabecera
            while ((filaCompleta = br.readLine()) != null) {
                String[] fila = filaCompleta.split(",");
                filas.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filas;
    }

    private void escribirCsv(String urlCSV, String[] fila) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(urlCSV, true))) {
            writer.write(String.join(",", fila));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
