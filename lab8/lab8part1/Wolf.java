package lab8part1;

public class Wolf extends Carnivore {

	public Wolf(String myName, Integer myAge) {
		super(myName, myAge);
	}
	
	public Wolf() {
		super();
	}


	public void makeNoise() {
		System.out.println("Woof");

	}
}
