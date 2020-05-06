package PEBB;

import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class Authorization extends JFrame{	
	public static String log, pass;
	Authorization(){
		super("Авторизация");
		JPanel panelAuthor = new JPanel();
		panelAuthor.setLayout(null);
		
		JLabel loginlab = new JLabel("Логин");
		loginlab.setSize(150,25);
		loginlab.setLocation(20, 30);
		panelAuthor.add(loginlab);
		
		JLabel passwordlab = new JLabel("Пароль");
		passwordlab.setSize(150,25);
		passwordlab.setLocation(20, 60);
		panelAuthor.add(passwordlab);
		
		JTextField login = new JTextField();
		login.setSize(150,25);
		login.setLocation(90, 30);
		panelAuthor.add(login);
		
		JTextField password = new JTextField();
		password.setSize(150,25);
		password.setLocation(90, 60);
		panelAuthor.add(password);
		
		JButton vhod = new JButton("Войти");
		vhod.setSize(160, 35); 
		vhod.setLocation(60,130); 
		vhod.addActionListener(new ActionListener(){ 
			@Override 
			public void actionPerformed(ActionEvent arg0){ 
				boolean proverka = false;
				log = login.getText();
				pass = password.getText();
				try {
					proverka = vhod();
				} catch (IOException e) {}
				if(proverka == true)
					dispose();
			} 
		}); 
		panelAuthor.add(vhod); 
	
		setContentPane(panelAuthor); 
		setSize(300, 250); 
		setVisible(true); 
	}
	public static boolean vhod() throws IOException { //Метод, проверяющий введенный логин и пароль
		boolean proverka = false;
		try(FileReader reader = new FileReader(log + ".txt")){
			Scanner scan = new Scanner(reader);
			String truepass = scan.nextLine();
            if(truepass.equals(pass)) {
            	proverka = true;
            	new Main();
            } else
            	JOptionPane.showMessageDialog(null, "Пароль введен неверно","Ошибка",JOptionPane.ERROR_MESSAGE);
			reader.close();
		} catch(IOException ex){  
			JOptionPane.showMessageDialog(null, "Пользователя с таким логином не существует","Ошибка",JOptionPane.ERROR_MESSAGE);
		} 
		return proverka;
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
