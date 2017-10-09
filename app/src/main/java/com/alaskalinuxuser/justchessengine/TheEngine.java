package com.alaskalinuxuser.justchessengine;

/*  Copyright 2017 by AlaskaLinuxUser (https://thealaskalinuxuser.wordpress.com)
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.alaskalinuxuser.justchessengine.MainActivity.theBoard;

public class TheEngine {

    public static String allMoves() {
        String list = "";
        for (int i = 0; i < 64; i++) {
            switch (theBoard[i]) {
                case 'N': list+=nightMoves(i);break;
                case 'R': list+=rookMoves(i);break;
            }
        }
        Log.i("WJH", list);
        return list;
        /*
         * The list is in this format 123456,
         * 1 = Moving piece
         * 2,3 = 2 digit from square
         * 4,5 = 2 digit to square
         * 6 = captured piece
         * followed by a comma.
         */
    } // End possible moves.

    public static String nightMoves(int i) {
        String list = "";
        List<Integer> theseMoves = new ArrayList<Integer>();
        String moveSquare;

        int rowNum = i/8;
        int colNum = i%8;

        if (rowNum < 7 ) {
            if (colNum > 1) {
                theseMoves.add(i + 6);
            }
            if (colNum < 6) {
                theseMoves.add(i+10);
            }
        }
        if (rowNum < 6 ) {
            if (colNum > 0) {
                theseMoves.add(i + 15);
            }
            if (colNum < 7) {
                theseMoves.add(i+17);
            }
        }
        if (rowNum > 0 ) {
            if (colNum < 6) {
                theseMoves.add(i - 6);
            }
            if (colNum > 1) {
                theseMoves.add(i-10);
            }
        }
        if (rowNum > 1 ) {
            if (colNum < 7) {
                theseMoves.add(i - 15);
            }
            if (colNum > 0) {
                theseMoves.add(i-17);
            }
        }

        for(int l=0; l<theseMoves.size();l++) {
            int k = theseMoves.get(l);
            if (Character.isLowerCase(theBoard[k]) || theBoard[k] == '*') {
                moveSquare = String.valueOf(theBoard[k]);
                theBoard[k] = 'N';
                theBoard[i] = moveSquare.charAt(0);
                if (isKingSafe()) {
                    String F = String.valueOf(i);
                    String T = String.valueOf(k);
                    if (i < 10) {
                        F = "0" + F;
                    }
                    if (k < 10) {
                        T = "0" + T;
                    }
                    list = list + "N" + F + T + moveSquare.charAt(0) + ",";
                }
                theBoard[k] = moveSquare.charAt(0);
                theBoard[i] = 'N';
            }
        }
        return list;
    } // End knight moves.

    public static String rookMoves(int i) {
        String list = "";
        List<Integer> theseMoves = new ArrayList<Integer>();
        String moveSquare;
        int g = i%8;

        // Up moves
        boolean notI = true;
        int j = 1;
        int vert = 8;
        int k = i;
        if (i < 56) {
            k = i + (vert * j);
        }
        while (theBoard[k] == '*' && notI) {
            theseMoves.add(k);
            vert += 8;
            if (k < 56) {
                k = i + (vert * j);
            } else {
                notI = false;
            }
        } // While it's empty.
        if (Character.isLowerCase(theBoard[k])) {
            theseMoves.add(k);
        } // When there is an enemy.

        // Down moves
        notI = true;
        j = -1;
        vert = 8;
        k = i;
        if (i > 7) {
            k = i + (vert * j);
        }
        while (theBoard[k] == '*' && notI) {
            theseMoves.add(k);
            vert += 8;
            if (k >7) {
                k = i + (vert * j);
            } else {
                notI = false;
            }
        } // While it's empty.
        if (Character.isLowerCase(theBoard[k])) {
            theseMoves.add(k);
        } // When there is an enemy.

        // Right side....
        notI = true;
        int rj = 1;
        int rk = i;
        if (g < 7) {
            rk = i + rj;
        }
        while (theBoard[rk] == '*' && notI) {
            theseMoves.add(rk);
            rj++;
            if (rk%8 < 7) {
                rk = i + rj;
            } else {
                notI = false;
            }
        } // While it's empty.
        if (Character.isLowerCase(theBoard[rk])) {
            theseMoves.add(rk);
        } // When there is an enemy.

        // Left side....
        notI=true;
        rj = 1;
        rk = i;
        if (g > 0) {
            rk = i - rj;
        }
        while (theBoard[rk] == '*' && notI) {
            theseMoves.add(rk);
            rj++;
            if (rk%8 > 0) {
                rk = i - rj;
            } else {
                notI=false;
            }
        } // While it's empty.
        if (Character.isLowerCase(theBoard[rk])) {
            theseMoves.add(rk);
        } // When there is an enemy.

        for(int l=0; l<theseMoves.size();l++) {
            k = theseMoves.get(l);
            moveSquare = String.valueOf(theBoard[k]);
            theBoard[k] = 'R';
            theBoard[i] = moveSquare.charAt(0);
            if (isKingSafe()) {
                String F = String.valueOf(i);
                String T = String.valueOf(k);
                if (i < 10) {
                    F = "0" + F;
                }
                if (k < 10) {
                    T = "0" + T;
                }
                list = list + "R" + F + T + moveSquare.charAt(0) + ",";
            }
            theBoard[k] = moveSquare.charAt(0);
            theBoard[i] = 'R';
        }
        return list;
    } // End Rook moves.

    public static boolean isKingSafe() {

        return true;

    } // End is king safe?

} // End The engine.
