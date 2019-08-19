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
    private static String line = "    ____________________________________________________________";
    /** Indentation for response. */
    private static String indent = "     ";

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
            String[] tokens = input.split(" ");
            switch (tokens[0]) {
                case "list":
                    printList();
                    break;
                case "done":
                    try {
                        doTask(Integer.parseInt(tokens[1]));
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        addTask(input);
                    }
                    break;
                default:
                    addTask(input);
            }
            input = br.readLine();
        }
        exit();
    }

    /**
     * Adds Task to the TaskList from the given text and prints response.
     * @param text String text to be added.
     */
    private static void addTask(String text) {
        taskList.addTask(text);
        String response = String.format("%s\n     added: %s\n%s\n", line, text, line);
        System.out.println(response);
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
