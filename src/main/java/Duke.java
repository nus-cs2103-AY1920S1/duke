import java.io.*;

import java.text.ParseException;

import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.initMessage();
        ui.readUserInput();
    }

    public static void main(String[] args) {
        new Duke("/data/duke.txt").run();
    }
}
