import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Deadline;

import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The main class. The entire chat-bot runs from here.
 */

public class Duke {

    private Scanner sc;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath Filepath of the list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui(this);
        tasks = new TaskList(this);
        parser = new Parser(this, tasks);
        try {
            setStorage(new Storage(filePath));
            tasks.setTaskList(storage.loadTasks());
        } catch (IOException e) {
            System.out.println("Invalid file path!");
        }
    }

    /**
     * Sets the storage object to an instance at the correct filepath.
     *
     * @param storage The storage object to be used.
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Executes the run method with the correct file-path input.
     *
     * @param args Command line inputs.
     */
    public static void main(String[] args) {
        new Duke("C:/CS2103/iP/data/duke.txt").run();
    }

    /**
     * Executes and orchestrates the running of the chat-bot.
     */
    public void run() {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        ui.dukeOutput("Hello, this is Duke.\n" + "How may I help you?");
        ui.readInputs();
    }

    /**
     * Evaluates the input string from the UI.
     *
     * @param input Input string from the UI
     * @throws DukeException If input is invalid.
     */
    public void evaluate(String input) throws DukeException {
        try {
            parser.evaluateInput(input);
            storage.writeTasks(getTasksAscii());
        } catch (IOException e) {
            print("OOPS!!! Invalid file path.");
        }
    }

    /**
     * Returns a string of tasks containing only ASCII characters.
     *
     * The string returned is suitable to be written to a plain .txt file.
     *
     * @return List of tasks.
     */
    public String getTasksAscii() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1) + ". " + tasks.get(i).getAscii());
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Outputs a message through the UI.
     *
     * @param message Message to be printed.
     */
    public void print(String message) {
        ui.dukeOutput(message);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks.
     */
    public String getNumberOfTasks() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Notifies the user about the most recently added task.
     */
    public void printTaskAdded() {
        String output = "Got it. I've added this task:\n"
                + "  " + tasks.get(tasks.size() - 1).toString()
                + "\n" + getNumberOfTasks();
        ui.dukeOutput(output);
    }

    /**
     * Outputs the list of tasks and their statuses.
     */
    public void printTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1) + ". " + tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        if (output.toString().isBlank()) {
            ui.dukeOutput("You have no tasks to do o_O!");
        } else {
            ui.dukeOutput(output.toString());
        }
    }

}
