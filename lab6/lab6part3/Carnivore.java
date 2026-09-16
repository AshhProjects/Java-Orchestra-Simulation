package lab6part3;

abstract class Carnivore extends Animal {

	public Carnivore(String myName, Integer myAge) {
		super(myName, myAge);
	}

	public void eat(Food myFood) throws Exception {
		if (myFood instanceof Plant) {
			throw new Exception("Not a meat food item.");
		}
		System.out.println(getName() + " is eating " + myFood.getName());
	}
}
