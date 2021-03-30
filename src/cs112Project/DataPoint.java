package cs112Project;

public class DataPoint {
	private Double f1;
	private Double f2;
	private String label;
	private boolean isTest;
	public DataPoint(Double f1, Double f2, String label, boolean isTest) {
		this.f1 = f1;
		this.f2 = f2;
		this.isTest = isTest;
		setLabel(label);
	}
	public DataPoint() {
		this.f1 = 0.0;
		this.f2 = 0.0;
		this.isTest = false;
		this.label = null;
	}
	public Double getF1() {
		
		return this.f1;
	}
	public Double getF2() {
		
		return this.f2;
	}
	public String getLabel() {
		return this.label;
	}
	public boolean getIsTest() {
		return this.isTest;
	}

	public void setF1(Double f1) {
		if(f1<0) {
			return;
		}
		this.f1=f1;
	}
	public void setF2(Double f2) {
		if(f2<0) {
			return;
		}
		this.f2=f2;
	}
	public void setLabel(String label) {
		if(!(label.equals("Green")|| label.equals("Blue"))) {
			return;
		}
		this.label = label;
	}
	public void setIsTest(boolean isTest) {
		this.isTest = true;
	}
	public String toString() {
		return f1 + " " + f2 + " ";
	}
}
