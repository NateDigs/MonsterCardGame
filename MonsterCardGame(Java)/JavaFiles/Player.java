package Game;

import java.util.LinkedList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class Player extends HBox {
	private LinkedList<Card> PlayerHand = new LinkedList<Card>();
	private String Name;
	private int Gold;
	private int Health;
	public Player()
	{
		Gold=5;
		Health =20;
		this.setAlignment(Pos.CENTER);
	}
	
	public void playerHand(LinkedList<Card> dealtCard, int numberOfCards)
	{
		
		for(int i=0; i<numberOfCards; i++)
		{
			PlayerHand.add(dealtCard.get(i));
			this.getChildren().add(PlayerHand.get(i));
			
		}
	}
	public void clearHand()
	{
		this.getChildren().clear();
	}
	
	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public void setGold(int inGold)
	{
		Gold = inGold;
	}
	public int getGold()
	{
		return Gold;
	}
	public void setHealth(int inHealth)
	{
		Health = inHealth;
	}
	public int getHealth()
	{
		return Health;
	}
	public LinkedList<Card> getPlayerHand()
	{
		return PlayerHand;
	}
	public void cardSelected()
	{
			PlayerHand.get(0).onMouseClickedProperty().set(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event) {
				
				
				if(PlayerHand.get(0).getGlow()==false)
				{
					PlayerHand.get(0).setGlow();
					
				}
				else
				{
					PlayerHand.get(0).clearGlow();
					
				}
			}
			});
			PlayerHand.get(1).onMouseClickedProperty().set(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event) {
				
				
				if(PlayerHand.get(1).getGlow()==false)
				{
					PlayerHand.get(1).setGlow();
					
				}
				else
				{
					PlayerHand.get(1).clearGlow();
				}
			}
			});
			PlayerHand.get(2).onMouseClickedProperty().set(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event) {
				
				
				if(PlayerHand.get(2).getGlow()==false)
				{
					PlayerHand.get(2).setGlow();
				}
				else
				{
					PlayerHand.get(2).clearGlow();
				}
			}
			});
			PlayerHand.get(3).onMouseClickedProperty().set(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event) {
				
				
				if(PlayerHand.get(3).getGlow()==false)
				{
					PlayerHand.get(3).setGlow();
				}
				else
				{
					PlayerHand.get(3).clearGlow();
				}
			}
			});
		
			PlayerHand.get(4).onMouseClickedProperty().set(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event) {
				
				
				if(PlayerHand.get(4).getGlow()==false)
				{
					PlayerHand.get(4).setGlow();
				}
				else
				{
					PlayerHand.get(4).clearGlow();
				}
			}
			});
		
		
		
	}
}
