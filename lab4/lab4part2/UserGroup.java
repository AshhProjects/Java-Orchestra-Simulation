package lab4part2;

import java.util.ArrayList;

public class UserGroup {

	/**
	Creates an ArrayList of type User, called users.
	*/
	ArrayList<User> users;
	
	/**
	Instantiate users with a new ArrayList of type User.
	*/
	public UserGroup() {
		users = new ArrayList<User>();
	}
	
	/**
	returns the arrayList users.
	*/
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	Creates sample data of random names and populates the group users with it.
	*/
	public void addSampleData() {
		users.add(new User("fj3", "user", "Kevin Rowe"));
		users.add(new User("amn", "user", "Jack Daniels"));
		users.add(new User("cm2", "user", "Barry Smith"));
		users.add(new User("pm5", "user", "Hugh Davies"));
		users.add(new User("joe", "user", "Pete Jackson"));
		users.add(new User("joe", "user", "Jerry Simpson"));
		users.add(new User("joe", "user", "Teresa Szelankovic"));
		users.add(new User("joe", "user", "Brian Degrasse Tyson"));
		users.add(new User("joe", "user", "Mike Hardcastle"));
		users.add(new User("joe", "user", "Danny Hanson"));
	}

	/**
	Returns user at the index the user inputs.
	*/
	public User getUser(Integer myInt) {
		return users.get(myInt);

	}

	/**
	Prints all usernames and their usertypes for everyone in the users group.
	*/
	public void printUsernames() {
		for (User myob : users) {
			System.out.println(myob.getUsername() + " " + myob.getUserType());

		}
	}
}
