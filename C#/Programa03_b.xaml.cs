using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
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

namespace ActividadesPractica1
{
    /// <summary>
    /// Lógica de interacción para Programa03_b.xaml
    /// </summary>
    public partial class Programa03_b : Window
    {
        ListaCategorias listacategorias;

        public Programa03_b()
        {
            InitializeComponent();
            InicializarCategorias();
            Tareacategoria.Text = listacategorias.ToLinea();
        }

        private void InicializarCategorias()
        {
            listacategorias = new ListaCategorias();
            Categoria nodo1 = new Categoria("01", "Materiales");
            Categoria nodo2 = new Categoria("02", "Mano de Obra");
            Categoria nodo3 = new Categoria("03", "Maquinaria y Equipo");
            Categoria nodo4 = new Categoria("04", "Servicios");
            listacategorias.AgregarCategoria(nodo1);
            listacategorias.AgregarCategoria(nodo2);
            listacategorias.AgregarCategoria(nodo3);
            listacategorias.AgregarCategoria(nodo4);
        }

        private void VolverAlInicio()
        {
            Bagregar.Content = "Agregar";
            Bsalir.Content = "Salir";
            Beliminar.IsEnabled = true;
            Tid.IsReadOnly = true;
            Tcategoria.IsReadOnly = true;
            Tid.Text = "";
            Tcategoria.Text = "";
        }

        private void Altas()
        {
            if (Bagregar.Content.ToString() == "Agregar")
            {
                Bagregar.Content = "Salvar";
                Bsalir.Content = "Cancelar";
                Beliminar.IsEnabled = false;
                Tid.IsReadOnly = false;
                Tcategoria.IsReadOnly = false;
            }
            else
            {
                if (!string.IsNullOrWhiteSpace(Tid.Text) && !string.IsNullOrWhiteSpace(Tcategoria.Text))
                {
                    string id = Tid.Text.Trim();
                    string nombre = Tcategoria.Text.Trim();
                    Categoria categoria = new Categoria(id, nombre);
                    if (!listacategorias.AgregarCategoria(categoria))
                    {
                        MessageBox.Show($"El ID {id} ya existe.");
                    }
                    else
                    {
                        Tareacategoria.Text = listacategorias.ToLinea();
                    }
                }
                VolverAlInicio();
            }
        }

        private void Eliminar()
        {
            if (listacategorias.Categorias.Count == 0)
            {
                MessageBox.Show("No hay categorías para eliminar.");
                return;
            }

            var seleccionada = SeleccionarCategoria();
            if (seleccionada != null)
            {
                MessageBoxResult confirmacion = MessageBox.Show($"¿Estás seguro de eliminar la categoría \"{seleccionada.Nombre}\"?", "Confirmar Eliminación", MessageBoxButton.YesNo);
                if (confirmacion == MessageBoxResult.Yes)
                {
                    listacategorias.EliminarCategoriaPorId(seleccionada.IdCategoria);
                    Tareacategoria.Text = listacategorias.ToLinea();
                }
            }
        }

        private Categoria SeleccionarCategoria()
        {
            var window = new Window
            {
                Title = "Eliminar Categoría",
                Width = 300,
                Height = 200,
                WindowStartupLocation = WindowStartupLocation.CenterOwner,
                Owner = this
            };

            var comboBox = new ComboBox { Margin = new Thickness(10), Width = 250 };
            foreach (var categoria in listacategorias.Categorias)
            {
                comboBox.Items.Add(categoria);
            }

            var btnSeleccionar = new Button { Content = "Seleccionar", Margin = new Thickness(10), Width = 80, HorizontalAlignment = HorizontalAlignment.Center };
            btnSeleccionar.Click += (sender, e) => { window.DialogResult = true; window.Close(); };

            var stackPanel = new StackPanel { Orientation = Orientation.Vertical, Margin = new Thickness(10) };
            stackPanel.Children.Add(comboBox);
            stackPanel.Children.Add(btnSeleccionar);

            window.Content = stackPanel;

            if (window.ShowDialog() == true && comboBox.SelectedItem != null)
            {
                return comboBox.SelectedItem as Categoria;
            }

            return null;
        }

        private void Agregar_Click(object sender, RoutedEventArgs e)
        {
            Altas();
        }

        private void Eliminar_Click(object sender, RoutedEventArgs e)
        {
            Eliminar();
        }

        private void Salir_Click(object sender, RoutedEventArgs e)
        {
            if (Bsalir.Content.ToString() == "Cancelar")
            {
                VolverAlInicio();
            }
            else
            {
                this.Close();
            }
        }
    }

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
            return $"{IdCategoria} - {Nombre}";
        }
    }

    public class ListaCategorias
    {
        public List<Categoria> Categorias { get; }

        public ListaCategorias()
        {
            Categorias = new List<Categoria>();
        }

        public bool AgregarCategoria(Categoria categoria)
        {
            if (BuscarCategoriaPorId(categoria.IdCategoria) == null)
            {
                Categorias.Add(categoria);
                return true;
            }
            return false;
        }

        public bool EliminarCategoriaPorId(string id)
        {
            Categoria categoria = BuscarCategoriaPorId(id);
            if (categoria != null)
            {
                Categorias.Remove(categoria);
                return true;
            }
            return false;
        }

        public Categoria BuscarCategoriaPorId(string id)
        {
            return Categorias.FirstOrDefault(c => c.IdCategoria == id);
        }

        public string ToLinea()
        {
            return string.Join("\n", Categorias.Select(c => c.ToString()));
        }
    }
}