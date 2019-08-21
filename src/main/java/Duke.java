import java.util.Scanner;
import java.util.StringJoiner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        ArrayList<Task> taskList = new ArrayList<>();

        greet();
        while (run && sc.hasNext()) {
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");

            try {
                checkCommand(commandArr);
                switch (commandArr[0]) {
                    case "list":
                        printArray(taskList);
                        break;

                    case "done":
                        Task currTask = taskList.get(Integer.parseInt(commandArr[1]) - 1);
                        currTask.markAsDone();
                        taskComplete(currTask);
                        break;

                    case "todo":
                        Task todo = new ToDo(getDescription(commandArr));
                        taskList.add(todo);
                        printTask(todo, taskList.size());
                        break;

                    case "event":
                        String eventTime = getTime(commandArr);
                        Task event = new Event(getDescription(commandArr), eventTime);
                        taskList.add(event);
                        printTask(event, taskList.size());
                        break;

                    case "deadline":
                        String deadlineTime = getTime(commandArr);
                        Task deadline = new Deadline(getDescription(commandArr), deadlineTime);
                        taskList.add(deadline);
                        printTask(deadline, taskList.size());
                        break;

                    case "bye":
                        exit();
                        run = false;
                        break;

                    default:
                        Task task = new Task(command);
                        taskList.add(task);
                        echo("added: " + command);
                        break;
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } catch (IndexOutOfBoundsException ex) {
                indexErrorMessage(commandArr[1], taskList.size());
            } catch (NumberFormatException ex) {
                numberErrorMessage();
            }
        }

        sc.close();
    }

    private static void numberErrorMessage() {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Please type in a valid index from 1 to 100\n" +
                "    ____________________________________________________________\n");
    }

    private static void indexErrorMessage(String index, int len) {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Index " + index + " out of bounds for task list of length " + len + "\n" +
                "    ____________________________________________________________\n");
    }

    private static void checkCommand(String[] commandArr) throws DukeException {
        if (!commandArr[0].matches("todo|deadline|event|done|list|bye")) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________\n");
        }

        if (commandArr[0].matches("list|bye")) {
            if (commandArr.length > 1) {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________\n");
            }
        }
    }

    private static String getDescription(String[] commandArr) throws DukeException {
        StringJoiner description = new StringJoiner(" ");
        int index;
        if (commandArr[0].equals("todo")) {
            index = commandArr.length;
        } else if (commandArr[0].equals("deadline")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = 1; i < index; i++) {
            description.add(commandArr[i]);
        }

        if (description.toString().equals("")) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a " + commandArr[0] + " cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }

        return description.toString();
    }

    private static String getTime(String[] commandArr) throws DukeException {
        StringJoiner timing = new StringJoiner(" ");
        int index;
        if (commandArr[0].equals("deadline")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = index + 1; i < commandArr.length; i++) {
            timing.add(commandArr[i]);
        }

        if (index == -1) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }

        return timing.toString();
    }

    private static void printTask(Task task, int size) {
        printLine();
        System.out.println("     Got it. I've added this task: \n       " + task);
        System.out.printf("     Now you have %d tasks in the list.\n", size);
        printLine();
        System.out.println();
    }

    private static void taskComplete(Task currTask) {
        printLine();
        System.out.println("     Nice! I've marked this task as done: \n       " + currTask);
        printLine();
        System.out.println();
    }

    private static void printArray(ArrayList<Task> taskList) {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i));
        }
        printLine();
        System.out.println();
    }

    //Greet the user when starting up Duke
    private static void greet() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        printLine();
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        printLine();
        System.out.println();
    }

    // Echo commands entered by users
    private static void echo(String command) {
        printLine();
        System.out.println("     " + command);
        printLine();
        System.out.println();
    }

    //Exits when the user types bye
    private static void exit() {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
        System.out.println();
    }

    // Print indented line
    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
