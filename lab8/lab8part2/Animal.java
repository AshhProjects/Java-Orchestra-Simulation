package lab8part2;
abstract class Animal implements Comparable<Animal> {

	private String name;
	private Integer age;

	public int compareTo(Animal myAnimal) {

		if (this.getAge() == myAnimal.getAge()) {
			return 0;
		} else if (this.getAge() > myAnimal.getAge()) {
			return 1;
		} else {
			return -1;
		}
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;

	}

	public Animal(String myName, Integer myAge) {
		name = myName;
		age = myAge;
	}

	public Animal() {
		this("newborn", 0);
	}


	public abstract void makeNoise();

	public abstract void eat(Food myFood) throws Exception;

	public void eat(Food myFood, Integer amount) {
		
		for (int i = 0; i < amount; i++) {
			System.out.println(getName() + " is eating " + myFood.getName());

		}
	}

}