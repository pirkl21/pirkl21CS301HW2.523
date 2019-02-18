package com.example.pirkl21cs301hw2;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {


   private TextView drawingName = null; // Object Currently Selected.
   SurfaceView drawingMade; // Makes Invalidate Use-able
   DrawObjects drawingSelected; // Current Object Selected
   private TextView redText = null;
   private TextView blueText = null;
   private TextView greenText = null;
   private SeekBar redSeekBar = null;
   private SeekBar blueSeekBar = null;
   private SeekBar greenSeekBar = null;
   // Integers that correspond to the progress value of the SeekBar
   int redProgress;
   int blueProgress;
   int greenProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int currentProgress, boolean fromUser) {

        if(blueSeekBar !=  null || redSeekBar != null || greenSeekBar != null) {
            if (seekBar == blueSeekBar) {
                blueText.setText("Blue: " + currentProgress);
                blueProgress = currentProgress;
            } else if (seekBar == redSeekBar) {
                redText.setText("Red: " + currentProgress);
                redProgress = currentProgress;
            } else if (seekBar == greenSeekBar) {
                greenProgress = currentProgress;
                greenText.setText("Green: " + currentProgress);
            }

            if (drawingSelected != null) {
                drawingSelected.setProgress(redProgress, blueProgress, greenProgress);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void initializeVariables(){

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

        drawingSelected = new DrawObjects(this);

        drawingName = findViewById(R.id.objectDefinition);
        drawingMade = findViewById(R.id.drawSpace);
        drawingMade.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int xLocation = (int)event.getX();
        int yLocation = (int)event.getY();


        if(xLocation >= 600.0f && xLocation <= 1010.0f && yLocation <= 1000.0f && yLocation >= 700.0f ) {
            drawingName.setText("Small Rectangle");
        } else if(xLocation >= 300 && xLocation <= 800 && yLocation <= 600 && yLocation >= 100) {
            drawingName.setText("Big Square");
        } else if(xLocation >= 80 && xLocation <= 180 && yLocation <= 270 && yLocation >= 170) {
            drawingName.setText("Small Circle");
        } else if(xLocation >= 20 && xLocation <= 420 && yLocation <= 1200 && yLocation >= 800) {
            drawingName.setText("Large Circle");
        } else {
            drawingName.setText("Select Object");
        }


        // Send the text currently stored under the TextView Object to
        // The other class to be used for color change
        drawingSelected.sendText(drawingName.getText().toString());
        drawingMade.invalidate();
        return true;
    }
}
