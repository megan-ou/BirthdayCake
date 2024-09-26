package cs301.birthdaycake;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import java.lang.reflect.Array;

public class CakeController implements OnClickListener, CompoundButton.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private CakeView cakeView;
    private CakeModel cakeModel;

    public CakeController(CakeView cv) {
        cakeView =  cv;
        cakeModel = cakeView.getCakeModel();

    }

    @Override
    public void onClick(View view) {
        Log.d("cake", "click!");

        //set candle flames to false
            cakeModel.candlesLit = false;

        //redraw the image
        cakeView.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.d("cake", "switch!");

//        //set candles to false if true and vice versa
//        if(cakeModel.hasCandles) {
//            cakeModel.hasCandles = false;
//        }
//        else {
//            cakeModel.hasCandles = true;
//        }

        cakeModel.hasCandles = b;

        //redraw the image
        cakeView.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        cakeModel.numCandles = i;
        cakeView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //Find out where the touch occurred and save it
        cakeModel.squareX = motionEvent.getX();
        cakeModel.squareY = motionEvent.getY();
        cakeModel.touched = true;

        cakeView.invalidate();
        return true;
    }
}
