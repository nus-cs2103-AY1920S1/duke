package main;

import command.Command;
import exception.DukeException;
import parser.Parser;
import parser.Storage;
import task.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction
 * with users.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Loads task list from hard disk if file is found.
     * Else create an empty task list.
     *
     * @param filePath directory where task list is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Collects input from users.
     * Prints exit message.
     * Saves task list into hard disk.
     */
    public void run() {
        boolean canEnd = false;
        while (!canEnd) {
            try {
                String fullCommand = ui.readCommand();
                Command command = new Parser().parse(fullCommand);
                command.execute(tasks, ui, storage);
                canEnd = command.canEnd();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        return "haha";
    }

//    public static void main(String[] args) {
//        Duke duke = new Duke("data/duke.txt");
//        duke.run();
//    }
}
