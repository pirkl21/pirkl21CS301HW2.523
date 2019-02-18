package com.example.pirkl21cs301hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.TextView;


public class DrawObjects extends SurfaceView {

    // Default value for the Alpha part of a color code.
    static final int ALPHA = 255;
    Canvas canvas1;
    // Objects to define the sizes of the "Rectangles"
    private String objectSelected = "";
    protected Rect bigSquare = new Rect(600, 700, 1010, 1000);
    protected Rect smallRectangle = new Rect(300, 600, 800, 100);

    // X position, Y position, Radius (Circle)
    private int[] smallCircle = {130, 220, 50};
    private int[] largeCircle = {220, 1000, 200};
    // Global Variables used to track the progress bar.
    private int greenProgress;
    private int redProgress;
    private int blueProgress;
    //Stored Colors of all 4 of the Shapes
    protected Paint smallCircleColor = new Paint();
    protected Paint largeCircleColor = new Paint();
    protected Paint smallRectangleColor = new Paint();
    protected Paint bigSquareColor = new Paint();

    public DrawObjects(Context context) {
        super(context);
        init();

    }

    public DrawObjects(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawObjects(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        init();


        if (greenProgress < 1 && blueProgress < 1 && redProgress < 1) {
            initDrawing(canvas);
        } else {
            initUpdateDrawing(canvas);
        }
    }

    public void initUpdateDrawing(Canvas canvas) {
        Paint greyStartPaint = new Paint();
        greyStartPaint.setColor(0xFF9B9B95);

        // If/Else Sequence to change the color
        // depending on the Object Selected.
        if (objectSelected.equals("Small Circle")) {
            smallCircleColor.setARGB(ALPHA, redProgress, blueProgress, greenProgress);
            canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], smallCircleColor);
            canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], greyStartPaint);
            canvas.drawRect(smallRectangle, greyStartPaint);
            canvas.drawRect(bigSquare, greyStartPaint);

        } else if (objectSelected.equals("Large Circle")) {
            largeCircleColor.setARGB(ALPHA, redProgress, blueProgress, greenProgress);
            canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], greyStartPaint);
            canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], largeCircleColor);
            canvas.drawRect(smallRectangle, greyStartPaint);
            canvas.drawRect(bigSquare, greyStartPaint);

        } else if (objectSelected.equals("Small Rectangle")) {
            smallRectangleColor.setARGB(ALPHA, redProgress, blueProgress, greenProgress);
            canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], greyStartPaint);
            canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], greyStartPaint);
            canvas.drawRect(smallRectangle, smallRectangleColor);
            canvas.drawRect(bigSquare, greyStartPaint);


        } else if (objectSelected.equals("Big Square")) {
            bigSquareColor.setARGB(ALPHA, redProgress, blueProgress, greenProgress);
            canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], greyStartPaint);
            canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], greyStartPaint);
            canvas.drawRect(smallRectangle, greyStartPaint);
            canvas.drawRect(bigSquare, bigSquareColor);
        }
    }

    public void initDrawing(Canvas canvas) {
        Paint greyStartPaint = new Paint();
        canvas.drawColor(0xFFFFFFFF);

        greyStartPaint.setColor(0xFF9B9B95);

        // Both circles don't have a specific object so I will have to use an array to store its data points
        // This is so I can access their sizes globally throughout the app without making too many
        // Individual variables.
        canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], greyStartPaint);
        canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], greyStartPaint);
        canvas.drawRect(bigSquare, greyStartPaint);
        canvas.drawRect(smallRectangle, greyStartPaint);
    }

    // Method to grab the most recent Shape selected
    public void sendText(String nameOfObject) {
        objectSelected = nameOfObject;
    }

    // Method to grab the integer values measured by the Progress of the
    // Seek Bars
    public void setProgress(int redProgressInfo, int greenProgressInfo, int blueProgressInfo) {
        redProgress = redProgressInfo;
        greenProgress = greenProgressInfo;
        blueProgress = blueProgressInfo;
    }

    public int getGreenProgress(int greenProgress){

    }

}
