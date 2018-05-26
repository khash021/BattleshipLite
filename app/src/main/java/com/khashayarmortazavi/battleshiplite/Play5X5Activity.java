package com.khashayarmortazavi.battleshiplite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Khash
 * <p>
 * This is the class for playing a 5x5 matrix game
 */

public class Play5X5Activity extends AppCompatActivity implements ImageView.OnClickListener {

    private final String TAG = Play5X5Activity.class.getSimpleName();

    final int BOMB = R.id.B2;

    private ImageView a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5,
            e1, e2, e3, e4, e5;

    private ArrayList<ImageView> gridArray;

    private ArrayList<Integer> mBombArrayList;

    private TextView mTextResult, mTextRemaining;

    private int mDifficulty, mShipSize, mRemainingTry;

    private static final int MATRIX_SIZE = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_5x5_layout);

        Log.v(TAG, "onCreate Called");

        //get the ship size and difficulty from intent
        extractIntentExtras();

        mTextRemaining = findViewById(R.id.text_remaining_try);
        mTextResult = findViewById(R.id.text_result);
        mTextResult.setText("");

        mRemainingTry = mDifficulty;
        mTextRemaining.setText(String.valueOf(mRemainingTry));

        gridArray = new ArrayList<>();

        //setup play matrix
        setupMatrix();

        //create bombs
        populateBombArray(MATRIX_SIZE, mShipSize);

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });//resetButton

        Button showBombButton = findViewById(R.id.button_show_bomb);
        showBombButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBomb();
            }
        });//showBombButton


    }//onCreate

    @Override
    public void onClick(View v) {

        //check for if the game has ended successfully
        if (mBombArrayList.size() < 1) {
            Toast.makeText(getApplicationContext(), "You have already won, please reset",
                    Toast.LENGTH_SHORT).show();
            return;
        } else if (mRemainingTry < 1) {
            //this is for when there is no tries left
            Toast.makeText(getApplicationContext(), "You have no more tries left, please reset",
                    Toast.LENGTH_SHORT).show();
            return;
        }//no tries left

        //get human readable id
        String viewId = getResources().getResourceName(v.getId());
        Log.v(TAG, "onClick called with view id:" + viewId);

        //cast to ImageView
        ImageView clickedImage = (ImageView) v;

        //decrement the number of tries and update UI
        mRemainingTry--;
        mTextRemaining.setText(String.valueOf(mRemainingTry));

        //goes through all of the bombs and checks it
        for (Integer bombId : mBombArrayList) {
            if (v.getId() == bombId) {
                //the case where they hit the bomb. update UI and parameters
                clickedImage.setImageResource(R.drawable.cell_bomb);

                mTextResult.setText("THAT WAS A HIT!");
                mBombArrayList.remove(bombId);

                //check to see if the user has won
                if (mBombArrayList.size() < 1) {
                    Toast.makeText(getApplicationContext(), "You have won!", Toast.LENGTH_SHORT).show();
                    mTextResult.setText("You have won!");
                }

                return;
            }//if
        }//for

        //this means it was not a bomb
        //this is the case they hit water
        clickedImage.setImageResource(R.drawable.cell_water);

        mTextResult.setText("YOU HIT WATER, SPLASH!");

        //check to see if the user has ran out of tries
        if (mRemainingTry < 1) {
            Toast.makeText(getApplicationContext(), "Game over!\n" +
                    "You ran out of tries.", Toast.LENGTH_SHORT).show();
            mTextResult.setText("Game over!");
        }//if
    }//onClick

    //helper method for revealing the ship
    private void showBomb() {
        Log.v(TAG, "showBomb called");
        for (Integer bombId : mBombArrayList) {

            ((ImageView) findViewById(bombId)).setImageResource(R.drawable.cell_bomb);
        }//for

    }//resetGrid

    //helper method for getting the difficulty and size of the ship
    private void extractIntentExtras() {
        Log.v(TAG, "extractIntentExtras called");

        try {
            ArrayList<Integer> inputList = (ArrayList<Integer>)
                    getIntent().getSerializableExtra(getString(R.string.intent_extra_difficulty));
            mDifficulty = inputList.get(0);
            mShipSize = inputList.get(1);
            Log.v(TAG, "extractIntentExtras successful. diff: " + mDifficulty + ". Size: " + mShipSize);
        } catch (Exception e) {
            Log.e(TAG, "Error getting input from Intent", e);
            //use defaults
            mDifficulty = 5;
            mShipSize = 2;
        }
    }//extractIntentExtras

    private void populateBombArray(int matrixSize, int shipSize) {
        Log.v(TAG, "extractIntentExtras called. Size: " + matrixSize);

        mBombArrayList = new ArrayList<>();

        Bomb bomb = new Bomb();

        ArrayList<Bomb> initialBombList = new ArrayList<>();

        //switch to figure out the size of the bomb to call appropriate function
        switch (shipSize) {
            case 1:
                initialBombList = bomb.bomb1(matrixSize);
                break;
            case 2:
                initialBombList = bomb.bomb2L(matrixSize);
                break;
            case 3:
                initialBombList = bomb.bomb3L(matrixSize);
                break;
        }//switch


        //go through all the bombs in the list, and get their image resource id and add the to the Arraylist
        for (Bomb b : initialBombList) {

            //convert coordinates of the bomb, to the corresponding resource id of the matrix
            int bombId = convertCoordinateToId(b);
            mBombArrayList.add(bombId);

            //TODO: check for error getting the view (-1)
        }//for
    }//populateBombArray


    private int convertCoordinateToId(Bomb bomb) {
        Log.v(TAG, "convertCoordinateToId called. Bomb (x,y): " + bomb.x +", " + bomb.y);

        String letter, number, id;
        int output;

        number = String.valueOf(bomb.x + 1);

        switch (bomb.y) {
            case 0:
                letter = "A";
                break;
            case 1:
                letter = "B";
                break;
            case 2:
                letter = "C";
                break;
            case 3:
                letter = "D";
                break;
            case 4:
                letter = "E";
                break;
            default:
                letter = "Z";
                break;
        }//switch

        //this means there was an error, so we don't need to continue
        if (letter.equalsIgnoreCase("Z")) {
            return -1;
        }//if

        id = letter.trim() + number.trim();
        Log.v(TAG, "convertCoordinateToId generated bomb id:" + id);

        output = getResources().getIdentifier(id, "id", getPackageName());
        Log.v(TAG, "convertCoordinateToId generated bomb's image id: " + output );
        return output;

    }//convertCoordinateToId

    //helper method for setting up the views
    private void setupMatrix() {
        Log.v(TAG, "setupMatrix called." );

        a1 = findViewById(R.id.A1);
        a1.setOnClickListener(this);
        gridArray.add(a1);
        a2 = findViewById(R.id.A2);
        a2.setOnClickListener(this);
        gridArray.add(a2);
        a3 = findViewById(R.id.A3);
        a3.setOnClickListener(this);
        gridArray.add(a3);
        a4 = findViewById(R.id.A4);
        a4.setOnClickListener(this);
        gridArray.add(a4);
        a5 = findViewById(R.id.A5);
        a5.setOnClickListener(this);
        gridArray.add(a5);


        b1 = findViewById(R.id.B1);
        b1.setOnClickListener(this);
        gridArray.add(b1);
        b2 = findViewById(R.id.B2);
        b2.setOnClickListener(this);
        gridArray.add(b2);
        b3 = findViewById(R.id.B3);
        b3.setOnClickListener(this);
        gridArray.add(b3);
        b4 = findViewById(R.id.B4);
        b4.setOnClickListener(this);
        gridArray.add(b4);
        b5 = findViewById(R.id.B5);
        b5.setOnClickListener(this);
        gridArray.add(b5);

        c1 = findViewById(R.id.C1);
        c1.setOnClickListener(this);
        gridArray.add(c1);
        c2 = findViewById(R.id.C2);
        c2.setOnClickListener(this);
        gridArray.add(c2);
        c3 = findViewById(R.id.C3);
        c3.setOnClickListener(this);
        gridArray.add(c3);
        c4 = findViewById(R.id.C4);
        c4.setOnClickListener(this);
        gridArray.add(c4);
        c5 = findViewById(R.id.C5);
        c5.setOnClickListener(this);
        gridArray.add(c5);

        d1 = findViewById(R.id.D1);
        d1.setOnClickListener(this);
        gridArray.add(d1);
        d2 = findViewById(R.id.D2);
        d2.setOnClickListener(this);
        gridArray.add(d2);
        d3 = findViewById(R.id.D3);
        d3.setOnClickListener(this);
        gridArray.add(d3);
        d4 = findViewById(R.id.D4);
        d4.setOnClickListener(this);
        gridArray.add(d4);
        d5 = findViewById(R.id.D5);
        d5.setOnClickListener(this);
        gridArray.add(d5);

        e1 = findViewById(R.id.E1);
        e1.setOnClickListener(this);
        gridArray.add(e1);
        e2 = findViewById(R.id.E2);
        e2.setOnClickListener(this);
        gridArray.add(e2);
        e3 = findViewById(R.id.E3);
        e3.setOnClickListener(this);
        gridArray.add(e3);
        e4 = findViewById(R.id.E4);
        e4.setOnClickListener(this);
        gridArray.add(e4);
        e5 = findViewById(R.id.E5);
        e5.setOnClickListener(this);
        gridArray.add(e5);
    }//setupMatrix


}//Play5X5Activity
