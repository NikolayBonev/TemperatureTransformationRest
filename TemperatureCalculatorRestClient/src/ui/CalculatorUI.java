package ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.google.gson.*;

import core.Temperature;
import rest.TemperatureCalculatorClient;

public class CalculatorUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorUI frame = new CalculatorUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculatorUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		createContentPane();
		
		createTemperatureTextField();
		
		createCalculateButton();
		
		createLabels();
		
		createTemperatureMesureComboBox();
		
	}
	
	/**
	 * Create content pane
	 */
	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	/**
	 * Create temperature text field for temperature input value
	 */
	private void createTemperatureTextField() {
		textField = new JTextField();
		textField.setBounds(240, 83, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
	}

	/**
	 * Create button for execution of the request to the Calculator Rest API
	 */
	private void createCalculateButton() {
		JButton btnCalcbtn = new JButton("Calculate");
		btnCalcbtn.setBounds(80, 80, 117, 25);

		btnCalcbtn.addActionListener(e -> {
			Double parsedTemperature = null;

			try {
				parsedTemperature = Double.parseDouble(textField.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "The value has to be a number!");
				
				return;
			}
			
			Temperature calculatedTemperature = executeRequest(parsedTemperature);
			
			JOptionPane.showMessageDialog(null, String.format("%s is: %f", calculatedTemperature.getMesure(), calculatedTemperature.getTemperature()));
		});

		contentPane.add(btnCalcbtn);
	}
	
	private Temperature executeRequest(double receivedTemperature) {
		TemperatureCalculatorClient client = new TemperatureCalculatorClient();

		String jsonTemperature = client
				.tranformTemperature(new Temperature(receivedTemperature, comboBox.getSelectedItem().toString()));

		JsonParser parser = new JsonParser();
		JsonObject calculationJsonObject = parser.parse(jsonTemperature).getAsJsonObject();

		return new Temperature(calculationJsonObject.get("temperature").getAsDouble(), calculationJsonObject.get("mesure").getAsString());
	}

	/**
	 * Creates all the labels on the frame
	 */
	private void createLabels() {
		JLabel lblTransformCalculatorOf = new JLabel("Transform calculator of temperature");
		lblTransformCalculatorOf.setBounds(83, 54, 288, 15);
		contentPane.add(lblTransformCalculatorOf);

		JLabel lblTransformTo = new JLabel("Transform to:");
		lblTransformTo.setBounds(83, 146, 114, 15);
		contentPane.add(lblTransformTo);
	}

	/**
	 * Creates combo box with temperatue mesures
	 */
	private void createTemperatureMesureComboBox() {
		String[] optionList = { "Celsius", "fahrenheit" };

		comboBox = new JComboBox(optionList);
		comboBox.setBounds(240, 141, 114, 24);
		comboBox.setSelectedIndex(1);
		contentPane.add(comboBox);
	}
}
