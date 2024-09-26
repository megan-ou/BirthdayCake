package cs301.birthdaycake;

public class CakeModel {

    public boolean candlesLit;
    public int numCandles;
    public boolean hasFrosting;
    public boolean hasCandles;
    public String touchLoc;
    public boolean balloonDrawn;
    public float balloonX;
    public float balloonY;


    public CakeModel() {
        candlesLit = true;
        numCandles = 2;
        hasFrosting = true;
        hasCandles = true;
        touchLoc = "no input";
        balloonDrawn = false;
        balloonX = 0;
        balloonY = 0;

    }

}
