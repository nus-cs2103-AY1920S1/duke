import exception.DukeIllegalStateException;
import exception.DukeIndexOutOfBoundsException;
import exception.DukeMissingDescriptionException;
import exception.DukeUnknownInputException;
import memory.Memory;
import task.Task;
import task.TaskList;
import task.TaskFactory;
import static task.TaskType.TODO;
import static task.TaskType.DEADLINE;
import static task.TaskType.EVENT;

import java.util.Arrays;
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
            "    Hello! I'm Duke. What can I do for you?\n";
    private static final String EXIT_MESSAGE =
            "    Bye. Hope to see you again soon!\n";

    private TaskList taskList;
    private TaskFactory factory;
    private PrettyPrinter pp;
    private Memory memory;

    public Duke() {
        this.memory = new Memory("data/duke.txt");
        this.taskList = memory.readFromDisk(); // leave index 0 empty for clarity
        this.pp = new PrettyPrinter();
    }

    public void run() {
        System.out.println(/* LOGO + */TOP_SEPARATOR + GREET_MESSAGE + BOTTOM_SEPARATOR);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] commands = sc.nextLine().split(" ");
            String[] args = commands.length >= 2
                                ? Arrays.copyOfRange(commands, 1, commands.length)
                                : new String[0];
            try {
                switch (commands[0]) {
                case "event":
                    Task eventTask = taskList.add(TaskFactory.getTask(EVENT, args));
                    System.out.println(pp.formatAddTask(eventTask, taskList));
                    memory.writeToDisk(taskList);
                    break;
                case "deadline":
                    Task deadlineTask = taskList.add(TaskFactory.getTask(DEADLINE, args));
                    System.out.println(pp.formatAddTask(deadlineTask, taskList));
                    memory.writeToDisk(taskList);
                    break;
                case "todo":
                    if (args.length == 0) {
                        throw new DukeMissingDescriptionException(
                                ":'( OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task todoTask = taskList.add(TaskFactory.getTask(TODO, args));
                    System.out.println(pp.formatAddTask(todoTask, taskList));
                    memory.writeToDisk(taskList);
                    break;
                case "done":
                    int doneIdx = Integer.valueOf(commands[1]);
                    Task task = taskList.markAsDone(doneIdx);
                    System.out.println(pp.formatDoneTask(task));
                    memory.writeToDisk(taskList);
                    break;
                case "delete":
                    int deleteIdx = Integer.valueOf(commands[1]);
                    Task deletedTask = taskList.delete(deleteIdx);
                    System.out.println(pp.formatDeleteTask(deletedTask, taskList));
                    memory.writeToDisk(taskList);
                    break;
                case "list":
                    System.out.println(pp.formatTaskList(taskList));
                    break;
                case "bye":
                    System.out.println(pp.addSeparators(EXIT_MESSAGE));
                    return;
                default:
                    throw new DukeMissingDescriptionException(
                            ":'( OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeUnknownInputException
                    | DukeMissingDescriptionException
                    | DukeIndexOutOfBoundsException
                    | DukeIllegalStateException e) {
                System.err.println("EXCEPTION!");
                System.out.println(pp.addSeparatorsAddIndent(e.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
