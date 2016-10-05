package Game;


import java.io.File;
import java.util.Stack;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class MiddlePlayingArea extends VBox {
	
	private Player PlayerBot = new Player();
	private Player PlayerTop = new Player();
	private HBox PlayerTopBox = new HBox();
	private HBox PlayerBotBox = new HBox();
	private Stack<Card> PlayerBotStack = new Stack();
	private Stack<Card> PlayerTopStack = new Stack();
	
	private Label PlayerHealth = new Label();
	private Label AIHealth = new Label();
	private Label PlayerGold = new Label();
	private Label AIGold = new Label();
	private Label PlayerName = new Label();
	private Label AIName = new Label();
	private Deck Deck = new Deck();
	private Button EndTurn= new Button("End Turn");
	private  Media sound = new Media(new File("whoosh.mp3").toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(sound);
	private Label middleLabel = new Label("Vs");
	
	
	
	private VBox Container = new VBox();
	private HBox AIRow = new HBox();
	private HBox MiddleRow = new HBox();
	private HBox playerMiddleBox = new HBox();
	private HBox aiMiddleBox = new HBox();
	private HBox PlayerRow = new HBox();
	
	private int count=0;
	private int PlayerBotCost;
	private int PlayerTopCost;
	@SuppressWarnings("unchecked")
	public MiddlePlayingArea(Player BotPlayer,Player TopPlayer, Deck deck)
	{
		PlayerBot = BotPlayer;
		PlayerTop = TopPlayer;
		Deck = deck;
		

		
		CreateMiddle();
		this.setMinHeight(300);
			
		
		EndTurn.setOnAction(new ButtonListener());
}


	public void CreateMiddle()
	{
		Container.getChildren().add(AIRow);
		Container.getChildren().add(MiddleRow);
		Container.getChildren().add(PlayerRow);
		Container.setStyle("-fx-background-color: #c0c0c0;");
		
		
		
		PlayerGold.setAlignment(Pos.CENTER);
		PlayerGold.setStyle("-fx-background-color: #ffd700;"
		+"-fx-text-fill: black;");
		PlayerGold.setMinWidth(150);
		PlayerGold.setFont(Font.font ("Verdana", 20));
		PlayerGold.setText(Integer.toString(PlayerBot.getGold()));
		
	
		PlayerName.setAlignment(Pos.CENTER);
		PlayerName.setStyle("-fx-background-color: #4b0082;"
		+"-fx-text-fill: white;");
		PlayerName.setMinWidth(150);
		PlayerName.setFont(Font.font ("Verdana", 20));
		PlayerName.setText(PlayerBot.getName());
		
		PlayerHealth.setAlignment(Pos.CENTER);
		PlayerHealth.setStyle("-fx-background-color: #b22222;"
		+"-fx-text-fill: white;");
		
		PlayerHealth.setMinWidth(150);
		PlayerHealth.setFont(Font.font ("Verdana", 20));
		PlayerHealth.setText(Integer.toString(PlayerBot.getHealth()));
		
		
		AIGold.setAlignment(Pos.CENTER);
		AIGold.setStyle("-fx-background-color: #ffd700;"
		+"-fx-text-fill: black;");
		AIGold.setMinWidth(150);
		AIGold.setFont(Font.font ("Verdana", 20));
		AIGold.setText(Integer.toString(PlayerTop.getGold()));
		
		
		AIName.setAlignment(Pos.CENTER);
		AIName.setStyle("-fx-background-color: #4b0082;"
		+"-fx-text-fill: white;");
		AIName.setMinWidth(150);
		AIName.setFont(Font.font ("Verdana", 20));
		AIName.setText(PlayerTop.getName());
		
		AIHealth.setAlignment(Pos.CENTER);
		AIHealth.setStyle("-fx-background-color: #b22222;"
		+"-fx-text-fill: white;");
		AIHealth.setMinWidth(150);
		AIHealth.setFont(Font.font ("Verdana", 20));
		AIHealth.setText(Integer.toString(PlayerTop.getHealth()));
		
		
		MiddleRow.setMinHeight(300);
		MiddleRow.setAlignment(Pos.CENTER_LEFT);
		MiddleRow.setStyle("-fx-background-color: #dcdcdc;"
				+"-fx-text-fill: white;"
				+ "-fx-border-color: black;");
		
		
		playerMiddleBox.setMinWidth(350);
		playerMiddleBox.setAlignment(Pos.CENTER_RIGHT);
		
		aiMiddleBox.setMinWidth(350);
		aiMiddleBox.setAlignment(Pos.CENTER_LEFT);
		
		middleLabel.setMinWidth(100);
		middleLabel.setAlignment(Pos.CENTER);
		
		MiddleRow.getChildren().add(playerMiddleBox);
		MiddleRow.getChildren().add(middleLabel);
		MiddleRow.getChildren().add(aiMiddleBox);
		
		
		AIRow.getChildren().add(AIName);
		AIRow.getChildren().add(AIHealth);
		AIRow.getChildren().add(AIGold);
		
		PlayerRow.getChildren().add(PlayerName);
		PlayerRow.getChildren().add(PlayerHealth);
		PlayerRow.getChildren().add(PlayerGold);
		PlayerRow.getChildren().add(EndTurn);
		this.getChildren().add(Container);
			
	}
	@SuppressWarnings("rawtypes")
	private class ButtonListener implements EventHandler 
	{
		public void handle(Event e) {
			
			if(PlayerTop.getHealth() <=0 || PlayerBot.getHealth() <=0)
			{
				middleLabel.setText("Game Over");
				clearPlayingArea();
			}
			else
			{
			//clearPlayingArea();
			CalculatePlayerCost();
			CalculateAICost();
			PlayCard();
			AIPlayCard();
			updateStats();
			updateGold();
			if(PlayerTop.getHealth() <=0 || PlayerBot.getHealth() <=0)
			{
				middleLabel.setText("Game Over");
				clearPlayingArea();
			}
			
			}
		}
		
		public void updateStats()
		{
			int playerAttack=0;
			int playerDefense=0;
			int playerHeal = 0;
			int aiAttack = 0;
			int aiDefense= 0;
			int aiHeal=0;
			
			while(!PlayerTopStack.isEmpty())
			{
				aiAttack =+ PlayerTopStack.peek().getAttack();
				aiDefense =+ PlayerTopStack.peek().getDefense();
				aiHeal =+ PlayerTopStack.peek().getHeal();
				PlayerTopStack.pop();
			}
			while(!PlayerBotStack.isEmpty())
			{
				playerAttack += PlayerBotStack.peek().getAttack();
				playerDefense += PlayerBotStack.peek().getDefense();
				playerHeal += PlayerBotStack.peek().getHeal();
				PlayerBotStack.pop();
			}
			
			//AI defends
			if(playerAttack > aiAttack)
			{
				int newHP;
				//heal AI
				PlayerTop.setHealth(PlayerTop.getHealth()+aiHeal);
				PlayerBot.setHealth(PlayerBot.getHealth()+playerHeal);
				
				newHP = (playerAttack-aiAttack)-aiDefense;
			
				if(newHP <0){newHP=0;}
				
				newHP = PlayerTop.getHealth()-newHP;
				PlayerTop.setHealth(newHP);
				AIHealth.setText(Integer.toString(PlayerTop.getHealth()));
				
			}
			//player defends
			else if(playerAttack <aiAttack)
			{
				int newHP;
				//heal User
				PlayerBot.setHealth(PlayerBot.getHealth()+playerHeal);
				PlayerTop.setHealth(PlayerTop.getHealth()+aiHeal);
				
				newHP = (aiAttack - playerAttack) - playerDefense;
				
				if(newHP <0){newHP=0;}
				
				newHP = PlayerBot.getHealth()-newHP;
				
				PlayerBot.setHealth(newHP);
				PlayerHealth.setText(Integer.toString(PlayerBot.getHealth()));
				
				
			}
			//attacks are equal
			//and i have defense item
			//and i have heal items
			else
			{
				PlayerBot.setHealth(PlayerBot.getHealth()+playerHeal);
				PlayerTop.setHealth(PlayerTop.getHealth()+aiHeal);	
				PlayerHealth.setText(Integer.toString(PlayerBot.getHealth()));
				AIHealth.setText(Integer.toString(PlayerTop.getHealth()));
			}
			
		}
		public void clearPlayingArea()
		{
			playerMiddleBox.getChildren().clear();
			aiMiddleBox.getChildren().clear();
		}
	
		public void CalculatePlayerCost(){
			PlayerBotCost =0;		
			for(int j=0; j<5; j++)
			{
				if(PlayerBot.getPlayerHand().get(j).getGlow() ==true){PlayerBotCost +=PlayerBot.getPlayerHand().get(j).getCost();}
			}
		}
		public void CalculateAICost(){
			PlayerTopCost =0;
			
			PlayerTop.getPlayerHand().get(0).setGlow(); //selects card to play
			for(int j=0; j<5; j++)
			{
				if(PlayerTop.getPlayerHand().get(j).getGlow() ==true){PlayerTopCost +=PlayerTop.getPlayerHand().get(j).getCost();}
				
			}
		}
		
		public void updateGold()
		{
			PlayerBot.setGold(PlayerBot.getGold()+5);
			PlayerTop.setGold(PlayerTop.getGold()+5);
			PlayerGold.setText(Integer.toString(PlayerBot.getGold()));
			AIGold.setText(Integer.toString(PlayerTop.getGold()));
		}
		
		public void AIPlayCard()
		{
			
			if(PlayerTop.getGold()>= PlayerTopCost)
			{
				PlayerTop.setGold(PlayerTop.getGold()-PlayerTopCost);
				for(int i=0; i<5; i++)
				{
					if(PlayerTop.getPlayerHand().get(i).getGlow() ==true)
					{
						if(PlayerTop.getPlayerHand().get(i).getName() == "Skeleton"){aiMiddleBox.getChildren().add(new Card("Skeleton","Skeleton",4,0,0,2));PlayerTopStack.add(new Card("Skeleton","Skeleton",4,0,0,2));}
						else if(PlayerTop.getPlayerHand().get(i).getName() == "Slime"){aiMiddleBox.getChildren().add(new Card("Slime","Slime",2,0,0,1)); PlayerTopStack.add(new Card("Slime","Slime",2,0,0,1));}
						else if(PlayerTop.getPlayerHand().get(i).getName() == "Goblin"){aiMiddleBox.getChildren().add(new Card("Goblin","Goblin",5,0,0,3)); PlayerTopStack.add(new Card("Goblin","Goblin",5,0,0,3));}
						else if(PlayerTop.getPlayerHand().get(i).getName() == "Golem"){aiMiddleBox.getChildren().add(new Card("Golem","Golem",8,0,0,4)); PlayerTopStack.add(new Card("Golem","Golem",8,0,0,4));}
						else if(PlayerTop.getPlayerHand().get(i).getName() == "Dragon"){aiMiddleBox.getChildren().add(new Card("Dragon","Dragon",9,0,0,5)); PlayerTopStack.add(new Card("Dragon","Dragon",9,0,0,5));}
						else if(PlayerTop.getPlayerHand().get(i).getName() == "WoodenShield"){aiMiddleBox.getChildren().add(new Card("WoodenShield","WoodenShield",0,1,0,1)); PlayerTopStack.add(new Card("WoodenShield","WoodenShield",0,1,0,1));}
						else if(PlayerTop.getPlayerHand().get(i).getName() == "IronShield"){aiMiddleBox.getChildren().add(new Card("IronShield","IronShield",0,2,0,2)); PlayerTopStack.add(new Card("IronShield","IronShield",0,2,0,2));}
						else if(PlayerTop.getPlayerHand().get(i).getName() == "Potion"){aiMiddleBox.getChildren().add(new Card("Potion","Potion",0,0,1,2)); PlayerTopStack.add(new Card("Potion","Potion",0,0,1,2));}
						else if(PlayerTop.getPlayerHand().get(i).getName() == "Elixir"){aiMiddleBox.getChildren().add(new Card("Elixir","Elixir",0,0,2,4)); PlayerTopStack.add(new Card("Elixir","Elixir",0,0,2,4));}
						mediaPlayer.play();
						PlayerTop.getPlayerHand().remove(i);
						PlayerTop.clearHand();
						PlayerTop.getPlayerHand().add(i,Deck.deal(1).get(0));
						PlayerTop.playerHand(PlayerTop.getPlayerHand(), 5);
						PlayerTop.cardSelected();
					}
				}
				
			}
		}
	
		public void PlayCard(){
			if(PlayerBot.getGold()>= PlayerBotCost)
			{
				PlayerBot.setGold(PlayerBot.getGold()-PlayerBotCost);
				for(int i=0; i<5; i++)
				{
					if(PlayerBot.getPlayerHand().get(i).getGlow() ==true)
					{
						if(PlayerBot.getPlayerHand().get(i).getName() == "Skeleton"){playerMiddleBox.getChildren().add(new Card("Skeleton","Skeleton",4,0,0,2));PlayerBotStack.add(new Card("Skeleton","Skeleton",4,0,0,2));}
						else if(PlayerBot.getPlayerHand().get(i).getName() == "Slime"){playerMiddleBox.getChildren().add(new Card("Slime","Slime",2,0,0,1)); PlayerBotStack.add(new Card("Slime","Slime",2,0,0,1));}
						else if(PlayerBot.getPlayerHand().get(i).getName() == "Goblin"){playerMiddleBox.getChildren().add(new Card("Goblin","Goblin",5,0,0,3)); PlayerBotStack.add(new Card("Goblin","Goblin",5,0,0,3));}
						else if(PlayerBot.getPlayerHand().get(i).getName() == "Golem"){playerMiddleBox.getChildren().add(new Card("Golem","Golem",8,0,0,4)); PlayerBotStack.add(new Card("Golem","Golem",8,0,0,4));}
						else if(PlayerBot.getPlayerHand().get(i).getName() == "Dragon"){playerMiddleBox.getChildren().add(new Card("Dragon","Dragon",9,0,0,5)); PlayerBotStack.add(new Card("Dragon","Dragon",9,0,0,5));}
						else if(PlayerBot.getPlayerHand().get(i).getName() == "WoodenShield"){playerMiddleBox.getChildren().add(new Card("WoodenShield","WoodenShield",0,1,0,1)); PlayerBotStack.add(new Card("WoodenShield","WoodenShield",0,1,0,1));}
						else if(PlayerBot.getPlayerHand().get(i).getName() == "IronShield"){playerMiddleBox.getChildren().add(new Card("IronShield","IronShield",0,2,0,2)); PlayerBotStack.add(new Card("IronShield","IronShield",0,2,0,2));}
						else if(PlayerBot.getPlayerHand().get(i).getName() == "Potion"){playerMiddleBox.getChildren().add(new Card("Potion","Potion",0,0,1,2)); PlayerBotStack.add(new Card("Potion","Potion",0,0,1,2));}
						else if(PlayerBot.getPlayerHand().get(i).getName() == "Elixir"){playerMiddleBox.getChildren().add(new Card("Elixir","Elixir",0,0,2,4)); PlayerBotStack.add(new Card("Elixir","Elixir",0,0,2,4));}
						mediaPlayer.play();
						PlayerBot.getPlayerHand().remove(i);
						PlayerBot.clearHand();
						PlayerBot.getPlayerHand().add(i,Deck.deal(1).get(0));
						PlayerBot.playerHand(PlayerBot.getPlayerHand(), 5);
						PlayerBot.cardSelected();
						
						
							
					}
				}
			}
		}
	}
}

