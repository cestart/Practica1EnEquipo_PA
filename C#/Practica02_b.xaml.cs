using System;
using System.Collections.Generic;
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

namespace PracticasUnidad1
{
    /// <summary>
    /// Lógica de interacción para Practica02_b.xaml
    /// </summary>
    public partial class Practica02_b : Window
    {
        public Practica02_b()
        {
            InitializeComponent();
        }

        private void Bsalir_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown(); 
        }
    }
}