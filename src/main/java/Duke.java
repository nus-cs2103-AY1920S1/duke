import java.io.IOException;

/**
 * Duke class for the running of UI, TaskList, Storage and Command. Main class of the project
 */
public class Duke {
    public static void main(String[] args) throws IOException, DukeException {

        UI ui = new UI();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        Command c = new Command();
        c.execute(taskList, ui, storage);
    }
}
