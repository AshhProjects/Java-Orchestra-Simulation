package lab3part3;

public class CardLock {
	SmartCard lastCard;

	Boolean allowStudents = false;
	
	public void swipeCard(SmartCard sc) {
		lastCard = sc;
	}

	public void toggleStudentAccess() {
		allowStudents = !allowStudents;
	}

	public SmartCard getLastCardSeen() {
		return lastCard;
	}
	
	/**Returns whether it's unlocked or locked.
	 * if staff, return unlocked (true).
	 * if allowStudents on, return unlocked (true).
	 * otherwise, return locked (false).
	 */
	public Boolean isUnlocked() {
		if (lastCard.isStaff()) {
			return true;
		} else if (allowStudents == true) {
			return true;
		} else {
			return false;
		}
	}
}