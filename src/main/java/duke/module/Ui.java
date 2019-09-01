package duke.module;

import java.util.Scanner;

public class Ui {

    private static final String DUKE_LOGO = " ____        _        \n"
                                          + "|  _ \\ _   _| | _____ \n"
                                          + "| | | | | | | |/ / _ \\\n"
                                          + "| |_| | |_| |   <  __/\n"
                                          + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DUKE_HELLO = "Hello! I'm Duke!\n    What can I do for you?";
    private static final String DUKE_BYE = "Bye. Hope to see you again soon!";
    private static final String DUKE_LINE = "    _________________________________________________________________";
    private static final String DUKE_TAB4 = "    ";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printToUser(String... message) {
        System.out.println(DUKE_LINE);
        for (String line : message) {
            System.out.println(DUKE_TAB4 + line);
        }
        System.out.println(DUKE_LINE + "\n");
    }

    public void greet() {
        this.printToUser(DUKE_LOGO, DUKE_HELLO);
    }

    public void bye() {
        this.printToUser(DUKE_BYE);
    }

    public String readCommand() {
        return sc.next();
    }

    public String readDescription() {
        String description = sc.nextLine();
        try {
            return description.substring(1);
        } catch (StringIndexOutOfBoundsException e) {
            return description;
        }
    }

}
