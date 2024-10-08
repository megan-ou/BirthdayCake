package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint textPaint = new Paint();
    Paint squareLTBtmR = new Paint();
    Paint squareRTBtmL = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    // made the candleWidth thicker (40.0f --> 90.0f)
    public static final float candleWidth = 90.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;
    public static final float balloonHeightDiv2 = 85.0f;
    public static final float balloonWidthDiv2 = 60.0f;
    public static final float squareHeight = 50.0f;
    public static final float squareWidth = 55.0f;

    //private instance variable of CakeModel
    private CakeModel myCake;


    /**
     * actor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFF89CFF0);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(56);
        squareLTBtmR.setColor(Color.GREEN);
        squareLTBtmR.setStyle(Paint.Style.FILL);
        squareRTBtmL.setColor(Color.RED);
        squareRTBtmL.setStyle(Paint.Style.FILL);


        setBackgroundColor(Color.WHITE);  //better than black default

        //create a new CakeModel object
        myCake = new CakeModel();
    }

    /**
     * Getter method to retrieve a reference of CakeModel object
     */
    public CakeModel getCakeModel () {
        return this.myCake;
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        //only draw objects in this method is hasCandles is true
        if(myCake.hasCandles) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

            //only draw flame if candles is true
            if (myCake.candlesLit) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }

            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }
    }

    public void drawSquare(Canvas canvas, float xLoc, float yLoc) {
        //left, top, right, bottom, paint

        //Draw top left rectangle
        canvas.drawRect(xLoc - squareWidth/2, yLoc - squareHeight/2, xLoc,
                yLoc, squareLTBtmR);
        //Draw bottom left rectangle
        canvas.drawRect(xLoc - squareWidth/2, yLoc, xLoc, yLoc + squareHeight/2, squareRTBtmL);
        //Draw bottom right rectangle
        canvas.drawRect(xLoc, yLoc - squareHeight/2, xLoc + squareWidth/2, yLoc + squareHeight/2, squareLTBtmR);
        //Draw top right rectangle
        canvas.drawRect(xLoc, yLoc - squareHeight/2, xLoc + squareWidth/2, yLoc, squareRTBtmL);

    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */


    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now draw a number of candles based on numCandles

        for (int i = 1; i <= myCake.numCandles; i++) {
            drawCandle(canvas, cakeLeft + i * cakeWidth/ (myCake.numCandles + 1)
                    - i * candleWidth/ (myCake.numCandles + 1), cakeTop);
        }




        canvas.drawText(myCake.touchLoc, 1550,900,textPaint);

//        drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth/3, cakeTop);
//
//        //Draw a candle to the right
//        drawCandle(canvas, cakeLeft + (2 * cakeWidth/3) - (2 * candleWidth/3), cakeTop);

        float cVballoonX = getCakeModel().balloonX;
        float cVballoonY = getCakeModel().balloonY;
        if (getCakeModel().balloonDrawn) {
            canvas.drawOval(cVballoonX - balloonWidthDiv2, cVballoonY - balloonHeightDiv2,
                    cVballoonX + balloonWidthDiv2, cVballoonY + balloonHeightDiv2,
                    cakePaint);
            canvas.drawLine(cVballoonX, cVballoonY + balloonHeightDiv2,
                    cVballoonX, cVballoonY + balloonHeightDiv2*2, candlePaint);
        }

        //Draw the checkerboard square drawing if user touches the screen
        if(myCake.touched) {
            drawSquare(canvas, myCake.squareX, myCake.squareY);
        }

    }//onDraw

}//class CakeView

