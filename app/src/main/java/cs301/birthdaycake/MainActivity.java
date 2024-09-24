package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        //create a reference to cakeview object in XML layout
        CakeView cakeView = findViewById(R.id.cakeview);

        //create a new CakeController object
        CakeController cakeController = new CakeController(cakeView);

        //create a reference to Blow Out Button
        Button btnBlowOut = findViewById(R.id.btnExtinguish);

        //create a reference to candles switch
        Switch swCandles = findViewById(R.id.swCandles);

        //create a reference to seek bar
        SeekBar numCandles = findViewById(R.id.numcandles);

        //register CakeController as listener for button
        btnBlowOut.setOnClickListener(cakeController);

        //register CakeController as listener for switch
        swCandles.setOnCheckedChangeListener(cakeController);

        //register CakeController as listener for seek bar
        numCandles.setOnSeekBarChangeListener(cakeController);
    }

    public void goodbye(View button) {
        Log.i("button", "Goodbye");
        finishAffinity();
    }
}
