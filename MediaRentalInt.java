import java.util.ArrayList;

public interface MediaRentalInt {

	public void addCustomer(String name, String address, String plan, String rented, String cart, String mobile,
			String ID);

	public void addMovie(String code, String title, int no_copies, String rate);

	public void addGame(String code, String title, int no_copies, double weight);

	public void addAlbum(String code, String title, int no_copies, String artist, String songs);

	public void setLimitedPlanLimit(int value);

	public String getAllCustomersInfo();

	public String getAllMediaInfo();

	public boolean addToCart(String customerName, String mediaTitle);

	public boolean removeFromCart(String customerName, String mediaTitle);

	public String processRequests();

	public boolean returnMedia(String customerName, String mediaTitle);

	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs);
	
	public boolean deleteCustomer(String ID);

	public String searchCustomer(String ID);

	boolean deleteMedia(String ID);

	public String searchMedia(String ID);

	String code_name(String Code);

	String ID_name(String ID);

	boolean updateCustomer(String ID, String Name, String Plan, String Address, String Mobile);

	boolean updateMedia(String Code, String Title, int No_Copies, Double Weight, String Rating, String Artist,
			String Songs);
	
}
