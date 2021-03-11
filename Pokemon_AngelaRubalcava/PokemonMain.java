package class0223;

public abstract class PokemonMain {
	//Hello Angela this is Jonathan!!
	private String name;
	private String color;
	
	public PokemonMain(String name, String color) {
		this.name = name;
		this.color = color;
		
	}
	
	public PokemonMain() {
		 this.name = "";
		 this.color = "";
		 
	  }
	
	 public String getName() {
		 return this.name;
	}
		  
	 public String getColor() {
		return this.color;
	}
	 
	 public void setName(String n) {
		 this.name = n;
	}
		  
	public void setColor(String c) {
		this.color = c;
	} 
	
	
	public abstract void speed();
	  
	public abstract void specialAttack();
	
	public String toString() {
	    return "I am a Pokemon: " + this.name + " : " + this.color + " : ";
	  }

}
