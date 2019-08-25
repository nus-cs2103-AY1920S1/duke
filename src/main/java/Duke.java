import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Printer.printString("Hello! I'm Duke\nWhat can I do for you?");
        // greet

        ArrayList<DoableTask> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        loop: while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            switch (input) {
            case "list":
                StringBuilder formattedList = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (i > 0) {
                        formattedList.append("\n");
                    }
                    formattedList.append(i + 1);
                    formattedList.append(".");
                    formattedList.append(list.get(i).toString());
                }
                // format list
                Printer.printString(formattedList.toString());
                // print list
                break;
            case "bye":
                Printer.printString("Bye. Hope to see you again soon!");
                break loop;
                // bye
            default:
                if (input.matches("^done [0-9]+$")) {
                    int index = Integer.parseInt(input.split("done ", 2)[1]) - 1;
                    // get index
                    if (index < 0 || index > list.size()) {
                        Printer.printString("That is not a valid task index");
                        // index out of bounds
                    } else {
                        list.get(index).markAsDone();
                        Printer.printString(
                                "Nice! I've marked this task as done:\n  " + list.get(index)
                                        .toString());
                        // mark done
                    }
                } else {
                    boolean erroneousInput = false;
                    DoableTask t = null;
                    String contentString;
                    String[] contentSegments;
                    if (input.matches("^todo .+")) {
                        t = new Todo(input.split("todo ", 2)[1]);
                    } else if (input.matches("^deadline .+ /by .+")) {
                        contentString = input.split("deadline ",2)[1];
                        contentSegments = contentString.split("/by ");
                        t = new Deadline(contentSegments[0], contentSegments[1]);
                    } else if (input.matches("^event .+ /at .+")) {
                        contentString = input.split("event ",2)[1];
                        contentSegments = contentString.split("/at ");
                        t = new Event(contentSegments[0], contentSegments[1]);
                    } else {
                        Printer.printString("That is not a valid input statement");
                        erroneousInput = true;
                    }
                    if (!erroneousInput) {
                        list.add(t);
                        Printer.addTaskMessage(t, list.size());
                    }
                    // add to list
                }
            }
        }
    }
}

class Task {
    private String name;

    public Task(String taskName) {
        name = taskName;
    }

    public String toString() {
        return name;
    }
}

class DoableTask extends Task {
    private boolean isDone;

    public DoableTask(String taskName) {
        super(taskName);
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + super.toString();
    }
}

class Todo extends DoableTask {
    public Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends DoableTask {
    private String deadline;

    public Deadline(String taskName, String due) {
        super(taskName);
        deadline = due;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}

class Event extends DoableTask {
    private String dateRange;

    public Event(String taskName, String range) {
        super(taskName);
        dateRange = range;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateRange + ")";
    }
}

class Printer {

    /** The spacing after padding for each line of text. */
    private static final int MARGIN_LENGTH = 1;
    /** The spacing before the horizontal line begin. */
    private static final int PADDING_LENGTH = 4;
    /** The length the right most tip of the horizontal line will span. */
    private static final int HORIZONTAL_LINE_LENGTH = 64;

    /** Text to be inserted before every line of text. */
    private static final String MARGIN_AND_PADDING = repeatChar(MARGIN_LENGTH + PADDING_LENGTH, ' ');
    /** Text to be inserted before the start of the first line and after the last line. */
    private static final String HORIZONTAL_LINE = repeatChar(PADDING_LENGTH, ' ')
            + repeatChar(HORIZONTAL_LINE_LENGTH - PADDING_LENGTH,'_')
            + "\n";

    /**
     * Displays duke logo.
     */
    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints an added message for the last task in the list.
     * @precondition must have at least one element in the list.
     * @param tasks ArrayList of tasks.
     */
    public static void addTaskMessage(Task task, int length) {
        printString("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + length
                + "tasks in the list.");
    }

    /**
     * Prints a string with formatting; border, margin and padding.
     * @param str   string to be printed.
     */
    public static void printString(String str) {
        System.out.print(formatString(str));
    }

    /**
     * Builds a string with borders and margin and padding for each line.
     * @param str   string to be formatted
     * @return      resulting formatted string
     */
    public static String formatString(String str) {
        StringBuilder formattedString = new StringBuilder();
        formattedString.append(HORIZONTAL_LINE);
        for (String s : str.split("\n")) {
            formattedString.append(MARGIN_AND_PADDING);
            formattedString.append(s);
            formattedString.append("\n");
        }
        formattedString.append(HORIZONTAL_LINE);
        formattedString.append("\n");
        return formattedString.toString();
    }

    /**
     * Generates a string of repeated characters.
     * @param length    number of repetitions
     * @param c         character to be repeated
     * @return          resulting repeated character string
     */
    private static String repeatChar(int length, char c) {
        StringBuilder s = new StringBuilder();
        s.append(String.valueOf(c).repeat(Math.max(0, length)));
        return s.toString();
    }
}