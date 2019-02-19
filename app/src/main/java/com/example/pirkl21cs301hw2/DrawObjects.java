package com.example.pirkl21cs301hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;


public class DrawObjects extends SurfaceView {

    // Objects to define the sizes of the "Rectangles"
    private String objectSelected = ""; // Variable containing the name of the object selected

    // Set object sizes so I don't have to type out their sizes every time I use them
    protected Rect smallRectangle = new Rect(600, 700, 1010, 1000);
    protected Rect bigSquare = new Rect(300, 600, 800, 100);

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
        // a if statement to check if the app is on first load-up. If so,
        // Run the default initDrawing method which creates the shapes under a
        // default shape. If the user sets all sliders to zero they can
        // reset the colors of all the shapes.
        if (greenProgress < 1 && blueProgress < 1 && redProgress < 1) {
            initDrawing(canvas);
        } else {
            initUpdateDrawing(canvas);
        }
    }


    // Init drawing will implement the color changes based on
    // the object selected in the main activity then commit the changes to
    // the instance variables. If no object is selected then the canvas
    // will show all the shapes and their colors.
    public void initUpdateDrawing(Canvas canvas) {
        Paint greyStartPaint = new Paint();
        greyStartPaint.setColor(0xFF9B9B95);

        // If/Else Sequence to change the color
        // depending on the Object Selected.
        if (objectSelected.equals("Small Circle")) {
            smallCircleColor.setColor(Color.rgb(redProgress, greenProgress, blueProgress));
            canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], smallCircleColor);
            canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], greyStartPaint);
            canvas.drawRect(smallRectangle, greyStartPaint);
            canvas.drawRect(bigSquare, greyStartPaint);

        } else if (objectSelected.equals("Large Circle")) {
            largeCircleColor.setColor(Color.rgb(redProgress, greenProgress, blueProgress));
            canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], greyStartPaint);
            canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], largeCircleColor);
            canvas.drawRect(smallRectangle, greyStartPaint);
            canvas.drawRect(bigSquare, greyStartPaint);

        } else if (objectSelected.equals("Small Rectangle")) {
            smallRectangleColor.setColor(Color.rgb(redProgress, greenProgress, blueProgress));
            canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], greyStartPaint);
            canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], greyStartPaint);
            canvas.drawRect(smallRectangle, smallRectangleColor);
            canvas.drawRect(bigSquare, greyStartPaint);


        } else if (objectSelected.equals("Big Square")) {
            bigSquareColor.setColor(Color.rgb(redProgress, greenProgress, blueProgress));
            canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], greyStartPaint);
            canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], greyStartPaint);
            canvas.drawRect(smallRectangle, greyStartPaint);
            canvas.drawRect(bigSquare, bigSquareColor);
        } else {
            initDrawing(canvas);
        }
    }

    // initDrawing is the method that will show all color changes on the canvas
    // based on values stored in the instance variables
    public void initDrawing(Canvas canvas) {
        Paint greyStartPaint = new Paint();
        canvas.drawColor(0xFFFFFFFF);

        greyStartPaint.setColor(0xFF9B9B95);

        // Check if the program is running for the first time, if so
        // set the colors to the default grey, if not. set the values
        // to the color stored in the instance variable.
        if (redProgress < 1 && blueProgress < 1 && greenProgress < 1) {
            this.smallCircleColor.setColor(greyStartPaint.getColor());
            this.largeCircleColor.setColor(greyStartPaint.getColor());
            this.bigSquareColor.setColor(greyStartPaint.getColor());
            this.smallRectangleColor.setColor(greyStartPaint.getColor());
        }
        canvas.drawCircle(smallCircle[0], smallCircle[1], smallCircle[2], smallCircleColor);
        canvas.drawCircle(largeCircle[0], largeCircle[1], largeCircle[2], largeCircleColor);
        canvas.drawRect(bigSquare, bigSquareColor);
        canvas.drawRect(smallRectangle, smallRectangleColor);
    }

    // Method to grab the most recent Shape selected
    public void sendText(String nameOfObject) {
        this.objectSelected = nameOfObject;
    }

    // Methods to grab the integer values measured by the Progress of the
    // Seek Bars

    /**
     * External Citation
     * Date: February 19, 2019
     * Problem: Getting the progress from the other class, I forgot
     * that setters/getters where a thing so I had to google it.
     * Resource:
     * https://www.codejava.net/coding/java-getter-and-setter-tutorial-from-basics-to-best-practices
     * Solution: Use a setter and getter to communicated changes in individual
     * progress bars then transmit them through instance variables to the other class.
     */
    public void setGreenProgress(int greenProgress) {
        this.greenProgress = greenProgress;
    }

    public void setRedProgress(int redProgress) {
        this.redProgress = redProgress;
    }

    public void setBlueProgress(int blueProgress) {
        this.blueProgress = blueProgress;
    }

}
