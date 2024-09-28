package Parte2;

import java.util.ArrayList;

public class ListaInsumos2 {
    private ArrayList<Insumo> insumos;

    public ListaInsumos2() {
        insumos = new ArrayList<>();
    }

    public boolean agregarInsumo(Insumo insumo) {
        if (buscarInsumoPorId(insumo.getIdProducto()) == null) {
            insumos.add(insumo);
            return true;
        }
        return false;
    }

    public boolean eliminarInsumoPorId(String id) {
        Insumo insumo = buscarInsumoPorId(id);
        if (insumo != null) {
            insumos.remove(insumo);
            return true;
        }
        return false;
    }

    public Insumo buscarInsumoPorId(String id) {
        for (Insumo insumo : insumos) {
            if (insumo.getIdProducto().equals(id)) {
                return insumo;
            }
        }
        return null;
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Insumo insumo : insumos) {
            sb.append(insumo.toString()).append("\n");
        }
        return sb.toString();
    }

    public String[] idinsumos() {
        int pos = 0; // Ajuste en el índice inicial
        String[] datos = new String[this.insumos.size()];
        for (Insumo nodo : this.insumos) {
            datos[pos++] = nodo.getIdProducto();
        }
        return datos;
    }
}