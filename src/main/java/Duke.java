import java.util.ArrayList;

public class Duke {
    protected static ArrayList<Task> listOfTasks;
    private static Storage storage;
    private static Ui ui;

    public static void main(String[] args) {
        ui = Ui.getInstance();
        storage = new Storage("data/duke.txt");
        listOfTasks = storage.loadTasks();

        ui.printLine("Hello! I'm Duke\nWhat can I do for you?");

        boolean isRunning = true;
        while (isRunning) {
            String input = ui.getNextLine();

            try {
                Command c = Parser.parse(input);
                c.execute(listOfTasks, ui, storage);
                isRunning = !c.isExit();
            } catch (DukeException e) {
                ui.printLine(e.getMessage());
            }
        }

        ui.printLine("Bye. Hope to see you again soon!");
    }
}
