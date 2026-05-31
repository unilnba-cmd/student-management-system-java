package poo;

import javax.swing.*;
import java.io.*;

public class SistemaRegistro {

    static String[] estudiantes = new String[20];
    static int contador = 0;

    static double[] notas = new double[20];

    // ===== CARGAR ARCHIVO =====
    public static void cargarEstudiantes() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("estudiantes.txt"));
            String linea;

            while ((linea = br.readLine()) != null && contador < estudiantes.length) {
    estudiantes[contador++] = linea;
}

    if (contador == estudiantes.length) {
        JOptionPane.showMessageDialog(null, "Límite de estudiantes alcanzado");
    }

            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar archivo estudiantes.txt");
        }
    }

    // ===== MOSTRAR ESTUDIANTES =====
    public static void verEstudiantes() {
        StringBuilder lista = new StringBuilder();

        if (contador == 0) {
    JOptionPane.showMessageDialog(null, "No hay estudiantes registrados en el sistema");
    return;
}

        for (int i = 0; i < contador; i++) {
            lista.append((i + 1)).append(". ").append(estudiantes[i]).append("\n");
        }

        JOptionPane.showMessageDialog(null, lista.toString());
    }

    // ===== FUNCIONES DE NOTA =====
    public static void agregarNota() {

    if (contador == 0) {
        JOptionPane.showMessageDialog(null, "No hay estudiantes registrados");
        return;
    }

    String input = JOptionPane.showInputDialog("Ingrese número de estudiante:");

    if (input == null) return;

    int index;

    try {
        index = Integer.parseInt(input) - 1;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Entrada inválida");
        return;
    }

    if (index < 0 || index >= contador) {
        JOptionPane.showMessageDialog(null, "Estudiante no válido");
        return;
    }

    String notaStr = JOptionPane.showInputDialog("Ingrese la nota:");

    if (notaStr == null) return;

    notaStr = notaStr.trim();

if (notaStr.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Debe ingresar una nota");
    return;
}

    try {
        double nota = Double.parseDouble(notaStr);

if (nota < 0 || nota > 100) {
    JOptionPane.showMessageDialog(null, "La nota debe estar entre 0 y 100");
    return;
}

notas[index] = nota;

        JOptionPane.showMessageDialog(null, "Nota registrada para " + estudiantes[index]);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Nota inválida");
    }
}

    public static void mostrarNotas() {

    if (contador == 0) {
        JOptionPane.showMessageDialog(null, "No hay estudiantes registrados");
        return;
    }

    StringBuilder lista = new StringBuilder();

    for (int i = 0; i < contador; i++) {

    lista.append((i + 1))
         .append(". ")
         .append(estudiantes[i])
         .append(" - Nota: ");

    if (notas[i] == 0) {
        lista.append("Sin nota");
    } else {
        lista.append(notas[i]);
    }

    lista.append("\n");
}

    JOptionPane.showMessageDialog(null, lista.toString());
}

    public static void mostrarPromedio() {
        
    if (contador == 0) {
        JOptionPane.showMessageDialog(null, "No hay estudiantes registrados");
        return;
    }

    double suma = 0;
    int cantidad = 0;

    for (int i = 0; i < contador; i++) {
        if (notas[i] > 0) {
            suma += notas[i];
            cantidad++;
        }
    }

    if (cantidad == 0) {
    JOptionPane.showMessageDialog(null, "No hay notas registradas para calcular promedio");
    return;
}

    double promedio = suma / cantidad;

    JOptionPane.showMessageDialog(null, "Promedio general: " + String.format("%.2f", promedio));
}

public static void buscarEstudiante() {

    if (contador == 0) {
        JOptionPane.showMessageDialog(null, "No hay estudiantes registrados");
        return;
    }

    String nombre = JOptionPane.showInputDialog("Ingrese nombre a buscar:");

    if (nombre == null) return;

    nombre = nombre.trim().toLowerCase();

    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
        return;
    }

    boolean encontrado = false;
    StringBuilder resultado = new StringBuilder();

    for (int i = 0; i < contador; i++) {

        if (estudiantes[i].toLowerCase().contains(nombre)) {

            resultado.append((i + 1))
                     .append(". ")
                     .append(estudiantes[i]);

            // Mostrar nota si existe
            if (notas[i] == 0) {
                resultado.append(" - Nota: Sin nota");
            } else {
                resultado.append(" - Nota: ").append(notas[i]);
            }

            resultado.append("\n");

            encontrado = true;
        }
    }

    if (encontrado) {
        JOptionPane.showMessageDialog(null, resultado.toString());
    } else {
        JOptionPane.showMessageDialog(null, "Estudiante no encontrado");
    }
}

    // ===== MAIN =====
    public static void main(String[] args) {

        cargarEstudiantes();

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    "===== SISTEMA DE REGISTRO =====\n"
                            + "1. Registro de Estudiante\n"
                            + "2. Admisión\n"
                            + "3. Matrícula\n"
                            + "4. Generar Carné\n"
                            + "5. Inscripción de Cursos\n"
                            + "\n=== CONSULTAS ===\n"
                            + "6. Ver estudiantes\n"
                            + "7. Buscar estudiante\n"
                            + "8. Eliminar estudiante\n"
                            + "9. Eliminar repetidos\n"
                            + "\n=== NOTAS ===\n"
                            + "10. Agregar nota\n"
                            + "11. Mostrar notas\n"
                            + "12. Mostrar promedio\n"
                            + "13. Salir"
            );

            if (opcion == null) {
                break;
            }

    opcion = opcion.trim();

    if (opcion.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Debe ingresar una opción");
    continue;
}

            switch (opcion) {

                case "6":
                    verEstudiantes();
                    break;
                
                case "7":
                    buscarEstudiante();
                    break;

                case "10":
                    agregarNota();
                    break;

                case "11":
                    mostrarNotas();
                    break;

                case "12":
                    mostrarPromedio();
                    break;

                case "13":
                    JOptionPane.showMessageDialog(null, "Sistema finalizado correctamente");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida o en construcción");
            }

        } while (opcion != null && !opcion.equals("13"));
    }
}