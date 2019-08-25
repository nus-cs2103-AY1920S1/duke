package main;

import java.util.Scanner;

public class Ui {
    Scanner sc;
    String currentLine;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void nextLine(String nextLine) {
        this.currentLine = nextLine;
    }

    public String readCommand() {
        currentLine = sc.nextLine();
        return currentLine;
    }

    public void showLine() {
        System.out.println(currentLine);
        currentLine = null;
    }

    public boolean hasLineToShow() {
        return (currentLine != null);
    }

    public void showError(String msg) {
        System.out.println("    ____________________________________________________________\n" + "    " + msg + "\n" +
                "    ____________________________________________________________");
    }

    public void showWelcome() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    public void showExit() {
        sc.close();
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
