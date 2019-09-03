import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Scanner object to read user input.
     */
    Scanner sc;

    /**
     * Class constructor that initialises the scanner object in the ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints greeting message to user on application launch.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showLine();
        String greetMsg = "     Hello! I'm Duke\n     What can I do for you?";
        System.out.println(logo + '\n' + greetMsg);
        showLine();
        System.out.println();
    }

    /**
     * Reads user input.
     * @return
     */
    public String readCommand() {
        String userInput = sc.nextLine();
        return userInput;
    }

    /**
     * Prints exit message and exists the application.
     */
    public static void handleBye() {
        System.out.println("     Bye. Hope to see you again soon!");
        showLine();
        System.exit(0);
    }

    /**
     * Prints out current lists of tasks.
     * @param input User input
     * @param tasks List of tasks
     * @throws DukeException When list command is not input correctly
     */
    public static void handleList(String input, TaskList tasks) throws DukeException {
        String[] inputStringArr = input.split(" ");
        if (inputStringArr.length > 1) {
            throw new DukeException("     ☹ OOPS!!! The list command should not be followed by other " +
                    "text!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                int listNum = i + 1;
                Task t = tasks.get(i);
                if (t.getType().equals("todo")) {
                    System.out.println("    " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t.toString());
                } else if (t.getType().equals("event")) {
                    System.out.println("    " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t.toString() + " (at: " + t.getDate() + ")");
                } else {
                    System.out.println("    " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t.toString() + " (by: " + t.getDate() + ")");
                }
            }
        }
    }

    /**
     * Print message that a task has been marked as completed.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     * @throws DukeException
     */
    public static void handleDone(String input, TaskList tasks, Storage storage) throws DukeException {
        String[] inputStringArr = input.split(" ");
        if (inputStringArr.length > 1) {
            int taskNum = Integer.parseInt(inputStringArr[1]);
            int totalTasks = tasks.size();
            if (taskNum < 1 || taskNum > totalTasks) {
                throw new DukeException("     ☹ OOPS!!! The number provided is not within the range of the " +
                        "list.");
            } else {
                Task t = tasks.get(taskNum - 1);
                boolean isDone = t.getStatus();
                if (isDone) {
                    throw new DukeException("     ☹ OOPS!!! The task has already been marked as completed.");
                } else {
                    t.setDone();
                    System.out.println("     Nice! I've marked this task as done: \n" +
                            "       [" + '+' + "] " + t);
                    storage.rewriteFile(tasks);
                }
            }
        } else {
            throw new DukeException("     ☹ OOPS!!! Please specify the completed task's number.");
        }
    }

    /**
     * When user adds a Todo class task.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     * @throws DukeException When task description given by user is empty
     */
    public static void handleTodo(String input, TaskList tasks, Storage storage) throws DukeException {
        String[] inputStringArr = input.split(" ");
        if (inputStringArr.length > 1) {
            String taskName = ((input.split(" ", 2))[1]).strip();
            Todo t = new Todo(taskName);
            tasks.add(t);
            System.out.println("     Got it. I've added this task: \n" +
                    "       [T]" + "[ ]" + ' ' + t+ '\n' +
                    "     Now you have " + tasks.size() + " tasks in the list.");
            storage.appendToFile("T | 0 | " + taskName + '\n');
        } else {
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * When user adds a Deadline class task.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     */
    public static void handleDeadline(String input, TaskList tasks, Storage storage) {
        String[] separateTaskDate = input.split("/", 2);
        String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
        String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
        DateTime dt = new DateTime(date);
        Deadline d = new Deadline(taskName, dt.toString());
        tasks.add(d);
        System.out.println("     Got it. I've added this task: \n" +
                "       [D][ ] " + d + " (by: " + dt + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.");
        storage.appendToFile("D | 0 | " + taskName + " | " + dt.toString() + '\n');
    }

    /**
     * When user adds an Event class task.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     */
    public static void handleEvent(String input, TaskList tasks, Storage storage) {
        String[] separateTaskDate = input.split("/", 2);
        String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
        String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
        DateTime dt = new DateTime(date);
        Event e = new Event(taskName, dt.toString());
        tasks.add(e);
        System.out.println("     Got it. I've added this task: \n" +
                "       [E][ ] " + e + " (at: " + dt + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.");
        storage.appendToFile("E | 0 | " + taskName + " | " + date + '\n');
    }

    /**
     * When user deletes a task from the task list.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     */
    public static void handleDelete(String input, TaskList tasks, Storage storage) {
        String[] inputStringArr = input.split(" ");
        int taskNum = Integer.parseInt(inputStringArr[1]);
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        System.out.println("     Noted. I've removed this task: ");
        if (t.getType().equals("todo")) {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t);
        } else if (t.getType().equals("event")) {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (at: "
                    + t.getDate() + ")");
        } else {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (by: "
                    + t.getDate() + ")");
        }
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        storage.rewriteFile(tasks);
    }

    /**
     * When user finds a task using a keyword.
     * @param input Input from user
     * @param tasks List of tasks
     */
    public static void handleFind(String input, TaskList tasks) {
        String[] inputStringArr = input.split(" ", 2);
        String searchTerm = inputStringArr[1];
        System.out.println("     Here are the matching tasks in your list:");
        int listNum = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String taskName = t.toString();
            if (taskName.contains(searchTerm)) {
                if (t.getType().equals("todo")) {
                    System.out.println("     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t);
                } else if (t.getType().equals("event")) {
                    System.out.println("     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t + " " + "(at: " + t.getDate() + ")");
                } else {
                    System.out.println("     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t + " (by: " + t.getDate() + ")");
                }
                listNum++;
            }
        }
    }

    /**
     * This method prints the divider for the application for decorative and clarity purposes.
     */
    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method prints the appropriate error message based on the erroneous user input.
     * @param errorMsg Error message to be printed
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }
}
