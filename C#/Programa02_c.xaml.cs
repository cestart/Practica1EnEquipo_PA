using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
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
    /// Lógica de interacción para Programa02_c.xaml
    /// </summary>
    public partial class Programa02_c : Window
    {
        public Programa02_c()
        {
            InitializeComponent();
        }

        private void Bsalir_Click(object sender, RoutedEventArgs e)
        {
            string cadena0 = "Valor de JTextField: " + campoTexto.Text.Trim();
            string cadena1 = "Valor de JPasswordField: " + campoPassword.Password.Trim();
            string cadena2 = "Valor de JTextArea: " + areaTexto.Text.Trim();
            string cadena3 = "Valor de JFormattedTextField: " + campoFormateado.Text.Trim();
            string cadena4 = "Valor de JSpinner: " + spinner.Text; // Cambiado a TextBox
            string cadena5 = "Valor de JSlider: " + slider.Value.ToString();
            string cadena6 = "Valor de JComboBox: " + (comboBox.SelectedItem as ComboBoxItem)?.Content.ToString();

            string cadena = cadena0 + "\n" + cadena1 + "\n" + cadena2 + "\n" + cadena3 + "\n" + cadena4 + "\n" + cadena5 + "\n" + cadena6;
            MessageBox.Show(cadena, "Valores de Entrada");
        }

        private void btnIncrease_Click(object sender, RoutedEventArgs e)
        {
            int currentValue = int.TryParse(spinner.Text, out currentValue) ? currentValue : 0;
            currentValue++;
            spinner.Text = currentValue.ToString();
        }

        private void btnDecrease_Click(object sender, RoutedEventArgs e)
        {
            int currentValue = int.TryParse(spinner.Text, out currentValue) ? currentValue : 0;
            if (currentValue > 0)
            {
                currentValue--;
            }
            spinner.Text = currentValue.ToString();
        }
    }
}