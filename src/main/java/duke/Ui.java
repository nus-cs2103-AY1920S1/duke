package duke;

import duke.task.Task;

import java.util.LinkedList;

/**
 * Handles user's interactions with the bot's ui, printing appropriate messages when needed to indicate
 * the result of a command typed in by the user.
 */
public class Ui {

    private String stringToPrint;

    public String getStringToPrint() {
        return stringToPrint;
    }

    private void addNewLine() {
        stringToPrint += "\n";
    }

    /**
     * Prints loading error message if any exception thrown when file with tasks is loaded from storage or created.
     */
    public void showLoadingError() {
        stringToPrint = "\u2639 OOPS!!! Unable to load file. Try again!";
        addNewLine();
    }

    /**
     * Prints error message for any error thrown from the parsing or execution of a user command.
     *
     * @param error Error message to be printed to screen.
     */
    public void showError(String error) {
        stringToPrint = error;
        addNewLine();
    }

    /**
     * Prints exit message before the bot is closed.
     */
    public void showExit() {
        stringToPrint = "Bye. Hope to see you again soon!";
        addNewLine();
    }

    /**
     * Prints all tasks in the list when user inputs list command.
     *
     * @param taskList List of tasks.
     */
    public void showTasks(TaskList taskList) {
        LinkedList<Task> tasks = taskList.getTasks();
        String result = "";

        result += "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i) + "\n";
        }

        stringToPrint = result;

        assert !stringToPrint.equals("");
    }

    /**
     * Prints a modified task if the task's status has changed (to completed) when the user inputs done command.
     *
     * @param t Changed Task.
     */
    public void showChangedTask(Task t) {
        String result = "";

        result += "Nice! I've marked this task as done:\n";
        result += t;

        stringToPrint = result;
        addNewLine();

        assert !stringToPrint.equals("");
    }

    /**
     * Prints a task that was deleted by the user through the delete command.
     *
     * @param t Deleted task.
     */
    public void showDeletedTask(Task t) {
        String result = "";

        result += "Noted. I've removed this task:\n";
        result += t;

        stringToPrint = result;
        addNewLine();

        assert !stringToPrint.equals("");
    }

    /**
     * Prints number of tasks left in the list once a task has been deleted through the delete command.
     *
     * @param tasks List of tasks.
     */
    public void showNumTasks(TaskList tasks) {
        int taskListSize = tasks.getTasks().size();
        String result = "";

        result += "Now you have " + taskListSize;
        if (taskListSize == 1) {
            result += " task ";
        } else {
            result += " tasks ";
        }
        result += "in the list.";

        stringToPrint += result;
        addNewLine();

        assert !stringToPrint.equals("");
    }

    /**
     * Prints a task that was added to task list through the commands deadline, event, or todo.
     *
     * @param task Added task.
     */
    public void printAddedTask(Task task) {
        String result = "";

        result += "Got it. I've added this task:\n";
        result += task;

        stringToPrint = result;
        addNewLine();

        assert !stringToPrint.equals("");
    }

    public void printFoundTasks(LinkedList<Task> foundTasks) {
        if (foundTasks.size() == 0) {
            stringToPrint = "No matching tasks found!";
            addNewLine();
        } else {
            String result = "";

            result += "Here are the matching tasks in your list:\n";

            for (int i = 0; i < foundTasks.size(); i++) {
                Task t = foundTasks.get(i);
                if (!t.getDescription().equals("fake task")) {
                    result += (i + 1) + "." + t + "\n";
                }
            }

            stringToPrint = result;
        }
        assert !stringToPrint.equals("");
    }

}
