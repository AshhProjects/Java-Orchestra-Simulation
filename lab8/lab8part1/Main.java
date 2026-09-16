package lab8part1;

public class Main {

	public static void main(String[] args) throws Exception {
		Wolf myWolf = new Wolf("wolf Bones",2);
		Wolf babyWolf = new Wolf();
		Parrot myParrot = new Parrot("parrot Bones",4);
		Cow myCow = new Cow("cow Bones",3);


		Meat myMeat = new Meat("steak");
		Plant myPlant = new Plant("leaves");

		myWolf.eat(myMeat,3);

		System.out.println(babyWolf.getName());

	}
}
