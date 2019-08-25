import duck.command.Command;
import duck.util.*;


import java.io.FileNotFoundException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    private void setUp() {
        ui.showWelcome();

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

        if (taskList.getTotalTask() > 0) {
            ui.showFullList(taskList);
        } else {
            ui.showNoTask();
        }
    }

    public void run() {

        setUp();

        boolean isExist = false;
        while (!isExist) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExist = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke("/Users/xiaoyu/duke/data/duke.txt").run();
    }
}
