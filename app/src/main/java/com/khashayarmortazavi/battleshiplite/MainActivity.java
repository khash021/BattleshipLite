package com.khashayarmortazavi.battleshiplite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Khash
 *
 * This is the main class for choosing game options
 */

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private RadioGroup mRadioGroupDiff;
    private RadioButton mRadioButtEasy, mRadioButtMed, mRadioButtHard;

    private Button mButtonPlay5x5;

    private int mDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroupDiff = findViewById(R.id.radio_group_diff);
        mRadioButtEasy = findViewById(R.id.radio_diff_easy);
        mRadioButtMed = findViewById(R.id.radio_diff_med);
        mRadioButtHard = findViewById(R.id.radio_diff_hard);

        mRadioGroupDiff.check(R.id.radio_diff_med);

        mButtonPlay5x5 = findViewById(R.id.button_play_5x5);
        mButtonPlay5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(MainActivity.this, Play5X5Activity.class);
                i.putExtra(getString(R.string.intent_extra_difficulty), mDifficulty);
                startActivity(i);
            }
        });//mButtonPlay5x5

        mRadioGroupDiff.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_diff_easy:
                        mDifficulty = 10;
                        break;
                    case R.id.radio_diff_med:
                    default:
                        mDifficulty = 5;
                        break;
                    case R.id.radio_diff_hard:
                        mDifficulty = 3;
                        break;
                }//switch
            }
        });//mRadioGroupDiff


    }//onCreate


}//Main class
