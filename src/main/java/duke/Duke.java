package duke;

import duke.commands.Command;
import duke.tasks.TaskList;

import java.io.FileNotFoundException;

public class Duke {
    private static final String FILE_PATH = "data/duke.txt";
    private static final String TASK_LOAD_ERR = "Error loading files, starting a new list...";
    private static final int NUMBER_TASKS = 100;

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean running;

    public Duke(String path) {
        storage = new Storage(path);
        ui = new Ui();
        running = true;
        try {
            tasks = new TaskList(NUMBER_TASKS, storage.read());
        } catch (FileNotFoundException e) {
            ui.sayError(TASK_LOAD_ERR);
            tasks = new TaskList(NUMBER_TASKS);
        }
    }

    private void start() {
        String input;
        ui.greet();
        while (running) {
            input = ui.readLine();
            try {
                Command command = Parser.parse(input);
                command.execute(storage, ui, tasks);
                if(command.isExit())
                    exit();
            } catch (DukeException e) {
                ui.sayError(e.getMessage());
            }
        }
    }

    private void exit() {
        ui.farewell();
        ui.close();
        this.running = false;
    }

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.start();
    }
}

