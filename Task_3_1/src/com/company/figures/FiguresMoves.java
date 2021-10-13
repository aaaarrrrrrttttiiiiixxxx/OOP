package com.company.figures;

import com.company.Fields.AngularField;
import com.company.Fields.CommonField;
import com.company.Fields.Field;
import com.company.GraphChessBoard;
import java.util.List;

public class FiguresMoves {
    public static void KingCanGoTo(GraphChessBoard bord, Field f, Boolean isWhite){
        if(f instanceof AngularField){
            switch (((AngularField) f).getW()) {
                case (0) -> {
                    TryTogoToField(bord.getCommonField(0, bord.getBOARD_LENDS() - 1), isWhite, f);
                }
                case (1) -> {
                    TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 1, bord.getBOARD_LENDS() - 1), isWhite, f);
                }
                case (2) -> {
                    TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 1, 0), isWhite, f);
                }
                case (3) -> {
                    TryTogoToField(bord.getCommonField(0, 0), isWhite, f);
                }
            }
        }
        if(f instanceof CommonField){
            for (int i = ((CommonField) f).getX() - 1; i <= ((CommonField) f).getX() + 1; i++) {
                for (int j = ((CommonField) f).getY() - 1; j <= ((CommonField) f).getY() + 1; j++) {
                    if(i >= 0 && j >= 0 && i < bord.getBOARD_LENDS() && j < bord.getBOARD_LENDS()) {
                        TryTogoToField(bord.getCommonField(i, j), isWhite, f);
                    }
                }
            }
            if(((CommonField) f).getX() == 0){
                if(((CommonField) f).getY() == 0){
                    TryTogoToField(bord.getAngularField(3), isWhite, f);
                }
                if(((CommonField) f).getY() == bord.getBOARD_LENDS() - 1){
                    TryTogoToField(bord.getAngularField(0), isWhite, f);
                }
            }
            if(((CommonField) f).getX() == bord.getBOARD_LENDS() - 1){
                if(((CommonField) f).getY() == 0){
                    TryTogoToField(bord.getAngularField(2), isWhite, f);
                }
                if(((CommonField) f).getY() == bord.getBOARD_LENDS() - 1){
                    TryTogoToField(bord.getAngularField(1), isWhite, f);
                }
            }
        }
    }

    public static void PawnCanGoTo(GraphChessBoard bord, CommonField f, boolean isWhite){
        int n = 1;
        if (!isWhite){
            if(f.getY() == 1) n = 3;
            for (int i = 1; i <= n; i++) {
                if(bord.getCommonField(f.getX(),f.getY() + i).getFigure() == null) {
                    f.addCanGoFromThis(bord.getCommonField(f.getX(), f.getY() + i));
                }
                else break;
            }
            if(f.getX() + 1 < bord.getBOARD_LENDS() ){
                if(bord.getCommonField(f.getX() + 1,f.getY() + 1).getFigure() != null) {
                    if(bord.getCommonField(f.getX() + 1,f.getY() + 1).getFigure().isWhite()) {
                        f.addCanGoFromThis(bord.getCommonField(f.getX() + 1, f.getY() + 1));
                    }
                }
            }
            if(f.getX() - 1 >= 0){
                if(bord.getCommonField(f.getX() - 1,f.getY() + 1).getFigure() != null) {
                    if(bord.getCommonField(f.getX() - 1,f.getY() + 1).getFigure().isWhite()) {
                        f.addCanGoFromThis(bord.getCommonField(f.getX() - 1, f.getY() + 1));
                    }
                }
            }
        }
        else{
            if(f.getY() == 8) n = 3;
            for (int i = 1; i <= n; i++) {
                if(bord.getCommonField(f.getX(),f.getY() - i).getFigure() == null) {
                    f.addCanGoFromThis(bord.getCommonField(f.getX(), f.getY() - i));
                }
                else break;
            }
            if(f.getX() + 1 < bord.getBOARD_LENDS() ){
                if(bord.getCommonField(f.getX() + 1,f.getY() - 1).getFigure() != null) {
                    if(!bord.getCommonField(f.getX() + 1,f.getY() - 1).getFigure().isWhite()) {
                        f.addCanGoFromThis(bord.getCommonField(f.getX() + 1, f.getY() - 1));
                    }
                }
            }
            if(f.getX() - 1 >= 0){
                if(bord.getCommonField(f.getX() - 1,f.getY() - 1).getFigure() != null) {
                    if(!bord.getCommonField(f.getX() - 1,f.getY() - 1).getFigure().isWhite()) {
                        f.addCanGoFromThis(bord.getCommonField(f.getX() - 1, f.getY() - 1));
                    }
                }
            }
        }
    }

    public static void RookCanGoTo(GraphChessBoard bord, CommonField f, boolean isWhite){
        int fieldsUp = f.getY();
        int fieldsDown = bord.getBOARD_LENDS() - f.getY() - 1;
        int fieldsLeft = f.getX();
        int fieldsRight = bord.getBOARD_LENDS() - f.getX() - 1;
        for (int i = 1; i <= fieldsUp; i++) {
            if(bord.getCommonField(f.getX(), f.getY() - i).getFigure() == null){
                f.addCanGoFromThis(bord.getCommonField(f.getX(), f.getY() - i));
                continue;
            }
            if(bord.getCommonField(f.getX(), f.getY() - i).getFigure().isWhite() != isWhite){
                f.addCanGoFromThis(bord.getCommonField(f.getX(), f.getY() - i));
                continue;
            }
            break;
        }
        for (int i = 1; i <= fieldsDown; i++) {
            if(bord.getCommonField(f.getX(), f.getY() + i).getFigure() == null){
                f.addCanGoFromThis(bord.getCommonField(f.getX(), f.getY() + i));
                continue;
            }
            if(bord.getCommonField(f.getX(), f.getY() + i).getFigure().isWhite() != isWhite){
                f.addCanGoFromThis(bord.getCommonField(f.getX(), f.getY() + i));
                continue;
            }
            break;
        }
        for (int i = 1; i <= fieldsLeft; i++) {
            if(bord.getCommonField(f.getX() - i, f.getY()).getFigure() == null){
                f.addCanGoFromThis(bord.getCommonField(f.getX() - i, f.getY()));
                continue;
            }
            if(bord.getCommonField(f.getX() - i, f.getY()).getFigure().isWhite() != isWhite){
                f.addCanGoFromThis(bord.getCommonField(f.getX() - i, f.getY()));
                continue;
            }
            break;
        }
        for (int i = 1; i <= fieldsRight; i++) {
            if(bord.getCommonField(f.getX() + i, f.getY()).getFigure() == null){
                f.addCanGoFromThis(bord.getCommonField(f.getX() + i, f.getY()));
                continue;
            }
            if(bord.getCommonField(f.getX() + i, f.getY()).getFigure().isWhite() != isWhite){
                f.addCanGoFromThis(bord.getCommonField(f.getX() + i, f.getY()));
                continue;
            }
            break;
        }
    }

    private static void ElephantCanGoToCommon(GraphChessBoard bord, CommonField f, boolean isWhite) {
        int fieldsUp = f.getY();
        int fieldsDown = bord.getBOARD_LENDS() - f.getY() - 1;
        int fieldsLeft = f.getX();
        int fieldsRight = bord.getBOARD_LENDS() - f.getX() - 1;
        int fieldsLeftUp;
        int fieldsLeftDown;
        int fieldsRightUp;
        int fieldsRightDown;
        fieldsLeftUp = Math.min(fieldsLeft, fieldsUp);
        fieldsLeftDown = Math.min(fieldsLeft, fieldsDown);
        fieldsRightUp = Math.min(fieldsRight, fieldsUp);
        fieldsRightDown = Math.min(fieldsRight, fieldsDown);
        for (int i = 1; i <= fieldsLeftUp; i++) {
            if(bord.getCommonField(f.getX() - i, f.getY() - i).getFigure() == null){
                f.addCanGoFromThis(bord.getCommonField(f.getX() - i, f.getY() - i));
                continue;
            }
            if(bord.getCommonField(f.getX() - i, f.getY() - i).getFigure().isWhite() != isWhite){
                f.addCanGoFromThis(bord.getCommonField(f.getX() - i, f.getY() - i));
            }
            break;
        }
        for (int i = 1; i <= fieldsRightUp; i++) {
            if(bord.getCommonField(f.getX() + i, f.getY() - i).getFigure() == null){
                f.addCanGoFromThis(bord.getCommonField(f.getX() + i, f.getY() - i));
                continue;
            }
            if(bord.getCommonField(f.getX() + i, f.getY() - i).getFigure().isWhite() != isWhite){
                f.addCanGoFromThis(bord.getCommonField(f.getX() + i, f.getY() - i));
            }
            break;
        }
        for (int i = 1; i <= fieldsLeftDown; i++) {
            if(bord.getCommonField(f.getX() - i, f.getY() + i).getFigure() == null){
                f.addCanGoFromThis(bord.getCommonField(f.getX() - i, f.getY() + i));
                continue;
            }
            if(bord.getCommonField(f.getX() - i, f.getY() + i).getFigure().isWhite() != isWhite){
                f.addCanGoFromThis(bord.getCommonField(f.getX() - i, f.getY() + i));
            }
            break;
        }
        for (int i = 1; i <= fieldsRightDown; i++) {
            if(bord.getCommonField(f.getX() + i, f.getY() + i).getFigure() == null){
                f.addCanGoFromThis(bord.getCommonField(f.getX() + i, f.getY() + i));
                continue;
            }
            if(bord.getCommonField(f.getX() + i, f.getY() + i).getFigure().isWhite() != isWhite){
                f.addCanGoFromThis(bord.getCommonField(f.getX() + i, f.getY() + i));
            }
            break;
        }
    }

    public static void ElephantCanGoTo(GraphChessBoard bord, Field f, boolean isWhite) {
        if(f instanceof CommonField){
            ElephantCanGoToCommon(bord, (CommonField) f,isWhite);
            List<Field> l = f.getCanGoFromThis();
            for (Field rf:l) {
                if(rf instanceof CommonField){
                    if(((CommonField) rf).getY() == 0 && ((CommonField) rf).getX() == 0 &&
                    bord.getCommonField(0, 0).getFigure() == null){
                        TryTogoToField(bord.getAngularField(3), isWhite, f);
                    }else
                    if(((CommonField) rf).getY() == bord.getBOARD_LENDS() - 1 && ((CommonField) rf).getX() == 0 &&
                            bord.getCommonField(0, bord.getBOARD_LENDS() - 1).getFigure() == null){
                        TryTogoToField(bord.getAngularField(0), isWhite, f);
                    }else
                    if(((CommonField) rf).getY() == 0 && ((CommonField) rf).getX() == bord.getBOARD_LENDS() - 1 &&
                            bord.getCommonField(bord.getBOARD_LENDS() - 1, 0).getFigure() == null){
                        TryTogoToField(bord.getAngularField(2), isWhite, f);
                    }else
                    if(((CommonField) rf).getY() == bord.getBOARD_LENDS() - 1 && ((CommonField) rf).getX() == bord.getBOARD_LENDS() - 1 &&
                            bord.getCommonField(bord.getBOARD_LENDS() - 1, bord.getBOARD_LENDS() - 1).getFigure() == null){
                        TryTogoToField(bord.getAngularField(1), isWhite, f);
                    }
                }
            }
            if(((CommonField) f).getX() == 0 && ((CommonField) f).getY() == 0){
                TryTogoToField(bord.getAngularField(3), isWhite, f);
            }
            if(((CommonField) f).getX() == bord.getBOARD_LENDS() && ((CommonField) f).getY() == 0 ){
                TryTogoToField(bord.getAngularField(2), isWhite, f);
            }
            if(((CommonField) f).getX() == bord.getBOARD_LENDS() && ((CommonField) f).getY() == bord.getBOARD_LENDS()){
                TryTogoToField(bord.getAngularField(1), isWhite, f);
            }
            if(((CommonField) f).getX() == 0 && ((CommonField) f).getY() == bord.getBOARD_LENDS()){
                TryTogoToField(bord.getAngularField(0), isWhite, f);
            }
        }else
        if(f instanceof AngularField){
            if(((AngularField) f).getW() == 3){
                int i = 0;
                for (; i < bord.getBOARD_LENDS(); i++) {
                    if(bord.getCommonField(i, i).getFigure() == null){
                        f.addCanGoFromThis(bord.getCommonField(i, i));
                        continue;
                    }
                    if(bord.getCommonField(i, i).getFigure().isWhite() != isWhite){
                        f.addCanGoFromThis(bord.getCommonField(i, i));
                    }
                    break;
                }
                if(i == bord.getBOARD_LENDS()){
                    TryTogoToField(bord.getAngularField(1), isWhite, f);
                }
            }else
            if(((AngularField) f).getW() == 1){
                int i = bord.getBOARD_LENDS() - 1;
                for (; i <= 0; i--) {
                    if(bord.getCommonField(i, i).getFigure() == null){
                        f.addCanGoFromThis(bord.getCommonField(i, i));
                        continue;
                    }
                    if(bord.getCommonField(i, i).getFigure().isWhite() != isWhite){
                        f.addCanGoFromThis(bord.getCommonField(i, i));
                    }
                    break;
                }
                if(i == -1){
                    TryTogoToField(bord.getAngularField(3), isWhite, f);
                }
            }else
            if(((AngularField) f).getW() == 0){
                int i = 0;
                for (; i < bord.getBOARD_LENDS(); i++) {
                    if(bord.getCommonField(i, bord.getBOARD_LENDS() - 1 - i).getFigure() == null){
                        f.addCanGoFromThis(bord.getCommonField(i, bord.getBOARD_LENDS() - 1 - i));
                        continue;
                    }
                    if(bord.getCommonField(i, bord.getBOARD_LENDS() - 1 - i).getFigure().isWhite() != isWhite){
                        f.addCanGoFromThis(bord.getCommonField(i, bord.getBOARD_LENDS() - 1 - i));
                    }
                    break;
                }
                if(i == bord.getBOARD_LENDS()){
                    TryTogoToField(bord.getAngularField(2), isWhite, f);
                }
            }else
            if(((AngularField) f).getW() == 2){
                int i = 0;
                for (; i < bord.getBOARD_LENDS(); i++) {
                    if(bord.getCommonField(bord.getBOARD_LENDS() - 1 - i, i).getFigure() == null){
                        f.addCanGoFromThis(bord.getCommonField(bord.getBOARD_LENDS() - 1 - i, i));
                        continue;
                    }
                    if(bord.getCommonField(bord.getBOARD_LENDS() - 1 - i, i).getFigure().isWhite() != isWhite){
                        f.addCanGoFromThis(bord.getCommonField(bord.getBOARD_LENDS() - 1 - i, i));
                    }
                    break;
                }
                if(i == bord.getBOARD_LENDS()){
                    TryTogoToField(bord.getAngularField(0), isWhite, f);
                }

            }
        }
    }

    public static void QueenCanGoTo(GraphChessBoard bord, Field f, boolean isWhite){
        if(f instanceof CommonField){
            RookCanGoTo(bord, (CommonField) f, isWhite);
        }
        ElephantCanGoTo(bord, f, isWhite);
    }

    private static void MagicianCanGoToCommon(GraphChessBoard bord, CommonField f, boolean isWhite){
        for (int i = -1; i < 1; i += 2) {
            for (int j = -3; j <= 3; j += 2) {
                if(f.getX() + j < bord.getBOARD_LENDS() && f.getY() + i < bord.getBOARD_LENDS()){
                    TryTogoToField(bord.getCommonField(f.getX() + i, f.getY() + j), isWhite, f);
                }
            }
        }
        if(f.getX() + 1 < bord.getBOARD_LENDS() && f.getY() + 3 < bord.getBOARD_LENDS()){
            TryTogoToField(bord.getCommonField(f.getX() + 1, f.getY() + 3), isWhite, f);
        }
        if(f.getX() - 1 < bord.getBOARD_LENDS() && f.getY() + 3 < bord.getBOARD_LENDS()){
            TryTogoToField(bord.getCommonField(f.getX() - 1, f.getY() + 3), isWhite, f);
        }
        if(f.getX() + 1 < bord.getBOARD_LENDS() && f.getY() - 3 < bord.getBOARD_LENDS()){
            TryTogoToField(bord.getCommonField(f.getX() + 1, f.getY() - 3), isWhite, f);
        }
        if(f.getX() - 1 < bord.getBOARD_LENDS() && f.getY() - 3 < bord.getBOARD_LENDS()){
            TryTogoToField(bord.getCommonField(f.getX() - 1, f.getY() - 3), isWhite, f);
        }
    }

    public static void MagicianCanGoTo(GraphChessBoard bord, Field f, boolean isWhite){
        if(f instanceof CommonField){
            MagicianCanGoToCommon(bord, (CommonField) f, isWhite);
            if((((CommonField) f).getX() == 0 && (((CommonField) f).getY() == bord.getBOARD_LENDS() - 1 || ((CommonField) f).getY() == bord.getBOARD_LENDS() - 3))
            || ((CommonField) f).getX() == 2 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 1){
                TryTogoToField(bord.getAngularField(0), isWhite, f);
            }
            if((((CommonField) f).getX() == bord.getBOARD_LENDS() - 1 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 1)
            || (((CommonField) f).getX() == bord.getBOARD_LENDS() - 3 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 1)
            ||((((CommonField) f).getX() == bord.getBOARD_LENDS() - 1 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 3))){
                TryTogoToField(bord.getAngularField(1), isWhite, f);
            }
            if((((CommonField) f).getX() == bord.getBOARD_LENDS() - 1 && ((CommonField) f).getY() == 0)
                    || (((CommonField) f).getX() == bord.getBOARD_LENDS() - 3 && ((CommonField) f).getY() == 0)
                    ||((((CommonField) f).getX() == bord.getBOARD_LENDS() - 1 && ((CommonField) f).getY() == 2))){
                TryTogoToField(bord.getAngularField(2), isWhite, f);
            }
            if((((CommonField) f).getX() == 0 && ((CommonField) f).getY() == 0)
                    || (((CommonField) f).getX() == 2 && ((CommonField) f).getY() == 0)
                    ||((((CommonField) f).getX() == 0 && ((CommonField) f).getY() == 2))){
                TryTogoToField(bord.getAngularField(3), isWhite, f);
            }
        }
        if(f instanceof AngularField){
            if(((AngularField) f).getW() == 0){
                TryTogoToField(bord.getCommonField(2, bord.getBOARD_LENDS() - 1), isWhite, f);
                TryTogoToField(bord.getCommonField(0, bord.getBOARD_LENDS() - 1), isWhite, f);
                TryTogoToField(bord.getCommonField(0, bord.getBOARD_LENDS() - 3), isWhite, f);
            }
            if(((AngularField) f).getW() == 1){
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 1, bord.getBOARD_LENDS() - 1), isWhite, f);
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 3, bord.getBOARD_LENDS() - 1), isWhite, f);
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 1, bord.getBOARD_LENDS() - 3), isWhite, f);
            }
            if(((AngularField) f).getW() == 2){
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 1, 2), isWhite, f);
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 3, 0), isWhite, f);
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 1, 0), isWhite, f);

            }
            if(((AngularField) f).getW() == 3){
                TryTogoToField(bord.getCommonField(0, 0), isWhite, f);
                TryTogoToField(bord.getCommonField(0, 2), isWhite, f);
                TryTogoToField(bord.getCommonField(2, 0), isWhite, f);

            }

        }
    }

    private static void ChampionCanGoToCommon(GraphChessBoard bord, CommonField f, boolean isWhite) {
        for (int i = -2; i <= 2; i += 2) {
            for (int j = -2; j <= 2; j += 2) {
                if (f.getX() + j >= 0 && f.getX() + j < bord.getBOARD_LENDS() && f.getY() + i >= 0 && f.getY() + i < bord.getBOARD_LENDS()) {
                    TryTogoToField(bord.getCommonField(f.getX() + j, f.getY() + i), isWhite, f);
                }
            }
        }
        if (f.getX() + 1 < bord.getBOARD_LENDS()) {
            TryTogoToField(bord.getCommonField(f.getX() + 1, f.getY()), isWhite, f);

        }
        if(f.getX() - 1 >=  0){
            TryTogoToField(bord.getCommonField(f.getX() - 1, f.getY()), isWhite, f);

        }
        if(f.getY() + 1 < bord.getBOARD_LENDS()) {
            TryTogoToField(bord.getCommonField(f.getX(), f.getY() + 1), isWhite, f);

        }
        if(f.getY() - 1 > 0) {
            TryTogoToField(bord.getCommonField(f.getX(), f.getY() - 1), isWhite, f);
        }
    }

    public static void ChampionCanGoTo(GraphChessBoard bord, Field f, boolean isWhite) {
        if(f instanceof CommonField){
            ChampionCanGoToCommon(bord, (CommonField) f, isWhite);
            if(((CommonField) f).getX() == 1 && ((CommonField) f).getY() == 1){
                TryTogoToField(bord.getAngularField(3), isWhite, f);

            }
            if(((CommonField) f).getX() == bord.getBOARD_LENDS() - 2 && ((CommonField) f).getY() == 1){
                TryTogoToField(bord.getAngularField(2), isWhite, f);

            }
            if(((CommonField) f).getX() == bord.getBOARD_LENDS() - 2 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 2){
                TryTogoToField(bord.getAngularField(1), isWhite, f);

            }
            if(((CommonField) f).getX() == 1 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 2){
                TryTogoToField(bord.getAngularField(0), isWhite, f);

            }
        }
        if(f instanceof AngularField){
            if(((AngularField) f).getW() == 3){
                TryTogoToField(bord.getCommonField(1, 1), isWhite, f);
            }
            if(((AngularField) f).getW() == 2){
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 2, 1), isWhite, f);
            }
            if(((AngularField) f).getW() == 1){
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 2, bord.getBOARD_LENDS() - 2), isWhite, f);
            }
            if(((AngularField) f).getW() == 0){
                TryTogoToField(bord.getCommonField(1, bord.getBOARD_LENDS() - 2), isWhite, f);

            }
        }
    }

    private static void HorseCanGoToCommon(GraphChessBoard bord, CommonField f, boolean isWhite){
        for (int i = -1; i <= 1 ; i += 2) {
            for (int j = -2; j <= 2; j += 4) {
                if(f.getX() + j >= 0 && f.getX() + j < bord.getBOARD_LENDS() && f.getY() + i >= 0 && f.getY() + i < bord.getBOARD_LENDS()) {
                    TryTogoToField(bord.getCommonField(f.getX() + j, f.getY() + i), isWhite, f);
                }
            }
        }
        for (int i = -2; i <= 2 ; i += 4) {
            for (int j = -1; j <= 1; j += 2) {
                if(f.getX() + j >= 0 && f.getX() + j < bord.getBOARD_LENDS() && f.getY() + i >= 0 && f.getY() + i < bord.getBOARD_LENDS()) {
                    TryTogoToField(bord.getCommonField(f.getX() + j, f.getY() + i), isWhite, f);
                }
            }
        }
    }

    public static void HorseCanGoTo(GraphChessBoard bord, Field f, boolean isWhite){
        if(f instanceof CommonField){
            HorseCanGoToCommon(bord, (CommonField) f, isWhite);
            if((((CommonField) f).getX() == 1 && ((CommonField) f).getY() == 0)
            || (((CommonField) f).getX() == 0 && ((CommonField) f).getY() == 1)){
                TryTogoToField(bord.getAngularField(3), isWhite, f);
            }
            if((((CommonField) f).getX() == bord.getBOARD_LENDS() - 2 && ((CommonField) f).getY() == 0)
                    || (((CommonField) f).getX() == bord.getBOARD_LENDS() - 1 && ((CommonField) f).getY() == 1)){
                TryTogoToField(bord.getAngularField(2), isWhite, f);
            }
            if((((CommonField) f).getX() == bord.getBOARD_LENDS() - 2 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 1)
                    || (((CommonField) f).getX() == bord.getBOARD_LENDS() - 1 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 2)){
                TryTogoToField(bord.getAngularField(1), isWhite, f);
            }
            if((((CommonField) f).getX() == 0 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 2)
                    || (((CommonField) f).getX() == 1 && ((CommonField) f).getY() == bord.getBOARD_LENDS() - 1)){
                TryTogoToField(bord.getAngularField(0), isWhite, f);
            }
        }
        if(f instanceof AngularField){
            if(((AngularField) f).getW() == 3){
                TryTogoToField(bord.getCommonField(1, 0), isWhite, f);
                TryTogoToField(bord.getCommonField(0, 1), isWhite, f);
            }
            if(((AngularField) f).getW() == 2){
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 2, 0), isWhite, f);
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 1, 1), isWhite, f);
            }
            if(((AngularField) f).getW() == 1){
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 1, bord.getBOARD_LENDS() - 2), isWhite, f);
                TryTogoToField(bord.getCommonField(bord.getBOARD_LENDS() - 2, bord.getBOARD_LENDS() - 1), isWhite, f);
            }
            if(((AngularField) f).getW() == 0){
                TryTogoToField(bord.getCommonField(1, bord.getBOARD_LENDS() - 1), isWhite, f);
                TryTogoToField(bord.getCommonField(0, bord.getBOARD_LENDS() - 2), isWhite, f);
            }
        }
    }

    private static void TryTogoToField(Field toGo, boolean isWhite, Field f){
        if(toGo.getFigure() == null || toGo.getFigure().isWhite() != isWhite) f.addCanGoFromThis(toGo);
    }
}