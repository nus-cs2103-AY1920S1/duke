public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.scan(tasks, storage);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
