package lab6part3;

public class Main {

	public static void main(String[] args) throws Exception {
		Wolf myWolf = new Wolf("wolf Bones",2);
		Parrot myParrot = new Parrot("parrot Bones",4);
		Cow myCow = new Cow("cow Bones",3);
		System.out.println(myWolf.getName() + "'s age is " + myWolf.getAge());
		System.out.println(myParrot.getName() + "'s age is " + myParrot.getAge());
		System.out.println(myCow.getName() + "'s age is " + myCow.getAge());


		Meat myMeat = new Meat("steak");
		Plant myPlant = new Plant("leaves");

		myWolf.eat(myMeat);
		myParrot.eat(myPlant);
		myCow.eat(myPlant);

		try {
			myWolf.eat(myPlant);
			myCow.eat(myMeat);
		}
		catch (Exception e) {
			System.err.print(e.getMessage());
		}

	}
}
