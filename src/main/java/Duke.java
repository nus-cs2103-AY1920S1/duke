import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    private static final String IDENTATION_LVL1 = "     "; // 5 spaces, for first level indentation.
    private static final String IDENTATION_LVL2 = "  "; // 2 spaces, for second level indentation (i.e. more inner).
    private List<Task> taskList;

    // 79 characters, excluding \n. Line is of length 75 characters.
    private static final String LINE = "    ___________________________________________________________________________\n";
    private static final int PRINT_THRESHOLD = 73; // length that a string to be printed should not exceed.

    public Duke() {
        taskList = new LinkedList<>();
    }

    public static void main(String[] args) {
        new Duke().start();
    }

    public void start() {
        printWelcomeMessage();
        receiveCommand();
    }

    private void printWelcomeMessage() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        System.out.print(LINE + logo + "\n");
        System.out.print(IDENTATION_LVL1 + "Hello! I'm Duke\n" + IDENTATION_LVL1 + "What can I do for you?\n");
        System.out.print(LINE + "\n");
    }

    private void receiveCommand() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine().trim();

            try {
                if (command.startsWith("todo")) {
                    addToDo(command);
                } else if (command.startsWith("deadline")) {
                    addDeadline(command);
                } else if (command.startsWith("event")) {
                    addEvent(command);
                } else if ("list".equals(command)) {
                    list();
                } else if (command.startsWith("done")) {
                    markTaskAsDone(command);
                } else if (command.startsWith("delete")) {
                    deleteTask(command);
                } else if ("bye".equals(command)) {
                    echo("Bye. Hope to see you again!");
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                echo(e.getMessage());
            }
        }
    }

    private void addToDo(String command) throws DukeException {
        String topic = command.substring(4).trim();

        if ("".equals(topic)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        addTask(new ToDo(topic));
    }

    private void addDeadline(String command) throws DukeException {
        String[] details = command.substring(8).trim().split("/by");

        if (details.length == 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }

        String topic = details[0].stripTrailing();
        String deadline = details[1].stripLeading();
        addTask(new Deadline(topic, deadline));
    }

    private void addEvent(String command) throws DukeException {
        String[] details = command.substring(5).trim().split("/at");

        if (details.length == 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and date of an event cannot be empty.");
        }

        String topic = details[0].stripTrailing();
        String date = details[1].stripLeading();
        addTask(new Event(topic, date));
    }

    private void addTask(Task task) {
        taskList.add(task);
        echo("Got it. I've added this task:",
                String.format("%s%s", IDENTATION_LVL2, task.toString()),
                String.format("Now you have %s in the list.", getTaskWord()));
    }

    private void deleteTask(String command) throws DukeException {
        try {
            int index = Integer.parseInt(command.substring(6).trim());

            if (!indexIsValid(index)) {
                throw new DukeException("Index must be between 1 and the number of task added!");
            }

            Task task = taskList.remove(index - 1);

            echo("Noted. I've removed this task:",
                    String.format("%s%s", IDENTATION_LVL2, task.toString()),
                    String.format("Now you have %s in the list.", getTaskWord()));
        } catch (NumberFormatException e) {
            echo(String.format("%s There can only be an integer after the word \"delete\"", DukeException.PREFIX ));
        }
    }

    private void markTaskAsDone(String command) throws DukeException {
        try {
            int index = Integer.parseInt(command.substring(4).trim());

            if (!indexIsValid(index)) {
                throw new DukeException("Index must be between 1 and the number of task added!");
            }

            Task task = taskList.get(index - 1);
            task.markAsDone();
            echo("Nice! I've marked this task as done:",
                    String.format("%s%s", IDENTATION_LVL2, task.toString()));
        } catch (NumberFormatException e) {
            echo(String.format("%s There can only be an integer after the word \"done\"", DukeException.PREFIX ));
        }
    }

    private void list() {
        System.out.print(LINE);
        System.out.print(IDENTATION_LVL1 + "Here are the tasks in your list:\n");
        ListIterator<Task> iterator = taskList.listIterator();

        for (int i = 0; i < taskList.size(); i++) {
            String taskDetails = iterator.next().toString();
            if (taskDetails.length() <= PRINT_THRESHOLD - 3) {
                System.out.printf("%s%d.%s\n", IDENTATION_LVL1, i + 1, taskDetails);
            } else {
                System.out.printf(splitIntoLines(String.format("%d.%s\n", i + 1, taskDetails)));
            }
        }

        System.out.print(LINE);
        System.out.print('\n');
    }

    /**
     * print the strings provided line by line, enclosed within two lines.
     * @param strings Strings to be printed.
     */
    public void echo(String... strings) {
        System.out.print(LINE);

        for (String string : strings) {
            if (string.length() <= PRINT_THRESHOLD) {
                System.out.printf("%s%s\n", IDENTATION_LVL1, string);
            } else {
                System.out.print(splitIntoLines(string));
            }
        }

        System.out.print(LINE);
        System.out.print("\n");
    }

    private String splitIntoLines(String string) {
        // To deal with the case where the length of string to be printed is greater than the print threshold.
        StringBuilder builder = new StringBuilder(string.length() + 30);
        String string_to_be_treated = string;

        // Should return a list of strings without adding any identation?
        while (true) {
            builder.append(IDENTATION_LVL1);
            builder.append(string_to_be_treated.substring(0, PRINT_THRESHOLD));
            builder.append("\n");

            string_to_be_treated = string_to_be_treated.substring(PRINT_THRESHOLD);
            if (string_to_be_treated.length() <= PRINT_THRESHOLD) {
                builder.append(IDENTATION_LVL1);
                builder.append(string_to_be_treated);
                builder.append("\n");
                break;
            }
        }

        return builder.toString();
    }

    private boolean indexIsValid(int index) {
        return index > 0 && index <= taskList.size();
    }

    private String getTaskWord() {
        int size = taskList.size();
        return size > 1 ? size + " tasks" : size + " task";
    }
}
