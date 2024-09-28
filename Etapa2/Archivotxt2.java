package Libreria;

import java.io.*;
import java.util.ArrayList;
import Parte2.Insumo;

public class Archivotxt2 {
    private String nombreArchivo;

    public Archivotxt2(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    // Guarda una lista de insumos en el archivo
    public boolean guardar(ArrayList<Insumo> insumos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Insumo insumo : insumos) {
                writer.write(insumo.getIdProducto() + "," + insumo.getProducto() + "," + insumo.getIdCategoria());
                writer.newLine(); // Añadir nueva línea después de cada insumo
            }
            return true; // Retorna true si el guardado fue exitoso
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
            return false; // Retorna false si ocurre un error
        }
    }

    // Carga insumos desde el archivo y devuelve una lista de objetos Insumo
    public ArrayList<Insumo> cargar() {
        ArrayList<Insumo> insumos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                if (elementos.length == 3) { // Asegurarse de que hay tres elementos
                    String id = elementos[0].trim();
                    String nombre = elementos[1].trim();
                    String idCategoria = elementos[2].trim();
                    Insumo insumo = new Insumo(id, nombre, idCategoria);
                    insumos.add(insumo); // Agregar el insumo a la lista
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }

        return insumos;
    }
}