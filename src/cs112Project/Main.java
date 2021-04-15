package cs112Project;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.DecimalFormat;
public class Main {

	
	
	public static void main(String[] args) {
		
		System.out.println("Enter odd number:");
		Scanner userinput = new Scanner(System.in);
		int k = 0;
		boolean cont = true;
		try {
			
			k = userinput.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("Error");
			cont = false;
		}
		if (cont) {
			if (k%2 ==0) {
				System.out.print("Error");		
		}else {
			
			JFrame window = new JFrame();
			window.setSize(400,400);
			window.setTitle("KNN Results");
			
			
			Container contentPane = window.getContentPane();
			
			window.setLayout(new GridLayout(2,2));
			
			KNNPredictor temp = new KNNPredictor(3);
			
			ArrayList <DataPoint> list = temp.readData("titanic.csv");

			DecimalFormat percentage = new DecimalFormat("#%"); //display as percent
			
			
			
			JButton accButton = new JButton("Accuracy:" + (percentage.format(temp.getAccuracy(list))));
			JButton preButton = new JButton("Precision:" + (percentage.format(temp.getPrecision(list))));
			
			
			contentPane.add(accButton);
			contentPane.add(preButton);

			window.pack();
			window.setVisible(true);
			
			
			
		}
		}
		
		
		userinput.close();

	}
		
		
		
}