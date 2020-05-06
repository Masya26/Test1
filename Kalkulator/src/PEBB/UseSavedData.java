package PEBB;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UseSavedData")
public class UseSavedData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int passen, k = 0, index;
	public static double lugg, dist;
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
    public UseSavedData() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
		
		double Smin, Smax, MinSum, MaxSum=0, k1, k2;
		
		FileReader reader = new FileReader(Check.login+ ".txt");
		Scanner scan = new Scanner(reader);
		k = Integer.parseInt(scan.nextLine());
		index = Integer.parseInt(scan.nextLine());
		passen = Integer.parseInt(scan.nextLine());
		lugg = Double.parseDouble(scan.nextLine());
		dist = Double.parseDouble(scan.nextLine());
		reader.close();
		
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
