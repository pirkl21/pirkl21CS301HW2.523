package com.example.pirkl21cs301hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


/*
 *
 *
 * @author Benjamin Daniel Pirkl
 *
 */

/**
 *  MainActivity is the name of the class that controls the LinearLayouts
 *  and their components and then communicates them to the DrawObjects Class
 * @author Benjamin Daniel Pirkl
 *
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {


    private TextView drawingName = null; // Object Currently Selected.
    SurfaceView drawingMade; // Makes Invalidate Use-able
    DrawObjects drawingSelected; // Current Object Selected
    private TextView redText = null; // Red Text View
    private TextView blueText = null; // Blue Text View
    private TextView greenText = null; // Green Text View
    private SeekBar redSeekBar = null; // Red Seek Bar
    private SeekBar blueSeekBar = null; // Blue Seek Bar
    private SeekBar greenSeekBar = null; // Green Seek Bar


    // Method that runs when the app is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();


    }

    @Override
    public void onClick(View v) {
        /* Ignore */
    }

    /** This method runs when the Seek Bars are incremented by a value.
     *
     * @param seekBar
     * @param currentProgress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int currentProgress, boolean fromUser) {

        /**External Citation
         * Date: February 19, 2019
         * Problem: Program kept crashing because drawingSelected did not
         * exist on startup
         * Resource: https://developer.android.com/reference/android/view/SurfaceView
         * Solution: Used error checking to ensure that the drawingSelected would not
         * be used if it was not initialized
         */
        if (seekBar == blueSeekBar) {
            blueText.setText("Blue: " + currentProgress);
            if (drawingSelected != null) {
                drawingSelected.setBlueProgress(currentProgress);
            }
        }
        if (seekBar == redSeekBar) {
            redText.setText("Red: " + currentProgress);
            if (drawingSelected != null) {
                drawingSelected.setRedProgress(currentProgress);
            }
        }
        if (seekBar == greenSeekBar) {
            greenText.setText("Green: " + currentProgress);
            if (drawingSelected != null) {
                drawingSelected.setGreenProgress(currentProgress);
            }
        }


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    // Basic variable initialization and findViewById
    // attaching to objects
    public void initializeVariables() {

        // Set the Texts to a default value of "Color: 0"
        redText = findViewById(R.id.redText);
        redText.setText("Red: 0");
        blueText = findViewById(R.id.blueText);
        blueText.setText("Blue: 0");
        greenText = findViewById(R.id.greenText);
        greenText.setText("Green: 0");


        redSeekBar = findViewById(R.id.redSeekBar);
        redSeekBar.setOnSeekBarChangeListener(this);
        redSeekBar.setMax(255);

        blueSeekBar = findViewById(R.id.blueSeekBar);
        blueSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setMax(255);

        greenSeekBar = findViewById(R.id.greenSeekBar);
        greenSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setMax(255);

        drawingName = findViewById(R.id.objectDefinition);
        drawingMade = findViewById(R.id.drawSpace);
        drawingMade.setOnTouchListener(this);

        drawingSelected = findViewById(R.id.drawSpace);
    }


    /**
     * Creates "hitboxes" for each object on the field
     * and adjusts the TextView - drawingName - when one
     * is selected
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int xLocation = (int) event.getX();
        int yLocation = (int) event.getY();


        if (xLocation >= 600.0f && xLocation <= 1010.0f && yLocation <= 1000.0f && yLocation >= 700.0f) {
            drawingName.setText("Small Rectangle");
        } else if (xLocation >= 300 && xLocation <= 800 && yLocation <= 600 && yLocation >= 100) {
            drawingName.setText("Big Square");
        } else if (xLocation >= 80 && xLocation <= 180 && yLocation <= 270 && yLocation >= 170) {
            drawingName.setText("Small Circle");
        } else if (xLocation >= 20 && xLocation <= 420 && yLocation <= 1200 && yLocation >= 800) {
            drawingName.setText("Large Circle");
        } else if (xLocation >= 800 && xLocation <= 1000 && yLocation <= 1250 && yLocation >= 1050){
            drawingName.setText("Small Square");
        } else if (xLocation >= 950 && xLocation <= 1150 && yLocation <= 500 && yLocation >= 300){
            drawingName.setText("Medium Circle");
        } else {
            drawingName.setText("Select Object");
        }


        // Send the text currently stored under the TextView Object to
        // The other class to be used for color change
        drawingSelected.sendText(drawingName.getText().toString());
        // reload the SurfaceView and call onDraw again.
        drawingMade.invalidate();
        return true;
    }
}
