package duke.ui;

import java.util.Scanner;

public class UI {
    Scanner sc;

    public UI(Scanner sc) {
        this.sc = sc;
    }

    //print welcome message
    public void showWelcome() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
    }

    //read user input
    public String readCommand() {

        String result = sc.nextLine();
        return result;
    }

    //show error message
    public void showError(String msg) {
        System.out.println(msg);
    }

    //print the divider line
    public void showLine() {
        System.out.println("-------------------------------------------------------------------");
    }
}
