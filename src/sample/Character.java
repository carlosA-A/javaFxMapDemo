package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Character  {

    int positionX;
    int positionY;
    Image sprite;

    Character(){
        sprite = new Image(getClass().getResourceAsStream("stickGuy.png"),Main.TILE_WIDTH,Main.TILE_HEIGHT,false,false);
        positionY = (((Main.SCREEN_HEIGHT/Main.TILE_HEIGHT))/2)*Main.TILE_HEIGHT;
        positionX = (((Main.SCREEN_WIDTH/Main.TILE_WIDTH))/2)*Main.TILE_WIDTH;
        System.out.println(positionX);
        System.out.println(positionY);
    }


    public void drawCharacter(GraphicsContext gc,int x, int y){

        gc.drawImage(sprite,x,y);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
}
