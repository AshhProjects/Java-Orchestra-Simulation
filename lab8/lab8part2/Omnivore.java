package lab8part2;

abstract class Omnivore extends Animal {

	public Omnivore(String myName, Integer myAge) {
		super(myName, myAge);
	}


	public void eat(Food myFood) {
		System.out.println(getName() + " is eating " + myFood.getName());
	}
}
