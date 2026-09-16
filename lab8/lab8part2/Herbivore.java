package lab8part2;

abstract class Herbivore extends Animal {

	public Herbivore(String myName, Integer myAge) {
		super(myName, myAge);
	}


	public void eat(Food myFood) throws Exception {
		if (myFood instanceof Meat) {
			throw new Exception("Not a plant food item.");
		}

		System.out.println(getName() + " is eating " + myFood.getName());
	}
}
