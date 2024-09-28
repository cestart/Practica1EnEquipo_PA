package Parte2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import Libreria.Archivotxt2;

public class Practica03_d extends JFrame implements ActionListener {
    // Declarar objetos para gestionar categorías y productos
    private ListaInsumos2 listainsumo;
    private ListaCategorias listacategorias;

    // Declarar objetos de control con tipos genéricos
    private JComboBox ComboCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea areaProductos;
    private JPanel panelFormulario;

    // Instancia de Archivotxt2 para manejar la persistencia
    private Archivotxt2 archivo;

    // Inicializar las categorías
    public void inicializarcategorias() {
        this.listacategorias = new ListaCategorias();
        this.listacategorias.agregarCategoria(new Categoria("01", "Materiales"));
        this.listacategorias.agregarCategoria(new Categoria("02", "Mano de Obra"));
        this.listacategorias.agregarCategoria(new Categoria("03", "Maquinaria y Equipo"));
        this.listacategorias.agregarCategoria(new Categoria("04", "Servicios"));
    }

    // Constructor de la clase
    public Practica03_d() {
        super("Administración de Productos");
        this.inicializarcategorias();
        this.listainsumo = new ListaInsumos2();

        // Inicializar Archivotxt2 con el nombre del archivo
        this.archivo = new Archivotxt2("insumos.txt");

        // Cargar insumos desde el archivo
        ArrayList<Insumo> insumosCargados = this.archivo.cargar();
        for (Insumo insumo : insumosCargados) {
            this.listainsumo.agregarInsumo(insumo);
        }

        configurarVentana();
        configurarPanel();

        // Actualizar el área de productos con los insumos cargados
        this.areaProductos.setText(this.listainsumo.toString());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Configurar las propiedades de la ventana
    private void configurarVentana() {
        setBounds(100, 100, 390, 370); // Posición y tamaño ajustados
    }

    // Configurar los componentes del panel
    private void configurarPanel() {
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        // Etiqueta y ComboBox para Categoría
        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(10, 66, 71, 20);
        ComboCategoria = new JComboBox<>(this.listacategorias.CategoriasArreglo());
        ComboCategoria.setEditable(false);
        ComboCategoria.setBounds(91, 66, 160, 20);
        ComboCategoria.addActionListener(this);
        panelFormulario.add(labelCategoria);
        panelFormulario.add(ComboCategoria);

        // Etiqueta y Campo de Texto para ID
        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        Tid = new JTextField(10);
        Tid.setEditable(false);
        Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);

        // Etiqueta y Campo de Texto para Insumo
        JLabel labelInsumo = new JLabel("Insumo:");
        labelInsumo.setBounds(10, 34, 71, 20);
        Tinsumo = new JTextField(20);
        Tinsumo.setEditable(false);
        Tinsumo.setBounds(91, 35, 147, 20);
        panelFormulario.add(labelInsumo);
        panelFormulario.add(Tinsumo);

        // Botón Agregar
        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(20, 104, 111, 20);
        Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        // Botón Eliminar
        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(153, 104, 111, 20);
        Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        // Botón Salir
        Bsalir = new JButton("Salir");
        Bsalir.setBounds(274, 104, 79, 20);
        Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);

        // Área de Texto para Mostrar Insumos
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 140, 357, 219); // Ajustado para mayor espacio
        panelFormulario.add(scrollPane);
        this.areaProductos = new JTextArea(10, 40);
        scrollPane.setViewportView(areaProductos);
        this.areaProductos.setEditable(false);
    }

    // Restablecer el formulario al estado inicial
    public void VolverAlInicio() {
        this.Bagregar.setText("Agregar");
        this.Bsalir.setText("Salir");
        this.Beliminar.setEnabled(true);
        this.Tid.setEditable(false);
        this.Tinsumo.setEditable(false);
        this.ComboCategoria.setEditable(false);
        this.Tid.setText("");
        this.Tinsumo.setText("");
        this.ComboCategoria.setSelectedIndex(0);
    }

    // Método para manejar la adición de insumos
    public void Altas() {
        if (this.Bagregar.getText().equals("Agregar")) {
            // Cambiar el estado del formulario para agregar
            this.Bagregar.setText("Salvar");
            this.Bsalir.setText("Cancelar");
            this.Beliminar.setEnabled(false);
            this.Tid.setEditable(true);
            this.Tinsumo.setEditable(true);
            this.ComboCategoria.setEditable(true);
            this.ComboCategoria.setFocusable(true);
        } else {
            // Guardar el nuevo insumo
            String id = this.Tid.getText().trim();
            String insumo = this.Tinsumo.getText().trim();
            Categoria categoriaSeleccionada = (Categoria) this.ComboCategoria.getSelectedItem();
            String idCategoria = categoriaSeleccionada != null ? categoriaSeleccionada.getIdcategoria().trim() : "";

            // Validación básica
            if (id.isEmpty() || insumo.isEmpty() || idCategoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Insumo nodo = new Insumo(id, insumo, idCategoria);

            // Intentar agregar el insumo
            boolean agregado = this.listainsumo.agregarInsumo(nodo);
            if (!agregado) { // Si no se pudo agregar, el ID ya existe
                JOptionPane.showMessageDialog(null, "Lo siento, el ID " + id + " ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            } else { // Agregado exitosamente
                this.areaProductos.setText(this.listainsumo.toString());

                // Guardar la lista actualizada en el archivo
                boolean guardadoExitoso = this.archivo.guardar(this.listainsumo.getInsumos());
                if (!guardadoExitoso) {
                    JOptionPane.showMessageDialog(this, "Error al guardar los insumos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Insumo agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            this.VolverAlInicio();
        }
    }

    // Método para manejar la eliminación de insumos
    public void Eliminar() {
        Object[] opciones = this.listainsumo.idinsumos();
        if (opciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay insumos para eliminar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String id = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona una opción:",
                "Eliminación de Insumos",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (id != null && !id.isEmpty()) {
            boolean eliminado = this.listainsumo.eliminarInsumoPorId(id);
            if (!eliminado) { // Si no se pudo eliminar, el ID no existe
                JOptionPane.showMessageDialog(this, "No existe ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else { // Eliminado exitosamente
                this.areaProductos.setText(this.listainsumo.toString());

                // Guardar la lista actualizada en el archivo
                boolean guardadoExitoso = this.archivo.guardar(this.listainsumo.getInsumos());
                if (!guardadoExitoso) {
                    JOptionPane.showMessageDialog(this, "Error al guardar los insumos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Insumo eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    // Manejar las acciones de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Bagregar)
            this.Altas();
        else if (e.getSource() == this.Beliminar)
            this.Eliminar();
        else if (e.getSource() == this.Bsalir) {
            if (this.Bsalir.getText().equals("Cancelar"))
                this.VolverAlInicio();
            else
                this.dispose();
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        // Ejecutar en el hilo de despacho de eventos para seguridad en Swing
        SwingUtilities.invokeLater(() -> new Practica03_d());
    }
}