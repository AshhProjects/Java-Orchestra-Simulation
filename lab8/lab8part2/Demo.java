package lab8part2;

import java.util.ArrayList;
import java.util.Collections;

public class Demo {
    /**
    Abstract classes can have abstract and non-abstract methods.
     Interfaces can only have abstract methods.

     Abstract classes can provide implementation of interfaces.
     Interfaces cannot provide implementation of abstract classes.

     abstract keyword is used to declare abstract classes.
     interface keyword is used to declare interface classes.

     abstract class can extend another Java class and implements multiple interfaces.
     Interfacs can only extend other interfacds.

     abstract classes uses keyword "extends".
     Interface uses keyword "implements".

     Members of interfaces are public by default.
     Abstract classes can have members like private, protected, etc.
    */

    public static void main(String[] args) throws Exception {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Cow("Cowy",5));
        animals.add(new Wolf("Wolfy",2));
        animals.add(new Parrot("Parroty",7));

        for(Animal myAnimal : animals) {
            System.out.println(myAnimal.getName());
        }

        Collections.sort(animals);

        for(Animal myAnimal : animals) {
            System.out.println(myAnimal.getName());
        }
    }
}
