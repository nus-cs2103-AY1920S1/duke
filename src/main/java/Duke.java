import java.io.IOException;
import java.text.ParseException;

/**
 * A program named Duke.
 * Something like a Personal Assistant Chatbot that helps
 * a person keep track of various things, it can only
 * understand text commands of a specific structure.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) throws DukeException, IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        taskList = new TaskList(storage.load());
    }

    public void run() throws IOException {
        ui.showGreetings();
        boolean inProgram = true;
        while (inProgram) {
            try {
                String userFullInput = ui.readUserInput();
                // Entered no input
                if (userFullInput == "") {
                    continue;
                }
                Command c = parser.parse(userFullInput);
                c.execute(taskList, ui, storage);
                inProgram = c.toContinue();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException, ParseException {
        new Duke("F:\\CS2103\\duke\\data\\duke.txt").run();
    }

    public TaskList getTaskList() {
        return taskList;
    }

}