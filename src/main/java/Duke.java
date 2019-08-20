package main.java;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String TOP_SEPARATOR =
            "\t____________________________________________________________\n";
    private static final String BOTTOM_SEPARATOR =
            "\t____________________________________________________________";
    private static final String GREET_MESSAGE =
            "\tHello! I'm Duke. What can I do for you?\n";
    private static final String ADD_MESSAGE =
            "\tGot it. I've added this task:\n";
    private static final String DONE_MESSAGE =
            "\tNice! I've marked this task as done:\n";
    private static final String ERROR_MESSAGE =
            "\tI don't understand your command...\n";
    private static final String EXIT_MESSAGE =
            "\tBye. Hope to see you again soon!\n";

    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList(); // leave index 0 empty for clarity
    }

    public void run() {
        System.out.println(/* LOGO + */TOP_SEPARATOR + GREET_MESSAGE + BOTTOM_SEPARATOR);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] commands = sc.nextLine().split(" ");
            String[] args = Arrays.copyOfRange(commands, 1, commands.length);

            switch (commands[0]) {
                case "event":
                    String[] eventArgs = String.join(" ", args).split("/at");
                    Task eventTask = taskList.add(new Event(eventArgs[0].strip(), eventArgs[1].strip()));
                    System.out.println(TOP_SEPARATOR + messageAddTask(eventTask) + BOTTOM_SEPARATOR);
                    break;
                case "deadline":
                    String[] todoArgs = String.join(" ", args).split("/by");
                    Task deadlineTask = taskList.add(new Deadline(todoArgs[0].strip(), todoArgs[1].strip()));
                    System.out.println(TOP_SEPARATOR + messageAddTask(deadlineTask) + BOTTOM_SEPARATOR);
                    break;
                case "todo":
                    String task = String.join(" ", args);
                    Task todoTask = taskList.add(new Todo(task));
                    System.out.println(TOP_SEPARATOR + messageAddTask(todoTask) + BOTTOM_SEPARATOR);
                    break;
                case "done":
                    int doneIdx = Integer.valueOf(commands[1]);
                    taskList.markAsDone(doneIdx);
                    System.out.println(TOP_SEPARATOR + DONE_MESSAGE + "\t" + taskList.get(doneIdx) + "\n" + BOTTOM_SEPARATOR);
                    break;
                case "list":
                    System.out.println(TOP_SEPARATOR + taskList.format() + BOTTOM_SEPARATOR);
                    break;
                case "bye":
                    System.out.println(TOP_SEPARATOR + EXIT_MESSAGE + BOTTOM_SEPARATOR);
                    return;
                default:
                    System.out.println(TOP_SEPARATOR + ERROR_MESSAGE + BOTTOM_SEPARATOR);
                    break;
            }
        }
    }

    // prints the message added and the number of tasks currently in the list.
    public String messageAddTask(Task task) {
        long taskCount = taskList.count();
        return ADD_MESSAGE + "\t" + task.toString() + "\n"
                + (taskCount <= 1
                    ? String.format("\tNow you have %d task in the list.\n", taskCount)
                    : String.format("\tNow you have %d tasks in the list.\n", taskCount));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
