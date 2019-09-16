package UserInterface;

import java.io.PrintStream;
import java.util.Scanner;

public class UI_CLI implements UI{

    private final Scanner sc;
    private final PrintStream output = System.out;

    public UI_CLI() {
        sc = new Scanner(System.in);
    }

    @Override
    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        println("Hello from\n" + logo);
        printContent("Hello! I'm Duke\n" +
                "What can I do for you?\n");
    }

    private void println(String s) {
        output.println(s);
    }


    public void printContent(String content){
        printHorizontalLine();
        println(content);
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        output.println("____________________________________________________________");
    }

    public String getUserInput(){
        return sc.nextLine();
    }
}
