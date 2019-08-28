import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetMsg = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(logo + '\n' + greetMsg);
    }

    public String getUserInput() {
        String userInput = sc.nextLine();
        return userInput;
    }

    public static void handleBye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
        System.exit(0);
    }

    public static void handleList(String input, TaskList tasks) throws DukeException {
        String[] inputStringArr = input.split(" ");
        try {
            if (inputStringArr.length > 1) {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! The list command should not be followed by other text!\n" +
                        "    ____________________________________________________________\n");
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:");
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
                System.out.println("    ____________________________________________________________\n");
            }
        } catch(DukeException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void handleBadCommand() throws DukeException {
        try {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________\n");
        } catch(DukeException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void handleDone(String input, TaskList tasks, Storage storage) throws DukeException {
        String[] inputStringArr = input.split(" ");
        try {
            if (inputStringArr.length > 1) {
                int taskNum = Integer.parseInt(inputStringArr[1]);
                int totalTasks = tasks.size();
                if (taskNum < 1 || taskNum > totalTasks) {
                    throw new DukeException("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! The number provided is not within the range of the list.\n" +
                            "    ____________________________________________________________\n");
                } else {
                    Task t = tasks.get(taskNum - 1);
                    boolean isDone = t.getStatus();
                    if (isDone) {
                        throw new DukeException("    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! The task has already been marked as completed.\n" +
                                "    ____________________________________________________________\n");
                    } else {
                        t.setDone();
                        System.out.println("    ____________________________________________________________\n" +
                                "     Nice! I've marked this task as done: \n" +
                                "       [" + '+' + "] " + t + '\n' +
                                "    ____________________________________________________________\n");
                        storage.rewriteFile(tasks);
                    }
                }
            } else {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! Please specify the completed task's number.\n" +
                        "    ____________________________________________________________\n");
            }
        } catch(DukeException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void handleTodo(String input, TaskList tasks, Storage storage) throws DukeException {
        String[] inputStringArr = input.split(" ");
        try {
            if (inputStringArr.length > 1) {
                String taskName = ((input.split(" ", 2))[1]).strip();
                Todo t = new Todo(taskName);
                tasks.add(t);
                System.out.println("    ____________________________________________________________\n" +
                        "     Got it. I've added this task: \n" +
                        "       [T]" + "[ ]" + ' ' + t+ '\n' +
                        "     Now you have " + tasks.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________\n");
                storage.appendToFile("T | 0 | " + taskName + '\n');
            } else {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                        "    ____________________________________________________________\n");
            }
        } catch(DukeException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void handleDeadline(String input, TaskList tasks, Storage storage) {
        String[] separateTaskDate = input.split("/", 2);
        String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
        String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
        DateTime dt = new DateTime(date);
        Deadline d = new Deadline(taskName, dt.toString());
        tasks.add(d);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [D][ ] " + d + " (by: " + dt + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
        storage.appendToFile("D | 0 | " + taskName + " | " + date + '\n');
    }

    public static void handleEvent(String input, TaskList tasks, Storage storage) {
        String[] separateTaskDate = input.split("/", 2);
        String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
        String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
        DateTime dt = new DateTime(date);
        Event e = new Event(taskName, dt.toString());
        tasks.add(e);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [E][ ] " + e + " (at: " + dt + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
        storage.appendToFile("E | 0 | " + taskName + " | " + date + '\n');
    }

    public static void handleDelete(String input, TaskList tasks, Storage storage) {
        String[] inputStringArr = input.split(" ");
        int taskNum = Integer.parseInt(inputStringArr[1]);
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: ");
        if (t.getType().equals("todo")) {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t);
        } else if (t.getType().equals("event")) {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (at: "
                    + t.getDate() + ")");
        } else {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (by: "
                    + t.getDate() + ")");
        }
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
        storage.rewriteFile(tasks);
    }
}
