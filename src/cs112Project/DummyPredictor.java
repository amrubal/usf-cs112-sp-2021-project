package cs112Project;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DummyPredictor extends Predictor{

	@Override
	public ArrayList<DataPoint> readData(String filename) {
		ArrayList<DataPoint> list = new ArrayList<DataPoint>();
		try {
			Scanner s = new Scanner(new File("Filename"));
			
			while(s.hasNextLine()) {
				double f1 = s.nextDouble();
				double f2 = s.nextDouble();
				int label = s.nextInt();
				boolean isTest = s.nextBoolean();
				DataPoint dp = new DataPoint(f1,f2,label,isTest);
				list.add(dp);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public String test(DataPoint data) {
		
		return null;
	}

	@Override
	public Double getAccuracy(ArrayList<DataPoint> data) {
		
		return 0.0;
	}

	@Override
	public Double getPrecision(ArrayList<DataPoint> data) {
		
		return 0.0;
	}

}
