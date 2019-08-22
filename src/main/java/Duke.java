import java.util.Scanner;
import java.util.ArrayList;

import java.lang.NumberFormatException;

public class Duke {
    protected ArrayList<Task> tasks;
    protected Scanner input;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.greet();
        duke.readCommand();
    }

    public Duke() {
        tasks = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public void greet() {
        printHorizontal();
        printWithIndentation("Hello! I'm Duke");
        printWithIndentation("What can I do for you?");
        printHorizontal();
    }

    public void readCommand() {
        String command = input.nextLine();
        String[] commandTokens = command.split(" ");
        if (command.equals("bye")) {
            exit();
        } else if (command.equals("list")) {
            list();
            readCommand();
        } else if (commandTokens[0].equals("done")) {
            markAsDone(commandTokens);
            readCommand();
        } else {
            add(command);
            readCommand();
        }
    }

    public void markAsDone(String[] commandTokens) {
        try {
            int itemNo = Integer.parseInt(commandTokens[1]);
            Task task = tasks.get(itemNo - 1);
            task.setIsDone(true);
            printHorizontal();
            printWithIndentation("Nice! I've marked this task as done:");
            printWithIndentation("  " + task);
            printHorizontal();
        } catch (NumberFormatException e) {
            printHorizontal();
            printWithIndentation(e.toString());
            printHorizontal();
        }
    }

    public void list() {
        printHorizontal();
        int i = 1;
        String output = "";
        for (Task task : tasks) {
            printWithIndentation(i + ". " + task);
            i++;
        }
        printHorizontal();
    }

    public void add(String taskDescription) {
        tasks.add(new Task(taskDescription));
        printHorizontal();
        printWithIndentation("added: " + taskDescription);
        printHorizontal();
    }

    public void exit() {
        printHorizontal();
        printWithIndentation("Bye. Hope to see you again soon!");
        printHorizontal();
    }

    public void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }

    public void printWithIndentation(String output) {
        System.out.println("     " + output);
    }
}
