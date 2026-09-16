package lab6part3;
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

	public abstract void eat(Food myFood) throws Exception;

}
