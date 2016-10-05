package Game;
import java.io.File;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Card extends ImageView {

	private String name;
	private int Attack, Defense, Heal, Cost;
	private Boolean Glow=false;
	
	
	public Card(String image, String Name, int attack, int defense, int heal, int cost)
	{
		this.Image(image);
		this.setName(Name);
		this.setAttack(attack);
		this.setDefense(defense);
		this.setHeal(heal);
		this.setCost(cost);
		this.setFitWidth(150);
		this.setFitHeight(225);
	}
	public void Image(String cardChoice)
	{
		if(cardChoice == "Skeleton")
		{
			Image stuff = new Image("Cards/skeleton.png");
			this.setImage(stuff);	
		}
		else if (cardChoice == "Dragon")
		{
			Image stuff = new Image("Cards/dragon.png");
			this.setImage(stuff);	
		}
		else if(cardChoice == "Elixir")
		{
			Image stuff = new Image("Cards/elixir.png");
			this.setImage(stuff);	
		}
		else if(cardChoice == "Goblin")
		{
			Image stuff = new Image("Cards/goblin.png");
			this.setImage(stuff);	
		}
		else if(cardChoice == "Golem")
		{
			Image stuff = new Image("Cards/golem.png");
			this.setImage(stuff);	
		}
		else if(cardChoice == "IronShield")
		{
			Image stuff = new Image("Cards/ironshield.png");
			this.setImage(stuff);	
		}
		else if(cardChoice == "Potion")
		{
			Image stuff = new Image("Cards/potion.png");
			this.setImage(stuff);	
		}
		else if(cardChoice == "Slime")
		{
			Image stuff = new Image("Cards/slime.png");
			this.setImage(stuff);	
		}
		else if(cardChoice == "WoodenShield")
		{
			Image stuff = new Image("Cards/woodenshield.png");
			this.setImage(stuff);	
		}
	}
	
	public void setName(String inName)
	{
		name = inName;
	}
	public String getName()
	{
		return name;
	}
	
	public void setAttack(int inAttack)
	{
	Attack = inAttack;
	}
	public int getAttack()
	{
		return Attack;
	}
	
	public void setDefense(int inDefense)
	{
	Defense = inDefense;	
	}
	public int getDefense()
	{
		return Defense;
	}
	public void setHeal(int inHeal)
	{
		Heal = inHeal;
	}
	public int getHeal()
	{
		return Heal;
	}
	public void setCost(int inCost)
	{
		Cost = inCost;
	}
	public int getCost()
	{
		return Cost;
	}
	public void setGlow()
	{
		 DropShadow dropShadow = new DropShadow();
		 dropShadow.setRadius(5.0);
		 dropShadow.setOffsetX(3.0);
		 dropShadow.setOffsetY(3.0);
		 dropShadow.setColor(Color.STEELBLUE);
		 Glow  glow = new Glow(0.5);
		 dropShadow.setInput(glow);
		 this.setEffect(dropShadow);
		 Media sound = new Media(new File("click.mp3").toURI().toString());
		 MediaPlayer mediaPlayer = new MediaPlayer(sound);
		 mediaPlayer.play();
		 
        Glow = true;
	}
	public void clearGlow()
	{

		 Media sound = new Media(new File("click2.mp3").toURI().toString());
		 MediaPlayer mediaPlayer = new MediaPlayer(sound);
		 mediaPlayer.play();
        this.setEffect(null);
        Glow=false;
	}
	public Boolean getGlow()
	{
		return Glow;
	}
}
