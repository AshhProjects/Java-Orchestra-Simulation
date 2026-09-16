package lab6part2;

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

	public abstract void makeNoise();

}
