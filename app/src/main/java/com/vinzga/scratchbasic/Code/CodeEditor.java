package com.vinzga.scratchbasic.Code;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.vinzga.scratchbasic.MainActivity;
import com.vinzga.scratchbasic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jared on 2016-05-23.
 */
public class CodeEditor extends LinearLayout {
    public static float textSize;
    public static int codeNumberMargin;

    // A list of variables
    public static List<CharSequence> variables = new ArrayList<>();
    static {
        variables.add("a");
        variables.add("b");
        variables.add("c");

        variables.add("x");
        variables.add("y");
        variables.add("z");
    }

    // BEGIN constructors...
    public CodeEditor(Context context) {
        super(context);
    }

    public CodeEditor(Context context, AttributeSet attrs) {
        super(context, attrs);

        constructDefault();
    }

    public CodeEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        constructDefault();
    }

    private void constructDefault() {textSize = 16;
        codeNumberMargin = getResources().getDimensionPixelSize(R.dimen.lineNoWidth);
    }

    // END ... constructors

    /*
    Get the inputted code...
     */
    public String getCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < this.getChildCount(); i++) {
            LineOfCode view = (LineOfCode) this.getChildAt(i);
            code.append(view.getCode());

            if (i < this.getChildCount() - 1) {
                code.append('\n');
            }
        }

        return code.toString();
    }

    /*
    Update the line numbers for each LineOfCode
     */
    public void updateLineNumbers() {
        for (int i = 0; i < this.getChildCount(); i++) {
            LineOfCode lineOfCode = (LineOfCode) this.getChildAt(i);
            lineOfCode.setLineNo(i);
        }
    }

    /*
    Update add a new LineOfCode
     */
    public void addLineOfCode(GUIPredicate predicate) {
        LineOfCode lineOfCode = new LineOfCode(getContext(), this.getChildCount(), predicate);
        this.addView(lineOfCode);
        lineOfCode.requestFocus();

        /*
        The 'requestFocus()' method does not work for many views... Thus,
        an alternate method of shifting the scrollview focus to bottom is optimal.
        */
    }
}
