package duke.ui;

import duke.DukeException;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.Todo;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

public abstract class Ui {
    /**
     * Returns a new {@code StringJoiner} using the system's newline separator.
     *
     * @return A new instance of {@code StringJoiner}.
     */
    public static StringJoiner createStringJoiner() {
        return new StringJoiner(System.lineSeparator());
    }

    /**
     * Returns a new {@code StringJoiner} using the system's newline separator,
     * initialized with the contents of the specified string.
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
     * Formats a Task differently based on its subtype (i.e. Todo/Deadline/Event).
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
            description = String.format(
                    "%s (at: %s)",
                    e.getDescription(),
                    e.getEventDateTime().format(dateTimeFormatter)
            );
        }

        return String.format("[%s][%s] %s", taskType, t.getStatusIcon(), description);
    }

    /**
     * Prints an empty line to the user.
     */
    public abstract void println();

    /**
     * Prints the content argument.
     *
     * @param content Text to display to the user.
     */
    public abstract void println(String content);

    /**
     * Prints a block user interface element containing the content argument.
     *
     * @param content Text to display to the user.
     */
    public abstract void printBlock(String content);

    /**
     * Displays the welcome message to the user.
     */
    public void displayWelcome() {
        printBlock("Hello! I'm Duke"
                + "\nWhat can I do for you?");
        println();
    }

    /**
     * Displays an error when Duke is unable to read the Tasks stored in a file.
     *
     * @param exc The reason for the failed read/parse operation.
     */
    public void displayLoadingError(Throwable exc) {
        StringJoiner errorMessage = createStringJoiner("Couldn't load previously saved Tasks.");
        errorMessage.add("Duke will start with an empty Task list.");
        errorMessage.add("");
        errorMessage.add("More details: " + exc.getMessage());
        if (exc.getCause() != null) {
            errorMessage.add(exc.getCause().getMessage());
        }

        displayError(errorMessage.toString());
    }

    /**
     * Displays a DukeException to the user.
     *
     * @param exc The DukeException that will be shown to the user.
     */
    public void displayError(DukeException exc) {
        displayError(exc.getMessage());
    }

    /**
     * Displays the error message to the user.
     *
     * @param message The error message that will be shown to the user.
     */
    public void displayError(String message) {
        printBlock(" ☹ OOPS!!! " + message);
        println();
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks Tasks to be displayed.
     */
    public void displayTasks(List<Task> tasks) {
        displayTasks(null, tasks);
    }

    /**
     * Displays a list of tasks to the user with a title.
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
     * Displays a message to inform the user that the task they had previously selected has been marked done.
     *
     * @param title A friendly "marked as done" style message to show to the user.
     * @param task  The task that was marked as done.
     */
    public void displaySuccessfullyDoneTask(String title, Task task) {
        StringJoiner successMessage = createStringJoiner(title);
        successMessage.add("  " + formatTask(task));
        printBlock(successMessage.toString());
    }

    /**
     * Displays a message to inform the user that the task they had previously selected has been removed.
     *
     * @param title     A friendly "removal successful" style message to show to the user.
     * @param task      The task that has been removed.
     * @param tasksLeft The number of tasks the user still has left.
     */
    public void displaySuccessfullyRemovedTask(String title, Task task, int tasksLeft) {
        StringJoiner successMessage = createStringJoiner(title);
        successMessage.add("  " + formatTask(task));
        successMessage.add(String.format("Now you have %d tasks in the list.", tasksLeft));
        printBlock(successMessage.toString());
    }

    /**
     * Displays a message to inform the user that they have added a new task to their list.
     *
     * @param title     A friendly "task added" style message to show to the user.
     * @param task      The task that had just been added.
     * @param tasksLeft The number of tasks the user still has left.
     */
    public void displaySuccessfullyAddedTask(String title, Task task, int tasksLeft) {
        StringJoiner successMessage = createStringJoiner(title);
        successMessage.add("  " + formatTask(task));
        successMessage.add(String.format("Now you have %d tasks in the list.", tasksLeft));
        printBlock(successMessage.toString());
        println();
    }
}
