import java.io.FileNotFoundException;

public class Duke {

    private Storage storage = new Storage("src/main/data/duke.txt");
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Duke with a path to list of tasks file.
     * New Ui instance is created for Gradle.
     * Loads data from the file and the tasks are stored into a TaskList.
     * Exception is reported, if file is not found or empty.
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
     * Creates new instance of Parser and run parse method to read user input and then give reply.
     * Catches and reports DukeExceptions.
     */
    public String getResponseDirect(String text) {
        try {
            Parser parser = new Parser(storage, tasks, ui);
            return parser.parse(text);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}