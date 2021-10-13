package com.company.Fields;

import com.company.GraphChessBoard;
import com.company.figures.Figure;

import java.util.List;

public abstract class Field {
    private List<Field> canGoFromThis;
    private Figure figure;

    public Field() {
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public List<Field> getCanGoFromThis() {
        return canGoFromThis;
    }

    public void setCanGoFromThis(List<Field> canGoFromThis) {
        this.canGoFromThis = canGoFromThis;
    }

    public void addCanGoFromThis(Field f){
        canGoFromThis.add(f);
    }
}
