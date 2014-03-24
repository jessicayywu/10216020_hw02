import java.awt.*;
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
		JPanel p1 = new JPanel(new GridLayout(5,2));
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
		
		setLayout(new GridLayout(3,1));
		add(p1);
		add(p2);
		add(p3);
	}
	
	public static void main(String[] args) {
		BMICalculator frame = new BMICalculator();
		frame.pack();
		frame.setTitle("BMI Calculator");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
