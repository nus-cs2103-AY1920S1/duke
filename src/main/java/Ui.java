//package mypackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the user interactions.
 */
public class Ui {

    Scanner scanner;
    Ui(){
        scanner = new Scanner(System.in);
    }

    /**
     * Displays welcome message.
     */
    void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Displays partition line.
     */
    void showLine(){
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Reads the command input.
     * @return returns the command line.
     */
    String readCommand(){
        return scanner.nextLine();
    }

    /**
     * Prints and display the given string to the users.
     * @param string string to be displayed
     */
    public void printString(String string){
        System.out.println("    " + string);
    }

    /**
     * Displays loading error.
     */
    void showLoadingError(){
        System.out.println("    Failed to load file");
    }

    /**
     * Displays error.
     * @param string description of error
     */
    void showError(String string){
        printString(string);
    }
}
