package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String WELCOME_MSG = "Hello! I'm Duke\n" +
            "What can I do for you?";
    static final String EXIT_MSG = "Bye. Hope to see you again soon!";

    static final Scanner SCANNER = new Scanner(System.in);

    public static void printWelcomeMsg() {
        System.out.println("\nHello from\n" + LOGO);
        CmdUx.printHBars(WELCOME_MSG);
    }

    public static void printExitMsg() {
        CmdUx.printHBars(EXIT_MSG);
    }

    public static String readInput() {
        return SCANNER.nextLine();
    }

    public static void listTasks(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("Here are the tasks in your list:\n");
        for (Task task : taskList) {
            sb.append(i++ + "." + task.toString() + "\n");
        }
        CmdUx.printHBars(sb.toString());
    }


    public static void printAddSuccess(ArrayList<Task> tasks, Task task) {
        CmdUx.printHBars("Got it. I've added this task: \n" +
                "  " + task.toString() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void printDoneSuccess(Task chosenTask) {
        CmdUx.printHBars("Nice! I've marked this task as done: \n" +
                " " + Checkbox.TICK.icon + " " + chosenTask.getTaskName());
    }

    public static void printDeleteSuccess(ArrayList<Task> tasks, Task deletedTask) {
        CmdUx.printHBars("Noted. I've removed this task: \n" +
                "  " + deletedTask.toString() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void exposeError(String errorMessage) {
        CmdUx.printHBars(errorMessage);
    }

}
