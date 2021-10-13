package com.company;


import com.company.Fields.AngularField;
import com.company.Fields.CommonField;
import com.company.Fields.Field;
import com.company.figures.*;

import java.util.ArrayList;

public class GraphChessBoard {
    private final int BOARD_LENDS = 10;
    private Field[][] commonFields = new Field[BOARD_LENDS][BOARD_LENDS];
    private Field[] angularFields = new Field[4];

    public int getBOARD_LENDS() {
        return BOARD_LENDS;
    }

    public Field getCommonField(int x, int y) {
        return commonFields[x][y];
    }

    public Field getAngularField(int w) {
        return angularFields[w];
    }

    public GraphChessBoard() {
        for (int i = 0; i < 4; i++) {
            angularFields[i] = new AngularField(i);
        }
        for (int i = 0; i <  BOARD_LENDS; i++) {
            for (int j = 0; j < BOARD_LENDS; j++) {
                commonFields[i][j] = new CommonField(i, j);
            }
        }
        angularFields[0].setFigure(new Magician(true));
        angularFields[1].setFigure(new Magician(true));
        angularFields[2].setFigure(new Magician(false));
        angularFields[3].setFigure(new Magician(false));

        for (int i = 0; i < BOARD_LENDS; i++) {
            commonFields[i][1] = new CommonField(i,1);
            commonFields[i][1].setFigure(new Pawn(false));
            commonFields[i][8] = new CommonField(i,8);
            commonFields[i][8].setFigure(new Pawn(true));
        }

        commonFields[0][0].setFigure(new Champion(false));
        commonFields[0][9].setFigure(new Champion(true));
        commonFields[9][0].setFigure(new Champion(false));
        commonFields[9][9].setFigure(new Champion(true));

        commonFields[1][0].setFigure(new Rook(false));
        commonFields[1][9].setFigure(new Rook(true));
        commonFields[8][0].setFigure(new Rook(false));
        commonFields[8][9].setFigure(new Rook(true));

        commonFields[2][0].setFigure(new Horse(false));
        commonFields[2][9].setFigure(new Horse(true));
        commonFields[7][0].setFigure(new Horse(false));
        commonFields[7][9] .setFigure(new Horse(true));

        commonFields[3][0].setFigure(new Elephant(false));
        commonFields[3][9].setFigure(new Elephant(true));
        commonFields[6][0].setFigure(new Elephant(false));
        commonFields[6][9].setFigure(new Elephant(true));

        commonFields[4][0].setFigure(new Queen(false));
        commonFields[4][9].setFigure(new Queen(true));

        commonFields[5][0].setFigure(new King(false));
        commonFields[5][9].setFigure(new King(true));
        buildGraphConnections();
    }

    public void buildGraphConnections(){
        for (int i = 0; i < 4; i++) {
            if(angularFields[i].getFigure() != null){
                buildConnectionsFor1Field(angularFields[i]);
            }
        }
        for (int i = 0; i < BOARD_LENDS; i++) {
            for (int j = 0; j < BOARD_LENDS; j++) {
                if(commonFields[i][j] != null) {
                    buildConnectionsFor1Field(commonFields[i][j]);
                }
            }
        }
    }

    public void buildConnectionsFor1Field(Field f){
        f.setCanGoFromThis(new ArrayList<>());
        if (f.getFigure() instanceof Pawn) FiguresMoves.PawnCanGoTo(this, (CommonField) f, f.getFigure().isWhite());
        if (f.getFigure() instanceof Horse) FiguresMoves.HorseCanGoTo(this, f, f.getFigure().isWhite());
        if (f.getFigure() instanceof King) FiguresMoves.KingCanGoTo(this, f, f.getFigure().isWhite());
        if (f.getFigure() instanceof Queen)  FiguresMoves.QueenCanGoTo(this, f, f.getFigure().isWhite());
        if (f.getFigure() instanceof Rook) FiguresMoves.RookCanGoTo(this, (CommonField) f, f.getFigure().isWhite());
        if (f.getFigure() instanceof Elephant) FiguresMoves.ElephantCanGoTo(this, f, f.getFigure().isWhite());
        if (f.getFigure() instanceof Champion) FiguresMoves.ChampionCanGoTo(this, f, f.getFigure().isWhite());
        if (f.getFigure() instanceof Magician) FiguresMoves.MagicianCanGoTo(this, f, f.getFigure().isWhite());
    }

}
