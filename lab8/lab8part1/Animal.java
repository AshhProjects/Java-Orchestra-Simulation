package lab8part1;
abstract class Animal {

	private String name;
	private Integer age;

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
