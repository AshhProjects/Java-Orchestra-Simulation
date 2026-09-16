package lab8part1;

abstract class Carnivore extends Animal {

	public Carnivore(String myName, Integer myAge) {
		super(myName, myAge);
	}
	
	public Carnivore() {
		super();
	}


	public void eat(Food myFood) throws Exception {
		if (myFood instanceof Plant) {
			throw new Exception("Not a meat food item.");
		}
		System.out.println(getName() + " is eating " + myFood.getName());
	}
}
