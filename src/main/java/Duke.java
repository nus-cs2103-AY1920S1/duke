import duke.exception.DukeException;
import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.text.Parser;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class Duke {
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            ui.setTasks(tasks);
        }
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke("data/duke.txt");
        newDuke.run();
    }

    public void run1() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(list);
        String a = list.get(1);
        System.out.println(a);
        a = "3";
        System.out.println(list);
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
