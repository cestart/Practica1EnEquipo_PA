package Parte1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SpinnerNumberModel;

public class Practica02_c extends JFrame implements ActionListener {
	String[] opciones = { "Opción 1", "Opción 2", "Opción 3" };
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
		setSize(412, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setBounds(0, 0, 403, 365);

		JLabel etiqueta1 = new JLabel("JButton:");
		etiqueta1.setBounds(10, 16, 60, 20);
		Bsalir = new JButton("Haz clic para Salir");
		Bsalir.setBounds(160, 11, 150, 30);
		Bsalir.addActionListener(this);
		panel.setLayout(null);
		panel.add(etiqueta1);
		panel.add(Bsalir);

		JLabel etiqueta2 = new JLabel("JTextField:");
		etiqueta2.setBounds(10, 54, 100, 20);
		campoTexto = new JTextField();
		campoTexto.setBounds(160, 52, 150, 25);
		panel.add(etiqueta2);
		panel.add(campoTexto);

		JLabel etiqueta3 = new JLabel("JPasswordField:");
		etiqueta3.setBounds(10, 96, 150, 20);
		campoPassword = new JPasswordField();
		campoPassword.setBounds(160, 94, 150, 25);
		panel.add(etiqueta3);
		panel.add(campoPassword);

		JLabel etiqueta4 = new JLabel("JTextArea:");
		etiqueta4.setBounds(10, 130, 100, 20);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 129, 150, 40);
		panel.add(scrollPane);
		areaTexto = new JTextArea();
		panel.add(etiqueta4);
		scrollPane.setViewportView(areaTexto);

		JLabel etiqueta5 = new JLabel("JFormattedTextField:");
		etiqueta5.setBounds(10, 182, 150, 20);
		campoFormateado = new JFormattedTextField();
		campoFormateado.setBounds(160, 180, 150, 25);
		campoFormateado.setValue(12345.67);
		panel.add(etiqueta5);
		panel.add(campoFormateado);

		JLabel etiqueta6 = new JLabel("JSpinner:");
		etiqueta6.setBounds(10, 219, 100, 20);
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0, 0, 100, 1);
		getContentPane().setLayout(null);
		spinner = new JSpinner(modeloSpinner);
		spinner.setBounds(160, 216, 150, 25);
		panel.add(etiqueta6);
		panel.add(spinner);

		JLabel etiqueta7 = new JLabel("JSlider:");
		etiqueta7.setBounds(10, 264, 100, 20);
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		slider.setBounds(160, 257, 150, 40);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		panel.add(etiqueta7);
		panel.add(slider);

		JLabel etiqueta8 = new JLabel("JComboBox:");
		etiqueta8.setBounds(10, 310, 100, 20);
		comboBox = new JComboBox<>(opciones);
		comboBox.setBounds(160, 308, 150, 25);
		panel.add(etiqueta8);
		panel.add(comboBox);

		getContentPane().add(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		Practica02_c ventana = new Practica02_c();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.Bsalir) {
			String cadena = "Valor de JTextField: " + this.campoTexto.getText().trim() + "\n"
					+ "Valor de JPasswordField: " + new String(this.campoPassword.getPassword()).trim() + "\n"
					+ "Valor de JTextArea: " + this.areaTexto.getText().trim() + "\n" + "Valor de JFormattedTextField: "
					+ this.campoFormateado.getText().trim() + "\n" + "Valor de JSpinner: "
					+ String.valueOf(this.spinner.getValue()) + "\n" + "Valor de JSlider: "
					+ String.valueOf(this.slider.getValue()) + "\n";
			if (comboBox.getSelectedIndex() > -1) {
				cadena += "Valor de JComboBox: " + this.comboBox.getSelectedItem().toString() + "\n";
			}

			JOptionPane.showMessageDialog(this, cadena);
		}
	}
}
