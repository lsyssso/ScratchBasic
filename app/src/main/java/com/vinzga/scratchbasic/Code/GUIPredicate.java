package com.vinzga.scratchbasic.Code;

/**
 * Created by Jared on 2016-05-11.
 */

/*
A predicate with a graphical component, i.e, a colour.
 */

public class GUIPredicate {
    public String name;
    public PredicateArgType[] args;
    public int color;

    public GUIPredicate(String name, PredicateArgType[] args, int color) {
        this.name = name;
        this.args = args;
        this.color = color;
    }
}
