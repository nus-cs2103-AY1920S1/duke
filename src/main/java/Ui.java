import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class Ui {
    private static final String LINE_INDENT = "\t";
    private static final String HORIZONTAL_LINE = LINE_INDENT + "_".repeat(60);
    private static final Pattern LINE_START_PATTERN = Pattern.compile("^", Pattern.MULTILINE);

    private final Scanner scanner;
    private final PrintStream output = System.out;

    Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets a line of input from the user.
     *
     * @return A line of input from the user.
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line to the user.
     */
    public void printHorizontalLine() {
        output.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an empty line to the user.
     */
    public void println() {
        output.println();
    }

    /**
     * Prints the content argument with indentation.
     *
     * @param content Text to display to the user.
     */
    public void println(String content) {
        content = LINE_START_PATTERN.matcher(content).replaceAll(LINE_INDENT);
        output.println(content);
    }

    /**
     * Prints a horizontal line, the argument and another horizontal line
     * while taking care of indentation to create a block user interface element.
     *
     * @param content Text to display to the user.
     */
    public void printBlock(String content) {
        printHorizontalLine();
        println(content);
        printHorizontalLine();
    }

    /**
     * A convenience method to create a {@code StringJoiner} using the system's newline separator.
     *
     * @return A new instance of {@code StringJoiner}.
     */
    public static StringJoiner createStringJoiner() {
        return new StringJoiner(System.lineSeparator());
    }

    /**
     * A convenience method to create a {@code StringJoiner} using the system's
     * newline separator initialized with the contents of the specified string.
     *
     * @return A new instance of {@code StringJoiner} with the contents of the specified string.
     */
    public static StringJoiner createStringJoiner(String str) {
        StringJoiner instance = createStringJoiner();
        if (str != null) {
            instance.add(str);
        }
        return instance;
    }

    /**
     * Convenience function to format a Task differently based on its subtype (i.e. Todo/Deadline/Event).
     *
     * @param t Task instance to represent as a string, can be Todo, Deadline or Event.
     * @return A textual representation of the given Task.
     */
    public static String formatTask(Task t) {
        String taskType = null;
        String description = null;
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        if (t instanceof Todo) {
            taskType = "T";
            description = t.getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            taskType = "D";
            description = String.format("%s (by: %s)", d.getDescription(), d.getDeadline().format(dateTimeFormatter));
        } else if (t instanceof Event) {
            Event e = (Event) t;
            taskType = "E";
            description = String.format("%s (at: %s)", e.getDescription(), e.getEventDateTime().format(dateTimeFormatter));
        }

        return String.format("[%s][%s] %s", taskType, t.getStatusIcon(), description);
    }

    /**
     * Convenience method to display the welcome message to the user.
     */
    public void displayWelcome() {
        printBlock("Hello! I'm Duke\n" +
                "What can I do for you?");
        println();
    }

    public void displayLoadingError(Throwable exc){
    /**
     * Display an error when Duke is unable to read the Tasks stored in a file.
     *
     * @param exc The reason for the failed read/parse operation.
     */
        StringJoiner errorMessage = createStringJoiner("Couldn't load previously saved Tasks.");
        errorMessage.add("Duke will start with an empty Task list.");
        errorMessage.add("");
        errorMessage.add("More details: " + exc.getMessage());
        if(exc.getCause() != null){
            errorMessage.add(exc.getCause().getMessage());
        }

        displayError(errorMessage.toString());
    }

    public void displayError(DukeException exc){
    /**
     * Convenience method of displaying a DukeException.
     *
     * @param exc The DukeException that will be shown to the user.
     */
        displayError(exc.getMessage());
    }

    public void displayError(String message){
    /**
     * Display the error message to the user.
     *
     * @param message The error message that will be shown to the user.
     */
        printBlock(" ☹ OOPS!!! " + message);
        println();
    }

    /**
     * Convenience method of displaying a list of tasks to the user.
     *
     * @param tasks Tasks to be displayed.
     */
    public void displayTasks(List<Task> tasks) {
        displayTasks(null, tasks);
    }

    /**
     * Display a list of tasks to the user with a title.
     *
     * @param title The title that is shown before the tasks.
     * @param tasks Tasks to be displayed.
     */
    public void displayTasks(String title, List<Task> tasks) {
        StringJoiner taskListDisplay = createStringJoiner(title);
        int listIdx = 1;
        for (Task task : tasks) {
            final String formattedTask = String.format("%d.%s", listIdx, formatTask(task));
            taskListDisplay.add(formattedTask);
            listIdx++;
        }
        printBlock(taskListDisplay.toString());
    }

    /**
     * Convenience method to inform the user that the
     * task they had previously selected has been marked done.
     *
     * @param title A friendly "marked as done" style message to show to the user.
     * @param task  The task that was marked as done.
     */
    public void displaySuccessfullyDoneTask(String title, Task task) {
        StringJoiner successMessage = Ui.createStringJoiner(title);
        successMessage.add("  " + formatTask(task));
        printBlock(successMessage.toString());
    }

    /**
     * Convenience method to inform the user that
     * the task they had previously selected has been removed.
     *
     * @param title     A friendly "removal successful" style message to show to the user.
     * @param task      The task that has been removed.
     * @param tasksLeft The number of tasks the user still has left.
     */
    public void displaySuccessfullyRemovedTask(String title, Task task, int tasksLeft) {
        StringJoiner successMessage = Ui.createStringJoiner(title);
        successMessage.add("  " + formatTask(task));
        successMessage.add(String.format("Now you have %d tasks in the list.", tasksLeft));
        printBlock(successMessage.toString());
    }

    /**
     * Convenience method to inform the user
     * that they have added a new task to their list.
     *
     * @param title     A friendly "task added" style message to show to the user.
     * @param task      The task that had just been added.
     * @param tasksLeft The number of tasks the user still has left.
     */
    public void displaySuccessfullyAddedTask(String title, Task task, int tasksLeft) {
        StringJoiner successMessage = Ui.createStringJoiner(title);
        successMessage.add("  " + formatTask(task));
        successMessage.add(String.format("Now you have %d tasks in the list.", tasksLeft));
        printBlock(successMessage.toString());
        println();
    }
}
