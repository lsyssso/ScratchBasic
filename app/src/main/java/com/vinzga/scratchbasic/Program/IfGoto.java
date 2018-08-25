package com.vinzga.scratchbasic.Program;


import java.security.Key;

public class IfGoto extends AbStatement {
    /**
     *Inherits AbStatement, it store the content of a IFGOTO statement inputed by the user
     */
    public IfGoto(String s)
    {
        super(s);
    }

    public void execute(Program m) {
        /**
         * getting the operator, and perform corresponding
         * comparison
         */
        //initiate a flag
        Boolean b = false;
        try {
            if (slicedStatement[2].matches("<"))//evaluate the symbol in the third index of the statements
            {
                //it is a <, then try to evaluate whether the number at the left < the number at the right
                if (m.returnValue(slicedStatement[1]) < m.returnValue(slicedStatement[3])) {
                    //set the flag to true
                    b = true;
                }
            } else if (slicedStatement[2].matches(">")) {
                //it is a >, then try to evaluate whether the number at the left > the number at the right
                if (m.returnValue(slicedStatement[1]) > m.returnValue(slicedStatement[3])) {
                    //set the flag to true
                    b = true;
                }
            } else if (slicedStatement[2].matches("==")) {
                //it is a ==, then try to evaluate whether the number at the left == the number at the right
                if (m.returnValue(slicedStatement[1]) == m.returnValue(slicedStatement[3])) {
                    //set the flag to true
                    b = true;
                }
            }
            if (b)// b is true, change the value of current line cl
            {
                m.setCl(m.returnValue(slicedStatement[5]) - 1);
            }
        }
        catch (KeyNotFoundException e)
        //trying to find a variable value but get nothing
        //means the variable does not exist
        {
            m.getConsole().setText(m.getConsole().getText() + e.getMessage());
        }
    }
}
