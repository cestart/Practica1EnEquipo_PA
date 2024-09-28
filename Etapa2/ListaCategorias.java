package Parte2;

import java.util.ArrayList;
import javax.swing.JComboBox;

public class ListaCategorias {
    ArrayList<Categoria> categorias;

    public ListaCategorias() {
        this.categorias = new ArrayList<>();
    }


    public boolean agregarCategoria(Categoria categoria) {
        if (buscarCategoriaPorId(categoria.getIdcategoria()) == null) {
            categorias.add(categoria);
            return true;  
        }
        return false;  
    }

     public void cargarCategorias(ArrayList<String[]> categoriasString) {
    	for(String[] categoriaString: categoriasString) {
    		String idCategoria = categoriaString[0];
    		String nombreCategoria = categoriaString[1];
    		Categoria categoria = new Categoria(idCategoria, nombreCategoria);
    		this.agregarCategoria(categoria);
    	}
    }
  
    public boolean eliminarCategoriaPorId(String id) {
        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria != null) {
            categorias.remove(categoria);
            return true;  // Se eliminó correctamente
        }
        return false;  // No se encontró la categoría
    }

    public String toLinea() {
        StringBuilder resultado = new StringBuilder();
        for (Categoria categoria : categorias) {
            resultado.append(categoria.toString()).append("\n");
        }
        return resultado.toString();
    }

    @Override
    public String toString() {
        return toLinea();
    }

    public Categoria buscarCategoriaPorId(String id) {
        for (Categoria categoria : categorias) {
            if (categoria.getIdcategoria().equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    public String buscarCategoria(String id) {
        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria != null) {
            return categoria.getCategoria();
        }
        return null;
    }

    public JComboBox agregarCategoriasAComboBox(JComboBox comboBox) {
        JComboBox aux = comboBox;
        aux.removeAllItems();
        for (Categoria categoria : categorias) {
            aux.addItem(categoria);
        }
        return aux;
    }

    public Object[] CategoriasArreglo() {
        return this.categorias.toArray();
    }
}