package Game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MonsterCardGame extends Application{


	
	public static void main(String[] args) {launch(args);}
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(new Group()); 
		stage.setTitle("Assignment 4 - Card Deck");
		
		
		
		PrimaryPane PrimaryPane = new PrimaryPane();
		VBox mainVBox = new VBox();
		mainVBox.getChildren().add(PrimaryPane);
		((Group) scene.getRoot()).getChildren().addAll(mainVBox);
		stage.setScene(scene); 
        stage.show(); 
        
	}

}





