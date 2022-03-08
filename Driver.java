import java.io.*;
import java.util.*;

public class Driver {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		MediaRental ex = new MediaRental();
		File customerDatabase = new File("src/Customers");
		File mediaDatabase = new File("src/Media_Database");
		BufferedReader customer_br = new BufferedReader(new FileReader(customerDatabase));
		BufferedReader media_br = new BufferedReader(new FileReader(mediaDatabase));
		String st_customer = "";
		while ((st_customer = customer_br.readLine()) != null) {
			String[] split = st_customer.split("-");
			for (int i = 0; i < split.length - 6; i++) {
				ex.addCustomer(split[i].replaceAll("\\s", ""), split[i + 1].replaceAll("\\s", ""),
						split[i + 2].replaceAll("\\s", ""),
						split[i + 3].replaceAll("\\s", "").replace("[", "").replace("]", ""),
						split[i + 4].replaceAll("\\s", "").replace("[", "").replace("]", ""),
						split[i + 5].replaceAll("\\s", "").replace("[", "").replace("]", ""),
						split[i + 6].replaceAll("\\s", "").replace("[", "").replace("]", ""));
			}
		}
		String st_media = "";
		while ((st_media = media_br.readLine()) != null) {
			String[] split = st_media.split("-");
			for (int i = 0; i < split.length - 3; i++) {
				if (isNumeric(split[i + 3])) {
					ex.addGame(split[i], split[i + 1], Integer.parseInt(split[i + 2]),
							Double.parseDouble(split[i + 3]));
				} else if (split[i + 3].equals("R") || split[i + 3].equals("PG-13") || split[i + 3].equals("21+")
						|| split[i + 3].equals("6+")) {
					ex.addMovie(split[i], split[i + 1], Integer.parseInt(split[i + 2]), split[i + 3]);
				}
			}
			for (int i = 0; i < split.length - 4; i++) {
				ex.addAlbum(split[i], split[i + 1], Integer.parseInt(split[i + 2]), split[i + 3],
						split[i + 4]);
			}
		}
		customer_br.close();
		media_br.close();
		System.out.println(
				"---------------Project Phase One-----------------Othman Hijawi ~ 1202927------------------Bizeit University");
		int flag = -1;
		while (flag != 420) {
			System.out.println(
					"Choose a Command: \n1. View Customer Database\n2. View Media Database\n3. Add Customer\n4. Add Media\n5. Add to Cart\n6. Remove From Cart\n7. Return Media\n8. Search Media\n9. Proceed to Checkout (Process Requests)\n10. Save Inventory(MUST EXECUTE!)\nPress any other key to Exit");
			int n = scan.nextInt();
			switch (n) {
			case 1:
				printCustomers(ex);
				break;
			case 2:
				printMedia(ex);
				break;
			case 3:
				testAddingCustomers(ex);
				break;
			case 4:
				testAddingMedia(ex);
				break;
			case 5:
				testingAddingToCart(ex);
				break;
			case 6:
				testingRemovingFromCart(ex);
				break;
			case 7:
				testReturnMedia(ex);
				break;
			case 8:
				testSearchMedia(ex);
				break;
			case 9:
				testProcessingRequestsOne(ex);
				testProcessingRequestsTwo(ex);
				break;
			case 10:
				SaveInventory(ex);
				break;
			default:
				flag = 420;
				break;
			}
		}
	}

	public static void printCustomers(MediaRental Obj) {
		System.out.println("Customer List: \n");
		System.out.println("\tName\t\t Address\t\tPlan\t\tRented\t\tCart");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.print(Obj.getAllCustomersInfo());
	}

	public static void printMedia(MediaRental Obj) {
		System.out.println("Media List: ");
		System.out.println(Obj.getAllMediaInfo());
	}

	public static boolean isNumeric(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void testAddingCustomers(MediaRental Obj) throws IOException {
		Writer append = new FileWriter("src/Customers", true);
		System.out.println("-------------------------------------------------------------------------");
		System.out.print("Enter Customer Name(Example -> OthmanHijawi): ");
		String name = scan.next();
		System.out.print("Enter Customer Address(Example -> NablusStreet): ");
		String addy = scan.next();
		System.out.print("Enter Plan(Example -> UNLIMITED): ");
		String plan = scan.next();
		Obj.addCustomer(name, addy, plan, null, null, null, null);
		append.append("\n" + name + "," + addy + "," + plan + "," + null + "," + null);
		append.close();
		System.out.println("Successfully added " + name + " to the Database! :)\n");
	}

	public static void testAddingMedia(MediaRental Obj) throws IOException {
		Writer append = new FileWriter("src/Media_Database", true);
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(
				"What type of media are you looking to add into the Database?\n1. Movies\n2. Games\n3. Music Albums\n----------------");
		int n = scan.nextInt();
		switch (n) {
		case 1:
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Enter the Movie Title(Example -> 21JumpStreet): ");
			String Title = scan.next();
			System.out.println("Enter Number of Copies in Stock: ");
			int no = scan.nextInt();
			System.out.println("Enter the Rating(Example -> PG_13, 21+): ");
			String rate = scan.next();
			//Obj.addMovie(Title, no, rate);
			append.append("\n" + Title + "-" + no + "-" + rate);
			append.close();
			System.out.println("Successfully added " + Title + " to the Database! :)\n");
			break;
		case 2:
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Enter the Game Title(Example -> RocketLeague): ");
			String title_g = scan.next();
			System.out.println("Enter Number of Copies in Stock: ");
			int no_g = scan.nextInt();
			System.out.println("Enter the Weight in Grams(Example -> 12.4): ");
			double weight = scan.nextDouble();
			//Obj.addGame(title_g, no_g, weight);
			append.append("\n" + title_g + "-" + no_g + "-" + weight);
			append.close();
			System.out.println("Successfully added " + title_g + " to the Database! :)\n");
			break;
		case 3:
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Enter the Album Title(Example -> AfterHours): ");
			String title_a = scan.next();
			System.out.println("Enter Number of Copies in Stock: ");
			int no_a = scan.nextInt();
			System.out.println("Enter the Artist name(Example -> TheWeeknd): ");
			String name = scan.next();
			System.out.println("Enter the Song list (Example -> AloneAgain,HardestToLove): ");
			String songs = scan.next();
			//Obj.addAlbum(title_a, no_a, name, songs);
			append.append("\n" + title_a + "-" + no_a + "-" + name + "-" + songs);
			append.close();
			System.out.println("Successfully added " + title_a + " by " + name + " to the Database! :)\n");
			break;
		}

	}

	public static void testingAddingToCart(MediaRental Obj) throws IOException {
		// Writer append = new FileWriter("src/Customer", true);
		try {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Enter your name and the media you want to add to your cart... ");
			System.out.print("Name: ");
			String name = scan.next();
			System.out.println("Media(Movie, Game, Album): ");
			String media = scan.next();
			Obj.addToCart(name, media);
			System.out.println("***************** Added to Cart ******************\n");

		} catch (Exception e) {
			System.out.println("Improper Input");
		}

		Obj.addToCart("Othman Ali", "AfterHours");
		Obj.addToCart("Othman Ali", "Relapse");
		Obj.addToCart("Othman Ali", "MasterofPuppets");
		Obj.addToCart("Othman Ali", "Blonde");
		Obj.addToCart("Marshall Mathers", "HouseofBalloons");
		Obj.addToCart("Ahmad Khalid", "Relapse");
		Obj.addToCart("Rana Hijawi", "Adele");
	}

	public static void testingRemovingFromCart(MediaRental Obj) {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Enter your name and the media you want to remove from your cart... ");
		System.out.print("Name: ");
		String name = scan.next();
		System.out.println("Media(Movie, Game, Album): ");
		String media = scan.next();
		Obj.removeFromCart(name, media);
		Obj.removeFromCart("Khalid", "The Interview");
		Obj.removeFromCart("Ahmad Khalid", "Relapse");
		if (Obj.removeFromCart(name, media))
			System.out.println("***************** Removed from Cart ******************\n");
		else
			System.out.println("There was a misinput, please try again.");
	}

	public static void testProcessingRequestsOne(MediaRental Obj) {
		System.out.print(Obj.processRequests());
	}

	public static void testProcessingRequestsTwo(MediaRental Obj) {
		System.out.print(Obj.processRequests());
	}

	public static void testReturnMedia(MediaRental Obj) {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Enter your name and the media you want to return from your rented list... ");
		System.out.print("Name: ");
		String name = scan.next();
		System.out.println("Media(Movie, Game, Album): ");
		String media_temp = scan.next();
		String media = media_temp;
		System.out.println(Obj.returnMedia(name, media));
	}

	public static void testSearchMedia(MediaRental Obj) {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Search Media: ");
		String input = scan.next();
		if(searchSong(Obj, input))
			System.out.println(Obj.searchMedia(null, null, null, input).toString());
		else if(searchArtist(Obj, input))
			System.out.println(Obj.searchMedia(null, null, input, null).toString());
		else if(searchRating(Obj, input))
			System.out.println(Obj.searchMedia(null, input, null, null).toString());
		else if(searchTitle(Obj, input))
			System.out.println(Obj.searchMedia(input, null, null, null).toString());

	}

	public static boolean searchSong(MediaRental Obj, String input) {
		try {
			Obj.searchMedia(null, null, null, input).toString();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean searchArtist(MediaRental Obj, String input) {
		try {
			Obj.searchMedia(null, null, input, null).toString();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean searchRating(MediaRental Obj, String input) {
		try {
			Obj.searchMedia(null, input, null, null).toString();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean searchTitle(MediaRental Obj, String input) {
		try {
			Obj.searchMedia(input, null, null, null).toString();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static void SaveInventory(MediaRental Obj) throws IOException {
		FileWriter writer = new FileWriter("src/Customers", false);
		writer.write(Obj.getAllCustomersInfo());
		writer.close();
	}
}
