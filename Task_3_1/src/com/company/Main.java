package com.company;

import com.company.figures.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main  extends Application {

    private static Game game = new Game();
    private static GridPane buttonPane = new GridPane();
    private static Button[] bb = new Button[4];
    private static Button[][] buttons = new Button[10][10];

    private static String name(Figure f){
        String res = "";
        if(f == null) return res;
        if(f.isWhite()) {
            res += "white\n   ";
        }else{
            res += "black\n   ";
        }
        if(f instanceof Pawn) return res + "P";
        if(f instanceof King) return res + "K";
        if(f instanceof Queen) return res + "Q";
        if(f instanceof Elephant) return res + "E";
        if(f instanceof Horse) return res + "H";
        if(f instanceof Champion) return res + "C";
        if(f instanceof Magician) return res + "M";
        if(f instanceof Rook) return res + "R";
        else return " ";
    }

    private static Button but(int i, int j){
        Button b = new Button(name(game.getBoard().getCommonField(i - 1 , j - 1).getFigure()));
        b.setMaxWidth(Double.MAX_VALUE);
        b.setMaxHeight(Double.MAX_VALUE);
        b.setOnMouseReleased(event ->  {
            game.MouseClick(game.getBoard().getCommonField(i - 1, j - 1));
            reSetText();
        });
        return b;
    }

    public static void reSetText(){
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
               buttons[i - 1][j - 1].setText(name(game.getBoard().getCommonField(i - 1 , j - 1).getFigure()));
            }
        }
        for (int i = 0; i < 4; i++) {
            bb[i].setText(name(game.getBoard().getAngularField(i).getFigure()));
        }
    }
    private static Button but2(Game game, int w){
        Button b = new Button(name(game.getBoard().getAngularField(w).getFigure()));
        b.setMaxWidth(Double.MAX_VALUE);
        b.setMaxHeight(Double.MAX_VALUE);
        b.setOnMouseReleased(event ->  {
            game.MouseClick(game.getBoard().getAngularField(w));
            reSetText();
        });
        return b;
    }

    @Override
    public void start(Stage stage) {
        stage.setWidth(800);
        stage.setHeight(800);

        for (int i = 0; i < 12; i++) {
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(50);
            buttonPane.getColumnConstraints().add(column1);
            RowConstraints row1 = new RowConstraints();
            row1.setPercentHeight(50);
            buttonPane.getRowConstraints().add(row1);
        }

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                buttons[i - 1][j - 1] = but(i, j);
                buttonPane.add(buttons[i - 1][j - 1], i, j);
            }
        }

        bb[3] =  but2(game, 3);
        bb[2] =  but2(game, 2);
        bb[1] =  but2(game, 1);
        bb[0] =  but2(game, 0);
        buttonPane.add(bb[3], 0, 0);
        buttonPane.add(bb[0], 0, 11);
        buttonPane.add(bb[2], 11, 0);
        buttonPane.add(bb[1], 11, 11);

        Scene scene = new Scene(buttonPane, 0, 0);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
