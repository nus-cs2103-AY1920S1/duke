import duke.exception.DukeException;
import duke.task.TaskList;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public static void main(String[] args) {
        ui = Ui.getInstance();
        storage = new Storage("data/duke.txt");
        tasks = storage.loadTasks();

        ui.printLine("Hello! I'm Duke\nWhat can I do for you?");

        boolean isRunning = true;
        while (isRunning) {
            String input = ui.getNextLine();

            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isRunning = !c.isExit();
            } catch (DukeException e) {
                ui.printLine(e.getMessage());
            }
        }

        ui.printLine("Bye. Hope to see you again soon!");
    }
}
