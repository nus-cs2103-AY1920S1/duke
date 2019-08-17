package main.java;

import java.io.*;

public class UnicodeTest {

    static String cross() {
        return "\u2718";
    }

    static String check() {
        return "\u2713";
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println("how is this?");
        out.println(cross());
        out.println(check());

    }
}