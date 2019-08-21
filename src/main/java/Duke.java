import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Duke Class.
 */
public class Duke {
    /** TaskList to store user input. */
    private static TaskList taskList = new TaskList();
    /** Line for responses. */
//    private static final String line = "    ____________________________________________________________";
    private static final String line = String.format("%4s", " ") + String.format("%60s", " ").replace(" ", "_");
    /** Indentation for response. */
    private static final String indent = String.format("%5s", " ");

    /**
     * Main Method.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            run(br);
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Prints out greetings, then responds to the users input, then exits when user responds "bye".
     * @param br BufferedReader to read user commands.
     */
    private static void run(BufferedReader br) throws IOException {
        greeting();
        String input = br.readLine();
        while (input != null && !input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else {
                String[] tokens = input.split("\\s+");
                if (tokens[0].equals("done")) {
                    try {
                        doTask(Integer.parseInt(tokens[1]));
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        addTask(input);
                    }
                } else if (tokens[0].equals("delete")) {
                    try {
                        deleteTask(Integer.parseInt(tokens[1]));
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        addTask(input);
                    }
                } else {
                    addTask(input);
                }
            }
            input = br.readLine();
        }
        exit();
    }

    /**
     * Adds Task to the TaskList from the given text and prints response.
     * @param text String text to be added.
     */
    private static void addTask(String description) {
        String message = null;
        try {
            Task task = TaskFactory.create(description);
            taskList.addTask(task);
            message = task.message();
        } catch (ToDoTaskException e) {
            message = "     OOPS!!! The description of a todo cannot be empty.";
        } catch (DeadlineTaskException e) {
            message = "     OOPS!!! The description of a deadline cannot be empty.";
        } catch (EventTaskException e) {
            message = "     OOPS!!! The description of a event cannot be empty.";
        } catch (TaskException e) {
            message = "     OOPS!!! I'm sorry, but I don't know what that means :-(";
        } finally {
            String response = String.format("%s\n%s\n%s\n", line, message, line);
            System.out.println(response);
        }
    }

    /**
     * Does the task at the given 1-based index. Does the item and prints out the message.
     * @param index 1-based index of task to do.
     */
    private static void doTask(int index) {
        taskList.doTask(index);
        String message = indent + "Nice! I've marked this task as done:\n" + indent + "  " + taskList.getTask(index);
        String response = String.format("%s\n%s\n%s\n", line, message, line);
        System.out.println(response);
    }

    /**
     * Deletes the task from the tasklist at the given 1-based index.
     * @param index 1-based index of the task to delete.
     */
    private static void deleteTask(int index) {
        Task task = taskList.deleteTask(index);
        String message;
        if (task == null) {
            message = indent + "Index out of range, no task found.";
        } else {
            StringBuilder strb = new StringBuilder();
            message = strb
                    .append(indent + "Noted. I've removed this task:\n")
                    .append(indent + "  " + task + "\n")
                    .append(indent + "Now you have " + taskList.getTotalTasks() + " tasks in the list.")
                    .toString();
        }
        String response = String.format("%s\n%s\n%s\n", line, message, line);
        System.out.println(response);
    }

    /**
     * Prints out the task list.
     */
    private static void printList() {
        String response = String.format("%s\n%s\n%s\n", line, taskList, line);
        System.out.println(response);
    }

    /**
     * Prints out the greeting.
     */
    private static void greeting() {
        String message = indent + "Hello! I'm Duke\n" + indent + "What can I do for you?";
        String response = String.format("%s\n%s\n%s\n", line, message, line);
        System.out.println(response);
    }

    /**
     * Exit string response by printing the standard bye response.
     */
    private static void exit() {
        String message = indent + "Bye. Hope to see you again soon!";
        String response = String.format("%s\n%s\n%s\n", line, message, line);
        System.out.println(response);
    }
}
