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

import static com.alaskalinuxuser.justchessengine.TheEngine.isKingSafe;
import static com.alaskalinuxuser.justchessengine.TheEngine.theBoard;

public class TheRating {

    public static int rating(int list, int depth) {
        int counter=0;
        counter+=rateMoveablitly(list, depth);
        return -(counter+depth*50);
    } // End rating

    public static int rateMoveablitly(int listLength, int depth) {
        int counter=0;
        counter+=listLength;//5 pointer per valid move
        if (listLength==0) {//current side is in checkmate or stalemate
            if (!isKingSafe()) {//if checkmate
                counter+=-200000*depth;
            } else {//if stalemate
                counter+=-150000*depth; }}
        return 0;} // Rate moveability....

    public static int rateMaterialWhite(){
        int materialScore = 0;
        for (int i = 0; i < 64; i++) {
            switch (theBoard[i]) {
                case 'N': materialScore = materialScore + 30;break;
                case 'R': materialScore = materialScore + 50;break;
                case 'B': materialScore = materialScore + 35;break;
                case 'Q': materialScore = materialScore + 90;break;
                case 'K': materialScore = materialScore + 901;break;
                case 'P': materialScore = materialScore + 10;break;
                case 'n': materialScore = materialScore - 30;break;
                case 'r': materialScore = materialScore - 50;break;
                case 'b': materialScore = materialScore - 35;break;
                case 'q': materialScore = materialScore - 90;break;
                case 'k': materialScore = materialScore - 900;break;
                case 'p': materialScore = materialScore - 10;break;
            }
        }
        return materialScore;
    }
    public static int rateMaterialBlack(){
        int materialScore = 0;
        for (int i = 0; i < 64; i++) {
            switch (theBoard[i]) {
                case 'N': materialScore = materialScore - 30;break;
                case 'R': materialScore = materialScore - 50;break;
                case 'B': materialScore = materialScore - 35;break;
                case 'Q': materialScore = materialScore - 90;break;
                case 'K': materialScore = materialScore - 900;break;
                case 'P': materialScore = materialScore - 10;break;
                case 'n': materialScore = materialScore + 30;break;
                case 'r': materialScore = materialScore + 50;break;
                case 'b': materialScore = materialScore + 35;break;
                case 'q': materialScore = materialScore + 90;break;
                case 'k': materialScore = materialScore + 901;break;
                case 'p': materialScore = materialScore + 10;break;
            }
        }
        return materialScore;
    }

}// End the rating.
