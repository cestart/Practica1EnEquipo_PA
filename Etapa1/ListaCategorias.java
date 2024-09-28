package Parte3;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class ListaCategorias {
	private ArrayList<Categoria> categorias;

	public ListaCategorias() {
		this.categorias = new ArrayList<>();
	}

	public void agregarCategoria(Categoria categoria) {
		if (buscarCategoriaPorId(categoria.getIdcategoria()) == null) {
			categorias.add(categoria);
		}
	}

	public void eliminarCategoria(String id) {
		Categoria categoria = buscarCategoriaPorId(id);
		if (categoria != null) {
			categorias.remove(categoria);
		}
	}

	public String toLinea() {
		String resultado = "";
		for (Categoria categoria : categorias) {
			resultado += categoria.toString() + "\n";
		}
		return resultado;
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
		for (Categoria categoria : categorias)
			aux.addItem(categoria);

		return aux;

	}

	public Object[] CategoriasArreglo() {
		return this.categorias.toArray();
	}
}