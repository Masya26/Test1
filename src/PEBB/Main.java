package PEBB;

import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class Main extends JFrame{ 
	public static int passen, k, index;
	public static double lugg, dist;
	public static boolean flag;
	String[] auto = {
		    "Hyundai Solaris",
		    "Renault Logan",
		    "Skoda Octavia",
		    "Kia Soul",
		    "Ford Focus",
		    "Lada Vesta"
	};
	public static double[][] data = {
		{6.4, 42.34},
		{6.6, 45.79},
		{5.2, 45.79},
		{8, 45.79},
		{5.5, 42.34},
		{7.2, 42.34}
	};
	public static double i1 = 0.0002, i2 = 0.0005;
	
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
		
		JCheckBox sezon = new JCheckBox("Зима");
		sezon.setSize(150,25);
		sezon.setLocation(10,110);
		panelMain.add(sezon);
		
		JButton calculate = new JButton("Рассчитать");
		calculate.setSize(110, 35); 
		calculate.setLocation(10,170); 
		calculate.addActionListener(new ActionListener(){ 
			@Override  
			public void actionPerformed(ActionEvent arg0){ 
				check(passenger.getText(), luggage.getText(), distance.getText());
				if (flag == true) {
					k = sezon.isSelected() ? 1 : 0;
					index = comboBox.getSelectedIndex();
					passen = Integer.parseInt(passenger.getText());
					lugg = Double.parseDouble(luggage.getText());
					dist = Double.parseDouble(distance.getText());
					try {
						saveData(); 
					} catch (Exception e) {}
					calc();
				}
				else
		    		System.out.println("3");
			} 
		}); 
		panelMain.add(calculate);

		JButton useSaveData = new JButton("Использовать сохраненные данные");
		useSaveData.setSize(250, 35); 
		useSaveData.setLocation(125,170); 
		useSaveData.addActionListener(new ActionListener(){ 
			@Override 
			public void actionPerformed(ActionEvent arg0){ 
					try {
						USD();
					} catch (Exception e) {}
					if (flag) {
					if(k == 1)
						sezon.setSelected(true);
					else
						sezon.setSelected(false);
					comboBox.setSelectedIndex(index);
					passenger.setText(Integer.toString(passen));
					luggage.setText(Double.toString(lugg));
					distance.setText(Double.toString(dist));
					}
			} 
		}); 
		panelMain.add(useSaveData);
		setContentPane(panelMain); 
		setSize(450, 250); 
		setVisible(true); 
	}
	public static void saveData() throws Exception{ 
		FileWriter writer = new FileWriter("saveData" + Authorization.log + ".txt", false);
		writer.write(k + "\n" + index + "\n" + passen + "\n" + lugg + "\n" + dist);
		writer.close();
	}
	public static boolean USD() throws Exception{ 
			try(FileReader reader = new FileReader("saveData" + Authorization.log + ".txt")){
				Scanner scan = new Scanner(reader);
				k = Integer.parseInt(scan.nextLine());
				index = Integer.parseInt(scan.nextLine());
				passen = Integer.parseInt(scan.nextLine());
				lugg = Double.parseDouble(scan.nextLine());
				dist = Double.parseDouble(scan.nextLine());
				reader.close();
				flag = true;
			} catch(IOException ex){  
				JOptionPane.showMessageDialog(null, "Сохраненные данные не найдены","Ошибка",JOptionPane.ERROR_MESSAGE);
				flag = false;
			}
			return flag;
	}
 	public static void calc() {
		double Smin, Smax, MinSum, MaxSum, k1, k2;
		lugg = lugg < 5 ? 0 : lugg;
		k1 = k == 0 ? 0 : 0.14;
		k2 = k == 0 ? 0 : 0.19;
		if (passen > 0 && passen < 5) {
			Smin = 0.01 * data[index][0] * dist * (((passen + 1) * 62 + lugg) * i1 + k1 + 1);
			Smax = 0.01 * data[index][0] * dist * (((passen + 1) * 62 + lugg) * i2 + k2 + 1);
			MinSum = Smin * data[index][1];
			MaxSum = Smax * data[index][1];
			Smin = Math.round(Smin * 100.0) / 100.0;
			Smax = Math.round(Smax * 100.0) / 100.0;
			MinSum = Math.round(MinSum * 100.0) / 100.0;
			MaxSum = Math.round(MaxSum * 100.0) / 100.0;
			JOptionPane.showMessageDialog(null, "Для данной поездки необходимо: \n"
												+ " - от " + Smin + " до " + Smax + " л. бензина;\n"
												+ " - от " + MinSum + " до " + MaxSum + " руб. денег."
												,"Результат",JOptionPane.INFORMATION_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Количество пассажиров должно быть от 1 до 4","Внимание!",JOptionPane.ERROR_MESSAGE);
	}
	public static void check(String pass, String lugg, String dist) {
		flag = true;
 		String[] cc = {"1","2","3","4","5","6","7","8","9","0","."};
    	int size = 0;
    	
    	if (pass.equals("") || lugg.equals("") || dist.equals("")) {
    		JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены!","Внимание!",JOptionPane.ERROR_MESSAGE);
    		flag = false;
    	}
  
    	
    	for(int i = 0; i < pass.length(); i++) {
    		for (int j = 0; j < 10; j++) {
	    		if(cc[j].equals(pass.substring(i, i+1))) {
	    			size++;
	    			break;
	    		}
    		}
    	}
    	if (size != pass.length()) {
    		JOptionPane.showMessageDialog(null, "Количество пассажиров должно быть целым числом!","Ошибка",JOptionPane.ERROR_MESSAGE);
    		flag = false;
    	}
    	
    	size = 0;
    	for(int i = 0; i < lugg.length(); i++) {
    		for (int j = 0; j < 11; j++) {
	    		if(cc[j].equals(lugg.substring(i, i+1))) {
	    			size++;
	    			break;
	    		}
    		}
    	}
    	if (size != lugg.length()) {
    		JOptionPane.showMessageDialog(null, "Вес багажа должен быть числом","Ошибка",JOptionPane.ERROR_MESSAGE);
    		flag = false;
    	}
    	
    	size = 0;
    	for(int i = 0; i < dist.length(); i++) {
    		for (int j = 0; j < 11; j++) {
	    		if(cc[j].equals(dist.substring(i, i+1))) {
	    			size++;
	    			break;
	    		}
    		}
    	}
    	if (size != dist.length()) {
    		JOptionPane.showMessageDialog(null, "Расстояние должно быть числом","Ошибка",JOptionPane.ERROR_MESSAGE);
    		flag = false;
    	}
	}
}
