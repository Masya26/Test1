package PEBB;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;



@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int passen, k, index;
	public static double lugg, dist;
	public static boolean flag;
	public String[] autoArr = {
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

    public Main() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        
        double Smin, Smax, MinSum, MaxSum=0, k1, k2;
        int flag = 0, size = 0;
        String[] cc = {"1","2","3","4","5","6","7","8","9","0","."};
        
		String userPath = request.getServletPath();

			
		if(request.getParameter("passenger").equals("") || request.getParameter("distance").equals("") || request.getParameter("luggage").equals("")) 
			flag = 1;

		
		for(int i = 0; i < request.getParameter("passenger").length(); i++) {
    		for (int j = 0; j < 10; j++) {
	    		if(cc[j].equals(request.getParameter("passenger").substring(i, i+1))) {
	    			size++;
	    			break;
	    		}
    		}
    	}
    	if (size != request.getParameter("passenger").length() || ((Integer.parseInt(request.getParameter("passenger")) < 0) || (Integer.parseInt(request.getParameter("passenger")) > 4))) 
    		flag = 2;
    	
    	size = 0;
    	for(int i = 0; i < request.getParameter("luggage").length(); i++) {
    		for (int j = 0; j < 11; j++) {
	    		if(cc[j].equals(request.getParameter("luggage").substring(i, i+1))) {
	    			size++;
	    			break;
	    		}
    		}
    	}
    	if (size != request.getParameter("luggage").length()) 
    		flag = 3;

    	
    	size = 0;
    	for(int i = 0; i < request.getParameter("distance").length(); i++) {
    		for (int j = 0; j < 11; j++) {
	    		if(cc[j].equals(request.getParameter("distance").substring(i, i+1))) {
	    			size++;
	    			break;
	    		}
    		}
    	}
    	if (size != request.getParameter("distance").length())
    		flag = 4;

    	
    	switch(flag) {
    		case 1:
    			writer.println("All fields must be filled in."
						+ "<br>Go back and correct the data.");
    			break;
    		case 2: 
    			writer.println("The number of passengers must be an integer from 1 to 4."
						+ "<br>Go back and correct the data.");
    			break;
    		case 3:
    			writer.println("The luggage weight must be a number."
						+ "<br>Go back and correct the data.");
    			break;
    		case 4:
    			writer.println("The distance must be a number."
						+ "<br>Go back and correct the data.");
    			break;
    		default:	    			
    			passen = Integer.parseInt(request.getParameter("passenger"));
    	        dist = Double.parseDouble(request.getParameter("distance"));
    	        lugg = Double.parseDouble(request.getParameter("luggage"));
    	        String auto = request.getParameter("auto");
    	        for(int i = 0; i < 6; i++) {
    	        	if(autoArr[i].equals(auto)) 
    	        		index=i;
    	        }
    	        if("Winter".equals(request.getParameter("season"))) 
    	        	k = 1;
    	        else
    	        	k = 0;
    	            	        
    	        FileWriter sd = new FileWriter(Check.login + ".txt", false);
    			sd.write(k + "\n" + index + "\n" + passen + "\n" + lugg + "\n" + dist);
    			sd.close();
    	        
    	        lugg = lugg < 5 ? 0 : lugg;
    			k1 = k == 0 ? 0 : 0.14;
    			k2 = k == 0 ? 0 : 0.19;
    			Smin = 0.01 * data[index][0] * dist * (((passen + 1) * 62 + lugg) * i1 + k1 + 1);
    			Smax = 0.01 * data[index][0] * dist * (((passen + 1) * 62 + lugg) * i2 + k2 + 1);
    			MinSum = Smin * data[index][1];
    			MaxSum = Smax * data[index][1];
    			Smin = Math.round(Smin * 100.0) / 100.0;
    			Smax = Math.round(Smax * 100.0) / 100.0;
    			MinSum = Math.round(MinSum * 100.0) / 100.0;
    			MaxSum = Math.round(MaxSum * 100.0) / 100.0;
    			writer.println("For this trip you need to:"
    					+ "<br>-from " + Smin + " to " + Smax + " liters of gasoline"
    					+ "<br>-from " + MinSum + " to " + MaxSum + " rubles of money");
    	}  
		
	}
}



