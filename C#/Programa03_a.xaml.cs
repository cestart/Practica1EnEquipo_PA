using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows;

namespace PracticasUnidad1
{
    public partial class Programa03_a : Window
    {
        private ListaInsumos listainsumo;
        private ListaCategorias listacategorias;
        public Programa03_a()
        {
            InitializeComponent();
            InicializarCategorias();
            listainsumo = new ListaInsumos();
            ComboCategoria.ItemsSource = listacategorias.Categorias;
            ComboCategoria.SelectedIndex = 0;
        }

        private void InicializarCategorias()
        {
            listacategorias = new ListaCategorias();
            listacategorias.AgregarCategoria(new Categoria("01", "Materiales"));
            listacategorias.AgregarCategoria(new Categoria("02", "Mano de Obra"));
            listacategorias.AgregarCategoria(new Categoria("03", "Maquinaria y Equipo"));
            listacategorias.AgregarCategoria(new Categoria("04", "Servicios"));
        }

        private void Bagregar_Click(object sender, RoutedEventArgs e)
        {
            if (Tid.Text.Trim() == "" || Tinsumo.Text.Trim() == "")
            {
                MessageBox.Show("Por favor, complete los campos");
                return;
            }

            var id = Tid.Text.Trim();
            var insumo = Tinsumo.Text.Trim();
            var idCategoria = ((Categoria)ComboCategoria.SelectedItem).IdCategoria;
            var nuevoInsumo = new Insumo(id, insumo, idCategoria);

            if (!listainsumo.AgregarInsumo(nuevoInsumo))
            {
                MessageBox.Show($"Lo siento, el id {id} ya existe");
            }
            else
            {
                ActualizarAreaProductos();
            }
        }

        private void Beliminar_Click(object sender, RoutedEventArgs e)
        {
            var id = Tid.Text.Trim();
            if (listainsumo.EliminarInsumoPorId(id))
            {
                MessageBox.Show($"El insumo con id {id} ha sido eliminado");
                ActualizarAreaProductos();
            }
            else
            {
                MessageBox.Show($"No se encontró el insumo con id {id}");
            }
        }

        private void Bsalir_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void ActualizarAreaProductos()
        {
            areaProductos.Text = listainsumo.ToString();
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
            return Nombre;
        }
    }

    public class ListaCategorias
    {
        public List<Categoria> Categorias { get; set; }

        public ListaCategorias()
        {
            Categorias = new List<Categoria>();
        }

        public void AgregarCategoria(Categoria categoria)
        {
            if (BuscarCategoriaPorId(categoria.IdCategoria) == null)
            {
                Categorias.Add(categoria);
            }
        }

        public Categoria BuscarCategoriaPorId(string id)
        {
            return Categorias.Find(c => c.IdCategoria == id);
        }
    }

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
            return $"{IdProducto}\t\t{Producto}\t\t{IdCategoria}\n";
        }
    }


    public class ListaInsumos
    {
        private List<Insumo> insumos;

        public ListaInsumos()
        {
            insumos = new List<Insumo>();
        }

        public bool AgregarInsumo(Insumo insumo)
        {
            if (BuscarInsumoPorId(insumo.IdProducto) == null)
            {
                insumos.Add(insumo);
                return true;
            }
            return false;
        }

        public bool EliminarInsumoPorId(string id)
        {
            var insumo = BuscarInsumoPorId(id);
            if (insumo != null)
            {
                insumos.Remove(insumo);
                return true;
            }
            return false;
        }

        public Insumo BuscarInsumoPorId(string id)
        {
            return insumos.Find(i => i.IdProducto == id);
        }

        public override string ToString()
        {
            string resultado = "";
            foreach (var insumo in insumos)
            {
                resultado += insumo.ToString();
            }
            return resultado;
        }
    }
}
