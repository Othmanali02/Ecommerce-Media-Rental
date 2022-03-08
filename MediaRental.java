import java.util.*;

public class MediaRental implements MediaRentalInt {

	protected List<Customer> Customer_List = new ArrayList<Customer>();
	protected List<Media> Media_List = new ArrayList<Media>();
	private int LIMITED_VAL;

	public MediaRental() {
		this.Customer_List = new ArrayList<Customer>();
		this.Media_List = new ArrayList<Media>();
		this.LIMITED_VAL = 2;
	}

	@Override
	public void addCustomer(String name, String address, String plan, String rented_temp, String cart_temp,
			String mobile, String ID) {
		Customer_List.add(new Customer(name, address, plan, rented_temp, cart_temp, mobile, ID));
	}

	@Override
	public void addMovie(String code, String title, int no_copies, String rate) {
		Media_List.add(new Movie(code, title, no_copies, rate));
	}

	@Override
	public void addGame(String code, String title, int no_copies, double weight) {
		Media_List.add(new Game(code, title, no_copies, weight));
	}

	@Override
	public void addAlbum(String code, String title, int no_copies, String artist, String songs) {
		Media_List.add(new Album(code, title, no_copies, artist, songs));
	}

	@Override
	public void setLimitedPlanLimit(int value) {
		this.LIMITED_VAL = value;
	}
	
	
	@Override
	public boolean deleteMedia(String ID) {
		for (int i = 0; i < Media_List.size(); i++) {
			if (Media_List.get(i).getCode().equals(ID)) {
				Media_List.remove(i);
				return true;
			} 
		}
		return false;
	}

	@Override
	
	public String searchMedia(String ID) {
		for (int i = 0; i < Media_List.size(); i++) {
			if (Media_List.get(i).getCode().equals(ID)) {
				return Media_List.get(i).toString();
			}
		}
		return "Media not Found";
	}
	
	@Override
	public boolean deleteCustomer(String ID) {
		for (int i = 0; i < Customer_List.size(); i++) {
			if (Customer_List.get(i).getID().equals(ID)) {
				Customer_List.remove(i);
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean updateCustomer(String ID, String Name, String Plan, String Address, String Mobile) {

		for (int i = 0; i < Customer_List.size(); i++) {
			if (Customer_List.get(i).getID().equals(ID)) {
				Customer_List.remove(i);
				Customer_List.add(new Customer(Name, Address, Plan, null, null, Mobile, ID));
				return true;
			}
		}
		return false;

	}
	
	@Override
	public boolean updateMedia(String Code, String Title, int No_Copies, Double Weight, String Rating, String Artist, String Songs) {

		for (int i = 0; i < Media_List.size(); i++) {
			if (Media_List.get(i).getCode().equals(Code)) {
				Media_List.remove(i);
				if(Artist == null && Songs == null && Rating == null) {
					Media_List.add(new Game(Code, Title, No_Copies, Weight));
					return true;
				}
				if(Artist == null && Songs == null && Weight == null) {
					Media_List.add(new Movie(Code, Title, No_Copies, Rating));
					return true;
				}
				if(Rating == null && Weight == null) {
					Media_List.add(new Album(Code, Title, No_Copies, Artist, Songs));
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public String searchCustomer(String ID) {

		for (int i = 0; i < Customer_List.size(); i++) {
			if (Customer_List.get(i).getID().equals(ID)) {
				return Customer_List.get(i).toString();
			}
		}
		return "Customer not Found";
	}

	@Override
	public String getAllCustomersInfo() {
		Collections.sort(Customer_List);
		String customerInfo = "";
		for (int i = 0; i < Customer_List.size(); i++) {
			customerInfo += Customer_List.get(i).toString() + "\n";
		}
		return customerInfo;
	}

	@Override
	public String getAllMediaInfo() {
		Collections.sort(Media_List);
		String MediaInfo = "";
		for (int i = 0; i < Media_List.size(); i++) {
			MediaInfo += Media_List.get(i).toString() + "\n";
		}
		return MediaInfo;
	}
	
	
	@Override
	public String code_name(String Code) {
		for (int i = 0; i < Media_List.size(); i++) {
			if (Media_List.get(i).getCode().equals(Code)) {
				return Media_List.get(i).getTitle();
			}
		}
		return "Title Not Found";
	}
	
	@Override
	public String ID_name (String ID) {
		for (int i = 0; i < Customer_List.size(); i++) {
			if (Customer_List.get(i).getID().equals(ID)) {
				return Customer_List.get(i).getName();
			}
		}
		return "Customer Not Found";
	}
	

	@Override
	public boolean addToCart(String customerName, String mediaTitle) {

		int FLAG = -1;// -1 because 0 can be a Customer who already has this media
		for (int i = 0; i < Customer_List.size(); i++) {
			if (Customer_List.get(i).getName().equals(customerName)) {// FLAG equals the index of the customer
				FLAG = i;
			}
		}
		if (FLAG == -1) {
			return false;// return false if the customer isn't found
		}
		for (int i = 0; i < Customer_List.get(FLAG).cart.size(); i++) {// iterating on the cart of the customer
			if (Customer_List.get(FLAG).cart.get(i).equals(mediaTitle)) {
				return false;// if the media already exists in the cart
			}
		}

		Customer_List.get(FLAG).cart.add(mediaTitle);// adds the media to the customers' cart list
		return true;
	}

	@Override
	public boolean removeFromCart(String customerName, String mediaTitle) {

		for (int i = 0; i < Customer_List.size(); i++) {
			if (Customer_List.get(i).getName().equals(customerName)) {// locating customer
				Customer_List.get(i).cart.remove(mediaTitle);// remove the media with the given title
				return true;
			}
		}

		return false;
	}

	@Override
	public String processRequests() {

		Collections.sort(Customer_List);
		String Result = "";
		int i = 0;
		while (i < Customer_List.size()) {
			Customer custom_ex = Customer_List.get(i);// gathering all the indexes of customer list
			int x = 0;// iterating through every media instance found in the cart

			if (custom_ex.getPlan().equals("LIMITED")) {// for those with limited plans
				for (int j = 0; j < custom_ex.cart.size(); j++) {// iterating through the customers cart
					for (int v = 0; v < Media_List.size(); v++) {// going through the media of the customer
						Media media_ex = Media_List.get(v);

						if (media_ex.getTitle().equals(custom_ex.cart.get(x))) {// if the title is found in the cart
							if (media_ex.getNo_copies() > 0 && custom_ex.rented.size() <= LIMITED_VAL) {// if the media
																										// is available
																										// and the limit
																										// hasn't been
																										// exceeded

								custom_ex.cart.remove(x);
								custom_ex.rented.add(media_ex.getTitle());

								media_ex.NumberOfCopies(false);

								Result += media_ex.getTitle() + " -> " + custom_ex.getName() + "\n";
								break;

							} else {
								x++;
							}
						}
					}
				}
			} else if (custom_ex.getPlan().equals("UNLIMITED")) {
				for (int j = 0; j < custom_ex.cart.size(); j++) {// iterating through the customers cart
					for (int v = 0; v < Media_List.size(); v++) {// going through the media of the customer
						Media media_ex = Media_List.get(v);

						if (media_ex.getTitle().equals(custom_ex.cart.get(x))) {// if the title is found in the cart
							if (media_ex.getNo_copies() > 0) {// if the media
																// is available
																// and the limit
																// hasn't been
																// exceeded
								custom_ex.cart.remove(x);
								custom_ex.rented.add(media_ex.getTitle());

								media_ex.NumberOfCopies(false);

								Result += media_ex.getTitle() + " -> " + custom_ex.getName() + "\n";
								break;

							} else {
								x++;
							}
						}
					}
				}
			}
			i++;
		}

		return Result;
	}

	@Override
	public boolean returnMedia(String customerName, String mediaTitle) {
		// LOCATING THE CUSTOMER, SAME AS PROCESS REQUESTS
		int FLAG = -1;
		for (int i = 0; i < Customer_List.size(); i++) {
			if (Customer_List.get(i).getName().equals(customerName)) {
				FLAG = i;// locates customer by index
			}
		}
		if (FLAG == -1) {
			return false;// returns false if the customer isn't found
		}
		// FLAG EQUALS CUSTOMER INDEX IF != -1
		Customer custom_ex = Customer_List.get(FLAG);
		for (int i = 0; i < custom_ex.rented.size(); i++) {// iterating through the customers rented list
			if (custom_ex.rented.get(i).equals(mediaTitle)) {// if the media is found
				custom_ex.rented.remove(mediaTitle);// removing the media from the customer's rented list
				for (int j = 0; j < Media_List.size(); j++) {
					if (Media_List.get(j).getTitle().equals(mediaTitle)) {
						Media_List.get(j).NumberOfCopies(true);// adding the media to the list because it has been
																// returned
					}
				}
				return true;// media removed
			}
		}
		return false;// wrong input
	}

	@Override
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {

		ArrayList<String> result = new ArrayList<String>();
		boolean t_Condition = false, r_Condition = false, a_Condition = false, s_Condition = false;

		for (Media media_ex : Media_List) {// for each media in the media database

			if (title == null)
				t_Condition = false;
			else if (title.equals(media_ex.getTitle()))
				t_Condition = true;
			else
				t_Condition = false;

			if (media_ex instanceof Album) {
				Album album = (Album) media_ex;

				if (artist == null)
					a_Condition = false;
				else if (album.getArtist().equals(artist))
					a_Condition = true;
				else
					a_Condition = false;

				if (songs == null)
					s_Condition = false;
				else if (album.getSongs().indexOf(songs) != -1)
					s_Condition = true;
				else
					s_Condition = false;

				r_Condition = false;
			} else if (media_ex instanceof Movie) {
				Movie movie = (Movie) media_ex;

				if (rating == null)
					r_Condition = false;
				else if (movie.getRate().equals(rating))
					r_Condition = true;
				else
					r_Condition = false;

				a_Condition = false;
				s_Condition = false;
			} // since the only condition for a game is the title, there is no need to compare
				// the rest of the parameters
			if (t_Condition || r_Condition || a_Condition || s_Condition) {// if the search matches
				result.add(media_ex.getTitle());// add the title to the sorted list
			}

		}
		Collections.sort(result);
		return result;
	}

}