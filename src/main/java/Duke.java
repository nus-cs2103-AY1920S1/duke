import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Duke {
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            if (!(e instanceof NoSuchFileException)) {
                ui.showError("Could not load tasks. Proceeding may overwrite old tasks.");
                e.printStackTrace();
            }
            tasks = new TaskList();
        }
        parser = new Parser();
        parser.register("todo", ToDo.getCommand(tasks, storage));
        parser.register("deadline", Deadline.getCommand(tasks, storage));
        parser.register("event", Event.getCommand(tasks, storage));
        parser.register("list", new ListCommand(tasks));
        parser.register("done", new DoneCommand(tasks, storage));
        parser.register("delete", new DeleteCommand(tasks, storage));
        parser.register("bye", new ByeCommand());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit && ui.hasNextLine()) {
            String[] words = ui.readCommand().split(" ");
            try {
                Command command = parser.parse(words);
                ui.printMessage(command.run(words));
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
