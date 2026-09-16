package lab4part2;


/**
User class.
*/
public class User {
	String username;
	String name;
	String userType;

	/**
	Constructor for the class.
	*/
	public User(String un, String usT, String n) {

		username = un;
		name = n;
		userType = usT;
	}

	/**
	Returns username.
	*/
	public String getUsername() {
		return username;
	}
	
	/**
	Returns name.
	*/
	public String getName() {
		return name;
	}
	
	/**
	Returns user type.
	*/
	public String getUserType() {
		return userType;

	}
	
	/**
	sets user type.
	*/
	public void setUserType(String usT) {
		userType = usT;
        
	}
}
