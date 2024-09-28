using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Practica03_d
{
    /// <summary>
    /// Lógica de interacción para Practica03_d.xaml
    /// </summary>
    public partial class Practica03_d : Window
    {
        private ObservableCollection<Insumo> insumos;
        private ObservableCollection<Categoria> categorias;

        public Practica03_d()
        {
            InitializeComponent();
            InicializarCategorias();

            insumos = new ObservableCollection<Insumo>();
            ActualizarAreaProductos();
        }

        // Inicializar las categorías
        private void InicializarCategorias()
        {
            categorias = new ObservableCollection<Categoria>
            {
                new Categoria("01", "Materiales"),
                new Categoria("02", "Mano de Obra"),
                new Categoria("03", "Maquinaria y Equipo"),
                new Categoria("04", "Servicios")
            };

            comboxCategoria.ItemsSource = categorias;
        }

        // Actualizar la lista de insumos en el área de productos
        private void ActualizarAreaProductos()
        {
            areaProductos.Text = string.Join("\n", insumos);
        }

        // Agregar un insumo
        private void bAgregar_Click(object sender, RoutedEventArgs e)
        {
            string id = txtID.Text.Trim();
            string insumo = txtInsumo.Text.Trim();
            var categoria = (Categoria)comboxCategoria.SelectedItem;

            if (string.IsNullOrWhiteSpace(id) || string.IsNullOrWhiteSpace(insumo) || categoria == null)
            {
                MessageBox.Show("Todos los campos deben estar llenos.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }

            insumos.Add(new Insumo(id, insumo, categoria.IdCategoria));
            ActualizarAreaProductos();
        }

        // Eliminar un insumo
        private void bEliminar_Click(object sender, RoutedEventArgs e)
        {
            // Pedir ID del insumo a eliminar
            string id = Microsoft.VisualBasic.Interaction.InputBox("Ingresa el ID del insumo a eliminar:", "Eliminar Insumo");

            if (!string.IsNullOrWhiteSpace(id))
            {
                var insumoAEliminar = insumos.FirstOrDefault(i => i.IdProducto == id);
                if (insumoAEliminar != null)
                {
                    insumos.Remove(insumoAEliminar);
                    MessageBox.Show("Insumo eliminado exitosamente.", "Éxito", MessageBoxButton.OK, MessageBoxImage.Information);
                    ActualizarAreaProductos();
                }
                else
                {
                    MessageBox.Show("ID no encontrado.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                }
            }
        }

        // Salir de la aplicación
        private void bSalir_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }

    // Clase Insumo
    public class Insumo
    {
        public string IdProducto { get; set; }
        public string Producto { get; set; }
        public string IdCategoria { get; set; }

        public Insumo(string idProducto, string producto, string idCategoria)
        {
            IdProducto = idProducto;
            Producto = producto;
            IdCategoria = idCategoria;
        }

        public override string ToString()
        {
            return $"{IdProducto} - {Producto} (Categoría: {IdCategoria})";
        }
    }

    // Clase Categoria
    public class Categoria
    {
        public string IdCategoria { get; set; }
        public string Nombre { get; set; }

        public Categoria(string idCategoria, string nombre)
        {
            IdCategoria = idCategoria;
            Nombre = nombre;
        }

        public override string ToString()
        {
            return Nombre;
        }
    }
}