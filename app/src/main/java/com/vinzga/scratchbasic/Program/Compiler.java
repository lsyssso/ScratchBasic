package com.vinzga.scratchbasic.Program;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Jared on 2016-05-24.
 */

public class Compiler {
    //console for displaying output
    private TextView standardOutputConsole;
    //console for displaying variable and their values
    private TextView debugConsole;

    public Compiler(TextView standardOutputConsole) {
        assert standardOutputConsole != null;

        this.standardOutputConsole = standardOutputConsole;
        this.debugConsole = null;
    }

    public Compiler(TextView standardOutputConsole, TextView debugConsole) {
        assert standardOutputConsole != null;
        assert debugConsole != null;

        this.standardOutputConsole = standardOutputConsole;
        this.debugConsole = debugConsole;
    }

    public void execute(String code) {
        assert this.standardOutputConsole != null;
        //create a new program, pass only the output console to it
        Program program = new Program(code.split("\n"), this.standardOutputConsole);
        program.start();
    }

    public void debug(String code) {
        assert this.standardOutputConsole != null;
        assert debugConsole != null;
        //create a new program, pass the output console to it and a debug console
        Program program = new Program(code.split("\n"), this.standardOutputConsole, this.debugConsole);
        program.debug();
    }
}
