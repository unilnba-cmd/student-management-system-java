package poo;

import javax.swing.*;
import java.io.*;

public class SistemaRegistro {

    static String[] estudiantes = new String[20];
    static int contador = 0;

    // ===== CARGAR ARCHIVO =====
    public static void cargarEstudiantes() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("estudiantes.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {

                boolean existe = false;

                for (int i = 0; i < contador; i++) {
                    if (estudiantes[i].equalsIgnoreCase(linea)) {
                        existe = true;
                        break;
                    }
                }

                if (!existe) {
                    estudiantes[contador] = linea;
                    contador++;
                }
            }

            br.close();

        } catch (IOException e) {
            // Si no existe el archivo, no pasa nada
        }
    }

    public static void main(String[] args) {

        cargarEstudiantes();

        // ===== LOGIN =====
        String usuarioCorrecto = "admin";
        String claveCorrecta = "1234";

        boolean acceso = false;

        for (int i = 0; i < 3; i++) {

            JTextField userField = new JTextField();
            JPasswordField passField = new JPasswordField();

            Object[] message = {
                "Usuario:", userField,
                "Contraseña:", passField
            };

            JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

            String usuario = userField.getText();
            String clave = new String(passField.getPassword());

            if (usuario.equals(usuarioCorrecto) && clave.equals(claveCorrecta)) {
                acceso = true;
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
            }
        }

        if (!acceso) {
            JOptionPane.showMessageDialog(null, "Acceso bloqueado");
            System.exit(0);
        }

        // ===== MENÚ =====
        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    "=== SISTEMA DE REGISTRO ===\n"
                    + "1. Registro de Estudiante\n"
                    + "2. Admisión\n"
                    + "3. Matrícula\n"
                    + "4. Generar Carné\n"
                    + "5. Inscripción de Cursos\n"
                    + "6. Ver estudiantes\n"
                    + "7. Buscar estudiante\n"
                    + "8. Eliminar estudiante\n"
                    + "9. Eliminar repetidos\n"
                    + "10. Salir"
            );

            switch (opcion) {

                case "1":

                    if (contador < 20) {

                        String nombre = JOptionPane.showInputDialog("Ingrese nombre:");
                        
                        if (nombre == null || nombre.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nombre inválido");
                            break;
                        }

                        boolean existe = false;

                        for (int i = 0; i < contador; i++) {
                            if (estudiantes[i].equalsIgnoreCase(nombre)) {
                                existe = true;
                                break;
                            }
                        }

                        if (existe) {
                            JOptionPane.showMessageDialog(null, "El estudiante ya existe");
                        } else {

                            estudiantes[contador] = nombre;
                            contador++;

                            try {
                                FileWriter fw = new FileWriter("estudiantes.txt", true);
                                fw.write(nombre + "\n");
                                fw.close();
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Error al guardar archivo");
                            }

                            JOptionPane.showMessageDialog(null, "Registrado: " + nombre);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Límite alcanzado");
                    }

                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Proceso de admisión...");
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Realizando matrícula...");
                    break;

                case "4":
                    JOptionPane.showMessageDialog(null, "Generando carné...");
                    break;

                case "5":
                    JOptionPane.showMessageDialog(null, "Inscripción de cursos...");
                    break;

                case "6":

                    if (contador == 0) {
                        JOptionPane.showMessageDialog(null, "No hay estudiantes registrados");
                    } else {

                        String lista = "=== LISTA DE ESTUDIANTES ===\n\n";

                        for (int i = 0; i < contador; i++) {
                            lista += (i + 1) + ". " + estudiantes[i] + "\n";
                        }
                        
                        // aquí
                        lista += "\nTotal estudiantes: " + contador;

                        JOptionPane.showMessageDialog(null, lista);
                    }

                    break;

                case "7":

                    String buscar = JOptionPane.showInputDialog("Ingrese nombre a buscar:");

                    boolean encontrado = false;
                    String resultado = "Resultados:\n";

                    for (int i = 0; i < contador; i++) {
                        if (estudiantes[i].equalsIgnoreCase(buscar)) {
                            resultado += (i + 1) + ". " + estudiantes[i] + "\n";
                            encontrado = true;
                        }
                    }

                    if (encontrado) {
                        JOptionPane.showMessageDialog(null, resultado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Estudiante no encontrado");
                    }

                    break;
                                    
                case "8":

                    String eliminar = JOptionPane.showInputDialog("Ingrese nombre a eliminar:");
                    boolean eliminado = false;

                    for (int i = 0; i < contador; i++) {

                        if (estudiantes[i].equalsIgnoreCase(eliminar)) {

                            // 🔥 mover elementos
                            for (int j = i; j < contador - 1; j++) {
                                estudiantes[j] = estudiantes[j + 1];
                            }

                            estudiantes[contador - 1] = null;
                            contador--;

                            eliminado = true;

                            // 🔥 ACTUALIZAR ARCHIVO AQUÍ MISMO
                            try {
                                FileWriter fw = new FileWriter("estudiantes.txt");

                                for (int k = 0; k < contador; k++) {
                                    fw.write(estudiantes[k] + "\n");
                                }

                                fw.close();

                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Error al actualizar archivo");
                            }

                            JOptionPane.showMessageDialog(null, "Estudiante eliminado");
                            break;
                        }
                    }

                    if (!eliminado) {
                        JOptionPane.showMessageDialog(null, "No encontrado");
                    }

                    break;
                    
                case "9":

                    for (int i = 0; i < contador; i++) {

                        for (int j = i + 1; j < contador; j++) {

                            if (estudiantes[i].equalsIgnoreCase(estudiantes[j])) {

                                // 🔥 ELIMINAR REPETIDO (igual que antes)
                                for (int k = j; k < contador - 1; k++) {
                                    estudiantes[k] = estudiantes[k + 1];
                                }

                                estudiantes[contador - 1] = null;
                                contador--;

                                j--; // 🔥 CLAVE para no saltarse elementos
                            }
                        }
                    }

                    try {
                        FileWriter fw = new FileWriter("estudiantes.txt");

                        for (int i = 0; i < contador; i++) {
                            fw.write(estudiantes[i] + "\n");
                        }

                        fw.close();

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar archivo");
                    }
                    
                    JOptionPane.showMessageDialog(null, "Repetidos eliminados");

                    break;
                    
                case "10":
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (!opcion.equals("10"));
        
    }
}