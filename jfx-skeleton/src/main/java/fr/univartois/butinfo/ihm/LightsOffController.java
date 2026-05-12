package fr.univartois.butinfo.ihm;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import javafx.scene.control.Label; 
import java.util.Random;
import javafx.event.ActionEvent;

public class LightsOffController {

	@FXML
	private GridPane lightGrid;
    
	private Button[][] lightButtons = new Button[5][5];
	
	private GameGrid gameGrid = new GameGrid();
	
	@FXML
	private Label monLabel;
	
	@FXML
	void initialize() {
	    for (Node child : lightGrid.getChildren()) {
	        Integer row = GridPane.getRowIndex(child);
	        if (row == null) {
	            row = 0;
	        }
	        Integer col = GridPane.getColumnIndex(child);
	        if (col == null) {
	            col = 0;
	        }
	        if (child instanceof Button button) {
	            lightButtons[row][col] = button;
	        }
	    }
	}
	
	void updateView() {
		for(int i=0;i<lightButtons.length;i++) {
			for(int y=0;y<lightButtons.length;y++) {
				monLabel.setText("Coups : " + gameGrid.getNbCoups());
				if(gameGrid.isOn(i, y)==true) {
					lightButtons[i][y].setText("*");
					lightButtons[i][y].setStyle("-fx-background-color: #FFFAF0;");
				}
				else {
					lightButtons[i][y].setText("");
					lightButtons[i][y].setStyle("-fx-background-color: #000000;");
				}
			}
		}
	}
	
	@FXML
	void onRestart() {
	    gameGrid.init(); // une seule fois avant la boucle
	    Random random = new Random(); // une seule fois aussi
	    for (int o = 0; o < 10; o++) {
	        gameGrid.switchAt(random.nextInt(5), random.nextInt(5));
	    }
	    gameGrid.resetNbCoups();
	    updateView();
	}

	@FXML
	public void onLightClick(ActionEvent event) {
	    Button button = (Button) event.getSource();
	    Integer row = GridPane.getRowIndex(button);
	    if (row == null) row = 0;
	    Integer col = GridPane.getColumnIndex(button);
	    if (col == null) col = 0;
	    
	    gameGrid.switchAt(row, col); // joue le coup
	    updateView(); // met à jour l'affichage
	    
	    if (gameGrid.isOff()) {
	        lightGrid.setDisable(true); // partie gagnée !
	    }
	}
}