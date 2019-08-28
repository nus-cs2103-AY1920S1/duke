import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String userInput;
    private static String[] inputStringArr;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException ex) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        String command = "";
        do {
            String userInput = ui.getUserInput();
            Parser p = new Parser(userInput);
            command = p.getCommand();
            executeCommand(command, userInput, tasks, storage);
        } while (!command.equals("bye"));
    }

    public void executeCommand(String command, String input, TaskList tasks, Storage storage) {
        if (command.equals("bye")) {
            ui.handleBye();
        } else if (command.equals("list")) {
            ui.handleList(input, tasks);
        } else if (command.equals("done")) {
            ui.handleDone(input, tasks, storage);
        } else if (command.equals("todo")) {
            ui.handleTodo(input, tasks, storage);
        } else if (command.equals("deadline")) {
            ui.handleDeadline(input, tasks, storage);
        } else if (command.equals("event")) {
            ui.handleEvent(input, tasks, storage);
        } else if (command.equals("delete")) {
            ui.handleDelete(input, tasks, storage);
        } else if (command.equals("find")) {
            ui.handleFind(input, tasks);
        } else {
            ui.handleBadCommand();
        }
    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }
}
