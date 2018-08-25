package com.vinzga.scratchbasic;

/*
This is the activity created after the user clicks 'RUN' from the MainActivity.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vinzga.scratchbasic.Program.Compiler;
import com.vinzga.scratchbasic.R;

public class RunActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        Bundle extras = getIntent().getExtras();
        String code = extras.getString("code");

        final Button haltButton = (Button) findViewById(R.id.buttonHalt);
        final Compiler compiler = new Compiler((TextView) findViewById(R.id.consoleRun));

        // Set up 'Halt' button
        haltButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Execute code
        compiler.execute(code);
    }
}
