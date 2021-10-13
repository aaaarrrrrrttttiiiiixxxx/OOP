package com.company;

import com.company.Fields.Field;

public class Game {
    private GraphChessBoard gBoard = new GraphChessBoard();
    private boolean whitesTurn = true;
    private Field chosenField = null;

    public GraphChessBoard getBoard() {
        return gBoard;
    }

    public void MouseClick(Field f){
        if(chosenField == null) {
            if(f.getFigure() != null){
                if (f.getFigure().isWhite() == whitesTurn) {
                    chosenField = f;
                }
            }
        }
        else{
            if(f.getFigure() != null){
                if (f.getFigure().isWhite() == whitesTurn) {
                    chosenField = f;
                }
            }
            for (Field ff: chosenField.getCanGoFromThis()) {
                if(ff.equals(f)){
                    Turn(chosenField, f);
                    chosenField = null;
                    break;
                }
            }
        }
    }

    private void Turn (Field from, Field to){
        to.setFigure(from.getFigure());
        from.setFigure(null);
        gBoard.buildGraphConnections();
        whitesTurn = !whitesTurn;
    }
}