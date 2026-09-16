package lab4part3;


import java.util.Iterator;

public class Main {

	/**
	Main subroutine, creates a usergroup called myUserGroup which is populated by the sample.
	And creates another usergroup called adminsistrators.
	This contains only the users with usertype 'admin' from myUserGroup.
	*/
	public static void main(String[] args) {

		UserGroup myUserGroup = new UserGroup();
		myUserGroup.addSampleData();

		myUserGroup.printUsernames();

		System.out.println();
		UserGroup administrators = new UserGroup();

		Iterator<User> yo = myUserGroup.getUserIterator();

		while (yo.hasNext()) {
			User nice;
			nice = yo.next();
			if (nice.getUserType() == "admin") {
				administrators.getUsers().add(nice);
			}
		}

		administrators.printUsernames();


	}
}
