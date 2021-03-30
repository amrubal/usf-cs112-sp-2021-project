package cs112Project;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Driver {

public static void main(String [] args) {
	SwingUtilities.invokeLater(
	          new Runnable() { public void run() { initAndShowGUI(); } }
	        );
	
	ArrayList<DataPoint> temp = new ArrayList<DataPoint>();
	DataPoint d = new DataPoint();
	DummyPredictor dpTest = new DummyPredictor();
	
	System.out.println(dpTest.readData("Filename"));
	System.out.println(dpTest.test(d));
	System.out.println(dpTest.getAccuracy(temp));
	System.out.println(dpTest.getPrecision(temp));
}
private static void initAndShowGUI() {
	
	JFrame myFrame = new JFrame("Project One Window"); 

	
	Container contentPane = myFrame.getContentPane();
	contentPane.setLayout(new GridLayout(2,2));
	
	
	

	
	
	ArrayList<DataPoint> temp = new ArrayList<DataPoint>();
	DataPoint d = new DataPoint();
	DummyPredictor dpTest = new DummyPredictor();
	
	
	
	String gPLabel = Double.toString(dpTest.getPrecision(temp));
	String gALabel =  Double.toString(dpTest.getAccuracy(temp));
	
	JLabel presLabel = new JLabel(gPLabel);
	JLabel accLabel = new JLabel(gALabel);
	
	contentPane.add(presLabel);
	contentPane.add(accLabel);
	
	
	


	
	myFrame.pack();
	myFrame.setVisible(true);
	
	
    }


    
}
