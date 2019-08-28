package duke;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    private Duke() {
        storage = new Storage("duke.txt");
        tasks = new TaskList();
        ui = new Ui();
    }

    private void run() {
        ui.showWelcome();

        try {
            tasks = storage.loadTasks();
        } catch (DukeException e) {
            ui.showWarning("Failed to load tasks from disk. " + e.getMessage());
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                if (input == null) {
                    isExit = true;
                    continue;
                }
                ui.showSeparator();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showSeparator();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
