import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Deadline;

import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Scanner sc;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

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

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public static void main(String[] args) {
        new Duke("C:/CS2103/iP/data/duke.txt").run();
    }

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

    public void evaluate(String input) throws DukeException {
        try {
            parser.evaluateInput(input);
            storage.writeTasks(getTasksAscii());
        } catch (IOException e) {
            print("OOPS!!! Invalid file path.");
        }
    }

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

    public void print(String message) {
        ui.dukeOutput(message);
    }

    public String getNumberOfTasks() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    public void printTaskAdded() {
        String output = "Got it. I've added this task:\n"
                + "  " + tasks.get(tasks.size() - 1).toString()
                + "\n" + getNumberOfTasks();
        ui.dukeOutput(output);
    }

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
