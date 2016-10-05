package Game;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class CenterBox extends BorderPane {
	private static Label playerMove = new Label("");
	private static Label playerBet = new Label("");
	private Player NateDigs = new Player();
	private Player BDawg = new Player();
	private static Player Middle = new Player();
	private static Deck deck = new Deck();
	
			
	public CenterBox()
	{
	BDawg.setName("B-Dawg");
	NateDigs.setName("NateDigs");
	MiddlePlayingArea mid = new MiddlePlayingArea(BDawg, NateDigs,deck);
	deck.createDeck();
	deck.Shuffle();
	
	this.setMinWidth(800);
	 
	NateDigs.playerHand(deck.deal(5), 5);
	BDawg.playerHand(deck.deal(5),5);
	

	NateDigs.setStyle("-fx-background-color: #8b0000;"
			+"-fx-text-fill: black;"
			+"-fx-border-style: solid inside;"
	        + "-fx-border-width: 2;" 
			+ "-fx-border-outsets: 5;"
	        + "-fx-border-radius: 1;"
			+ "-fx-border-color: black;");
	BDawg.setStyle("-fx-background-color: #006400;"
			+"-fx-text-fill: black;"
			+"-fx-border-style: solid inside;"
	        + "-fx-border-width: 2;" 
			+ "-fx-border-outsets: 5;"
	        + "-fx-border-radius: 1;"
			+ "-fx-border-color: black;");
	this.setTop(NateDigs);
	this.setBottom(BDawg);
	this.setCenter(mid);
	
	BDawg.cardSelected();
	
	
	
	}
	
	
}