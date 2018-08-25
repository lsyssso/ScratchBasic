package com.vinzga.scratchbasic.Program;

public class Goto extends AbStatement {
    /**
     *Inherits AbStatement, it store the content of a GOTO statement inputed by the user
     */

    public Goto(String s)
    {
        super(s);
    }

    public void execute(Program m) {
        /**
         * change the current line cl to what's specified
         * in the goto statement.
         */
        try {
            //get the second item in a goto statement, which is a number or a variable name
            //set the current line in mainactivity to that number
            m.setCl(m.returnValue(this.slicedStatement[1]) - 1);
        }
        catch (KeyNotFoundException e)
        //trying to find a variable value but get nothing
        //means the variable does not exist
        {
            m.getConsole().setText(m.getConsole().getText() + e.getMessage());
        }
    }
}
