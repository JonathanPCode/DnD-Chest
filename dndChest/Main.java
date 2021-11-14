package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	
	@Override
	public void start(Stage primaryStage) {
		VBox newCharacter = new VBox();
		newCharacter.setAlignment(Pos.CENTER);
		newCharacter.setSpacing(10);
		Scene setUp = new Scene(newCharacter, 900, 700);
		BorderPane mainPane = new BorderPane();
		Scene mainScene = new Scene(mainPane, 900, 700);
		
		HBox header = new HBox();
		//header.setSpacing(200);
		header.spacingProperty().bind(mainScene.widthProperty().subtract(50).divide(5));
		
		
		Image logo = new Image("file:dndappiconclear.png");
		ImageView setUpLogo = new ImageView(logo);
		
		setUpLogo.setFitWidth(400);
		setUpLogo.setPreserveRatio(true);
		
		ImageView mainLogo = new ImageView(logo);
		mainLogo.setFitWidth(100);
		mainLogo.setPreserveRatio(true);
		
		mainPane.setTop(header);
		
		
		TextField inputName = new TextField();
		TextField inputClass = new TextField();
		Label inputNameLbl = new Label("Please enter your name: ", inputName);
		inputNameLbl.setContentDisplay(ContentDisplay.RIGHT);
		Label inputClassLbl = new Label("Please enter your class: ", inputClass);
		inputClassLbl.setContentDisplay(ContentDisplay.RIGHT);
		Button submitCharacter = new Button("Continue");
		
		
		Label displayName = new Label();
		Label displayClass = new Label();
		
		submitCharacter.setOnAction(e -> {
			displayName.setText("Name: " + inputName.getText());
			displayClass.setText("Class: "+ inputClass.getText());
			
			primaryStage.setScene(mainScene);
		});
		
		Button addContainer = new Button("New Container");
		addContainer.setMinWidth(100);
		
		
		
		Button addTrinket = new Button("New Trinket");
		addTrinket.setMinWidth(100);
		
		HBox buttons = new HBox(addContainer, addTrinket);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(10);
		
		
        
		// Main Scene using BorderPane
		newCharacter.getChildren().addAll(setUpLogo, inputNameLbl, inputClassLbl, submitCharacter);
		newCharacter.setSpacing(10);
		
		VBox characterInfo = new VBox(5);
		TextField gold = new TextField();
		gold.setMaxWidth(75);
		Label userGold = new Label("Gold: ", gold);
		userGold.setContentDisplay(ContentDisplay.RIGHT);
		characterInfo.setAlignment(Pos.CENTER_LEFT);
		
		characterInfo.getChildren().addAll(displayName, displayClass, userGold);
		header.getChildren().addAll(characterInfo, buttons, mainLogo);
		header.setAlignment(Pos.CENTER);
		header.setStyle("-fx-border-color:BLACK");
		
		Label weightRatio = new Label("Container is 0.00% full.");
		Label containedGold = new Label("Container has 0.00 gold.");
		
		VBox containersTab = new VBox();
        containersTab.setStyle("-fx-border-color:BLACK");
		ComboBox containerSelect = new ComboBox();
        Label containerLabel = new Label("Containers:", containerSelect);
        containerLabel.setContentDisplay(ContentDisplay.BOTTOM);
        containersTab.getChildren().addAll(containerSelect, containerLabel, weightRatio, containedGold);
        mainPane.setLeft(containersTab);
        containersTab.setPadding(new Insets(5));
        containersTab.setMinWidth(185);
        containersTab.setMaxWidth(185);
        
        
        // New Container using VBox
		TextField containerNameSelect = new TextField();
	    TextField containerMaxWeightSelect = new TextField();
	
	    Button containerRemove = new Button("Nevermind");
	    Button containerConfirmation = new Button("Confirm");
	    HBox conButtons = new HBox(10, containerRemove, containerConfirmation);
	    conButtons.setAlignment(Pos.CENTER);
	
	    Label containersBigText = new Label("Container Set Up");
	    containersBigText.setScaleX(2);
	    containersBigText.setScaleY(2);
	    Label cNameLabel = new Label("Name of container: ", containerNameSelect);
	    Label cWeightLabel = new Label("Maximum weight (Put 0 for infinite): ", containerMaxWeightSelect);
	    cNameLabel.setContentDisplay(ContentDisplay.RIGHT);
	    cWeightLabel.setContentDisplay(ContentDisplay.RIGHT);
	    VBox containersVBox = new VBox(10, containersBigText, containerNameSelect, containerMaxWeightSelect, cNameLabel, cWeightLabel,conButtons);
	    containersVBox.setAlignment(Pos.CENTER);
	
	    // Changes scene to containersVBox
	    Scene newContainer = new Scene(containersVBox, 900, 700);
		addContainer.setOnAction(e -> {
			primaryStage.setScene(newContainer);
		});
		
		// Returns to main scene
		containerRemove.setOnAction(e -> {
			containerNameSelect.setText("");
			containerMaxWeightSelect.setText("");
			primaryStage.setScene(mainScene);
		});
		
		// Adds container to combobox, then returns to main scene
		containerConfirmation.setOnAction(e -> {
			if (containerNameSelect.getText() != "" && containerMaxWeightSelect.getText() != "") {
				containerSelect.getItems().add(new Container(containerNameSelect.getText(), Double.parseDouble(containerMaxWeightSelect.getText())));
			}
			else if (containerNameSelect.getText() != "" && containerMaxWeightSelect.getText() == "") {
				containerSelect.getItems().add(new Container(containerNameSelect.getText()));
			}
			else {
				containerSelect.getItems().add(new Container());
			}
			containerNameSelect.setText("");
			containerMaxWeightSelect.setText("");
			primaryStage.setScene(mainScene);
		});
		
		
		// New Trinket scene setup
		TextField trinketNameSelect = new TextField();
        TextField trinketWeightSelect = new TextField();
        TextField trinketGoldSelect = new TextField();
        TextArea trinketDescSelect = new TextArea();
        trinketDescSelect.setPrefColumnCount(20);
        
        Button trinketRemove = new Button("Nevermind");
        Button trinketConfirmation = new Button("Confirm");
        HBox trinketButtons = new HBox(10, trinketRemove, trinketConfirmation);
        trinketButtons.setAlignment(Pos.CENTER);
        
        Label trinketBigText = new Label("Trinket Set Up");
        trinketBigText.setScaleX(2);
        trinketBigText.setScaleY(2);
        
        Label tNameLabel = new Label("Name of trinket: ", trinketNameSelect);
        Label tWeightLabel = new Label("Weight of trinket", trinketWeightSelect);
        Label tGoldLabel = new Label("Worth of trinket in gold", trinketGoldSelect);
        Label tDescLabel = new Label("Description of trinket", trinketDescSelect);
        tNameLabel.setContentDisplay(ContentDisplay.RIGHT);
        tWeightLabel.setContentDisplay(ContentDisplay.RIGHT);
        tGoldLabel.setContentDisplay(ContentDisplay.RIGHT);
        tDescLabel.setContentDisplay(ContentDisplay.RIGHT);
        VBox trinketVBox = new VBox(10, trinketBigText, trinketNameSelect, trinketWeightSelect, trinketGoldSelect, trinketDescSelect, 
        tNameLabel, tWeightLabel, tGoldLabel, tDescLabel, trinketButtons);
        trinketVBox.setAlignment(Pos.CENTER);
        
        // Initialize ListView early
        ListView trinketListView = new ListView();
        trinketListView.setStyle("-fx-border-color:BLACK");
        
        // Changes scene to newTrinket VBox
        Scene newTrinket = new Scene(trinketVBox, 900, 700);
        addTrinket.setOnAction(e -> {
			primaryStage.setScene(newTrinket);
		});
        
        trinketRemove.setOnAction(e -> {
        	trinketNameSelect.setText("");
        	trinketDescSelect.setText("");
        	trinketGoldSelect.setText("");
        	trinketWeightSelect.setText("");
        	trinketBigText.setText("Trinket Set Up");
        	primaryStage.setScene(mainScene);
        });
        
        trinketConfirmation.setOnAction(e -> {
        	Container selectedContainer;
        	Trinket tempTrinket;
        	if (trinketNameSelect.getText() != "" && trinketWeightSelect.getText() != "" && trinketGoldSelect.getText() != "") {
        		selectedContainer = (Container)containerSelect.getValue();
        		String newTrinketName = trinketNameSelect.getText();
        		String newTrinketDesc = trinketDescSelect.getText();
        		Double newTrinketGold = Double.parseDouble(trinketGoldSelect.getText());
        		Double newTrinketWeight = Double.parseDouble(trinketWeightSelect.getText());
        		
        		if (newTrinketWeight + selectedContainer.getContainedWeight() > selectedContainer.getMaxWeight()) {
        			trinketBigText.setText("Trinket too heavy.");
        			return;
        		}
        		tempTrinket = new Trinket(newTrinketName, newTrinketDesc, newTrinketGold, newTrinketWeight);
        		
        		selectedContainer.addTrinket(tempTrinket);
        		trinketListView.getItems().add(tempTrinket);
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
        	trinketNameSelect.setText("");
        	trinketDescSelect.setText("");
        	trinketGoldSelect.setText("");
        	trinketWeightSelect.setText("");
        	trinketBigText.setText("Trinket Set Up");
        	primaryStage.setScene(mainScene);
        });
        
		// ListView implementation
        
        trinketListView.setMinHeight(100);
        mainPane.setCenter(trinketListView);
        containerSelect.valueProperty().addListener(e -> {
        	trinketListView.getItems().clear();
        	Container selectedContainer = (Container)containerSelect.getValue();
        	if (selectedContainer.getMaxWeight() != Double.POSITIVE_INFINITY) {
        		weightRatio.setText(String.format("Container is %.2f%% full.", 100*selectedContainer.getWeightRatio()));
        	}
        	else {
        		weightRatio.setText("Container is INFINITE.00% full.");
        	}
        	containedGold.setText(String.format("Container has %.2f gold.", selectedContainer.getContainedValue()));
        	ArrayList<Trinket> trinketList = selectedContainer.getList();
        	for (Trinket x : trinketList) {
        		trinketListView.getItems().add(x);
        	}
        });
        
        
        Label trinketNameView = new Label("Name: ");
        Label trinketWeightView = new Label("Weight: ");
        Label trinketGoldView = new Label("Value: ");
        Label trinketDescView = new Label("Description: ");
        trinketNameView.setWrapText(true);
        trinketDescView.setWrapText(true);
        VBox trinketView = new VBox(20, trinketNameView, trinketWeightView, trinketGoldView, trinketDescView);
        mainPane.setRight(trinketView);
        trinketView.setPadding(new Insets(5));
        trinketView.setMinWidth(185);
        trinketView.setMaxWidth(185);
        trinketView.setStyle("-fx-border-color:BLACK");
        
        
        trinketListView.getSelectionModel().selectedItemProperty().addListener(e ->{
        	Trinket temp = (Trinket)trinketListView.getSelectionModel().getSelectedItem();
        	trinketNameView.setText("Name: "+ temp.getName());
        	trinketWeightView.setText("Weight: "+ temp.getWeight());
        	trinketGoldView.setText("Value: "+ temp.getValue());
        	trinketDescView.setText("Description: "+ temp.getDesc());
        });
        
		primaryStage.setScene(setUp);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}