import java.util.*;

public class Customer extends MediaRental implements Comparable<Customer> {

	protected ArrayList<String> cart;
	protected ArrayList<String> rented;
	private String name;
	private String address;
	private String plan;
	private String ID;
	private String mobile;

	public Customer(String name, String address, String plan, String rented_temp, String cart_temp, String mobile, String ID) {
		this.name = name;
		this.address = address;
		this.plan = plan;
		this.ID = ID;
		this.mobile = mobile;
		rented = new ArrayList<String>();
		cart = new ArrayList<String>();
		
		if (rented_temp != null) {
			String[] split_1 = rented_temp.split(",");

			for (int i = 0; i < split_1.length; i++) {
				rented.add(split_1[i]);
			}
		}

		if (cart_temp != null) {
			String[] split_2 = cart_temp.split(",");
			for (int i = 0; i < split_2.length; i++) {
				cart.add(split_2[i]);
			}
		}
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String toString() {//\tName\t\t Address\t\tPlan\t\tRented\t\tCart
		return "\t" + name + "   -   " + address + "   -   " + plan + "   -   " + rented + "   -   " + cart + "  -  " + mobile + "  -  "  + ID;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.compareTo((Customer) o) == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Customer o) {
		if (this.getName().compareTo(o.getName()) > 0) {
			return 1;
		} else if (this.getName().compareTo(o.getName()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}
