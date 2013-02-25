/*
 * --------------------------------------------------
 * The Simple awsome Tic-Tac-toe game 
 * 
 * Developed By : Krishanthan Krishnamoorthy
 * 
 * --------------------------------------------------
 * 
 * --------------------HOW IT WORKS-------------------
 * 
 * When player touch the box, say first box, then the program will able to figure out where the user is clicked 
 * and get coordinates and place the proper X or O shapes in that box, and it will add the placement into the 3d arrays.
 * After the 5th moves, it will start to valid the game state for another single movements. 
 * 
 * Time complexity of the program is - O(n)
 * 
 *
 * */

package com.krish_game.ttt;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {

	ImageView tttBoard;
	Canvas canvas;
	Bitmap board;
	final int boardsize = 480;
	float canvas_x;
	float canvas_y;
	float posX;
	float posY;
	
	boolean turn;
	boolean someWon;
	boolean again;
	
	Rect myRec;
	Paint myColor;
	Bitmap shapeX;
	Bitmap shapeO;
	
    ArrayList<Integer> boxes = new ArrayList<Integer>();
    String [][] boardRef = new String[3][3];
    
    int numOfTimes = 0;
    int player;
    int player1_score=0;
    int player2_score=0;
    int box;
    
    Dialog dialog = null;
    AlertDialog.Builder builder;
    TextView p1_score;
    TextView p2_score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tttBoard = (ImageView)findViewById(R.id.game_board);
		board = Bitmap.createBitmap(boardsize, boardsize, Bitmap.Config.ARGB_8888);
		p1_score = (TextView)findViewById(R.id.lbl_winner);
		p2_score = (TextView)findViewById(R.id.lbl_loser);
		canvas = new Canvas(board);
	    canvas.drawColor(Color.WHITE);  //background color for the board.
		tttBoard.setImageBitmap(board);
		tttBoard.setOnTouchListener(new S3TouchListener());
		
		//For drawing
		myRec = new Rect(0,0,10,10);
		myColor = new Paint();  //default black
		myColor.setStyle(Paint.Style.FILL);
		
		canvas_y= canvas.getHeight();
		canvas_x= canvas.getWidth();
		posX = canvas_x / 3;
 		posY = canvas_y / 3;
		
 		//Draw the board
 		canvas.drawLine(0,160,canvas_x,160,myColor);
 		canvas.drawLine(0,320,canvas_x,320,myColor);
 		canvas.drawLine(0,480,canvas_x,480,myColor);
		
 		canvas.drawLine(160,0,160,canvas_y,myColor);
 		canvas.drawLine(320,0,320,canvas_y,myColor);
 		canvas.drawLine(0,480,canvas_y,480,myColor);
		
 		shapeX = BitmapFactory.decodeResource(getResources(), R.drawable.img_x);
 		shapeO = BitmapFactory.decodeResource(getResources(), R.drawable.img_o);
 		int i;
 		
 		//Adding numbers to the box, when player hit the box, then it will replace the particular number with -1,
 		//So by that information the program will understand that user is already clicked the box
 		for(i=1;i<=9;i++)
 		{
 			boxes.add(i);
 		}
	}
	
	//Touch events
	class S3TouchListener implements View.OnTouchListener
	{
		@Override
		public boolean onTouch(View arg0, MotionEvent event) 
		{
			//Players will able to play until some one wins the game... 
			
			if(!someWon)
			{
				// X and Y values that from touch events
				int x = (int) event.getX();
	    		int y = (int) event.getY();
	    		
	    		//Find the place in the canvas and place the shapes
	    		
	    		if(x < 160 && y < 160)
	    		{
	    			box = 1;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(0,0,0,0);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		else if(x < 320 && y < 160)
	    		{
	    			box = 2;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(160,0,1,0);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		else if(x < 480 && y < 160)
	    		{
	    			box = 3;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(320,0,2,0);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		else if(x < 160 && y < 320)
	    		{
	    			box = 4;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(0,160,0,1);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		else if(x < 320 && y < 320)
	    		{
	    			box = 5;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(160,160,1,1);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		else if(x < 480 && y < 320)
	    		{
	    			box = 6;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(320,160,2,1);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		else if(x < 160 && y < 480)
	    		{
	    			box = 7;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(0,320,0,2);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		else if(x < 320 && y < 480)
	    		{
	    			box = 8;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(160,320,1,2);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		else if(x < 480 && y < 480)
	    		{
	    			box = 9;
	    			if(boxes.get(box-1)!=-1)
	    			{
	    				numOfTimes++;
	    				drawObj(320,320,2,2);
	    				boxes.set(box-1, -1);
	    			}
	    		}
	    		
	    		
	    		/*If number of times more than 5, that means Someone put 3 times, so that can be a winning for that player
	    		Therefore we need to check the game state that, is that anyone have a chance to win the game
	    		*/
	    	
	    		if(numOfTimes >= 5 )
	    		{
	    			//Check the game state
	    			checkGameState();
	    		}
			}
    		
			return false;
		}
	}
	
	//Check the status of the game, and after the decision is made it will prompt a dialog box
	public void checkGameState()
	{
		someWon = false;
		
		//Checking the winning conditions
		
		//First check in the Columns
		int c;
		for(c=0;c<3;c++)
		{
			if(boardRef[0][c] == "X" && boardRef[1][c] == "X" && boardRef[2][c] == "X")
			{
				//Player one won
				Log.w("game status", "Player One Won");
				someWon = true;
				player = 1;
				player1_score++;
				p1_score.setText(""+player1_score);
			}
			else if(boardRef[0][c] == "O" && boardRef[1][c] == "O" && boardRef[2][c] == "O")
			{
				//Player two won
				Log.w("game status", "Player Two Won");
				someWon = true;
				player = 2;
				player2_score++;
				p2_score.setText(""+player2_score);
				
			}
		}
		
		//check in the Rows
		int r;
		for(r=0;r<3;r++)
		{
			if(boardRef[r][0] == "X" && boardRef[r][1] == "X" && boardRef[r][2] == "X")
			{				
				//Player one won
				Log.w("game status", "Player One Won");
				someWon = true;
				player = 1;
				player1_score++;
				p1_score.setText(""+player1_score);
						
			}
			else if(boardRef[r][0] == "O" && boardRef[r][1] == "O" && boardRef[r][2] == "O")
			{
				//Player two won
				Log.w("game status", "Player Two Won");
				someWon = true;
				player = 2;
				player2_score++;
				p2_score.setText(""+player2_score);
						
			}
		}
		
		//check in the diagonals
		
		//(1)
		
		if(boardRef[0][0] == "X" && boardRef[1][1] == "X" && boardRef[2][2] == "X")
		{
			//Player one won
			Log.w("game status", "Player One Won");
			someWon = true;
			player = 1;
			player1_score++;
			p1_score.setText(""+player1_score);
		}
		else if(boardRef[0][0] == "O" && boardRef[1][1] == "O" && boardRef[2][2] == "O")
		{
			//Player two won
			Log.w("game status", "Player Two Won");
			someWon = true;
			player = 2;
			player2_score++;
			p2_score.setText(""+player2_score);
		}
		
		//(2)

		if(boardRef[2][0] == "X" && boardRef[1][1] == "X" && boardRef[0][2] == "X")
		{
			//Player one won
			Log.w("game status", "Player One Won");
			someWon = true;
			player = 1;
			player1_score++;
			p1_score.setText(""+player1_score);
		}
		else if(boardRef[2][0] == "O" && boardRef[1][1] == "O" && boardRef[0][2] == "O")
		{
			//Player two won
			Log.w("game status", "Player Two Won");
			someWon = true;
			player = 2;
			player2_score++;
			p2_score.setText(""+player2_score);
		}	
		
		
		//Someone won the game
		if(someWon)
		{
			gameWon();
		}else{}
		
		//Check if the game is tie
		if(numOfTimes == 9 && !someWon)
		{
			//Game Tie
			builder = new AlertDialog.Builder(this);
            builder.setMessage("Game Tie no one won the game, Still do you want to play another game?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                	  //Yes
                	  playAgain();
                  }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                	  //No 
                    dialog.cancel();
                  }
                });
            dialog = builder.create();
            builder.show();
		}
	}
	public void gameWon()
	{
		//Display user prompt message		
		
		builder = new AlertDialog.Builder(this);
		Log.w("game status", Integer.toString(player));
        builder.setMessage("Whoooo!!" + " Player " + player + " Won the game!, Still do you want to play another game?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
            	  //Yes
            	  playAgain();
              }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
            	  //No 
                dialog.cancel();
              }
            });
        dialog = builder.create();
        
        builder.show();
	}
	
	public void playAgain()
	{
		//One of the players chose to play another game!
		
		//So clear the data structures
		boardRef = new String[3][3];
		boxes.clear();
		someWon = false;
		again = true;
		turn = false;
		canvas = new Canvas(board);
	    canvas.drawColor(Color.WHITE);  //background color for the board.
		tttBoard.setImageBitmap(board);
		tttBoard.setOnTouchListener(new S3TouchListener());
		
		//For drawing
		myRec = new Rect(0,0,10,10);
		myColor = new Paint();  //default black
		myColor.setStyle(Paint.Style.FILL);
		
		canvas_y= canvas.getHeight();
		canvas_x= canvas.getWidth();
		posX = canvas_x / 3;
 		posY = canvas_y / 3;
		
 		//Draw the board
 		canvas.drawLine(0,160,canvas_x,160,myColor);
 		canvas.drawLine(0,320,canvas_x,320,myColor);
 		canvas.drawLine(0,480,canvas_x,480,myColor);
		
 		canvas.drawLine(160,0,160,canvas_y,myColor);
 		canvas.drawLine(320,0,320,canvas_y,myColor);
 		canvas.drawLine(0,480,canvas_y,480,myColor);
		
 		shapeX = BitmapFactory.decodeResource(getResources(), R.drawable.img_x);
 		shapeO = BitmapFactory.decodeResource(getResources(), R.drawable.img_o);
 		int i;
 		for(i=1;i<=9;i++)
 		{
 			boxes.add(i);
 		}
 		numOfTimes = 0;
 		player = 0;
 		builder = new AlertDialog.Builder(this);
	}
	
	public void newGame(View v)
	{
		//New game is selected so clear the scores!
			playAgain();
			player1_score = 0;
			player2_score = 0;
			p1_score.setText("");
			p2_score.setText("");
	}

	
	public void drawObj(int posX, int posY,int row, int column)
	{
	   if(!turn)
	   {
			canvas.drawBitmap(shapeX,posX+20,posY+20,null);
			tttBoard.invalidate();
			
			//Adding 'X' to the 2d array
			boardRef[row][column] = "X";
			turn = true;
	   }
	   else
	   {
		   canvas.drawBitmap(shapeO,posX+20,posY+20,null);
		   tttBoard.invalidate();
		   
		    //Adding 'O' to the 2d array
			boardRef[row][column] = "O";		   
		   turn = false;
	   }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
