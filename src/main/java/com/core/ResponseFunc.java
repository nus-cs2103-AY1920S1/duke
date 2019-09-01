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
    boolean funcCall(String i, State s);
}