import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class UserInterface {
    private static final String LINE_INDENT = "\t";
    private static final String HORIZONTAL_LINE = LINE_INDENT + "_".repeat(60);
    private static final Pattern LINE_START_PATTERN = Pattern.compile("^", Pattern.MULTILINE);

    private final Scanner scanner;
    private final PrintStream output = System.out;

    UserInterface() {
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
        instance.add(str);
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
}
