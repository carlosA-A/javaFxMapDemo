package sample;

import javafx.scene.image.Image;

public class Tile {

    int positionX;
    int positionY;
    Image sprite;

    Tile(int x,int y){
        sprite = new Image(getClass().getResourceAsStream("Path1.png"),Main.TILE_WIDTH,Main.TILE_HEIGHT,false,false);
        positionX = x*Main.TILE_WIDTH;
        positionY = y*Main.TILE_HEIGHT;
        System.out.println(positionX);
        System.out.println(positionY);

    }

}
