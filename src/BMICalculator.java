/** Name: Yen-Yi Wu
 *  ID: U10216020
 *  Ex.: Design a GUI according to Listing 10.4 BMI.java
 *  Information:
 *  			A program that prompts the user to input his/her First Name, Last Name,
 *  			Age, Weight, and Height to get the BMI and the status.
 *  
 *  			The user can choose the unit of weight/height with the combo boxes.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class BMICalculator extends JFrame {
	// Create text fields for First Name, Last Name, Age, Weight, Height
	private JTextField jtfFirstName = new JTextField();
	private JTextField jtfLastName = new JTextField();
	private JTextField jtfAge = new JTextField();
	private JTextField jtfWeight = new JTextField();
	private JTextField jtfHeight = new JTextField();	

	// Create a Enter button
	private JButton jbtEnter = new JButton("Enter");

	// Create Labels for BMI and Status
	private JLabel jlBMI = new JLabel("(BMI)");
	private JLabel jlStatus = new JLabel("(Status)");

	// Create String arrays for combo boxes
	private String[] unitOfWeight = {"pounds", "kilograms"};
	private String[] unitOfHeight = {"inches", "meters", "centimeters"};

	// Create Combo Boxes for Weight and Height
	private JComboBox jcboWeight = new JComboBox(unitOfWeight);
	private JComboBox jcboHeight = new JComboBox(unitOfHeight);

	public BMICalculator() {		

		// Panel p1 to hold labels and text fields
		JPanel p1 = new JPanel(new GridLayout(3,2,5,5));
		p1.add(new JLabel("   First Name: "));
		p1.add(jtfFirstName);
		p1.add(new JLabel("   Last Name: "));
		p1.add(jtfLastName);
		p1.add(new JLabel("   Age: "));
		p1.add(jtfAge);

		// Panel p2 to hold labels, text fields, and combo boxes
		JPanel p2 = new JPanel(new GridLayout(2,3,5,5));
		p2.add(new JLabel(" *Weight: "));
		p2.add(jtfWeight);
		p2.add(jcboWeight);
		p2.add(new JLabel(" *Height: "));
		p2.add(jtfHeight);
		p2.add(jcboHeight);
		p2.setBorder(new TitledBorder("* Required field."));

		// Panel p1 to hold labels and the button
		JPanel p3 = new JPanel(new GridLayout(3,2,5,5));
		p3.add(new JLabel());
		p3.add(jbtEnter);
		p3.add(new JLabel("    BMI: "));
		p3.add(jlBMI);
		p3.add(new JLabel("    Status: "));
		p3.add(jlStatus);

		// Set the layout and add panels to the frame
		setLayout(new GridLayout(3,1,5,5));
		add(p1);
		add(p2);
		add(p3);

		// Register listener
		jbtEnter.addActionListener(new ButtonListener());
	}

	/** Handle the Enter button */
	public class ButtonListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Check if the required fields are not both filled
			if (jtfWeight.getText().equals("") || jtfHeight.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Some required fields are not filled.", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				// Get values from text fields
				double usersWeight = Double.parseDouble(jtfWeight.getText());
				double usersHeight = Double.parseDouble(jtfHeight.getText());

				// Convert the weight from pounds to kilograms
				if (jcboWeight.getSelectedIndex() == 0)
					usersWeight *= 0.45359237;

				// Convert the height from inches to meters
				if (jcboHeight.getSelectedIndex() == 0)
					usersHeight *= 0.0254;
				// Convert the height from centimeters to meters
				if (jcboHeight.getSelectedIndex() == 2)
					usersHeight *= 0.01;
				
				// Get the BMI and the status
				double usersBMI = getBMI(usersWeight, usersHeight); 
				String usersStatus = getStatus(usersBMI);

				// Display the BMI and the Status
				jlBMI.setText(String.format("%.2f", usersBMI));
				jlStatus.setText(String.format("%s", usersStatus));	
			}
		}

		/** Return BMI */
		public double getBMI(double weight, double height) {
			double bmi = weight / (Math.pow(height, 2)); // BMI = weight(kg) / (height(m) ^ 2)
			return Math.round(bmi * 100) / 100.0;
		}

		/** Return Status */
		public String getStatus(double bmi) {
			if (bmi < 18.5)
				return "Underweight";
			else if (bmi < 25)
				return "Normal";
			else if (bmi < 30)
				return "Overweight";
			else
				return "Obese";
		}

	}

	/** Main method */
	public static void main(String[] args) {
		BMICalculator frame = new BMICalculator(); // Create the frame
		frame.setSize(300,300); // Set the frame size
		frame.setTitle("BMI Calculator"); // Set the frame title
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate when the frame is closed
		frame.setVisible(true); // Display the frame

	}
}