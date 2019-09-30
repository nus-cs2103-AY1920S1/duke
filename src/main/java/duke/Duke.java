package duke;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String INDENT_SPACING = "    ";
    private static final String ROOT_DIRECTORY = "C:/Users/gbrls/OneDrive/Desktop/duke-master/src/main/java/duke/";
    //private static final String SAVE_DIRECTORY = "/data";
    //private static final String SAVE_FILE_NAME = "tasks.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(ROOT_DIRECTORY + "data/tasks.text").run();
    }



}