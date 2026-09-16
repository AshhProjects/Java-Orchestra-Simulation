package lab4part3;


import java.util.ArrayList;
import java.util.Iterator;

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
		users.add(new User("fj3", "admin", "Kevin Rowe"));
		users.add(new User("amn", "admin", "Jack Daniels"));
		users.add(new User("cm2", "user", "Barry Smith"));
		users.add(new User("pm5", "user", "Hugh Davies"));
		users.add(new User("joe", "admin", "Pete Jackson"));
		users.add(new User("joejoe", "admin", "Jerry Simpson"));
		users.add(new User("joejoejoe", "admin", "Teresa Szelankovic"));
		users.add(new User("joeeee", "user", "Brian Degrasse Tyson"));
		users.add(new User("bob", "admin", "Mike Hardcastle"));
		users.add(new User("rat", "admin", "Danny Hanson"));
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
	
	
	/**
	removes first user.
	*/
	public void removeFirstUser() {
		users.remove(0);
	}
	
	/**
	Removes last user.
	*/
	public void removeLastUser() {
		users.remove(users.size() - 1);
	}
	
	/**
	Removes the user with the username that the user running the program inputs.
	*/
	public void removeUser(String nameInput) {

		Iterator<User> rem = users.iterator();

		while (rem.hasNext()) {
			User nice;
			nice = rem.next();
			if (nice.getUsername() == nameInput) {
				System.out.println("removing " + nice.getUsername());
				rem.remove();
			}
		}
	}

	/**
	Returns the iterator for users.
	*/
	public Iterator<User> getUserIterator() {
		Iterator<User> itr = users.iterator();
		return itr;

	}
}
