import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private static List<Task> list;
    private static Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadList());
        } catch (LoadingErrorDukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("src/data/list.txt").run();
    }

    public void run() {
        boolean running = true;
        ui.showWelcome();
        while (running) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                running = command.isRunning();
            } catch (Exception e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }
}
