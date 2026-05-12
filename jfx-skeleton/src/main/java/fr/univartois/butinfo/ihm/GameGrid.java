package fr.univartois.butinfo.ihm;
public class GameGrid {

    private static final int SIZE = 5;
    private boolean[][] lights = new boolean[SIZE][SIZE];
    private int nbCoups = 0;
    
    public int getNbCoups() {
        return nbCoups;
    }
    
    public void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                lights[i][j] = false;
            }
        }
    }

    public boolean isOn(int row, int column) {
        return lights[row][column];
    }

    public boolean isOff() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (lights[i][j] == true) {
                    return false; 
                }
            }
        }
        return true;
    }
    
    public void resetNbCoups() {
        nbCoups = 0;
    }
    
    private void toggle(int row, int column) {
        if (row >= 0 && row < SIZE && column >= 0 && column < SIZE) {
            lights[row][column] = !lights[row][column];
        }
    }

    public void switchAt(int row, int column) {
        toggle(row, column);     // la case cliquée
        toggle(row - 1, column); // haut
        toggle(row + 1, column); // bas
        toggle(row, column - 1); // gauche
        toggle(row, column + 1); // droite
        nbCoups++;
    }

}