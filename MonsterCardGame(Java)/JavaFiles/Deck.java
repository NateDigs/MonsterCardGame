package Game;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

import javafx.scene.layout.HBox;

public class Deck extends HBox{
	private LinkedList<Card> cardDeck = new LinkedList<Card>();
	private LinkedList<Card> dealCard = new LinkedList<Card>();
	private Stack<Card> shuffledDeck = new Stack<Card>();
	private int randUpper=9;
	
	public Deck(){}

	
	public void createDeck()
	{
		for(int i=0; i<9; i++)
		{
		cardDeck.add(new Card("Skeleton",		"Skeleton",				4 , 0 , 0 , 2 ));
		cardDeck.add(new Card("Slime",			"Slime",				2 , 0 , 0 , 1 ));
		cardDeck.add(new Card("Goblin", 		"Goblin",				5 , 0 , 0 , 3 ));
		cardDeck.add(new Card("Golem", 			"Golem", 				8 , 0 , 0 , 4 ));
		cardDeck.add(new Card("Dragon",		 	"Dragon", 				9 , 0 , 0 , 5 ));
		cardDeck.add(new Card("WoodenShield",	"WoodenShield", 		0 , 1 , 0 , 1 ));
		cardDeck.add(new Card("IronShield", 	"IronShield", 			0 , 2 , 0 , 2 ));
		cardDeck.add(new Card("Potion", 		"Potion", 				0 , 0 , 1 , 2 ));
		cardDeck.add(new Card("Elixir", 		"Elixir", 				0 , 0 , 2 , 4 ));
		}
	}
	
	
	
	public void Shuffle()
	{
		randUpper=81;
			for(int i=0; i<81; i++)
			{
				int x = 0 +(int)(Math.random()*randUpper);
				shuffledDeck.push(cardDeck.get(x));
				cardDeck.remove(x);
				randUpper--;
			}
			this.getChildren().clear();
	}

	public LinkedList<Card> deal(int dealNumber)
	{
	dealCard.clear();
	if(dealNumber==0){}
	else
		{		
		try
			{
			for(int i=0; i<dealNumber; i++)
				{
					dealCard.add(shuffledDeck.pop());
				}

			}
		catch(EmptyStackException e)
			{
				createDeck();
				Shuffle();
				for(int i=0; i<dealNumber; i++)
				{
					dealCard.add(shuffledDeck.pop());
				}
	
			}
		}
	return dealCard;
	}
}
