/** Main Duke class. */
public class Duke {
    private TaskList tasks;

    /** Constructor. */
    public Duke(String path) {
        Storage.setPath(path);
        try {
            tasks = new TaskList(Storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.toString());
            System.out.println(e.getCause());
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("tasks.dmp");
    }
}
