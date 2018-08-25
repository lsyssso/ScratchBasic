package com.vinzga.scratchbasic.Code;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.internal.util.Predicate;
import com.vinzga.scratchbasic.MainActivity;
import com.vinzga.scratchbasic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jared on 2016-05-23.
 */

/*
A proposition in graphical form which the user can edit.
GUIProposition is used by the LineOfCode class.
 */
public class GUIProposition extends LinearLayout {
    private GUIPredicate predicate;

    public GUIProposition(Context context, GUIPredicate predicate) {
        super(context);

        this.predicate = predicate;

        this.setOrientation(HORIZONTAL);
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        /* Add proposition's predicate */
        TextView nameView = new TextView(context);
        nameView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        nameView.setBackgroundColor(predicate.color);
        nameView.setGravity(Gravity.CENTER);
        nameView.setTextColor(Color.BLACK);
        nameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, CodeEditor.textSize);
        nameView.setText(predicate.name);
        nameView.setPadding(15, 0, 15, 0);
        this.addView(nameView);

        /* Add proposition's args */
        for (PredicateArgType predicateArgType : predicate.args) {
            /* ArgType 0 */
            if (predicateArgType == PredicateArgType.noSelect) {
                EditText arg = new EditText(context);
                arg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                arg.setMinimumWidth(100);
                arg.setGravity(Gravity.CENTER);
                arg.setTextColor(Color.BLACK);
                arg.setTextSize(TypedValue.COMPLEX_UNIT_SP, CodeEditor.textSize);
                arg.setInputType(InputType.TYPE_CLASS_TEXT);

                this.addView(arg);
            }

            /* ArgType 1 */
            if (predicateArgType == PredicateArgType.selectStatement) {
                GUIPredicate selectedStatement = MainActivity.getPredicate("GOTO");
                GUIProposition arg = new GUIProposition(context, selectedStatement);
                this.addView(arg);
            }

            /* ArgType 2 */
            if (predicateArgType == PredicateArgType.selectVariable) {
                Spinner arg = new Spinner(context);
                arg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                arg.setMinimumWidth(getResources().getDimensionPixelSize(R.dimen.GUIPropositionArgMinWidth));

                List<CharSequence> spinnerArray = new ArrayList<>();
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<> (
                        context, android.R.layout.simple_spinner_item, spinnerArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                arg.setAdapter(adapter);

                this.addView(arg);
            }

            /* ArgType 3 (Incomplete) */
            if (predicateArgType == PredicateArgType.selectGotoNum) {
                EditText arg = new EditText(context);
                arg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                arg.setMinimumWidth(100);
                arg.setGravity(Gravity.CENTER);
                arg.setTextColor(Color.BLACK);
                arg.setTextSize(TypedValue.COMPLEX_UNIT_SP, CodeEditor.textSize);
                arg.setInputType(InputType.TYPE_CLASS_TEXT);

                this.addView(arg);
            }
        }

        /*
        Add closing bracket to proposition
         */
        View endView = new View(context);
        endView.setBackgroundColor(predicate.color);
        endView.setLayoutParams(new ViewGroup.LayoutParams(15, ViewGroup.LayoutParams.MATCH_PARENT));
        this.addView(endView);
    }

    /*
    Get this proposition in code (string) form
     */
    public String getCode() {
        StringBuilder code = new StringBuilder();

        for (int i=0; i < this.getChildCount() - 1; i++) {
            View view = getChildAt(i);

            if (view instanceof GUIProposition) {
                code.append(((GUIProposition) view).getCode());
            }

            if (view instanceof TextView) {
                code.append(((TextView) view).getText());
            }

            if (view instanceof Spinner) {
                code.append(((Spinner) view).getSelectedItem());
            }

            if (i < this.getChildCount() - 2) {
                code.append(' ');
            }
        }

        return code.toString();
    }
}
