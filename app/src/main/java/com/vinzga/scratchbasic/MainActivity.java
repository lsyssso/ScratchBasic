package com.vinzga.scratchbasic;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.vinzga.scratchbasic.Code.CodeEditor;
import com.vinzga.scratchbasic.Code.GUIPredicate;
import com.vinzga.scratchbasic.Code.LineOfCode;
import com.vinzga.scratchbasic.Code.PredicateArgType;

/* The MainActivity */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get MainActivity 'the' views...
        final CodeEditor codeEditor = (CodeEditor) findViewById(R.id.codeEditor);
        final GridLayout GUIPredicateButtonView = (GridLayout) findViewById(R.id.GUIPredicateButtonContainer);
        final Button runButton = (Button) findViewById(R.id.buttonRun);
        final Button debugButton = (Button) findViewById(R.id.buttonDebug);

        //Set up 'Run' button
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RunActivity.class);
                intent.putExtra("code", codeEditor.getCode());
                startActivity(intent);
            }
        });

        //Set up 'Debug' button
        debugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DebugActivity.class);
                intent.putExtra("code", codeEditor.getCode());
                StringBuilder aString = new StringBuilder();
                aString.append("0");
                for (int i=1; i < codeEditor.getChildCount(); i++) {
                    aString.append('\n');
                    aString.append(i);
                }
                intent.putExtra("codeNumbers", aString.toString());
                startActivity(intent);
            }
        });

        /*
        Add GUIPredicateButton(s).
        When a GUIPredicateButton is pressed, it adds a LineOfCode to the CodeEditor.
         */
        int GUIpredicateHeight = getResources().getDimensionPixelSize(R.dimen.GUIPredicateHeight);
        for (final GUIPredicate predicate : ScratchBasicFunctions) {
            // CREATE
            TextView myView = new TextView(this);
            myView.setWidth(1);
            myView.setHeight(GUIpredicateHeight);
            myView.setText(predicate.name);
            myView.setGravity(Gravity.CENTER);
            myView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            myView.setTextColor(Color.BLACK);
            myView.setBackgroundColor(predicate.color);

            // BUTTON-ize
            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    codeEditor.addLineOfCode(predicate);
                }
            });

            //GridView parameters...
            int margin = 5;
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(margin, margin, margin, margin);

            GUIPredicateButtonView.addView(myView, params);
        }
    }

    //ArrayList of predicates...
    private static final GUIPredicate[] ScratchBasicFunctions = new GUIPredicate[] {
            new GUIPredicate("LET", new PredicateArgType[] {PredicateArgType.selectVariable, PredicateArgType.noSelect}, Color.YELLOW),
            new GUIPredicate("PRINT", new PredicateArgType[] {PredicateArgType.noSelect}, Color.YELLOW),
            new GUIPredicate("REM", new PredicateArgType[] {PredicateArgType.noSelect}, Color.WHITE),
            new GUIPredicate("GOTO", new PredicateArgType[] {PredicateArgType.selectGotoNum}, Color.BLUE),
            new GUIPredicate("IF", new PredicateArgType[] {PredicateArgType.noSelect, PredicateArgType.selectStatement}, Color.BLUE),
            new GUIPredicate("GOSUB", new PredicateArgType[] {PredicateArgType.selectGotoNum}, Color.RED),
            new GUIPredicate("FUNC", new PredicateArgType[] {PredicateArgType.selectGotoNum}, Color.RED),
            new GUIPredicate("RETURN", new PredicateArgType[] {PredicateArgType.selectVariable}, Color.RED),
    };

    /*
    Get a predicate from the above ArrayList with certain name
     */
    public static GUIPredicate getPredicate(String name) {
        for (GUIPredicate GUIPredicate : ScratchBasicFunctions) {
            if (GUIPredicate.name.equals(name)) {
                return GUIPredicate;
            }
        }

        return null;
    }
}
