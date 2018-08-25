package com.vinzga.scratchbasic.Program;

import android.widget.TextView;

import java.security.Key;

public class Print extends AbStatement {
    /**
     *Inherits AbStatement, it store the content of a PRINT statement inputed by the user
     */
    public Print(String s)
    {
        super(s);
    }



    public void execute(Program p){
        /**
         * append the output to the end of output string
         */
        try {
            TextView t = p.getConsole();
            //get the textview where to store the output
            if (!t.getText().toString().matches(""))
            //there is text in the textview, should add a new line
            {
                t.setText(t.getText() + "\n");
            }
            //append what needs to be printed to the output
            t.setText(t.getText() + Integer.toString(p.returnValue(this.slicedStatement[1])));
        }
        catch(KeyNotFoundException e)
        //trying to find a variable value but get nothing
        //means the variable does not exist
        {
            p.getConsole().setText(p.getConsole().getText() + e.getMessage());
        }
    }
}
