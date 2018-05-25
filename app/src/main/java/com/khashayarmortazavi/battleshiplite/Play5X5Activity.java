package com.khashayarmortazavi.battleshiplite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Khash
 *
 * This is the class for playing a 5x5 matrix game
 */

public class Play5X5Activity extends AppCompatActivity implements ImageView.OnClickListener {

    final int BOMB = R.id.B2;

    private ImageView a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5,
                    e1, e2, e3, e4, e5;

    private ArrayList<ImageView> gridArray;

    private TextView mTextResult, mTextRemaining;

    private int mDifficulty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_5x5_layout);
        mDifficulty = getIntent().getIntExtra(getString(R.string.intent_extra_difficulty), 5);

        mTextRemaining = findViewById(R.id.text_remaining);
        mTextResult = findViewById(R.id.text_result);

        gridArray = new ArrayList<>();

        setupMatrix();

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGrid();
            }
        });


    }//onCreate

    @Override
    public void onClick(View v) {
        ImageView clickedImage = (ImageView) v;
        if (v.getId() == BOMB) {
            clickedImage.setImageResource(R.drawable.cell_bomb);
        } else {
            clickedImage.setImageResource(R.drawable.cell_water);
        }
    }//onClick

    private void resetGrid() {
        for (ImageView imageView : gridArray) {
            imageView.setImageResource(R.drawable.cell_initial);
        }

    }//resetGrid

    //helper method for setting up the views
    private void setupMatrix() {
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
