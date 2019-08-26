package ui;

import java.util.Scanner;

public class Ui {
    protected String horizontalLine;
    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.horizontalLine = "    ____________________________________________________________";
    }

    public void showLine() {
        System.out.println(horizontalLine);
    }

    public void showWelcome() {
        System.out.println(horizontalLine);
        System.out.println("     Hello from! I'm Duke\n" + "     What can I do for you?");
        System.out.println(horizontalLine + "\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String e) {
        System.out.println("     " + e);
    }

    public void showLoadingError() {
        System.out.println(horizontalLine);
        System.out.println("     Note: task.TaskList storage is initially empty / the file is corrupted");
        System.out.println("     New empty file will be created.");
        System.out.println(horizontalLine + "\n");
    }
}