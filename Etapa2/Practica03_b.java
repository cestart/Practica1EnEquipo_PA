package Parte2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practica03_b extends JFrame implements ActionListener {
    ListaCategorias listacategorias; // Maneja las categorías
    private JTextField Tid, Tcategoria;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea Tareacategoria;
    private JPanel panelFormulario;

    public void inicializarCategorias() {
        this.listacategorias = new ListaCategorias();
        Categoria nodo1 = new Categoria("01", "Materiales");
        Categoria nodo2 = new Categoria("02", "Mano de Obra");
        Categoria nodo3 = new Categoria("03", "Maquinaria y Equipo");
        Categoria nodo4 = new Categoria("04", "Servicios");
        this.listacategorias.agregarCategoria(nodo1);
        this.listacategorias.agregarCategoria(nodo2);
        this.listacategorias.agregarCategoria(nodo3);
        this.listacategorias.agregarCategoria(nodo4);
    }

    public Practica03_b() {
        super("Administración de Categorías");
        this.inicializarCategorias();
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
        Tcategoria.setEditable(false); // Inicia desactivado
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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 132, 357, 179);
        panelFormulario.add(scrollPane);

        this.Tareacategoria = new JTextArea(10, 40);
        scrollPane.setViewportView(Tareacategoria);
        this.Tareacategoria.setEditable(false);
        this.Tareacategoria.setText(this.listacategorias.toLinea()); // Mostrar categorías iniciales

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void Volveralinicio() {
        this.Bagregar.setText("Agregar");
        this.Bsalir.setText("Salir");
        this.Beliminar.setEnabled(true);
        this.Tid.setEditable(false);
        this.Tcategoria.setEditable(false); // Se desactiva de nuevo
        this.Tid.setText("");
        this.Tcategoria.setText("");
    }

    public void Altas() {
        if (this.Bagregar.getText().compareTo("Agregar") == 0) {
            this.Bagregar.setText("Salvar");
            this.Bsalir.setText("Cancelar");
            this.Beliminar.setEnabled(false);
            this.Tid.setEditable(true);
            this.Tcategoria.setEditable(true); // Se activa al agregar
        } else {
            if (!this.Tid.getText().trim().isEmpty() && !this.Tcategoria.getText().trim().isEmpty()) {
                String id = this.Tid.getText().trim();
                String nombre = this.Tcategoria.getText().trim();
                Categoria categoria = new Categoria(id, nombre);
                if (!this.listacategorias.agregarCategoria(categoria)) { // Lógica corregida
                    JOptionPane.showMessageDialog(null, "El ID " + id + " ya existe.");
                } else {
                    this.Tareacategoria.setText(this.listacategorias.toLinea()); // Usar toLinea()
                }
            }
            this.Volveralinicio(); // Vuelve al estado inicial y desactiva los campos
        }
    }

    public void Eliminar() {
        Object[] opciones = this.listacategorias.CategoriasArreglo();
        if (opciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay categorías para eliminar.");
            return;
        }

        Categoria selectedCategoria = (Categoria) JOptionPane.showInputDialog(
            null,
            "Selecciona una categoría para eliminar:",
            "Eliminación de Categorías",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (selectedCategoria != null) {
            String id = selectedCategoria.getIdcategoria();
            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que deseas eliminar la categoría \"" + selectedCategoria.getCategoria() + "\"?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                if (this.listacategorias.eliminarCategoriaPorId(id)) {
                    JOptionPane.showMessageDialog(this, "Categoría eliminada correctamente.");
                    this.Tareacategoria.setText(this.listacategorias.toLinea()); // Usar toLinea()
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar. ID no encontrado.");
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Bagregar) {
            this.Altas();
        } else if (e.getSource() == this.Beliminar) {
            this.Eliminar();
        } else if (e.getSource() == this.Bsalir) {
            if (this.Bsalir.getText().compareTo("Cancelar") == 0) {
                this.Volveralinicio();
            } else {
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        new Practica03_b();
    }
}