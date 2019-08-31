/** Main Duke class. */
public class Duke {
    private UserInterface ui;
    private TaskList tasks;

    /** Constructor. */
    public Duke(String path) {
        ui = new UserInterface();
        Storage.setPath(path);
        try {
            tasks = new TaskList(Storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /** Entry point for program. */
    public void run() {
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.run();
    }

    /** Main function. */
    public static void main(String[] args) {
        new Duke("tasks.dmp").run();
    }
}
