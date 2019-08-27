public class Duke {
    private static TaskList taskList;

    public static void main(String[] args) throws DukeException {
        run();
    }

    /**
     * the body of the main method, where Tasklist is initialised and the code runs.
     */
    public static void run() {
        Task[] tasks = new Task[100];
        taskList = new TaskList(tasks);
        Storage.readSavedList(taskList);

        Ui.greet();

        String input = Ui.readCommand();
        Parser.Parse(input, taskList);

        Ui.exit();
    }
}