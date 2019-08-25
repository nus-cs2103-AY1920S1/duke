import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Encapsulates a product named Duke, a personal assistant chat bot that helps a person to keep track of various things.
 * It can add, delete, and list task entries and mark them as done.
 */
public class Duke {
    private static final String INDENTATION_LVL1 = "     "; // 5 spaces, for first level indentation.
    private static final String INDENTATION_LVL2 = "       "; // 7 spaces, for second level indentation (i.e. more inner).
    private DukeDatabase database;
    private List<Task> taskList;

    // 79 characters, excluding \n. Line is of length 75 characters.
    private static final String LINE = "    ___________________________________________________________________________\n";
    private static final int CHARACTERS_LIMIT = 73; // length that a string to be printed should not exceed.

    /**
     * Construct a Duke chat bot.
     */
    public Duke() {
        taskList = new LinkedList<>();
    }

    /**
     * Run the entire program.
     *
     * @param args user input (not required).
     */
    public static void main(String[] args) {
        new Duke().start();
    }

    /**
     * Start the Duke chat bot.
     */
    public void start() {
        initialise();
        printWelcomeMessage();
        receiveCommand();
    }

    /**
     * Initialise the essential components of Duke bot.
     */
    private void initialise() {
        database = DukeDatabase.getDukeDatabaseInstance();
        taskList = database.getAllTasks();
    }

    /**
     * Clean up the essential components of Duke bot before exiting the program.
     */
    public void quit() {
        echo("Bye. Hope to see you again!");
        database.update(taskList);
    }

    private void printWelcomeMessage() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        echo(() -> {
            System.out.print(logo + "\n");
            System.out.print(INDENTATION_LVL1 + "Hello! I'm Duke\n" + INDENTATION_LVL1 + "What can I do for you?\n");
        });
    }

    // Receive command from the user, exits the program when user enter "bye".
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
                    quit();
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                echo(e.getMessage());
            }
        }
    }

    // add to do entry to the taskList.
    private void addToDo(String command) throws DukeException {
        String topic = command.substring(4).trim();

        if ("".equals(topic)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        addTask(new ToDo(topic));
    }

    // add deadline entry to the taskList.
    private void addDeadline(String command) throws DukeException {
        String[] details = command.substring(8).trim().split("/by");

        if (details.length == 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }

        try {
            String topic = details[0].stripTrailing();
            String deadlineUserInput = details[1].stripLeading();
            String deadline = formatDateAndTime(deadlineUserInput);

            addTask(new Deadline(topic, deadline));
        } catch (ParseException e) {
            echo(DukeException.PREFIX + " The format of date and time is wrong!");
        }
    }

    // add event entry to the taskList.
    private void addEvent(String command) throws DukeException {
        String[] details = command.substring(5).trim().split("/at");

        if (details.length == 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and date of an event cannot be empty.");
        }

        try {
            String topic = details[0].stripTrailing();
            String dateUserInput = details[1].stripLeading();
            String date = formatDateAndTime(dateUserInput);

            addTask(new Event(topic, date));
        } catch (ParseException e) {
            echo(DukeException.PREFIX + " The format of date and time is wrong!");
        }
    }

    private String formatDateAndTime(String dateTime) throws ParseException {
        DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputFormatter = new SimpleDateFormat("d MMMM yyyy, h:mm a");
        Date date = inputFormatter.parse(dateTime);

        return outputFormatter.format(date);
    }

    // add Task entries(to do, deadline & event) to the taskList.
    private void addTask(Task task) {
        taskList.add(task);
        echo(() -> {
            System.out.printf("%sGot it. I've added this task:\n", INDENTATION_LVL1);
            System.out.printf(indentAndSplit(task.toString(), INDENTATION_LVL2));
            System.out.printf(String.format("%sNow you have %s in the list.\n", INDENTATION_LVL1, getTaskPhrase()));
        });
    }

    // delete a task from the taskList.
    private void deleteTask(String command) throws DukeException {
        try {
            int index = Integer.parseInt(command.substring(6).trim());

            if (!indexIsValid(index)) {
                throw new DukeException("Index must be between 1 and the number of task added!");
            }

            Task task = taskList.remove(index - 1);

            echo(() -> {
                System.out.printf("%sNoted. I've removed this task:\n", INDENTATION_LVL1);
                System.out.printf(indentAndSplit(task.toString(), INDENTATION_LVL2)); // task details
                System.out.printf("%sNow you have %s in the list.\n", INDENTATION_LVL1, getTaskPhrase());
            });
        } catch (NumberFormatException e) {
            echo(String.format("%s There can only be an integer after the word \"delete\"", DukeException.PREFIX ));
        }
    }

    // Mark a task in the task list as done.
    private void markTaskAsDone(String command) throws DukeException {
        try {
            int index = Integer.parseInt(command.substring(4).trim());

            if (!indexIsValid(index)) {
                throw new DukeException("Index must be between 1 and the number of task added!");
            }

            Task task = taskList.get(index - 1);
            task.markAsDone();

            echo(() -> {
                System.out.printf("%sNice! I've marked this task as done:\n", INDENTATION_LVL1);
                System.out.printf(indentAndSplit(task.toString(), INDENTATION_LVL2)); // task details
            });
        } catch (NumberFormatException e) {
            echo(String.format("%s There can only be an integer after the word \"done\"", DukeException.PREFIX ));
        }
    }

    // List all the tasks in the taskList.
    private void list() {
        echo(() -> {
            System.out.print(INDENTATION_LVL1 + "Here are the tasks in your list:\n");
            ListIterator<Task> iterator = taskList.listIterator();

            for (int i = 0; i < taskList.size(); i++) {
                String taskDetails = iterator.next().toString();
                System.out.printf(indentAndSplit(String.format("%d.%s", i + 1, taskDetails),
                        INDENTATION_LVL1));
            }
        });
    }

    /**
     * print the stuff specified by the printFunction, enclosed within two lines.
     *
     * @param printFunction function which print something.
     */
    public void echo(PrintFunction printFunction) {
        System.out.print(LINE);
        printFunction.print();
        System.out.print(LINE);
        System.out.print("\n");
    }

    /**
     * Print the strings provided line by line, enclosed within two long horizontal lines.
     * Each line is indented by 5 spaces.
     *
     * @param strings strings to be printed.
     */
    public void echo(String... strings) {
        echo(() -> {
            for (String string : strings) {
                System.out.print(indentAndSplit(string, INDENTATION_LVL1));
            }
        });
    }

    // Indent a string using the indentation string given and add a newline character at the back.
    // If the length of the string is more than the number of characters allowed in a line
    // (taking the indentation into account), split the string into separate lines.
    private String indentAndSplit(String string, String indentation) {
        int lengthLimit = getLengthLimit(indentation.length());

        if (string.length() <= lengthLimit) {
            return String.format("%s%s\n", indentation, string);
        } else {
            return splitIntoLines(string, indentation.length());
        }
    }

    /*
     * Split a given string into lines if it's more than the characters limit allowed in one line
     * , with indentation in front of the string in each line.
     * Indentation Number is the number of spaces in front of a string in each line.
     */
    private String splitIntoLines(String string, int indentation) {
        StringBuilder builder = new StringBuilder(string.length() + 30);
        String indentation_string = getIndentationString(indentation);

        // Remove the spaces in front of the given string first.
        String string_to_be_treated = string.trim();

        int lengthLimit = getLengthLimit(indentation);
        while (true) {
            builder.append(indentation_string);
            builder.append(string_to_be_treated.substring(0, lengthLimit));
            builder.append("\n");

            string_to_be_treated = string_to_be_treated.substring(lengthLimit);
            if (string_to_be_treated.length() <= lengthLimit) {
                builder.append(indentation_string);
                builder.append(string_to_be_treated);
                builder.append("\n");
                break;
            }
        }

        return builder.toString();
    }

    // Return an indentation String.
    private String getIndentationString(int indentation) {
        if (indentation == INDENTATION_LVL1.length()) {
            return INDENTATION_LVL1;
        } else if (indentation == INDENTATION_LVL2.length()) {
            return INDENTATION_LVL2;
        } else {
            StringBuilder identationBuilder = new StringBuilder(indentation);
            for (int i = 0; i < indentation; i++) {
                identationBuilder.append(" ");
            }
            return identationBuilder.toString();
        }
    }

    // Check if a user-given index for accessing the taskList is valid.
    private boolean indexIsValid(int index) {
        return index > 0 && index <= taskList.size();
    }

    // Return the phrase "N word" or "N words" (singular or plural).
    // N is the the number of tasks in the taskList.
    private String getTaskPhrase() {
        int size = taskList.size();
        return size > 1 ? size + " tasks" : size + " task";
    }

    // Return the number of characters allowed in one line after taking indentation into consideration.
    private int getLengthLimit(int indentation) {
        return CHARACTERS_LIMIT - (indentation - INDENTATION_LVL1.length());
    }
}
