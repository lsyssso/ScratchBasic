package com.vinzga.scratchbasic.Program;

public class Let extends AbStatement {
    /**
     *Inherits AbStatement, it store the content of a LET statement inputed by the user
     */
    public Let(String s)
    {
        super(s);
    }

    public void execute(Program m){
        /**
         * change the value of specified key in variable hashmap, currently only support single value assignment.
         */
        try {
            if (slicedStatement.length > 4)
            //determine the length of the statement, if it's greater than 4, it means it's assigning the variable with operation
            //In this case, call calculate() to assign the result of operation to the variable
            {
                calculate(m);
            }
            else
            {
                //in this case it means, it's assigning a variable with single value, update the variable value in dictionary

                Integer i = Integer.parseInt(slicedStatement[1]);
                m.getConsole().setText(m.getConsole().getText() + slicedStatement[1] + " is not a proper name for variable");

            }
        }
        catch(NumberFormatException n)
        {
            try
            {
                m.getVariables().put(slicedStatement[1], m.returnValue(slicedStatement[3]));
            }

            catch(KeyNotFoundException e)
            //trying to find a variable value but get nothing
            //means the variable does not exist
            {
                m.getConsole().setText(m.getConsole().getText() + e.getMessage());
            }
        }
    }

    private void calculate(Program m) {
        /**
        the method will evaluate the statement, and see what operation it is to perform
         it then get the value of each side of the operation symbol, either a variable or
         integer number, do the operation, and update the variable value in variable hashmap
         */
        int result;
        try {
            if (slicedStatement[4].matches("\\+")) {
                //it is an addition operation
                result = m.returnValue(slicedStatement[3]) + m.returnValue(slicedStatement[5]);
                m.getVariables().put(slicedStatement[1], result);
            } else if (slicedStatement[4].matches("-")) {
                //it is a subtraction operation
                result = m.returnValue(slicedStatement[3]) - m.returnValue(slicedStatement[5]);
                m.getVariables().put(slicedStatement[1], result);
            } else if (slicedStatement[4].matches("\\*")) {
                //it is a multiplication operation
                result = m.returnValue(slicedStatement[3]) * m.returnValue(slicedStatement[5]);
                m.getVariables().put(slicedStatement[1], result);
            } else if (slicedStatement[4].matches("/")) {
                //it is a division operation
                result = m.returnValue(slicedStatement[3]) / m.returnValue(slicedStatement[5]);
                m.getVariables().put(slicedStatement[1], result);
            }
        }
        catch(KeyNotFoundException e)
        //trying to find a variable value but get nothing
        //means the variable does not exist
        {
            m.getConsole().setText(m.getConsole().getText() + e.getMessage());
        }
    }
}
