package ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        showLine();
        print("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    public void showError(String message) {
        print(message);
    }

    public void showLoadingError() {
        print("This is your first time using Duke, no history loaded.");
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void print(String str) {
        String[] lines = str.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append("\n     " + line);
        }
        System.out.println(sb.toString());
    }

}
