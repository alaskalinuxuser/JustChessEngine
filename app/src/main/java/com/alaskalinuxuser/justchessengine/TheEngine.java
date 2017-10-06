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
        return list;//x1,x2,captured piece
    } // End possible moves.

    public static String nightMoves(int i) {
        String list="";
        return list;
    } // End night moves.

}
