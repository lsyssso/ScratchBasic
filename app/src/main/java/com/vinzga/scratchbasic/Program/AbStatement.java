package com.vinzga.scratchbasic.Program;

import com.vinzga.scratchbasic.Program.Program;

public abstract class AbStatement {
    /**
     * this is a abstract class for statements
     */

    //a slicedStatement is a statement being sliced by using a space
    //for example "PRINT 10" will be sliced to {"PRINT","10"}
    protected String[] slicedStatement;

    //the statements is sliced into string array
    public AbStatement(String s){
        slicedStatement = s.split(" ");
    }

    public abstract void execute(Program p);
}
