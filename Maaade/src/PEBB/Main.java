package PEBB;

import javax.swing.*;

public class Main extends JFrame{
	String[] auto = {
		    "Hyundai Solaris",
		    "Renault Logan",
		    "Skoda Octavia",
		    "Kia Soul",
		    "Ford Focus",
		    "Lada Vesta"
	};
	Main(){
		super("Калькулятор");
		JPanel panelMain = new JPanel();
		panelMain.setLayout(null);
		
		JLabel passenlab = new JLabel("Кол-во пассажиров, чел.");
		passenlab.setSize(150,25);
		passenlab.setLocation(10, 20);
		panelMain.add(passenlab);
		
		JLabel lugglab = new JLabel("Вес багажа, кг");
		lugglab.setSize(150,25);
		lugglab.setLocation(10, 50);
		panelMain.add(lugglab);
		
		JLabel distlab = new JLabel("Расстояние, км");
		distlab.setSize(150,25);
		distlab.setLocation(10, 80);
		panelMain.add(distlab);
		
		JTextField passenger = new JTextField();
		passenger.setSize(100,25);
		passenger.setLocation(160, 20);
		panelMain.add(passenger);
		
		JTextField luggage = new JTextField();
		luggage.setSize(100,25);
		luggage.setLocation(160, 50);
		panelMain.add(luggage);
		
		JTextField distance = new JTextField();
		distance.setSize(100,25);
		distance.setLocation(160, 80);
		panelMain.add(distance);
		
		JComboBox comboBox = new JComboBox(auto);
		comboBox.setSize(150,25);
		comboBox.setLocation(270,20);
		panelMain.add(comboBox);
		
		JRadioButton sezon = new JRadioButton("Зима");
		sezon.setSize(150,25);
		sezon.setLocation(10,110);
		panelMain.add(sezon);
		
		JButton calculate = new JButton("Рассчитать");
		calculate.setSize(110, 35); 
		calculate.setLocation(10,170); 

		panelMain.add(calculate);

		JButton useSaveData = new JButton("Использовать сохраненные данные");
		useSaveData.setSize(250, 35); 
		useSaveData.setLocation(125,170); 
		panelMain.add(useSaveData);
		
		setContentPane(panelMain); 
		setSize(450, 250); 
		setVisible(true); 
	}
	public static void main(String[] args) {
		new Main();
	}
}
