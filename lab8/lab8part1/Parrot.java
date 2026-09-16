package lab8part1;

public class Parrot extends Omnivore {
	
	public Parrot(String myName, Integer myAge) {
		super(myName, myAge);
	}

	public Parrot(Integer myInt) {
		this("Polly", myInt);
	}

	public void makeNoise() {
		System.out.println("squaks");

	}
}
