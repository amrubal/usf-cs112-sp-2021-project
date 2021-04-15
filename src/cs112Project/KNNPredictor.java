package cs112Project;


import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;


public class KNNPredictor extends Predictor{
	
	private static int survivalIndex = 1;
	private static int ageIndex = 0;
	private static int fareIndex = 0;
	private int k;
	
	private static int survivedTest = 0;
	private static int deceasedTest = 1;
	
	private int numOfSurvived;
	private int numOfDeceased;
	
	private ArrayList <DataPoint> dataTemp = new ArrayList <DataPoint>();
	
	public KNNPredictor(int k) {
		
		this.k = k;
		
	}
	
	
	private List<String> getLine(String line){
		List <String> values = new ArrayList<String>();
		try(Scanner rowScanner = new Scanner(line)){
			rowScanner.useDelimiter(",");
			while(rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
		
	}

	@Override
	public ArrayList<DataPoint> readData(String file1) {
		Random random = new Random();
		Scanner scanner;
		
		ArrayList<DataPoint> temp = new ArrayList<DataPoint>();
		
		File file = new File(file1);
		
		try {
			 scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return null;
		}
		//Set to 0
		numOfSurvived = 0;
		numOfDeceased = 0;
		//line number 
		int lineN = 0;
		//records not needed
		int unusedRecords = 0; 
		
		scanner.nextLine(); //skip first line of titanic.csv
		
		while(scanner.hasNextLine()) { //checks to make sure there is more data
			lineN++;
			
		List<String> records = getLine(scanner.nextLine());
		
		if(records.size()<7) {	//checks for missing fields
			unusedRecords++;	
		}
		
		
		//Getting info from csv file in string form
		String sAge = records.get(ageIndex);
		String sFare = records.get(fareIndex);
		String sSurvival = records.get(survivalIndex);
		
		//data types for info we are getting from previous strings 
		double age = 0.0;
		double fare = 0.0;
		int survived = 0; //will be 0 or 1
		
		
		//parses through and ignored whenever info is missing 
		try {
			age = Double.parseDouble(sAge);
		}catch(NumberFormatException x) {
			//System.err.prinln("Wrong age: "+ sAge + "at index" + lineN);
			unusedRecords++;
		}
		
		try {
			fare = Double.parseDouble(sFare);
		}catch(NumberFormatException x) {
			//System.err.prinln("Wrong fare: "+ sFare + "at index" + lineN);
			unusedRecords++;
		}
		
		try {
			survived = Integer.parseInt(sSurvival);
		}catch(NumberFormatException x) {
			//System.err.prinln("Wrong survival data: "+ sSurvival + "at index" + lineN);
			unusedRecords++;
		}
		
		boolean isTest = true;
		if(random.nextDouble()<0.9) {
			isTest = false;
		}
		
		if(isTest == false) {
			if(survived == survivedTest) {
				numOfSurvived++;
			}
			else {
				numOfDeceased++;
			}
		}
		DataPoint dP1 = new DataPoint(age, fare, survived, isTest);
		temp.add(dP1);
			
		}
		
		scanner.close();
		
		System.out.println("The file contains: " + 
				"\nTotal records: " + lineN + 
				"\nTotal unused records "+ unusedRecords + 
				"\nTotal training: " + (numOfSurvived + numOfDeceased) +
				"\nTotal test: " + ((lineN - unusedRecords)- (numOfSurvived + numOfDeceased))+
				"\nTraining data has: " + 
				"\nPassengers who survived: " + numOfSurvived + 
				"\nPassengers who died: " + numOfDeceased);
		
		
				
		


		
		
		return temp;
	

}
	
	
	private double getDistance(DataPoint p1, DataPoint p2) {
		double dist = 0.0;
		double n = 0.0;
		double x = p1.getF1();
		double y = p1.getF2();
		double x1 = p2.getF1();
		double y1 = p2.getF2();
		
		n = Math.pow((x1-x1), 2) + Math.pow((y1-y), 2);
		dist = Math.sqrt(n);
		
		return dist;
		
		
	}
	@Override
	public String test(DataPoint data) {
		/*if(!data.getIsTest()) {
			return "Data is not a test point";
		}
		*/
		int trainingData = numOfSurvived + numOfDeceased;
		
		//2D array for distance
		
		Double [][] newTemp = new Double[trainingData][2];
		
		for(int r = 0; r<trainingData; r++) {
			DataPoint trainData = dataTemp.get(r);
			double dist = getDistance(data, trainData);
			double label = trainData.getLabel();
			newTemp[r][0] = dist;
			newTemp[r][1] = label;
		}
		
		//sorting code
		java.util.Arrays.sort(newTemp, new java.util.Comparator<Double[]>(){
			public int compare(Double [] a , Double [] b ) {
				return a[0].compareTo(b[0]);
			}
		});
		
		int tSurvived = 0;
		int tDeceased = 0;
		
		for(int r = 0; r <k; r++) {
			double sRecord = newTemp[r][1];
			if(sRecord == survivedTest) {
				tSurvived++;
			}
			else {
				tDeceased++;
			}
		}
		if(tSurvived>tDeceased) {
			return String.valueOf(survivedTest);
			
		}
		else {
			return String.valueOf(deceasedTest);
		}
		
		
	}
	
	

	@Override
	public Double getAccuracy(ArrayList<DataPoint> data) {
		double tPos = 0.0;
		double fPos = 0.0;
		double tNeg = 0.0;
		double fNeg = 0.0;
		double results = 0.0;
		double acc = 0.0;
		
		for(DataPoint point: data) {
			double newLabel = point.getLabel();
			
			dataTemp = data;
			double testLabel = Double.parseDouble(test(point));
			
			if(newLabel == 1.0) {
				if(testLabel == 1.0) {
					tPos++;
				}
				else {
					fPos++;
				}
			}
			else {
				if(testLabel == 1.0) {
					fNeg++;
				}
				else {
					tNeg++;
				}
			}
		}
			results = tNeg+ tPos + fNeg + fPos;
			
			if(results == 0) {
				System.out.println("Invalid num");
			}
			
			acc = (double)(tPos+tNeg)/ results;
			
			
			return acc;
			
			
		}
		
		
	

	@Override
	public Double getPrecision(ArrayList<DataPoint> data) {
		double tPos = 0.0;
		double fPos = 0.0;
		double tNeg = 0.0;
		double fNeg = 0.0;
		double dem = 0.0;
		double pre = 0.0;
		for(DataPoint point: data) {
			double newLabel = point.getLabel();
			
			dataTemp = data;
			double testLabel = Double.parseDouble(test(point));
			
			if(newLabel == 1.0) {
				if(testLabel == 1.0) {
					tPos++;
				}
				else {
					fPos++;
				}
			}
			else {
				if(testLabel == 1.0) {
					fNeg++;
				}
				else {
					tNeg++;
				}
			}
			
			
		}
		dem = tPos + fNeg;
		
		if(dem == 0) {
			System.out.println("Error division by zero");
		}
		pre = (double)(tPos)/ dem;
		
		return pre;
		
	}

}
