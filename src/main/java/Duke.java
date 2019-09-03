import java.io.FileNotFoundException;

public class Duke {

    private Storage storage = new Storage("src/main/data/duke.txt");
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Duke with a path to task list file.
     * New Ui instance is created.
     * Loads data from the file and the tasks are stored into a TaskList.
     * If file is not found or empty, exception is reported.
     */
    public Duke() {
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Creates new instance of Parser and run parse method to read user input.
     * Catches and reports DukeExceptions.
     */
    public String getResponse(String text) {
        try {
            Parser p = new Parser(storage, tasks, ui);
            return p.parse(text);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
