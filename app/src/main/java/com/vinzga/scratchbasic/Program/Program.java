package com.vinzga.scratchbasic.Program;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {
    /**
     * Program is a class that contains a list of Statement and ready to be executed
     * any sub function of a Program is kept in a hashmap.
     */
    private int linenumber = 0;
    //a hashmap to store variables
    private HashMap<String, Integer> variables = new HashMap<String, Integer>();
    //track the position of linenumber executed
    private int cl = 0;
    //an array list to store all statements to be executed
    private ArrayList<AbStatement> statements = new ArrayList<AbStatement>();
    //to display the output
    private TextView console;
    private TextView varConsole;
    //a list of function, which is shared across all functions
    private static HashMap<String, Program> functions = new HashMap<String, Program>();




    public int returnValue(String s) throws KeyNotFoundException {
        /**
         * return a variable, since we don't know whether it is a numeric or alphabetic, we try
         * to convert it into a numeric value, if succeed return the numeric value, otherwise
         * look into the variable hashmap.
         */
        try {
            int i = Integer.parseInt(s);
            return i;
        }
        catch(Exception e){
            try {
                int i = this.variables.get(s);
                return i;
            }
            catch(NullPointerException f)
            {
                throw new KeyNotFoundException(s + " is not declared\n");
            }


        }
    }

    public TextView getConsole() {
        return console;
    }

    public int getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(int linenumber) {
        this.linenumber = linenumber;
    }

    public HashMap<String, Integer> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, Integer> variables) {
        this.variables = variables;
    }

    public int getCl() {
        return cl;
    }

    public void setCl(int cl) {
        this.cl = cl;
    }

    public ArrayList<AbStatement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<AbStatement> statements) {
        this.statements = statements;
    }

    public Program(String[] cmds, TextView standardOutputConsole)
    {
        console = standardOutputConsole;
        prepareForRun(cmds);
    }

    public Program(String[] cmds, TextView outputConsole, TextView variableConsole)
    {
        console = outputConsole;
        varConsole = variableConsole;
        varConsole.setText("");
        prepareForRun(cmds);
    }

    private void prepareForRun(String[] cmds)
    /**
     * the method take an array of string, for each index in the array, it is a scratch basic statement
     * ,the method then analysis what statement it is, create corresponding statement object and store it
     * to the list.
     */
    {
        String cmd;
        //functionMode is used to detect whether we are trying to interpret part of sub-function
        //whenever we encounter a FUNC prefix, increment this number, and decrement it when we encounter RETURN
        int functionMode = 0;
        //function is used to store all statements for sub-function
        String function = "";
        //function name store the function name
        String functionName = "";
        for(String s : cmds)
        {
            //slice the statement
            String[] sliced = s.split(" ");
            cmd = sliced[0];
            switch (cmd){
                //check what statement it is
                case "PRINT":
                    if(functionMode > 0)
                    //if we have encounter FUNC, do not create new instance of Statement
                    {
                        function = function + s;
                        function = function + '\n';
                        break;
                    }
                    AbStatement a = new Print(s);
                    statements.add(a);
                    break;
                case "LET":
                    if(functionMode > 0)
                    //if we have encounter FUNC, do not create new instance of Statement
                    {
                        function = function + s;
                        function = function + '\n';
                        break;
                    }
                    AbStatement b = new Let(s);
                    statements.add(b);
                    break;
                case "GOTO":
                    if(functionMode > 0)
                    //if we have encounter FUNC, do not create new instance of Statement
                    {
                        function = function + s;
                        function = function + '\n';
                        break;
                    }
                    AbStatement d = new Goto(s);
                    statements.add(d);
                    break;
                case "IF":
                    if(functionMode > 0)
                    //if we have encounter FUNC, do not create new instance of Statement
                    {
                        function = function + s;
                        function = function + '\n';
                        break;
                    }
                    AbStatement e = new IfGoto(s);
                    statements.add(e);
                    break;
                case "FUNC":
                    if(functionMode == 0) {
                        //if we have encounter FUNC, do not create new instance of Statement
                        functionMode += 1;
                        functionName = s.split(" ")[1];
                    }else{
                        function = function + s;
                        function = function + '\n';
                    }
                    break;
                case "RETURN":

                    functionMode -= 1;
                    if(functionMode == 0) {
                        //if we have encounter FUNC, do not create new instance of Statement
                        functions.put(functionName, new Program(function.split("\n"), console));
                        function = "";
                    }else{
                        function = function + s;
                        function = function + '\n';
                    }
                    break;
                case "GOSUB":
                    if(functionMode > 0)
                    //if we have encounter FUNC, do not create new instance of Statement
                    {
                        function = function + s;
                        function = function + '\n';
                        break;
                    }
                    AbStatement g = new GoSub(s);
                    statements.add(g);
                default:
                    break;
            }
        }
        //the total line number is the total number of statements in the list
        linenumber = statements.size();
    }

    public void start()
    {
        /**
         * look into each textview, generate statement objects based on the text, then
         * execute all the statements.
         */

        while(cl < linenumber && linenumber != 0){
            //loop until current line >= to total line number
            next();
            cl++;
        }

    }

    public void next()
    {
        /**
         * execute the statement base on current line
         */
        statements.get(cl).execute(this);
    }

    public void debug()
    {
        this.start();
        for(String key : variables.keySet())
        {
            varConsole.setText(varConsole.getText() + key + " = " + variables.get(key) + '\n');
        }
    }

    public void setConsole(TextView console) {
        this.console = console;
    }

    public HashMap<String, Program> getFunctions() {
        return functions;
    }

    public void setFunctions(HashMap<String, Program> functions) {
        this.functions = functions;
    }
}
