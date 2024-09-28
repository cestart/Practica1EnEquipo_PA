package Parte2;

import Libreria.Archivotxt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Practica03_c extends JFrame implements ActionListener {
	ListaCategorias2 listacategorias;
	private JTextField Tid, Tcategoria;
	private JButton Bagregar, Beliminar, Bsalir, Bguardar;
	private JTextArea Tareacategoria;
	private JPanel panelFormulario;
	private Archivotxt archivoCategorias;

	public Practica03_c() {
		super("Administración de Categorías");

		// Inicializar archivo
		archivoCategorias = new Archivotxt("categorias.txt");

		// Inicializar componentes de GUI
		setBounds(0, 0, 390, 370);
		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
		getContentPane().add(panelFormulario, BorderLayout.CENTER);

		JLabel labelCategoria = new JLabel("Categoría:");
		labelCategoria.setBounds(10, 35, 71, 20);
		panelFormulario.add(labelCategoria);

		JLabel labelId = new JLabel("ID:");
		labelId.setBounds(10, 9, 71, 20);
		Tid = new JTextField(10);
		Tid.setEditable(false);
		Tid.setBounds(91, 9, 147, 20);
		panelFormulario.add(labelId);
		panelFormulario.add(Tid);
		Tcategoria = new JTextField(20);
		Tcategoria.setEditable(false);
		Tcategoria.setBounds(91, 35, 147, 20);
		panelFormulario.add(Tcategoria);

		Bagregar = new JButton("Agregar");
		Bagregar.setBounds(20, 104, 111, 20);
		Bagregar.addActionListener(this);
		panelFormulario.add(Bagregar);

		Beliminar = new JButton("Eliminar");
		Beliminar.setBounds(153, 104, 111, 20);
		Beliminar.addActionListener(this);
		panelFormulario.add(Beliminar);

		Bsalir = new JButton("Salir");
		Bsalir.setBounds(274, 104, 79, 20);
		Bsalir.addActionListener(this);
		panelFormulario.add(Bsalir);

		Bguardar = new JButton("Guardar");
		Bguardar.setBounds(20, 80, 111, 20);
		Bguardar.addActionListener(this);
		panelFormulario.add(Bguardar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 357, 179);
		panelFormulario.add(scrollPane);

		Tareacategoria = new JTextArea(10, 40);
		scrollPane.setViewportView(Tareacategoria);
		Tareacategoria.setEditable(false);

		// Inicializar categorías desde el archivo
		inicializarCategoriasDesdeArchivo();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void inicializarCategoriasDesdeArchivo() {
		listacategorias = new ListaCategorias2();
		ArrayList<String> categoriasCargadas = archivoCategorias.cargar();

		for (int i = 0; i < categoriasCargadas.size(); i += 2) {
			String id = categoriasCargadas.get(i);
			String nombre = categoriasCargadas.get(i + 1);
			Categoria categoria = new Categoria(id, nombre);
			listacategorias.agregarCategoria(categoria);
		}
		Tareacategoria.setText(listacategorias.toLinea());
	}

	public void guardarCategoriasEnArchivo() {
		try {
			archivoCategorias.guardar(listacategorias.toLinea());
			JOptionPane.showMessageDialog(this, "Categorías guardadas exitosamente.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error al guardar categorías.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Métodos de acción de botones
	public void Altas() {
		if (this.Bagregar.getText().compareTo("Agregar") == 0) {
			this.Bagregar.setText("Salvar");
			this.Bsalir.setText("Cancelar");
			this.Beliminar.setEnabled(false);
			this.Tid.setEditable(true);
			this.Tcategoria.setEditable(true);
		} else {
			if (!this.Tid.getText().trim().isEmpty() && !this.Tcategoria.getText().trim().isEmpty()) {
				String id = this.Tid.getText().trim();
				String nombre = this.Tcategoria.getText().trim();
				Categoria categoria = new Categoria(id, nombre);
				if (!this.listacategorias.agregarCategoria(categoria)) {
					JOptionPane.showMessageDialog(null, "El ID " + id + " ya existe.");
				} else {
					this.Tareacategoria.setText(this.listacategorias.toLinea());
				}
			}
			this.Volveralinicio();
		}
	}

	public void Eliminar() {
		Object[] opciones = this.listacategorias.CategoriasArreglo();
		if (opciones.length == 0) {
			JOptionPane.showMessageDialog(this, "No hay categorías para eliminar.");
			return;
		}

		Categoria selectedCategoria = (Categoria) JOptionPane.showInputDialog(null,
				"Selecciona una categoría para eliminar:", "Eliminación de Categorías", JOptionPane.PLAIN_MESSAGE, null,
				opciones, opciones[0]);

		if (selectedCategoria != null) {
			String id = selectedCategoria.getIdcategoria();
			int confirmacion = JOptionPane.showConfirmDialog(this,
					"¿Estás seguro de que deseas eliminar la categoría \"" + selectedCategoria.getCategoria() + "\"?",
					"Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

			if (confirmacion == JOptionPane.YES_OPTION) {
				if (this.listacategorias.eliminarCategoriaPorId(id)) {
					JOptionPane.showMessageDialog(this, "Categoría eliminada correctamente.");
					this.Tareacategoria.setText(this.listacategorias.toLinea());
				} else {
					JOptionPane.showMessageDialog(this, "No se pudo eliminar. ID no encontrado.");
				}
			}
		}
	}

	public void Volveralinicio() {
		this.Bagregar.setText("Agregar");
		this.Bsalir.setText("Salir");
		this.Beliminar.setEnabled(true);
		this.Tid.setEditable(false);
		this.Tcategoria.setEditable(false);
		this.Tid.setText("");
		this.Tcategoria.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Bagregar) {
			Altas();
		} else if (e.getSource() == Beliminar) {
			Eliminar();
		} else if (e.getSource() == Bsalir) {
			System.exit(0);
		} else if (e.getSource() == Bguardar) {
			guardarCategoriasEnArchivo();
		}
	}

	public static void main(String[] args) {
		new Practica03_c();
	}
}