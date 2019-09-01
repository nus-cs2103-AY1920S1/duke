package duke.ui;

import java.util.Scanner;

public class Ui {
    final public static String line = "    ____________________________________________________________\n";
    final public static String indent = "    ";
    final public static String logo = line
            + "     ____        _           \n"
            + "    |  _ \\ _   _| | _____   \n"
            + "    | | | | | | | |/ / _ \\  \n"
            + "    | |_| | |_| |   <  __/   \n"
            + "    |____/ \\__,_|_|\\_\\___|\n"
            + "     Hello! I'm Duke          \n"
            + "     What can I do for you?   \n"
            + line;

    public Ui() {
    }

    public void printLogo() {
        System.out.println(logo);
    }

    public String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.print(line);
        System.out.println(indent + "!!! FAILED TO LOAD LIST !!!");
        System.out.println(line);
    }
}
