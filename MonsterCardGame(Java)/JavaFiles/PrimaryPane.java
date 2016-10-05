package Game;


import javafx.scene.layout.BorderPane;

public class PrimaryPane extends BorderPane {

	
	private CenterBox CenterBox = new CenterBox();
	
	private TopBox TopBox = new TopBox();
	
	//private RightBox RightBox = new RightBox();
	//private LeftBox LeftBox = new LeftBox();
	
	
	public PrimaryPane()
	{
		this.setStyle("-fx-background-color: #c0c0c0;"
				+"-fx-text-fill: black;"
				+"-fx-border-style: solid inside;"
		        + "-fx-border-width: .5;" 
				+ "-fx-border-outsets: 5;"
		        + "-fx-border-radius: 1;"
				+ "-fx-padding: 10;");
		
		//setTop(TopBox);
		//setRight(RightBox);
		//setLeft(LeftBox);
		setCenter(CenterBox);
		
	}
	

}
