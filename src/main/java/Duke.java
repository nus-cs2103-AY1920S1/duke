/**
 * Represents the main logic of the applications.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes duke application with the path of the file of the list of tasks.
     *
     * @param filePath Path of the file that is being used to load and save the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the duke application.
     */

    public void run() {
        ui.printHello();
        String command;

        while (!(command = ui.getNext()).equals("bye")) {
            try {
                Parser.parse(tasks, ui, storage, command, ui.getNextLine());
            } catch (DukeException e) {
                ui.printLine();
                System.out.println("     " + e.getMessage());
                System.out.println("    _____________________________________\n");
            }
        }

        storage.save(tasks.getListOfTasks());
        ui.printBye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}