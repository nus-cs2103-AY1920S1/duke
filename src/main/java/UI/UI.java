package UI;

import java.io.PrintStream;
import java.util.Scanner;

public class UI {

    private final Scanner sc;
    private final PrintStream output = System.out;

    public UI() {
        sc = new Scanner(System.in);
    }

    public String nextLine() {
        return sc.nextLine();
    }

    public void printHorizontalLine() {
        output.println("____________________________________________________________");
    }

    public void println(String s) {
        output.println(s);
    }

    public void printData(String content) {
        printHorizontalLine();
        println(content);
        printHorizontalLine();
    }

    public void printLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}

