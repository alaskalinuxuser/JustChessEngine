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

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.alaskalinuxuser.justchessengine.TheEngine.allMoves;
import static com.alaskalinuxuser.justchessengine.TheUserInterface.drawBoardPieces;

public class MainActivity extends AppCompatActivity {

    static char[] theBoard = {'R','N','B','Q','K','B','N','R','P','P','P','P','P','P','P','P','*','*','*','*',
            '*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*',
            '*','*','*','*','*','p','p','p','p','p','p','p','p','r','n','b','q','k','b','n','r'};

    static ImageView x63,x62,x61,x60,x59,x58,x57,x56,x55,x54,x53,x52,x51,x50,x49,x48,x47,x46,x45,
            x44,x43,x42,x41,x40,x39,x38,x37,x36,x35,x34,x33,x32,x31,x30,x29,x28,x27,x26,x25,x24,
            x23,x22,x21,x20,x19,x18,x17,x16,x15,x14,x13,x12,x11,x10,x9,x8,x7,x6,x5,x4,x3,x2,x1,x0;

    static int[] imageViews = {R.id.p0,R.id.p1,R.id.p2,R.id.p3,R.id.p4,R.id.p5,R.id.p6,R.id.p7,R.id.p8,R.id.p9,
            R.id.p10,R.id.p11,R.id.p12,R.id.p13,R.id.p14,R.id.p15,R.id.p16,R.id.p17,R.id.p18,R.id.p19,
            R.id.p20,R.id.p21,R.id.p22,R.id.p23,R.id.p24,R.id.p25,R.id.p26,R.id.p27,R.id.p28,R.id.p29,
            R.id.p30,R.id.p31,R.id.p32,R.id.p33,R.id.p34,R.id.p35,R.id.p36,R.id.p37,R.id.p38,R.id.p39,
            R.id.p40,R.id.p41,R.id.p42,R.id.p43,R.id.p44,R.id.p45,R.id.p46,R.id.p47,R.id.p48,R.id.p49,
            R.id.p50,R.id.p51,R.id.p52,R.id.p53,R.id.p54,R.id.p55,R.id.p56,R.id.p57,R.id.p58,R.id.p59,
            R.id.p60,R.id.p61,R.id.p62,R.id.p63};

    static ImageView [] chessImage = {x0,x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,x14,x15,
            x16,x17,x18,x19,x20,x21,x22,x23,x24,x25,x26,x27,x28,x29,x30,x31,
            x32,x33,x34,x35,x36,x37,x38,x39,x40,x41,x42,x43,x44,x45,x46,x47,
            x48,x49,x50,x51,x52,x53,x54,x55,x56,x57,x58,x59,x60,x61,x62,x63};

    static Button nextMoveB,pB,mB;
    static TextView pN, tVms, mCtv;
    static String moveOptions;
    static long moveTime;
    static int searchDepth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        moveOptions = "Move Options";
        pB = (Button)findViewById(R.id.plusB);
        mB = (Button)findViewById(R.id.minusB);
        pN = (TextView)findViewById(R.id.plyNum);
        tVms = (TextView)findViewById(R.id.timeText);
        mCtv = (TextView)findViewById(R.id.moveChoiceText);

        mCtv.setText(moveOptions);

        pN.setText(String.valueOf(searchDepth));

        nextMoveB = (Button)findViewById(R.id.nextMoveButton);

        // Declare all of our image views programatically.
        for (int i=0; i<64; i++) {
            chessImage[i]=(ImageView)findViewById(imageViews[i]);
            chessImage[i].setBackgroundResource(R.drawable.dark);

            if (i==1 || i==3 || i==5 || i==7 ||
                    i==8 || i==10 || i==12 || i==14 ||
                    i==17 || i==19 || i==21 || i==23 ||
                    i==24 || i==26 || i==28 || i==30 ||
                    i==33 || i==35 || i==37 || i==39 ||
                    i==40 || i==42 || i==44 || i==46 ||
                    i==49 || i==51 || i==53 || i==55 ||
                    i==56 || i==58 || i==60 || i==62) {
                chessImage[i].setBackgroundResource(R.drawable.light);
            }
        } // checker board.


        // Display the board in the logs....
        String logBoard="";
        for(int i = 0 ; i < 64; i++) {
            logBoard = logBoard + theBoard[i];
        }
        // Display the board as small strings //
        //Log.i("WJH", logBoard.substring(56,63));
        //Log.i("WJH", logBoard.substring(48,55));
        //Log.i("WJH", logBoard.substring(40,47));
        //Log.i("WJH", logBoard.substring(32,39));
        //Log.i("WJH", logBoard.substring(24,31));
        //Log.i("WJH", logBoard.substring(16,23));
        //Log.i("WJH", logBoard.substring(8,15));
        //Log.i("WJH", logBoard.substring(0,7));
        // To log the whole board as one long string // Log.i("WJH", logBoard);

        // Visually Draw the board....
        drawBoardPieces();

        allMoves();

    }// End on create.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } // End on create options menu.

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } // End on options selected menu.
} // End Main Activity.
