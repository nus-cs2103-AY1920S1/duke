package duke.module;

import java.util.Scanner;

public class Ui {

    private static final String DUKE_HELLO = "Hello! I'm Duke!\n    What can I do for you?";
    private static final String DUKE_BYE = "Bye. Hope to see you again soon!";
    private static final String DUKE_START_COMMAND = "What can I do for you?\n";
    private static final String DUKE_LIST_TASKS = "Here are the tasks in your list:";
    private static final String DUKE_NO_TASKS = "You currently have no tasks in your list.";
    private static final String DUKE_MARK_AS_DONE = "Nice! I've marked this task as done:\n";
    private static final String DUKE_ADD_TASK = "Got it. I've added this task:\n";
    private static final String DUKE_DELETE_TASK = "Noted. I've removed this task:\n";
    private static final String DUKE_DELETE_ALL_TASKS = "Noted. I've removed all tasks.";
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";
    private static final String DUKE_LINE = "    _________________________________________________________________";
    private static final String DUKE_TAB4 = "    ";
    private static final String DUKE_TAB2 = "  ";

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
        this.printToUser(DUKE_HELLO);
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
