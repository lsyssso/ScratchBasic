package com.vinzga.scratchbasic;

/*
This is the activity created after the user clicks 'DEBUG' from the MainActivity.
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinzga.scratchbasic.Program.Compiler;
import com.vinzga.scratchbasic.R;

public class DebugActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        // INTENT
        Bundle extras = getIntent().getExtras();
        String code = extras.getString("code");
        ((TextView) findViewById(R.id.codeEditor)).setText(code);
        ((TextView) findViewById(R.id.codeNumbers)).setText(extras.getString("codeNumbers"));

        // 'the' views
        final Button haltButton = (Button) findViewById(R.id.buttonHalt);
        final Compiler compiler = new Compiler((TextView) findViewById(R.id.consoleRun), (TextView) findViewById(R.id.consoleDebug));
        final ImageView consoleSlider = (ImageView) findViewById(R.id.consoleSlider);

        compiler.debug(code);

        // Set up 'Halt' button
        haltButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        // Execute code
        compiler.execute(code);

        // Set up slider
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        consoleSlider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                LinearLayout footer = (LinearLayout) findViewById(R.id.footer);
                footer.bringToFront();

                float limitLower = metrics.heightPixels - 200;
                float limitUpper = findViewById(R.id.header).getHeight();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        footer.getLayoutParams().height = (int) (metrics.heightPixels - event.getRawY());

                        int tNa;
                        tNa = (int) (metrics.heightPixels - limitUpper);
                        if (footer.getLayoutParams().height > tNa) {
                            footer.getLayoutParams().height = tNa;
                        }
                        tNa = (int) (metrics.heightPixels - limitLower);
                        if (footer.getLayoutParams().height < tNa) {
                            footer.getLayoutParams().height = tNa;
                        }

                        footer.setBaselineAligned(true);

                        break;
                }

                return true;
            }
        });
    }
}
