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
    /// Lógica de interacción para Practica01_03.xaml
    /// </summary>
    public partial class Practica01_03 : Window
    {
        public Practica01_03()
        {
            InitializeComponent();
        }
        [STAThread]
        public static void Main()
        {
            Application app = new Application();
            Practica01_03 dialog = new Practica01_03();
            dialog.ShowDialog(); // Se asegura de que sea modal, como el JDialog en Java
        }
    }
}