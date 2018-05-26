package com.khashayarmortazavi.battleshiplite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by Khash
 * <p>
 * This is the main class for choosing game options
 */

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private RadioGroup mRadioGroupDiff, mRadioGroupShipSize;
    private RadioButton mRadioButtEasy, mRadioButtMed, mRadioButtHard, mRadioButtInf, mRadioShip1, mRadioShip2, mRadioShip3;

    private Button mButtonPlay5x5;

    private int mDifficulty, mShipSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroupDiff = findViewById(R.id.radio_group_diff);
        mRadioButtEasy = findViewById(R.id.radio_diff_easy);
        mRadioButtMed = findViewById(R.id.radio_diff_med);
        mRadioButtHard = findViewById(R.id.radio_diff_hard);
        mRadioButtInf = findViewById(R.id.radio_diff_inf);

        mRadioGroupDiff.check(R.id.radio_diff_med);

        mRadioGroupShipSize = findViewById(R.id.radio_group_ship_size);
        mRadioShip1 = findViewById(R.id.radio_ship_size_1);
        mRadioShip2 = findViewById(R.id.radio_ship_size_2);
        mRadioShip3 = findViewById(R.id.radio_ship_size_3);

        mRadioGroupShipSize.check(R.id.radio_ship_size_2);

        //TODO: make this dynamic
//        mDifficulty = Integer.parseInt(((RadioButton) findViewById(mRadioGroupDiff.getCheckedRadioButtonId())).getText().toString());
//        mShipSize = Integer.parseInt(((RadioButton) findViewById(mRadioGroupShipSize.getCheckedRadioButtonId())).getText().toString());
        mDifficulty = 5;
        mShipSize = 2;

        mButtonPlay5x5 = findViewById(R.id.button_play_5x5);
        mButtonPlay5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Play5X5Activity.class);
                ArrayList<Integer> intentExtras = new ArrayList<>();
                intentExtras.add(mDifficulty);
                intentExtras.add(mShipSize);
                i.putExtra(getString(R.string.intent_extra_difficulty), intentExtras);
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
                    case R.id.radio_diff_inf:
                        mDifficulty = 100;
                        break;
                }//switch
            }
        });//mRadioGroupDiff

        mRadioGroupShipSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_ship_size_1:
                        mShipSize = 1;
                        break;
                    case R.id.radio_ship_size_2:
                    default:
                        mShipSize = 2;
                        break;
                    case R.id.radio_ship_size_3:
                        mShipSize = 3;
                        break;
                }//switch
            }
        });//mRadioGroupShipSize


    }//onCreate


}//Main class
