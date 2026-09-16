package lab3part3;


public class SmartCard {
	String ownerName;
	Boolean staffStatus = false;

	public Boolean isStaff() {
		return staffStatus;
	}

	public SmartCard(String name) {
		ownerName = name;
	}

	public void setStaff(boolean staff) {
		staffStatus = staff;
	}
	
	public String getOwner() {
		return ownerName;
	}

}