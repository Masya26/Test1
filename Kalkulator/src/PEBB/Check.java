package PEBB;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String login, password;
    public Check() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        
        boolean check = false;
        login = request.getParameter("login");
        password = request.getParameter("password");
        
        FileReader fr= new FileReader("dataForAuthorization.txt");
        Scanner scan = new Scanner(fr);
        int i = 1;
        String truePass, trueLog;
        while (scan.hasNextLine()) {
            trueLog = scan.nextLine();
            if(trueLog.equals(login)) {
            	truePass = scan.nextLine();
            	if(truePass.equals(password))
            		check = true;
            }
            i++;
        }
        
        if(check) 
            request.getRequestDispatcher("/MainView.html").forward(request, response); 
        else
            writer.println("Incorrect username or password."
            			+ "<br>Go back and try again. ");

	}
}