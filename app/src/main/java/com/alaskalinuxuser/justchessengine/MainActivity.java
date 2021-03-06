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

import android.os.AsyncTask;
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

import java.util.Arrays;

import static com.alaskalinuxuser.justchessengine.TheEngine.terminal;
import static com.alaskalinuxuser.justchessengine.TheUserInterface.drawBoardPieces;

public class MainActivity extends AppCompatActivity {

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

    int engineStrength;
    boolean wTurn;

    static Button nextMoveB,pB,mB;
    static TextView pN, tVms, mCtv;
    static String moveOptions;
    static long startTime, stopTime;
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

        //Start a new game.
        terminal("newGame");

        // Visually Draw the board....
        drawBoardPieces();

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

    // Our new class to tell the computer to think about a move....
    public class thinkMove extends AsyncTask<String, Void, String> {

        // Do this in the background.
        @Override
        protected String doInBackground(String... urls) {

            startTime = System.currentTimeMillis();
            // Try this.
            try {
                terminal("makeMove,"+String.valueOf(engineStrength));
                // Have an exception clause so you don't crash.
            } catch (Exception e) {
                e.printStackTrace();
                stopTime = System.currentTimeMillis();
                return "Exception";
            }
            stopTime = System.currentTimeMillis();
            return "Pass";
        }}// End asyncronous task of finding a move....

    public void getNextMove() {
        // Call the class to make a move...
        thinkMove task = new thinkMove();
        String result = null;
        try {
            // execute, or go on and do that task.
            result = task.execute("done").get();
            // A fail clause.
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result=="Pass"){
            if (wTurn) {
                wTurn = false;
            } else {
                wTurn = true;
            }
            // draw the board.
            drawBoardPieces();
            // rename the move button.
            nextMoveB.setText("Move");
            tVms.setText(String.valueOf(stopTime-startTime) + " ms");
            mCtv.setText(moveOptions);
        } else {
            engineStrength=2;
            getNextMove();
        }
    } // End get next move.

    public void buttonNextMove (View view) {

        nextMoveB.setText("Thinking...");
        moveOptions= terminal("availMoves,"+String.valueOf(wTurn));

        /*
         * This next two lines could be used in place of getNextMove()
         * to aleviate the "application may be doing too much work on its main thread." error.
         * However, if you have this in place, and a phone is too slow, dropping or suspending a thread,
         * it may not work anymore.
         */
        //Executor executor = Executors.newSingleThreadExecutor();
        //executor.execute(new Runnable() { public void run() { getNextMove(); } });
        getNextMove();

        // To test responses // String query = "";
        // query = terminal("whoseTurn"); // To find out whose turn the computer thinks it is.
        // query = terminal("setTurn,white"); // To set the computer's turn to white. (use black for black).
        // query = terminal("suggestMove,black"); // To ask for a suggested rated move for black, use white for white.
        // query = terminal("getBoard"); // to get the logical board.

        /*
         * To set the engine board to equal your board. It is just a string of 64 characters.
         *
         * Uppercase are white, lowercase are black. Asterisk is blank.
         * RNBQKBNRPPPPPPPP********************************pppppppprnbqkbnr
         *
         * char[] myBoard = {'R','N','B','Q','K','B','N','R','P','P','P','P','P','P','P','P','*','*','*','*',
         *       '*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*',
         *       '*','*','*','*','*','p','p','p','p','p','p','p','p','r','n','b','q','k','b','n','r'};
         * String stringBoard="";
         * for (int i = 0; i < 64; i++) {stringBoard = stringBoard + String.valueOf(myBoard[i]);}
         * query = terminal("setBoard,"+ stringBoard);
         *
         */

        // query = terminal("pieceMoves,N,13"); // If a knight was at square 13, on the current board, what moves could be made?
        // This is useful for clicking a piece to see what options it has.
        // K = king, Q = queen, R = rook, B = bishop, N = knight, P = pawn.
        // Use lowercase for black, uppercase for white.
        // Note that this should only call for moves where a real piece exists, or the logical board may get messed up.

        // query = terminal("undoMove,P1220*");
        // Undo this move. Should be a real move.
        // Piece moved - square from - square to - taken

        // query = terminal("undoLastMove"); // Undo the last move done. Only works once, not sequential.

        // query = terminal("moveNow");
        // to force making a move. E.g., if the strength is so high it takes too long.
        // You can follow this with undoLastMove and setTurn as well to go back.
        // It is not perfect, but will choose the best move it has so far.
        // Usually takes 10 ms from the time it was told to stop.

        //query = terminal("myMove,p5438*");
        // To input your move. It will then flip white/black turn.

        // To test responses. // Log.i("WJH", query);

    } // End next move buton.

    public void moveablePiece (View view) {
        Log.i("WJH", "clicked sqaure.");
    } // End clicked piece.

    public void plyAdjustPlus(View view) {

        engineStrength++;
        pN.setText(String.valueOf(engineStrength));

    } // End ply plus.

    public void plyAdjustMinus(View view) {

        engineStrength--;
        pN.setText(String.valueOf(engineStrength));

    } // end ply minus.

    public void resetGame(View view) {
        // Call for a new game and redraw the board.
        terminal("newGame");
        wTurn = true;
        pN.setText(String.valueOf(engineStrength));
        drawBoardPieces();
        nextMoveB.setText("Move");
        mCtv.setText(moveOptions);
    } // End reset game.
} // End Main Activity.
