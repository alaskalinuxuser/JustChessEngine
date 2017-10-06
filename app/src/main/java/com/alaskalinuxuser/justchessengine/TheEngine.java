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
        if (i<56) {
            if (i != 0 && i != 1 && i != 8 && i != 9 && i != 16 && i != 17 && i != 24 && i != 25 &&
                    i != 32 && i != 33 && i != 40 && i != 41 && i != 48 && i != 49) {
                theseMoves.add(i + 6);
            }
        }
        if (i<54) {
            if (i != 6 && i != 7 && i != 14 && i != 15 && i != 22 && i != 23 && i != 30 &&
                    i != 31 && i != 38 && i != 39 && i != 46 && i != 47) {
                theseMoves.add(i+10);
            }
        }
        if (i<48) {
            if (i != 0 && i != 8 && i != 16 && i != 24 && i != 32 && i != 40) {
                theseMoves.add(i+15);
            }
        }
        if (i<47) {
            if (i != 7 && i != 15 && i != 23 && i != 31 && i != 39) {
                theseMoves.add(i+17);
            }
        }
        if (i>7) {
            if (i != 14 && i != 15 && i != 22 && i != 23 && i != 30 && i != 31 && i != 38 &&
                    i != 39 && i != 46 && i != 47 &&  i != 54 && i != 55 &&  i != 62 && i != 63) {
                theseMoves.add(i-6);
            }
        }
        if (i>9) {
            if (i != 16 && i != 17 && i != 24 && i != 25 && i != 32 && i != 33 &&
                    i != 40 && i != 41 && i != 48 && i != 49 && i != 56 && i != 57) {
                theseMoves.add(i-10);
            }
        }
        if (i>15) {
            if (i != 47 && i != 55 && i != 63 && i != 23 && i != 31 && i != 39)  {
                theseMoves.add(i-15);
            }
        }
        if (i>16) {
            if (i != 48 && i != 56 && i != 24 && i != 32 && i != 40) {
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

    public static boolean isKingSafe() {

        return true;
        
    } // End is king safe?

} // End The engine.
