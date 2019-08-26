package com.core;

import com.util.Printer;

public enum Response {
    BYE("(?i)^bye\\s*", (i, s) -> {
        Printer.printString("Bye. Hope to see you again soon!");
        s.toExit = true;
    }), UNKNOWN(".*", (i, s) -> {
        Printer.printError("I'm sorry but I don't know what that means :-(");
    });

    private String regex;
    private ResponseFunc func;
    Response(String r, ResponseFunc f) {
        regex = r;
        func = f;
    }

    /**
     * Given a string and program state, if string matches regex this enum will
     * call its response function.
     * @param i input string
     * @param s state object
     * @return  boolean if the string has matched
     */
    public boolean call(String i, State s) {
        if (i.matches(regex)) {
            func.f(i, s);
            return true;
        }
        return false;
    }
}