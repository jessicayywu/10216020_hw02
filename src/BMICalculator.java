import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BMICalculator extends JFrame {
	private JTextField jtfFirstName = new JTextField();
	private JTextField jtfLastName = new JTextField();
	private JTextField jtfAge = new JTextField();
	private JTextField jtfWeight = new JTextField();
	private JTextField jtfHeight = new JTextField();	
	
	private JButton jbtEnter = new JButton("Enter");

	private JLabel jlBMI = new JLabel("(BMI)");
	private JLabel jlStatus = new JLabel("(Status)");
	
	public BMICalculator() {		
		JPanel p1 = new JPanel(new GridLayout(5,2,5,5));
		p1.add(new JLabel("FirstName: "));
		p1.add(jtfFirstName);
		p1.add(new JLabel("LastName: "));
		p1.add(jtfLastName);
		p1.add(new JLabel("Age: "));
		p1.add(jtfAge);
		p1.add(new JLabel("Weight(pounds): "));
		p1.add(jtfWeight);
		p1.add(new JLabel("Height(inches): "));
		p1.add(jtfHeight);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(jbtEnter);
		
		JPanel p3 = new JPanel(new GridLayout(2,2));
		p3.add(new Label("BMI: "));
		p3.add(jlBMI);
		p3.add(new Label("Status: "));
		p3.add(jlStatus);
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);
		
		jbtEnter.addActionListener(new ButtonListener());
	}
	
	public class ButtonListener implements ActionListener {
		public static final double KILOGRAMS_PER_POUND = 0.45359237;
		public static final double METERS_PER_INCH = 0.0254;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			double usersBMI = getBMI(Double.parseDouble(jtfWeight.getText()), Double.parseDouble(jtfHeight.getText()));
			String usersStatus = getStatus(usersBMI);
			
			jlBMI.setText(String.format("%.2f", usersBMI));
			jlStatus.setText(String.format("%s", usersStatus));			
		}
		
		public double getBMI(double weight, double height) {
			double bmi = weight * KILOGRAMS_PER_POUND / (Math.pow((height * METERS_PER_INCH), 2));
			return Math.round(bmi * 100) / 100.0;
		}
		
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
	
	public static void main(String[] args) {
		BMICalculator frame = new BMICalculator();
		frame.setSize(300,250);
		frame.setTitle("BMI Calculator");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
