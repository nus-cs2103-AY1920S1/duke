package com.core;

/**
 * Interface used by Enum to store references to lambdas.
 */
public interface ResponseFunc {

    /**
     * Lambda's signature interface.
     * @param i input string
     * @param s state object
     */
    void f(String i, State s);
}