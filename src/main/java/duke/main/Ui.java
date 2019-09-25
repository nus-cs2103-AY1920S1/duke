package duke.main;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    private StringBuilder output;

    Ui() {
        output = new StringBuilder();
    }

    void resetOutput() {
        output.setLength(0);
    }

    String getOutput() {
        return output.toString();
    }

    private void appendToOutput(String str) {
        output.append(str);
    }

    /**
     * Prints a success message after an undo operation is successfully performed.
     *
     * @param stepsToUndo Number of times to perform undo operation.
     */
    public void printUndoSuccessMessage(int stepsToUndo) {
        appendToOutput("Performed undo " + stepsToUndo + " times!");
    }

    /**
     * Prints a success message after a task is successfully added to the list.
     *
     * @param task Task added to list.
     * @param size Current size of list after addition.
     */
    public void printAddSuccessMessage(Task task, int size) {
        appendToOutput("Got it. I've added this task:\n");
        appendToOutput("\t" + task + "\n");
        appendToOutput("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints a success message after a task is deleted from to the list.
     *
     * @param task Task deleted from list.
     * @param size Current size of list after deletion.
     */
    public void printDeleteSuccessMessage(Task task, int size) {
        appendToOutput("Noted. I've removed this task:\n");
        appendToOutput("\t" + task + "\n");
        appendToOutput("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints the given list with formatting.
     *
     * @param tasks List of tasks to be printed.
     * @param message The message to be printed before printing the list.
     */
    public void printList(ArrayList<Task> tasks, String message) {
        appendToOutput(message + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            int oneBasedIndex = i + 1;
            String formattedOutput = String.format("%d. %s\n", oneBasedIndex, tasks.get(i));
            appendToOutput(formattedOutput);
        }
    }

    String getGreeting() {
        return "Hello from Duke\nWhat can I do for you?";
    }

    /**
     * Prints the exit message.
     *
     */
    public void printExitMessage() {
        appendToOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a given message with formatting.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        appendToOutput(message);
    }
}
