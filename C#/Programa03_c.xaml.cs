using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
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

namespace Practica1Practica03_c
{
    /// <summary>
    /// Lógica de interacción para Programa03_c.xaml
    /// </summary>
    public partial class Programa03_c : Window
    {
        private ListaCategorias listacategorias;
        private Archivotxt archivoCategorias;

        public Programa03_c()
        {
            InitializeComponent();
            archivoCategorias = new Archivotxt("categorias.txt");
            InicializarCategoriasDesdeArchivo();
        }

        private void InicializarCategoriasDesdeArchivo()
        {
            listacategorias = new ListaCategorias();
            var categoriasCargadas = archivoCategorias.Cargar();
            for (int i = 0; i < categoriasCargadas.Count; i += 2)
            {
                string id = categoriasCargadas[i];
                string nombre = categoriasCargadas[i + 1];
                Categoria categoria = new Categoria(id, nombre);
                listacategorias.AgregarCategoria(categoria);
            }
            Tareacategoria.Text = listacategorias.ToLinea();
        }

        private void GuardarCategoriasEnArchivo()
        {
            try
            {
                archivoCategorias.Guardar(listacategorias.ToLinea());
                MessageBox.Show("Categorías guardadas exitosamente.");
            }
            catch (IOException)
            {
                MessageBox.Show("Error al guardar categorías.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void Bagregar_Click(object sender, RoutedEventArgs e)
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

        private void Beliminar_Click(object sender, RoutedEventArgs e)
        {
            if (listacategorias.Categorias.Count == 0)
            {
                MessageBox.Show("No hay categorías para eliminar.");
                return;
            }

            var opciones = listacategorias.Categorias.Select(c => c.CategoriaNombre).ToArray();
            var seleccion = MessageBox.Show("Selecciona una categoría para eliminar", "Eliminación de Categorías", MessageBoxButton.YesNo);

            if (seleccion == MessageBoxResult.Yes)
            {
                listacategorias.EliminarCategoriaPorId(Tid.Text);
                Tareacategoria.Text = listacategorias.ToLinea();
            }
        }

        private void Bsalir_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown();
        }

        private void Bguardar_Click(object sender, RoutedEventArgs e)
        {
            GuardarCategoriasEnArchivo();
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
    }

    public class Categoria
    {
        public string IdCategoria { get; }
        public string CategoriaNombre { get; }

        public Categoria(string idCategoria, string categoriaNombre)
        {
            IdCategoria = idCategoria;
            CategoriaNombre = categoriaNombre;
        }
    }

    public class ListaCategorias
    {
        public ObservableCollection<Categoria> Categorias { get; }

        public ListaCategorias()
        {
            Categorias = new ObservableCollection<Categoria>();
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
            var categoria = BuscarCategoriaPorId(id);
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
            return string.Join(", ", Categorias.Select(c => $"{c.IdCategoria} - {c.CategoriaNombre}"));
        }
    }

    public class Archivotxt
    {
        private readonly string nombreArchivo;

        public Archivotxt(string nombreArchivo)
        {
            this.nombreArchivo = nombreArchivo;
        }

        public void Guardar(string texto)
        {
            File.WriteAllText(nombreArchivo, texto);
        }

        public ObservableCollection<string> Cargar()
        {
            var lineas = new ObservableCollection<string>();
            if (File.Exists(nombreArchivo))
            {
                var contenido = File.ReadAllText(nombreArchivo);
                var elementos = contenido.Split(new[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
                foreach (var elemento in elementos)
                {
                    lineas.Add(elemento.Trim());
                }
            }
            return lineas;
        }
    }
}