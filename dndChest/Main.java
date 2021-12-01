package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {
	File file;
	WriteReadClass WriteReadClassObj;
	
	@Override
	public void start(Stage primaryStage) {
		// Main pane for setup screen
		VBox newCharacter = new VBox();
		newCharacter.setId("scene");
		newCharacter.getStylesheets().add("file:design.css");
		newCharacter.setAlignment(Pos.CENTER);
		newCharacter.setSpacing(10);
		// Scene to display newCharacter VBox
		Scene setUp = new Scene(newCharacter, 900, 700);
		
		// Primary pane that displays container and trinket info, plus utilities
		BorderPane mainPane = new BorderPane();
		mainPane.setId("scene");
		mainPane.getStylesheets().add("file:design.css");
		// Scene to display mainPane BorderPane
		Scene mainScene = new Scene(mainPane, 900, 700);
		
		// Top header of mainPane
		HBox header = new HBox();
		header.spacingProperty().bind(mainScene.widthProperty().subtract(50).divide(5));
		mainPane.setTop(header);
		
		// DnD Chest logo
		Image logo = new Image("file:Images/dndappiconclear.png");
		Image d20 = new Image("file:Images/dndappd20.png");
		
		// ImageView to display within the newCharacter setup screen
		ImageView setUpLogo = new ImageView(logo);
		setUpLogo.setFitWidth(400);
		setUpLogo.setPreserveRatio(true);
		
		// ImageView to display in top right of mainPane
		ImageView mainLogo = new ImageView(d20);
		mainLogo.setFitWidth(100);
		mainLogo.setPreserveRatio(true);
		
		// TextFields receive name and class of users character, on the setUp pane
		TextField inputName = new TextField();
		TextField inputClass = new TextField();
		// Labels that display above TextFields to the right
		Label inputNameLbl = new Label("Please enter your name: ", inputName);
		inputNameLbl.setContentDisplay(ContentDisplay.RIGHT);
		Label inputClassLbl = new Label("Please enter your class: ", inputClass);
		inputClassLbl.setContentDisplay(ContentDisplay.RIGHT);
		// Labels displayed on mainPane header, which store the chosen name and class.
		Label displayName = new Label();
		Label displayClass = new Label();
		// File chooser
		Button openFileButton = new Button("Open File");
		Label fileChosen = new Label("Choose a saved file: ", openFileButton);
		fileChosen.setContentDisplay(ContentDisplay.RIGHT);
		// Button confirms character setup and continues to mainScene
		Button submitCharacter = new Button("Continue");
		
		// Show which file selected and save
		openFileButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File Chosenfile = fileChooser.showOpenDialog(primaryStage);
			if (Chosenfile != null) {
				fileChosen.setText(Chosenfile.getName()
						+ "  selected");
				file = Chosenfile;
			}
		});
		
		// Add all setUp elements to newCharacter vbox
		newCharacter.getChildren().addAll(setUpLogo, inputNameLbl, inputClassLbl, fileChosen, submitCharacter);
		newCharacter.setSpacing(10);
		
		// VBox to display character information in top left of mainScene, left side of header
		VBox characterInfo = new VBox(5);
		TextField gold = new TextField();
		gold.setMaxWidth(75);
		Label userGold = new Label("Gold: ", gold);
		userGold.setContentDisplay(ContentDisplay.RIGHT);
		characterInfo.setAlignment(Pos.CENTER_LEFT);
		characterInfo.getChildren().addAll(displayName, displayClass, userGold);
		
		// Buttons to add a new container, trinket, or save the program state
		Button addContainer = new Button("New Container");
		addContainer.setMinWidth(100);
		Button addTrinket = new Button("New Trinket");
		addTrinket.setMinWidth(100);
		Button saveButton = new Button("Save");
		saveButton.setMinWidth(100);
		// Store buttons in HBox for formatting
		HBox buttons = new HBox(addContainer, addTrinket, saveButton);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(10);
		
		// Add header elements to header
		header.getChildren().addAll(characterInfo, buttons, mainLogo);
		header.setAlignment(Pos.CENTER);
		header.setStyle("-fx-border-color:#A9A9A9");
		
		// VBox to display information about containers on left of mainPane
		VBox containersTab = new VBox(20);
		containersTab.setStyle("-fx-border-color:#A9A9A9");
		// ComboBox to store and select containers
		ComboBox<Container> containerSelect = new ComboBox<Container>();
		containerSelect.setMinWidth(114);
		// Button that will remove a container
		Button containerRemove = new Button("Remove Container");
        Label containerLabel = new Label("Containers:", containerSelect);
        containerLabel.setContentDisplay(ContentDisplay.BOTTOM);
        // Weight to max weight ratio display
        Label weightRatio = new Label("Container is 0.00% full.");
        // Total net value of trinkets in container
		Label containedGold = new Label("Container has 0.00 gold.");
        containersTab.getChildren().addAll(containerRemove, weightRatio, containedGold, containerSelect, containerLabel);
        containersTab.setPadding(new Insets(5));
        containersTab.setMinWidth(185);
        containersTab.setMaxWidth(185);
        mainPane.setLeft(containersTab);
        
        containerRemove.setOnAction(e -> {
			int i = containerSelect.getSelectionModel().getSelectedIndex();
			containerSelect.getItems().remove(i);
		});
        
        // New Container scene, mainly using VBox
        Label containersBigText = new Label("Container Set Up");
        containersBigText.setScaleX(2);
	    containersBigText.setScaleY(2);
		TextField containerNameSelect = new TextField();
	    TextField containerMaxWeightSelect = new TextField();
	    Label cNameLabel = new Label("Name of container: ", containerNameSelect);
	    Label cWeightLabel = new Label("Maximum weight (Put 0 for infinite): ", containerMaxWeightSelect);
	    cNameLabel.setContentDisplay(ContentDisplay.RIGHT);
	    cWeightLabel.setContentDisplay(ContentDisplay.RIGHT);
	    
	    // Confirm and deny buttons, stored in HBox for formatting
	    Button containerCancel = new Button("Nevermind");
	    Button containerConfirmation = new Button("Confirm");
	    HBox conButtons = new HBox(10, containerCancel, containerConfirmation);
	    conButtons.setAlignment(Pos.CENTER);
	    VBox containersVBox = new VBox(10, containersBigText, containerNameSelect, containerMaxWeightSelect, cNameLabel, cWeightLabel,conButtons);
	    containersVBox.setAlignment(Pos.CENTER);
	    containersVBox.setId("scene");
	    containersVBox.getStylesheets().add("file:design.css");
	    
	    // New container creation scene
	    Scene newContainer = new Scene(containersVBox, 900, 700);
	    
	    // Button on mainPane to add container
		addContainer.setOnAction(e -> {
			primaryStage.setScene(newContainer);
		});
		
		// Returns to main scene without adding container
		containerCancel.setOnAction(e -> {
			containerNameSelect.setText("");
			containerMaxWeightSelect.setText("");
			primaryStage.setScene(mainScene);
		});
		
		// Adds container to combobox, then returns to main scene
		containerConfirmation.setOnAction(e -> {
			// If all fields are filled, create Container object and add to containerSelect ComboBox
			if (containerNameSelect.getText() != "" && containerMaxWeightSelect.getText() != "") {
				containerSelect.getItems().add(new Container(containerNameSelect.getText(), Double.parseDouble(containerMaxWeightSelect.getText())));
			}
			// If given name and but no max weight, create Container with infinite capacity and add to containerSelect ComboBox
			else if (containerNameSelect.getText() != "" && containerMaxWeightSelect.getText() == "") {
				containerSelect.getItems().add(new Container(containerNameSelect.getText()));
			}
			// Add a default Container, named Bag of Holding with infinite capacity
			else {
				containerSelect.getItems().add(new Container());
			}
			// Reset fields
			containerNameSelect.setText("");
			containerMaxWeightSelect.setText("");
			// Select new Container in ComboBox
			containerSelect.getSelectionModel().selectLast();
			primaryStage.setScene(mainScene);
		});
		
		// New Trinket scene setup
		Label trinketBigText = new Label("Trinket Set Up");
        trinketBigText.setScaleX(2);
        trinketBigText.setScaleY(2);
		TextField trinketNameSelect = new TextField();
        TextField trinketWeightSelect = new TextField();
        TextField trinketGoldSelect = new TextField();
        TextArea trinketDescSelect = new TextArea();
        trinketDescSelect.setPrefColumnCount(20);
        Label tNameLabel = new Label("Name of trinket: ", trinketNameSelect);
        Label tWeightLabel = new Label("Weight of trinket", trinketWeightSelect);
        Label tGoldLabel = new Label("Worth of trinket in gold", trinketGoldSelect);
        Label tDescLabel = new Label("Description of trinket", trinketDescSelect);
        tNameLabel.setContentDisplay(ContentDisplay.RIGHT);
        tWeightLabel.setContentDisplay(ContentDisplay.RIGHT);
        tGoldLabel.setContentDisplay(ContentDisplay.RIGHT);
        tDescLabel.setContentDisplay(ContentDisplay.RIGHT);
        
        // Confirm and deny buttons
        Button trinketDeny = new Button("Nevermind");
        Button trinketConfirmation = new Button("Confirm");
        HBox trinketButtons = new HBox(10, trinketDeny, trinketConfirmation);
        trinketButtons.setAlignment(Pos.CENTER);
        
        // Display formatting and style
        VBox trinketVBox = new VBox(10, trinketBigText, trinketNameSelect, trinketWeightSelect, trinketGoldSelect, trinketDescSelect, 
        tNameLabel, tWeightLabel, tGoldLabel, tDescLabel, trinketButtons);
        trinketVBox.setAlignment(Pos.CENTER);
        trinketVBox.setId("scene");
        trinketVBox.getStylesheets().add("file:design.css");
        
        // Implement trinket ListView on mainPane
        ListView<Trinket> trinketListView = new ListView<Trinket>();
        trinketListView.setStyle("-fx-border-color:#A9A9A9");
        trinketListView.getStylesheets().add("file:design.css");
        trinketListView.setMinHeight(100);
        mainPane.setCenter(trinketListView);
        
        // When a container in the containerSelect ComboBox is selected
        containerSelect.valueProperty().addListener(e -> {
        	if(containerSelect.getSelectionModel().getSelectedIndex() >= 0){
        		// Reset trinket ListView
            	trinketListView.getItems().clear();
            	// Get the container stored in the selected ComboBox field
            	Container selectedContainer = (Container)containerSelect.getValue();
            	// Update container info in left of mainPane
            	if (selectedContainer.getMaxWeight() != Double.POSITIVE_INFINITY) {
            		weightRatio.setText(String.format("Container is %.2f%% full.", 100*selectedContainer.getWeightRatio()));
            	}
            	else {
            		weightRatio.setText("Container is INFINITE.00% full.");
            	}
            	containedGold.setText(String.format("Container has %.2f gold.", selectedContainer.getContainedValue()));
            	// Fill trinket ListView with trinkets contained in container
            	ArrayList<Trinket> trinketList = selectedContainer.getList();
            	for (Trinket x : trinketList) {
            		trinketListView.getItems().add(x);
            	}
        	}
        });
        
        // Changes scene to newTrinket VBox
        Scene newTrinket = new Scene(trinketVBox, 900, 700);
        addTrinket.setOnAction(e -> {
			primaryStage.setScene(newTrinket);
		});
        
        // Return to main scene and reset fields
        trinketDeny.setOnAction(e -> {
        	trinketNameSelect.setText("");
        	trinketDescSelect.setText("");
        	trinketGoldSelect.setText("");
        	trinketWeightSelect.setText("");
        	trinketBigText.setText("Trinket Set Up");
        	primaryStage.setScene(mainScene);
        });
        
        // Confirms user input and adds trinket to container
        trinketConfirmation.setOnAction(e -> {
        	Container selectedContainer;
        	Trinket tempTrinket;
        	// If name field, max weight field, and gold value select are not empty
        	if (trinketNameSelect.getText() != "" && trinketWeightSelect.getText() != "" && trinketGoldSelect.getText() != "") {
        		selectedContainer = (Container)containerSelect.getValue();
        		String newTrinketName = trinketNameSelect.getText();
        		String newTrinketDesc = trinketDescSelect.getText();
        		Double newTrinketGold = Double.parseDouble(trinketGoldSelect.getText());
        		Double newTrinketWeight = Double.parseDouble(trinketWeightSelect.getText());
        		
        		// If weight overfills the container, do not add trinket
        		if (newTrinketWeight + selectedContainer.getContainedWeight() > selectedContainer.getMaxWeight()) {
        			trinketBigText.setText("Trinket too heavy.");
        			return;
        		}
        		
        		tempTrinket = new Trinket(newTrinketName, newTrinketDesc, newTrinketGold, newTrinketWeight);
        		selectedContainer.addTrinket(tempTrinket);
        		trinketListView.getItems().add(tempTrinket);
        		// If max weight is not infinite
        		if (selectedContainer.getMaxWeight() != Double.POSITIVE_INFINITY) {
            		weightRatio.setText(String.format("Container is %.2f%% full.", 100*selectedContainer.getWeightRatio()));
            	}
            	else {
            		weightRatio.setText("Container is INFINITE.00% full.");
            	}
            	containedGold.setText(String.format("Container has %.2f gold.", selectedContainer.getContainedValue()));
        	}
        	else {
        		return;
        	}
        	// Reset input fields and return
        	trinketNameSelect.setText("");
        	trinketDescSelect.setText("");
        	trinketGoldSelect.setText("");
        	trinketWeightSelect.setText("");
        	trinketBigText.setText("Trinket Set Up");
        	primaryStage.setScene(mainScene);
        });
        
        // Remove trinket from container
        Button removeTrinket = new Button("Remove Trinket");
        removeTrinket.setOnAction(e -> {
        	// Pull container from selected ComboBox
        	Container selectedContainer = (Container)containerSelect.getValue();
        	// Get selected ListView index
        	int i = trinketListView.getSelectionModel().getSelectedIndex();
        	// Remove trinket from container and ListView
        	selectedContainer.removeTrinket(i);
        	trinketListView.getItems().remove(i);
        	// Update container display info on left of mainPane
        	if (selectedContainer.getMaxWeight() != Double.POSITIVE_INFINITY) {
        		weightRatio.setText(String.format("Container is %.2f%% full.", 100*selectedContainer.getWeightRatio()));
        	}
        	else {
        		weightRatio.setText("Container is INFINITE.00% full.");
        	}
        	containedGold.setText(String.format("Container has %.2f gold.", selectedContainer.getContainedValue()));
        });
        
        // Selected trinket info on right of mainPane
        Label trinketNameView = new Label("Name: ");
        Label trinketWeightView = new Label("Weight: ");
        Label trinketGoldView = new Label("Value: ");
        Label trinketDescView = new Label("");
        trinketNameView.setWrapText(true);
        trinketDescView.setWrapText(true);
        
        // VBox containing remove trinket button, and various selected trinket info
        VBox trinketView = new VBox(20, removeTrinket, trinketNameView, trinketWeightView, trinketGoldView, trinketDescView);
        trinketView.setPadding(new Insets(5));
        trinketView.setMinWidth(185);
        trinketView.setMaxWidth(185);
        trinketView.setStyle("-fx-border-color:#A9A9A9");
        mainPane.setRight(trinketView);
        
        // Fires when a trinket in the ListView is selected
        trinketListView.getSelectionModel().selectedItemProperty().addListener(e ->{
        	// If a trinket is actually selected
        	if (!trinketListView.getSelectionModel().isEmpty()) {
	        	// Gets selected trinket in ListView
        		Trinket temp = (Trinket)trinketListView.getSelectionModel().getSelectedItem();
	        	// Updates trinket info display
        		trinketNameView.setText("Name: "+ temp.getName());
	        	trinketWeightView.setText("Weight: "+ temp.getWeight());
	        	trinketGoldView.setText("Value: "+ temp.getValue());
	        	if (temp.getDesc() == "") {
	        		trinketDescView.setText("");
	        	}
	        	else {
	        		trinketDescView.setText("Description: "+ temp.getDesc());
	        	}
	        }
        });
        
        // BEGIN saveButton event
        saveButton.setOnAction( e->{
        	WriteReadClass writeOut = new WriteReadClass();
			try {
				
				//Set extension filter for text files
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	            fileChooser.getExtensionFilters().add(extFilter);
	            //Show save file dialog
	            File file = fileChooser.showSaveDialog(primaryStage);
	 
				//Create write buffer
				FileWriter fw = new FileWriter(file);
				BufferedWriter Writebw = new BufferedWriter(fw);

				//Write name, class and gold to file
				String nameOutput = displayName.getText().substring(6);
				String classOutput = displayClass.getText().substring(7);		
				writeOut.writeFile(Writebw,nameOutput);
	        	System.out.println(nameOutput);
	        	writeOut.writeFile(Writebw,classOutput);
	        	System.out.println(classOutput);
	        	writeOut.writeFile(Writebw, gold.getText());
	        	System.out.println(gold.getText());
	        	
	        	ObservableList<Container> items = containerSelect.getItems();
				for (Container item : items) {
					System.out.println(item.getName());
					writeOut.writeFile(Writebw,item.getName());
					
					System.out.println( item.getMaxWeight() );
					writeOut.writeFile(Writebw, String.valueOf( item.getMaxWeight()  ));
					
					
					ArrayList<Trinket> tempTrinket = new ArrayList<Trinket>();
					tempTrinket = item.getList();
					for (Trinket trinket : tempTrinket) {
						System.out.println("START TRINKETS");
						
						System.out.println( trinket.getName() );
						writeOut.writeFile(Writebw,trinket.getName());
						
						System.out.println( trinket.getDesc() );
						writeOut.writeFile(Writebw, trinket.getDesc());
						
						System.out.println( trinket.getValue() );
						writeOut.writeFile(Writebw, String.valueOf( trinket.getValue() ));
						
						System.out.println( trinket.getWeight() );
						writeOut.writeFile(Writebw, String.valueOf(trinket.getWeight()) );
						
					}
					writeOut.writeFile(Writebw,"EndOfTrinkets");
					
				}
				writeOut.writeFile(Writebw,"EndOfFile");
	        	
	        	Writebw.close();
				
			} catch (IOException e1){
			      System.out.println("An error occurred.");
			      e1.printStackTrace();	
			}
        });
        
        // End newCharacter setup scene and either create new character or load previous state
		submitCharacter.setOnAction(e -> {
			
			//if user loads a saved file
			if (file != null) {
				String Name, Class, Gold;
				
				WriteReadClassObj = new WriteReadClass(file);
	
				try {
					//Create read buffer
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					
					//Read saved name and class
					Name = WriteReadClassObj.readFile(br);
					Class = WriteReadClassObj.readFile(br);
					Gold = WriteReadClassObj.readFile(br);
					
					//display name and class read
					displayName.setText("Name: " + Name);
					displayClass.setText("Class: "+ Class);
					gold.setText(Gold);
					
					// savedContainerName either holds another name of container or "EndOfFile"
					String savedContainerName = WriteReadClassObj.readFile(br);
					while ( !(savedContainerName.equals("EndOfFile")) ) {
						
						//read container name and max weight
						//String containerName = savedContainerName;
						String maxWeightTest = WriteReadClassObj.readFile(br);
						
						double maxWeight;
						if ( maxWeightTest.equals("Infinity") ) {
							maxWeight = Double.POSITIVE_INFINITY;
						}
						else {
							maxWeight = Double.parseDouble( maxWeightTest );
						}
						 
						//Creates loaded container
						Container tempContainer = new Container(savedContainerName, maxWeight);
						containerSelect.getItems().add(tempContainer);
						
						// savedTrinketName either holds another name of trinket or "EndOfTrinkets"
						String savedTrinketName = WriteReadClassObj.readFile(br);
						while ( !(savedTrinketName.equals("EndOfTrinkets")) ) {
							
							String trinketName = savedTrinketName;
							String trinketDesc = WriteReadClassObj.readFile(br);
							double trinketValue = Double.parseDouble( WriteReadClassObj.readFile(br) );
							double trinketWeight = Double.parseDouble( WriteReadClassObj.readFile(br) );
							
							//Creates a Trinket of the current Container
							Trinket tempTrinket = new Trinket(trinketName, trinketDesc, trinketValue, trinketWeight);
							tempContainer.addTrinket(tempTrinket);
			        		
			        		savedTrinketName = WriteReadClassObj.readFile(br);
						}
						savedContainerName = WriteReadClassObj.readFile(br);
						
					}
					containerSelect.getSelectionModel().selectFirst();
				} catch(IOException e2){
					System.out.println("An error occurred.");
				     e2.printStackTrace();	
				}
							
			}
			//If the user did not load a saved file
			else {
				displayName.setText("Name: " + inputName.getText());
				displayClass.setText("Class: " + inputClass.getText());
			}
			primaryStage.setScene(mainScene);
		});
        
        // Display stage
		primaryStage.getIcons().add(d20);
        primaryStage.setTitle("D&D Chest");
		primaryStage.setScene(setUp);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
