package model;
import java.util.Random;

import java.util.ArrayList;

public class Tablero {
    private final int TAMANO = 10;
    private int[][] tableroReal;
    private int[][] tablero;
    private final Random random = new Random();
    public Tablero() {
        tablero = new int[TAMANO][TAMANO];
        tableroReal = new int[TAMANO][TAMANO];
    }

    public void iniciadorTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = 0;
                tableroReal[i][j] = 0;
            }
        }
    }

    public void colocarBombas(int numeroBombas) {
        for (int i = 0; i < numeroBombas; i++) {

            int x = random.nextInt(TAMANO);
            int y = random.nextInt(TAMANO);

            tableroReal[x][y] = 1;
        }
    }

    public boolean verificarImpacto(int x, int y){
        if(tableroReal[x][y]==1){
            tablero[x][y]=3;
            tableroReal[x][y]=3;
            return true;
        }
        tablero[x][y]= 2;
        tableroReal[x][y]=2;
        return false;
    }


    public void mostrarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void mostrarTableroREAL() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                System.out.print(tableroReal[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getTablero(){
        return tablero;
    }
    public int[][] getTableroReal(){
        return tableroReal;
    }
}
