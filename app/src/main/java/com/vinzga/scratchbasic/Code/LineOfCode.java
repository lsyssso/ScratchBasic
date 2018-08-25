package com.vinzga.scratchbasic.Code;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinzga.scratchbasic.R;

/**
 * Created by Jared on 2016-05-24.
 */

/*
LineOfCode instances are used in the CodeEditor class.
Each LineOfCode represents a line of code which is an aggregation of a line number and a proposition.
*/
public class LineOfCode extends LinearLayout {
    public LineOfCode(Context context, int lineNo, GUIPredicate predicate) {
        super(context);

        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.lineOfCodeHeight)));
        this.setOrientation(HORIZONTAL);

        // lineNoView... (The LineNoView displays the lineNo)
        TextView lineNoView = new TextView(context);
        lineNoView.setLayoutParams(new ViewGroup.LayoutParams(CodeEditor.codeNumberMargin, ViewGroup.LayoutParams.MATCH_PARENT));
        lineNoView.setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
        lineNoView.setPadding(0, 0, 10, 0);
        lineNoView.setGravity(Gravity.CENTER);
        lineNoView.setTextColor(Color.BLACK);
        lineNoView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        this.addView(lineNoView);
        setLineNo(lineNo);
        // ...lineNoView

        // Add the predicate part...
        this.addView(new GUIProposition(context, predicate));

        /* Delete view from layout by touching proposition */
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (v instanceof LineOfCode) {
                    CodeEditor codeEditor = (CodeEditor) v.getParent();
                    codeEditor.removeView(v);
                    codeEditor.updateLineNumbers();
                }

                return true;
            }
        });
    }

    /*
    Set the line number to a specified value.
    Also update the lineNoView.
     */
    public void setLineNo(int lineNo) {
        TextView lineNoView = (TextView) getChildAt(0);
        lineNoView.setText(Integer.toString(lineNo));
    }

    /*
    Get the code of the LineOfCode.
    Excludes the line number.
     */
    public String getCode() {
        GUIProposition proposition = (GUIProposition) this.getChildAt(1);
        return proposition.getCode();
    }
}
