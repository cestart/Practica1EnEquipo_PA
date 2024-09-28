package Parte2;

import javax.swing.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import javax.swing.SpinnerNumberModel;

public class Practica02_c extends JFrame implements ActionListener {  
    String[] opciones = {"Opción 1", "Opción 2", "Opción 3"};  
    JComboBox<String> comboBox;  
    JPanel panel;  
    JButton Bsalir;  
    JPasswordField campoPassword;  
    JTextField campoTexto;  
    JTextArea areaTexto;  
    JFormattedTextField campoFormateado;  
    JSlider slider;  
    JSpinner spinner;

    public Practica02_c() {  
        setTitle("Ejemplo de componentes de entrada de texto");  
        setSize(442, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        panel = new JPanel();  
        panel.setLayout(null);
        
        JLabel etiqueta1 = new JLabel("JButton");  
        etiqueta1.setBounds(3, 179, 60, 20);  
        Bsalir = new JButton("Salir");  
        Bsalir.setBounds(100, 179, 100, 30);  
        Bsalir.addActionListener(this);  
        panel.add(Bsalir);  

        JLabel etiqueta2 = new JLabel("JTextField:");  
        etiqueta2.setBounds(10, 50, 100, 20);  
        panel.add(etiqueta2);
        campoTexto = new JTextField();  
        campoTexto.setBounds(120, 50, 150, 25);  
        panel.add(campoTexto);  

        JLabel etiqueta3 = new JLabel("JPasswordField:");  
        etiqueta3.setBounds(10, 90, 150, 20);  
        panel.add(etiqueta3);
        campoPassword = new JPasswordField();  
        campoPassword.setBounds(120, 90, 150, 25);  
        panel.add(campoPassword);  

        JLabel etiqueta4 = new JLabel("JTextArea:");  
        etiqueta4.setBounds(10, 130, 100, 20);  
        panel.add(etiqueta4);
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setBounds(120, 130, 150, 60);  
        areaTexto = new JTextArea();  
        scrollPane.setViewportView(areaTexto);  
        panel.add(scrollPane);

        JLabel etiqueta5 = new JLabel("JFormattedTextField:");
        etiqueta5.setBounds(10, 200, 150, 20);
        panel.add(etiqueta5);
        campoFormateado = new JFormattedTextField();
        campoFormateado.setBounds(160, 200, 150, 25);
        campoFormateado.setValue(12345.67);
        panel.add(campoFormateado);

        JLabel etiqueta6 = new JLabel("JSpinner:");
        etiqueta6.setBounds(10, 240, 100, 20);
        panel.add(etiqueta6);
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0, 0, 100, 1);
        spinner = new JSpinner(modeloSpinner);
        spinner.setBounds(120, 240, 150, 25);
        panel.add(spinner);

        JLabel etiqueta7 = new JLabel("JSlider:");
        etiqueta7.setBounds(10, 280, 100, 20);
        panel.add(etiqueta7);
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setBounds(120, 280, 150, 40);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);

        JLabel etiqueta8 = new JLabel("JComboBox:");
        etiqueta8.setBounds(10, 330, 100, 20);
        panel.add(etiqueta8);
        comboBox = new JComboBox<>(opciones);
        comboBox.setBounds(120, 330, 150, 25);
        panel.add(comboBox);

        getContentPane().add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Practica02_c ventana = new Practica02_c();
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Bsalir) {
            String cadena0 = "Valor de JTextField: " + this.campoTexto.getText().trim();
            String cadena1 = "Valor de JPasswordField: " + new String(this.campoPassword.getPassword()).trim();
            String cadena2 = "Valor de JTextArea: " + this.areaTexto.getText().trim();
            String cadena3 = "Valor de JFormattedTextField: " + this.campoFormateado.getText().trim();
            String cadena4 = "Valor de JSpinner: " + String.valueOf(this.spinner.getValue());
            String cadena5 = "Valor de JSlider: " + String.valueOf(this.slider.getValue());
            String cadena6 = "Valor de JComboBox: " + this.comboBox.getSelectedItem().toString();

            String cadena = cadena0 + "\n" + cadena1 + "\n" + cadena2 + "\n" + cadena3 + "\n" + cadena4 + "\n" + cadena5 + "\n" + cadena6;
            JOptionPane.showMessageDialog(this, cadena);
        }
    }
}
