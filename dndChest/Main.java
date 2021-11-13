package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {
	String characterName = new String();
	String characterClass = new String();

	@Override
	public void start(Stage primaryStage) {
		VBox newCharacter = new VBox();
		Scene setUp = new Scene(newCharacter, 400, 400);
		BorderPane mainPane = new BorderPane();
		Scene mainScene = new Scene(mainPane, 400, 400);
		
		
		
		HBox header = new HBox();
		
		Button addContainer = new Button("New Container");
		Button addTrinket = new Button("New Trinket");
		
		//Ellipse logo = new Ellipse(10, 30);
		ImageView logo = new ImageView("file:Images/dndappiconclear.png");
		logo.setFitWidth(30);
		logo.setPreserveRatio(true);
		
		mainPane.setTop(header);
		
		newCharacter.setAlignment(Pos.BOTTOM_CENTER);
		newCharacter.setSpacing(10);
		TextField inputName = new TextField();
		TextField inputClass = new TextField();
		Label inputNameLbl = new Label("Please enter your name: ", inputName);
		inputNameLbl.setContentDisplay(ContentDisplay.RIGHT);
		Label inputClassLbl = new Label("Please enter your class: ", inputClass);
		inputClassLbl.setContentDisplay(ContentDisplay.RIGHT);
		Button submitCharacter = new Button("Continue");
		
		submitCharacter.setOnAction(e -> {
			characterName = inputName.getText();
			characterClass = inputClass.getText();
			
			primaryStage.setScene(mainScene);
		});
		
		newCharacter.getChildren().addAll(logo, inputNameLbl, inputClassLbl, submitCharacter);
		
		VBox characterInfo = new VBox();
		
		Label displayName = new Label(characterName);
		Label displayClass = new Label(characterClass);
		characterInfo.getChildren().addAll(displayName, displayClass);
		header.getChildren().addAll(characterInfo, addContainer, addTrinket, logo);
		header.setAlignment(Pos.BASELINE_CENTER);
		
		
		setUp.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(setUp);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
