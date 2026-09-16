package lab6part2;

public class Main {

	public static void main(String[] args) {
		Wolf myWolf = new Wolf("wolf Bones",2);
		Parrot myParrot = new Parrot("parrot Bones",4);
		System.out.println(myWolf.getName() + "'s age is " + myWolf.getAge());
		System.out.println(myParrot.getName() + "'s age is " + myParrot.getAge());

		Meat myMeat = new Meat("steak");
		Plant myPlant = new Plant("leaves");


	}
}