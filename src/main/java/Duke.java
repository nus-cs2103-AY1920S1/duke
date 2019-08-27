public class Duke {
    private static TaskList tasklist;

    public static void main(String[] args) throws DukeException {
        Task[] tasks = new Task[100];
        tasklist = new TaskList(tasks);

        Storage.readSavedList(tasklist);

        Ui.greet();

        String input = Ui.readCommand();

        Parser.Parse(input, tasklist);

        Ui.exit();
    }
}