package com.vinzga.scratchbasic.Program;

/**
 * Created by liangsiyang on 25/05/16.
 */
public class GoSub extends AbStatement {

    public GoSub(String s)
    {
        super(s);
    }



    public void execute(Program p){
        /**
         * append the output to the end of output string
         */
        try {
            p.getFunctions().get(slicedStatement[1]).start();
        }
        catch(NullPointerException e) //the name of the function does not exist in the dictionary
        {
            p.getConsole().setText(p.getConsole().getText() + "function " + slicedStatement[1] + " is not declared\n");
        }
    }
}
