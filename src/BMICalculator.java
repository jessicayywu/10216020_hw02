import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class BMICalculator extends JFrame {
	private JTextField jtfFirstName = new JTextField();
	private JTextField jtfLastName = new JTextField();
	private JTextField jtfAge = new JTextField();
	private JTextField jtfWeight = new JTextField();
	private JTextField jtfHeight = new JTextField();	
	
	private JButton jbtEnter = new JButton("Enter");

	private JLabel jlBMI = new JLabel("(BMI)");
	private JLabel jlStatus = new JLabel("(Status)");
	
	private String[] unitOfWeight = {"pounds", "kilograms"};
	private String[] unitOfWeight = {"inches", "meters"};
	
	private JComboBox jcboWeight = new JComboBox(unitOfWeight);
	private JComboBox jcboHeight = new JComboBox(unitOfWeight);
	
	public BMICalculator() {		

		
		JPanel p1 = new JPanel(new GridLayout(3,2,5,5));
		p1.add(new JLabel("   First Name: "));
		p1.add(jtfFirstName);
		p1.add(new JLabel("   Last Name: "));
		p1.add(jtfLastName);
		p1.add(new JLabel("   Age: "));
		p1.add(jtfAge);
		
		JPanel p2 = new JPanel(new GridLayout(2,3,5,5));
		p2.add(new JLabel(" *Weight: "));
		p2.add(jtfWeight);
		p2.add(jcboWeight);
		p2.add(new JLabel(" *Height: "));
		p2.add(jtfHeight);
		p2.add(jcboHeight);
		p2.setBorder(new TitledBorder("* Required field."));
		
		JPanel p3 = new JPanel(new GridLayout(3,2,5,5));
		p3.add(new JLabel());
		p3.add(jbtEnter);
		p3.add(new JLabel("    BMI: "));
		p3.add(jlBMI);
		p3.add(new JLabel("    Status: "));
		p3.add(jlStatus);
		
		setLayout(new GridLayout(3,1,5,5));
		add(p1);
		add(p2);
		add(p3);
		
		jbtEnter.addActionListener(new ButtonListener());
		jcboWeight.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				double usersWeight = Double.parseDouble(jtfWeight.getText());
				if (jcboWeight.getSelectedIndex() == 0)
					usersWeight *= 0.45359237;
			}
		});
		jcboHeight.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				double usersHeight = Double.parseDouble(jtfHeight.getText());
				if (jcboHeight.getSelectedIndex() == 0)
					usersHeight *= 0.0254;
			}	
		});
	}
	
	public class ButtonListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (jtfWeight.getText().equals("") || jtfHeight.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Some required fields are not filled.", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				double usersBMI = getBMI(usersWeight, usersHeight);
				String usersStatus = getStatus(usersBMI);
				
				jlBMI.setText(String.format("%.2f", usersBMI));
				jlStatus.setText(String.format("%s", usersStatus));	
			}
		}
		
		public double getBMI(double weight, double height) {
			double bmi = weight / (Math.pow(height, 2));
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
		frame.setSize(300,300);
		frame.setTitle("BMI Calculator");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
