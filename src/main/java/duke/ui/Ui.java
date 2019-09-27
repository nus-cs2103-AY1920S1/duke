package duke.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import duke.task.TaskList;
import duke.exceptions.DukeException;

/**
 * Represents interactions with user via CLI.
 */
public class Ui {
    /** BufferedReader to read CLI input. */
    private BufferedReader br;
    /** Line for responses. */
    private static final String line = String.format("%4s", "") + String.format("%60s", "").replace(" ", "_");
    /** Indentation for response. */
    private static final String indent = String.format("%5s", "");

    /**
     * Creates an instance of Ui.
     * Opens BufferedReader.
     */
    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads the user command from CLI.
     *
     * @return String command from user.
     * @throws DukeException If there is any IOException from bufferedreader.
     */
    public String readCommand() throws DukeException {
        try {
            String line = br.readLine();
            return line == null ? "" : line;
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * Prints out the greeting.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = indent + "Hello! I'm Duke\n" + indent + "What can I do for you?";
        String response = String.format("%s\n%s\n%s\n", line, message, line);
        System.out.println(response);
    }

    /**
     * Prints out the task list.
     *
     * @param tasklist TaskList to be printed out.
     */
    public static void printList(TaskList tasklist) {
        System.out.println(tasklist);
    }

    /**
     * Prints line.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Gets the in text indent width.
     *
     * @return Indentation.
     */
    public String getIndent() {
        return indent;
    }

    /**
     * Prints error.
     */
    public void showError(String errMessage) {
        System.out.println(indent + errMessage);
    }

    /**
     * Closes the bufferedreader.
     */
    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("Buffered Reader could not close properly.");
        }
    }
}
