using System;
using System.Windows;

namespace PracticasUnidad1
{
    public partial class Practica01_02xaml : Window
    {
        public Practica01_02xaml()
        {
            InitializeComponent();
        }

        [STAThread]
        public static void Main()
        {
            Application app = new Application();
            Practica01_02xaml window = new Practica01_02xaml();  // cambia Practica01_02 por Practica01_02xaml
            app.Run(window);
        }
    }
}