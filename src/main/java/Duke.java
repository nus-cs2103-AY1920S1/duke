import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;
    private static Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList  = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        parser = new Parser(taskList, ui);
    }

    public void run() {
        ui.start(parser, storage, taskList);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
