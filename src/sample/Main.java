package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.*;


public class Main extends Application {

    public static int TILE_WIDTH = 60;
    public static int TILE_HEIGHT = 60;
    public static int SCREEN_WIDTH = 600;
    public static int SCREEN_HEIGHT = 360;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root,SCREEN_WIDTH, SCREEN_HEIGHT);
        Canvas canvas = new Canvas(((SCREEN_WIDTH/TILE_WIDTH)+50)*TILE_WIDTH, ((SCREEN_HEIGHT/TILE_HEIGHT)+50)*TILE_HEIGHT);
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        Camera parallelCamera = new ParallelCamera();

        scene.setCamera(parallelCamera);

        GraphicsContext gc = canvas.getGraphicsContext2D();


        Character character = new Character();
        Image[][] tiles = createMap();


        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP){

                character.setPositionY(character.getPositionY()-TILE_HEIGHT);
                parallelCamera.setTranslateY(parallelCamera.getTranslateY()-TILE_HEIGHT);


            }
            else if (event.getCode() == KeyCode.DOWN){

                character.setPositionY(character.getPositionY()+TILE_HEIGHT);
                parallelCamera.setTranslateY(parallelCamera.getTranslateY()+TILE_HEIGHT);


            }
            else if (event.getCode() == KeyCode.RIGHT){

                character.setPositionX(character.getPositionX()+TILE_WIDTH);
                parallelCamera.setTranslateX(parallelCamera.getTranslateX()+TILE_WIDTH);



            }

            else if (event.getCode() == KeyCode.LEFT){

                character.setPositionX(character.getPositionX()-TILE_WIDTH);

                parallelCamera.setTranslateX(parallelCamera.getTranslateX()-TILE_WIDTH);

            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {

                gc.clearRect(0,0 , gc.getCanvas().getWidth(),gc.getCanvas().getHeight());


                drawTiles(gc,tiles,character);
                character.drawCharacter(gc,character.positionX,character.positionY);

                System.out.println(character.positionX);
                System.out.println(character.positionY);




            }
        }.start();



        primaryStage.show();
    }

    public Image[][] createMap(){
        int cols = SCREEN_HEIGHT/TILE_HEIGHT;
        int rows = SCREEN_WIDTH/TILE_WIDTH;

        Image[][] images = new Image[rows+50][cols+50];

        for (int i = 0; i < rows+50; i++){
            for (int j = 0; j < cols+50;j++){

                images[i][j] = i!=j?new Image(getClass().getResourceAsStream("Path1.png"),TILE_WIDTH,TILE_HEIGHT,false,false):new Image(getClass().getResourceAsStream("Lava.png"),TILE_WIDTH,TILE_HEIGHT,false,false);


            }
        }
        return images;

    }



    public void drawTiles(GraphicsContext gc,Image[][] tiles,Character character){

//        To only display tiles around you in viewport
//        For example: Your screen size is 500x500, your tiles are 100x100, and your world size is 1000x1000.
// You would simply need to do a quick check before drawing tiles to find out which tiles are on screen. Using
// the location of your viewport - let's say 400x500 - you would only need to draw tiles from rows 4 to 9 and
// columns 5 to 10. You can get those numbers by dividing the location by the tile size, then offsetting those
// values with the screen size divided by the tile size.

        int cols = SCREEN_HEIGHT/TILE_HEIGHT;
        int rows = SCREEN_WIDTH/TILE_WIDTH;
        int startCol = (character.positionY/TILE_HEIGHT)-(cols/2);
        int startRow = (character.positionX/TILE_WIDTH)-(rows/2);
        int endCol =(character.positionY/TILE_HEIGHT)+(cols/2);
        int endRow =(character.positionX/TILE_HEIGHT)+(rows/2);



        for (int i = startRow; i <= endRow+1; i++){
            for (int j = startCol; j <= endCol+1;j++){

                gc.drawImage(tiles[i][j],i * TILE_WIDTH,j*TILE_HEIGHT);


            }
        }



    }

    public static void main(String[] args) {
        launch(args);
    }
}
