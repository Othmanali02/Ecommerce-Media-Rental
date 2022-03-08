
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MediaRental instance = new MediaRental();
			File customerDatabase = new File("src/Customers");
			File mediaDatabase = new File("src/Media_Database");
			BufferedReader customer_br = new BufferedReader(new FileReader(customerDatabase));
			BufferedReader media_br = new BufferedReader(new FileReader(mediaDatabase));
			String st_customer = "";
			while ((st_customer = customer_br.readLine()) != null) {//READING FROM FILES
				String[] split = st_customer.split("-");
				for (int i = 0; i < split.length - 6; i++) {
					instance.addCustomer(split[i].replaceAll("\\s", ""), split[i + 1].replaceAll("\\s", ""),
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
						instance.addGame(split[i], split[i + 1], Integer.parseInt(split[i + 2]),
								Double.parseDouble(split[i + 3]));
					} else if (split[i + 3].equals("R") || split[i + 3].equals("PG-13") || split[i + 3].equals("21+")
							|| split[i + 3].equals("6+")) {
						instance.addMovie(split[i], split[i + 1], Integer.parseInt(split[i + 2]), split[i + 3]);
					}
				}
				for (int i = 0; i < split.length - 4; i++) {
					instance.addAlbum(split[i], split[i + 1], Integer.parseInt(split[i + 2]), split[i + 3],
							split[i + 4]);
				}
			}
			customer_br.close();
			media_br.close();
			Pane root = new Pane();// main introduction
			Pane root1 = new Pane();// customer
			Pane root2 = new Pane();// media
			Pane root3 = new Pane();// rent

			Image img = new Image("javafx129.jpg", 1280, 720, false, false);//setting resolution
			Image img2 = new Image("main2.jpg", 1280, 720, false, false);
			Image img3 = new Image("main3.jpg", 1280, 720, false, false);
			Image img4 = new Image("main4.jpg", 1280, 720, false, false);

			Button mediabutt = new Button();//designing the buttons
			mediabutt.setTranslateX(272);
			mediabutt.setTranslateY(400);
			mediabutt.setText("Media");
			mediabutt.setStyle("-fx-background-color: #494848;");
			mediabutt.setFont(Font.loadFont("file:resources/fonts/MomcakeBold-WyonA.otf", 27));
			mediabutt.setTextFill(Color.WHITE);

			Button customerbutt = new Button();
			customerbutt.setTranslateX(250);
			customerbutt.setTranslateY(340);
			customerbutt.setText("Customer");
			customerbutt.setStyle("-fx-background-color: #494848;");
			customerbutt.setFont(Font.loadFont("file:resources/fonts/MomcakeBold-WyonA.otf", 27));
			customerbutt.setTextFill(Color.WHITE);

			Button rentbutt = new Button();
			rentbutt.setTranslateX(278);
			rentbutt.setTranslateY(470);
			rentbutt.setText("Rent");
			rentbutt.setStyle("-fx-background-color: #494848;");
			rentbutt.setFont(Font.loadFont("file:resources/fonts/MomcakeBold-WyonA.otf", 27));
			rentbutt.setTextFill(Color.WHITE);

			Button inventory = new Button("Save Inventory");
			inventory.setTranslateX(220);
			inventory.setTranslateY(532);
			inventory.setStyle("-fx-background-color: #494848;");
			inventory.setFont(Font.loadFont("file:resources/fonts/MomcakeBold-WyonA.otf", 27));
			inventory.setTextFill(Color.WHITE);
			root.getChildren().add(inventory);

			inventory.setOnAction(new EventHandler<ActionEvent>() {//saving all the customer database
				@Override
				public void handle(ActionEvent arg0) {
					try {
						FileWriter writer = new FileWriter("src/Customers", false);
						writer.write(instance.getAllCustomersInfo());
						writer.close();
					} catch (Exception e) {

					}
				}
			});

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,//Setting the background for every scene
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);// main screen

			BackgroundImage bImg2 = new BackgroundImage(img2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround2 = new Background(bImg2);
			root1.setBackground(bGround2);// customer

			BackgroundImage bImg3 = new BackgroundImage(img3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround3 = new Background(bImg3);
			root2.setBackground(bGround3);// media

			BackgroundImage bImg4 = new BackgroundImage(img4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround4 = new Background(bImg4);
			root3.setBackground(bGround4);// rent

			root.getChildren().add(mediabutt);
			root.getChildren().add(customerbutt);
			root.getChildren().add(rentbutt);

			/*
			 * Customer Stuff Add new Customer Delete Customer Update Information about
			 * Customer Search a Customer by id Return to main page
			 */
			Button AddCustomer = new Button();
			AddCustomer.setTranslateX(162);
			AddCustomer.setTranslateY(139);
			AddCustomer.setText("Add new Customer");
			AddCustomer.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			AddCustomer.setTextFill(Color.BLACK);
			root1.getChildren().add(AddCustomer);

			Button DeleteCustomer = new Button();
			DeleteCustomer.setTranslateX(152);
			DeleteCustomer.setTranslateY(220);
			DeleteCustomer.setText("Delete Customer");
			DeleteCustomer.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			DeleteCustomer.setTextFill(Color.BLACK);
			root1.getChildren().add(DeleteCustomer);

			Button UpdateCustomer = new Button();
			UpdateCustomer.setTranslateX(135);
			UpdateCustomer.setTranslateY(303);
			UpdateCustomer.setText("Update Customer information");
			UpdateCustomer.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			UpdateCustomer.setTextFill(Color.BLACK);
			root1.getChildren().add(UpdateCustomer);

			Button searchCustomer = new Button();
			searchCustomer.setTranslateX(162);
			searchCustomer.setTranslateY(386);
			searchCustomer.setText("Search Customer");
			searchCustomer.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			searchCustomer.setTextFill(Color.BLACK);
			root1.getChildren().add(searchCustomer);

			Button back_1 = new Button();
			back_1.setTranslateX(162);
			back_1.setTranslateY(469);
			back_1.setText("Return to Main");
			back_1.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			back_1.setTextFill(Color.BLACK);
			root1.getChildren().add(back_1);

			/* Media Stuff */
			Button AddMedia = new Button();
			AddMedia.setTranslateX(162);
			AddMedia.setTranslateY(139);
			AddMedia.setText("Add new Media");
			AddMedia.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			AddMedia.setTextFill(Color.BLACK);
			root2.getChildren().add(AddMedia);

			Button DeletMedia = new Button();
			DeletMedia.setTranslateX(152);
			DeletMedia.setTranslateY(220);
			DeletMedia.setText("Delete Media");
			DeletMedia.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			DeletMedia.setTextFill(Color.BLACK);
			root2.getChildren().add(DeletMedia);

			Button UpdateMedia = new Button();
			UpdateMedia.setTranslateX(135);
			UpdateMedia.setTranslateY(303);
			UpdateMedia.setText("Update Media information");
			UpdateMedia.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			UpdateMedia.setTextFill(Color.BLACK);
			root2.getChildren().add(UpdateMedia);

			Button searchMedia = new Button();
			searchMedia.setTranslateX(162);
			searchMedia.setTranslateY(386);
			searchMedia.setText("Search Media");
			searchMedia.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			searchMedia.setTextFill(Color.BLACK);
			root2.getChildren().add(searchMedia);

			Button PrintMedia = new Button();
			PrintMedia.setTranslateX(162);
			PrintMedia.setTranslateY(469);
			PrintMedia.setText("Print all Media");
			PrintMedia.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			PrintMedia.setTextFill(Color.BLACK);
			root2.getChildren().add(PrintMedia);

			Button back_2 = new Button();
			back_2.setTranslateX(162);
			back_2.setTranslateY(559);
			back_2.setText("Return to Main");
			back_2.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			back_2.setTextFill(Color.BLACK);
			root2.getChildren().add(back_2);

			// Rent Stuff
			TextField Idinput = new TextField("Enter Customer ID");
			Idinput.setTranslateX(300);
			Idinput.setTranslateY(284);
			Text CustData = new Text();

			Button Sub = new Button("Submit");
			Sub.setTranslateX(470);
			Sub.setTranslateY(284);
			CustData.setTranslateX(-10);
			CustData.setTranslateY(352);
			CustData.setFill(Color.WHITE);
			CustData.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));

			Sub.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					CustData.setText(instance.searchCustomer(Idinput.getText()));
				}
			});

			TextField Codeinput = new TextField("Enter Media Code");
			Codeinput.setTranslateX(300);
			Codeinput.setTranslateY(394);
			Text MediaData = new Text();

			Button Subm = new Button("Submit");
			Subm.setTranslateX(470);
			Subm.setTranslateY(394);
			MediaData.setTranslateX(0);
			MediaData.setTranslateY(462);
			MediaData.setFill(Color.WHITE);
			MediaData.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));

			Subm.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					MediaData.setText(instance.searchMedia(Codeinput.getText()));
				}
			});
			
			
			Button PlayboiCarti = new Button("Add to Cart");
			PlayboiCarti.setTranslateX(50);
			PlayboiCarti.setTranslateY(600);
			PlayboiCarti.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			PlayboiCarti.setTextFill(Color.BLACK);

			PlayboiCarti.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					System.out.println(instance.addToCart(instance.ID_name(Idinput.getText()),
							instance.code_name(Codeinput.getText())));
				}
			});

			
			Button Checkout = new Button("Proceed to Checkout");
			Checkout.setTranslateX(200);
			Checkout.setTranslateY(600);
			Checkout.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			Checkout.setTextFill(Color.BLACK);

			Checkout.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					System.out.println(instance.processRequests());
					System.out.println(instance.processRequests());
				}
			});

			root3.getChildren().add(Codeinput);
			root3.getChildren().add(MediaData);
			root3.getChildren().add(Subm);
			root3.getChildren().add(Idinput);
			root3.getChildren().add(CustData);
			root3.getChildren().add(Sub);
			root3.getChildren().add(Checkout);
			root3.getChildren().add(PlayboiCarti);

			Text curDate = new Text();
			Date date = new Date();
			curDate.setText(date.toString());
			curDate.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));
			curDate.setTranslateX(255);
			curDate.setTranslateY(522);
			curDate.setFill(Color.WHITE);

			root3.getChildren().add(curDate);
			Button back_3 = new Button();
			back_3.setTranslateX(500);
			back_3.setTranslateY(600);
			back_3.setText("Back");
			back_3.setStyle("-fx-background-color: #44CCC4; -fx-font-size:17");
			back_3.setTextFill(Color.BLACK);
			root3.getChildren().add(back_3);

			Scene scene = new Scene(root, 1280, 720);

			Scene CustomerScene = new Scene(root1, 1280, 720);

			Scene MediaScene = new Scene(root2, 1280, 720);
			
			Scene RentScene = new Scene(root3, 1280, 720);

			customerbutt.setOnAction(e -> primaryStage.setScene(CustomerScene));

			mediabutt.setOnAction(e -> primaryStage.setScene(MediaScene));

			rentbutt.setOnAction(e -> primaryStage.setScene(RentScene));

			back_1.setOnAction(e -> primaryStage.setScene(scene));

			back_2.setOnAction(e -> primaryStage.setScene(scene));

			back_3.setOnAction(e -> primaryStage.setScene(scene));
			// customer
			AddCustomer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);
					
					GridPane dialogVbox = new GridPane();					
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label enterID = new Label("Customer ID:");
					Label enterName = new Label("Customer Name:");
					Label enterAddress = new Label("Customer Address:");
					Label enterMobile = new Label("Customer Mobile:");
					Label enterPlan = new Label("Customer Plan:");
					
					TextField NameInput = new TextField();
					TextField IDInput = new TextField();
					TextField AddressInput = new TextField();
					TextField MobileInput = new TextField();
					TextField PlanInput = new TextField();
					
					NameInput.setPrefColumnCount(14);
					IDInput.setPrefColumnCount(14);
					AddressInput.setPrefColumnCount(14);
					MobileInput.setPrefColumnCount(14);
					PlanInput.setPrefColumnCount(14);

					Button ADDcustomer = new Button("Submit!");

					dialogVbox.add(enterID, 0, 1);
					dialogVbox.add(IDInput, 1, 1);
					dialogVbox.add(enterName, 0, 2);
					dialogVbox.add(NameInput, 1, 2);
					dialogVbox.add(enterAddress, 0, 3);
					dialogVbox.add(AddressInput, 1, 3);
					dialogVbox.add(enterMobile, 0, 4);
					dialogVbox.add(MobileInput, 1, 4);
					dialogVbox.add(enterPlan, 0, 5);
					dialogVbox.add(PlanInput, 1, 5);

					dialogVbox.add(ADDcustomer, 1, 6);

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);

					ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							instance.addCustomer(NameInput.getText(), AddressInput.getText(), PlanInput.getText(), null,
									null, MobileInput.getText(), IDInput.getText());
							System.out.println(instance.getAllCustomersInfo());
						}
					});
					Scene dialogScene = new Scene(borderPane, 500, 500);
					dialog.setTitle("Add Customer");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			DeleteCustomer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label enterID = new Label("Customer ID:");

					TextField IDInput = new TextField();
					IDInput.setPrefColumnCount(14);

					Button ADDcustomer = new Button("Submit!");

					dialogVbox.add(enterID, 0, 1);
					dialogVbox.add(IDInput, 1, 1);

					dialogVbox.add(ADDcustomer, 1, 2);

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);

					ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							System.out.println(instance.deleteCustomer(IDInput.getText()));
							System.out.println(instance.getAllCustomersInfo());
						}
					});

					Scene dialogScene = new Scene(borderPane, 500, 500);
					dialog.setTitle("Delete Customer");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});
			UpdateCustomer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label text = new Label();

					text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

					Label enterID = new Label("Customer ID:");

					TextField IDInput = new TextField();
					IDInput.setPrefColumnCount(14);

					Button ADDcustomer = new Button("Submit!");
					dialogVbox.add(enterID, 0, 1);
					dialogVbox.add(IDInput, 1, 1);
					dialogVbox.add(ADDcustomer, 1, 2);

					ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							text.setText(instance.searchCustomer(IDInput.getText()));
						}
					});

					Label enterID_1 = new Label("Customer ID:");
					Label enterName = new Label("Customer Name:");
					Label enterAddress = new Label("Customer Address:");
					Label enterMobile = new Label("Customer Mobile:");
					Label enterPlan = new Label("Customer Plan:");

					TextField NameInput = new TextField();
					// TextField IDInput_1 = new TextField();
					TextField AddressInput = new TextField();
					TextField MobileInput = new TextField();
					TextField PlanInput = new TextField();

					NameInput.setPrefColumnCount(14);
					// IDInput.setPrefColumnCount(14);
					AddressInput.setPrefColumnCount(14);
					MobileInput.setPrefColumnCount(14);
					PlanInput.setPrefColumnCount(14);
					Text text2 = new Text(IDInput.getText());
					text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

					Button Updatecustomer = new Button("Update");

					dialogVbox.add(text, 0, 3);
					dialogVbox.add(enterID_1, 0, 4);
					dialogVbox.add(text2, 1, 4);
					dialogVbox.add(enterName, 0, 5);
					dialogVbox.add(NameInput, 1, 5);
					dialogVbox.add(enterAddress, 0, 6);
					dialogVbox.add(AddressInput, 1, 6);
					dialogVbox.add(enterMobile, 0, 7);
					dialogVbox.add(MobileInput, 1, 7);
					dialogVbox.add(enterPlan, 0, 8);
					dialogVbox.add(PlanInput, 1, 8);

					dialogVbox.add(Updatecustomer, 1, 9);

					UpdateCustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							instance.updateCustomer(IDInput.getText(), NameInput.getText(), PlanInput.getText(),
									AddressInput.getText(), MobileInput.getText());
							System.out.println(instance.getAllCustomersInfo());
						}
					});

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);
					Scene dialogScene = new Scene(borderPane, 500, 500);
					dialog.setTitle("Update Customer");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			searchCustomer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label text = new Label();

					text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

					Label enterID = new Label("Customer ID:");

					TextField IDInput = new TextField();
					IDInput.setPrefColumnCount(14);

					Button ADDcustomer = new Button("Submit!");
					dialogVbox.add(enterID, 0, 1);
					dialogVbox.add(IDInput, 1, 1);
					dialogVbox.add(ADDcustomer, 1, 2);

					ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							text.setText(instance.searchCustomer(IDInput.getText()));
						}
					});

					dialogVbox.add(text, 0, 3);

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);
					Scene dialogScene = new Scene(borderPane, 640, 480);
					dialog.setTitle("Search Customer");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			// Media
			AddMedia.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					HBox radios = new HBox();
					radios.setPrefWidth(300);
					radios.setSpacing(20);
					radios.setPadding(new Insets(0, 0, 0, 0));

					Label question = new Label("What type of media would you like to add?");
					ToggleGroup answer = new ToggleGroup();
					RadioButton movie = new RadioButton("Movie");
					movie.setToggleGroup(answer);
					RadioButton album = new RadioButton("Album");
					album.setToggleGroup(answer);
					RadioButton game = new RadioButton("Game");
					game.setToggleGroup(answer);

					radios.getChildren().addAll(movie, album, game);
				
					dialogVbox.add(question, 0, 1);
					dialogVbox.add(radios, 1, 1);
					Label enterID = new Label("Media Code:");
					Label enterName = new Label("Media Title:");
					Label enterAddress = new Label("Number of Copies:");
					
					TextField NameInput = new TextField();
					TextField IDInput = new TextField();
					TextField AddressInput = new TextField();
					
					String code_temp = moviecode("");
					Label code = new Label(code_temp);
					
					NameInput.setPrefColumnCount(14);
					IDInput.setPrefColumnCount(14);
					AddressInput.setPrefColumnCount(14);
					
					dialogVbox.add(enterID, 0, 3);
					dialogVbox.add(code, 1, 3);
					dialogVbox.add(enterName, 0, 4);
					dialogVbox.add(NameInput, 1, 4);
					dialogVbox.add(enterAddress, 0, 5);
					dialogVbox.add(AddressInput, 1, 5);
					
					Label enterWeight = new Label("Enter Weight: ");
					TextField WeightInput = new TextField();
					Label enterPlan = new Label("Enter Rating: ");
					TextField PlanInput = new TextField();
					Label enterArtist = new Label("Enter Artist: ");
					Label enterSongs = new Label("Enter Songs: ");
					TextField ArtistInput = new TextField();
					TextField SongInput = new TextField();
					
					movie.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							
							
							PlanInput.setPrefColumnCount(14);
							
							dialogVbox.add(enterPlan, 0, 6);
							dialogVbox.add(PlanInput, 1, 6);
							
							Button ADDcustomer = new Button("Submit!");
							
							dialogVbox.getChildren().remove(enterWeight);
							dialogVbox.getChildren().remove(WeightInput);
							dialogVbox.add(ADDcustomer, 1, 10);
							
							ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									instance.addMovie(code_temp, NameInput.getText(),
											Integer.parseInt(AddressInput.getText()), PlanInput.getText());
									
									try {
										Writer append = new FileWriter("src/Media_Database", true);
										append.append("\n" + code_temp + "-" + NameInput.getText() + "-"
												+ Integer.parseInt(AddressInput.getText()) + "-" + PlanInput.getText());
										append.close();
									} catch (Exception e) {
									}
									System.out.println(instance.getAllMediaInfo());
								}
							});
						}
					});
					
					game.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							
							WeightInput.setPrefColumnCount(14);
							
							dialogVbox.add(enterWeight, 0, 6);
							dialogVbox.add(WeightInput, 1, 6);
							
							Button ADDcustomer = new Button("Submit!");
							
							dialogVbox.getChildren().remove(enterPlan);
							dialogVbox.getChildren().remove(PlanInput);
							dialogVbox.getChildren().remove(enterArtist);
							dialogVbox.getChildren().remove(ArtistInput);
							dialogVbox.getChildren().remove(enterSongs);
							dialogVbox.getChildren().remove(SongInput);
							dialogVbox.add(ADDcustomer, 1, 10);
							
							ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									instance.addGame(code_temp, NameInput.getText(),
											Integer.parseInt(AddressInput.getText()),
											Double.parseDouble(WeightInput.getText()));
									try {
										Writer append = new FileWriter("src/Media_Database", true);
										append.append("\n" + code_temp + "-" + NameInput.getText() + "-"
												+ Integer.parseInt(AddressInput.getText()) + "-" + WeightInput.getText());
										append.close();
									} catch (Exception e) {
									}
									System.out.println(instance.getAllMediaInfo());
								}
							});
						}
					});
					
					album.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
						

							ArtistInput.setPrefColumnCount(14);
							SongInput.setPrefColumnCount(14);
							
							dialogVbox.add(enterArtist, 0, 6);
							dialogVbox.add(ArtistInput, 1, 6);
							dialogVbox.add(enterSongs, 0, 7);
							dialogVbox.add(SongInput, 1, 7);
							
							
							Button ADDcustomer = new Button("Submit!");
							
							
							dialogVbox.getChildren().remove(enterWeight);
							dialogVbox.getChildren().remove(WeightInput);
							dialogVbox.getChildren().remove(enterPlan);
							dialogVbox.getChildren().remove(PlanInput);
							dialogVbox.add(ADDcustomer, 1, 10);
							
							ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									instance.addAlbum(code_temp, NameInput.getText(),
											Integer.parseInt(AddressInput.getText()), ArtistInput.getText(),
											SongInput.getText());
									try {
										Writer append = new FileWriter("src/Media_Database", true);
										append.append("\n" + code_temp + "-" + NameInput.getText() + "-"
												+ Integer.parseInt(AddressInput.getText()) + "-" + ArtistInput.getText() + "-" +
												SongInput.getText());
										append.close();
									} catch (Exception e) {
									}
									System.out.println(instance.getAllMediaInfo());
								}
							});
							
						}
					});
					

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);

					Scene dialogScene = new Scene(borderPane, 600, 500);
					dialog.setTitle("Add Media");
					
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			DeletMedia.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label enterID = new Label("Media Code:");

					TextField IDInput = new TextField();
					IDInput.setPrefColumnCount(14);

					Button ADDcustomer = new Button("Submit!");

					dialogVbox.add(enterID, 0, 1);
					dialogVbox.add(IDInput, 1, 1);

					dialogVbox.add(ADDcustomer, 1, 2);

					ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {

							System.out.println(instance.deleteMedia(IDInput.getText()));

							System.out.println(instance.getAllMediaInfo());
						}
					});

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);
					Scene dialogScene = new Scene(borderPane, 500, 500);
					dialog.setTitle("Delete Media");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			UpdateMedia.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label text = new Label();

					text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 8));

					Label enterID = new Label("Media Code:");

					TextField IDInput = new TextField();
					IDInput.setPrefColumnCount(14);

					Button ADDcustomer = new Button("Submit!");
					dialogVbox.add(enterID, 0, 1);
					dialogVbox.add(IDInput, 1, 1);
					dialogVbox.add(ADDcustomer, 1, 2);

					ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							text.setText(instance.searchMedia(IDInput.getText()));
						}
					});

					Label enterID_1 = new Label("Media Code:");
					Label enterName = new Label("Media Title:");
					Label enterAddress = new Label("Number Of Copies:");
					Label enterMobile = new Label("Weight (Game only):");
					Label enterPlan = new Label("Rating (Movie only):");
					Label enterArtist = new Label("Artist (Album only):");
					Label enterSongs = new Label("Songs (Album only):");
					
					
					TextField NameInput = new TextField();
					TextField IDInput_1 = new TextField();
					TextField AddressInput = new TextField();
					TextField MobileInput = new TextField();
					TextField PlanInput = new TextField();
					TextField ArtistInput = new TextField();
					TextField SongInput = new TextField();

					NameInput.setPrefColumnCount(14);
					IDInput.setPrefColumnCount(14);
					AddressInput.setPrefColumnCount(14);
					MobileInput.setPrefColumnCount(14);
					PlanInput.setPrefColumnCount(14);
					ArtistInput.setPrefColumnCount(14);
					SongInput.setPrefColumnCount(14);
					
					Button Updatecustomer = new Button("Update");

					dialogVbox.add(text, 0, 3);
					dialogVbox.add(enterID_1, 0, 4);
					dialogVbox.add(IDInput_1, 1, 4);
					dialogVbox.add(enterName, 0, 5);
					dialogVbox.add(NameInput, 1, 5);
					dialogVbox.add(enterAddress, 0, 6);
					dialogVbox.add(AddressInput, 1, 6);
					dialogVbox.add(enterMobile, 0, 7);
					dialogVbox.add(MobileInput, 1, 7);
					dialogVbox.add(enterPlan, 0, 8);
					dialogVbox.add(PlanInput, 1, 8);
					dialogVbox.add(enterArtist, 0, 9);
					dialogVbox.add(ArtistInput, 1, 9);
					dialogVbox.add(enterSongs, 0, 10);
					dialogVbox.add(SongInput, 1, 10);

					dialogVbox.add(Updatecustomer, 1, 11);

					UpdateCustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							instance.updateMedia(IDInput.getText(), NameInput.getText(), Integer.parseInt(AddressInput.getText()),
									Double.parseDouble(MobileInput.getText()), PlanInput.getText(), ArtistInput.getText(), SongInput.getText());
							System.out.println(instance.getAllMediaInfo());
						}
					});
					
					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);
					Scene dialogScene = new Scene(borderPane, 500, 500);
					dialog.setTitle("Update Media");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			searchMedia.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label text = new Label();

					text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

					Label enterID = new Label("Media Code:");

					TextField IDInput = new TextField();
					IDInput.setPrefColumnCount(14);

					Button ADDcustomer = new Button("Submit!");
					dialogVbox.add(enterID, 0, 1);
					dialogVbox.add(IDInput, 1, 1);
					dialogVbox.add(ADDcustomer, 1, 2);

					ADDcustomer.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							text.setText(instance.searchMedia(IDInput.getText()));
						}
					});

					dialogVbox.add(text, 0, 3);

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);
					Scene dialogScene = new Scene(borderPane, 640, 480);
					dialog.setTitle("Search Media");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			PrintMedia.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label text = new Label();

					text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

					text.setText(instance.getAllMediaInfo());

					dialogVbox.add(text, 0, 3);

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);
					Scene dialogScene = new Scene(borderPane, 640, 480);
					dialog.setTitle("Print Media");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			primaryStage.setTitle("Euphoria - Beta");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	

	public static String moviecode(String input) {

		String random_Thangs = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		String result = "";
		int i = 0;
		while (i < 4) {
			double n = Math.random() * 100;
			if (Math.round(n) < 35) {
				result += random_Thangs.charAt((int) n);
				i++;
			}
		}
		return result;
	}

	public static boolean isNumeric(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}



