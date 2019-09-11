import java.io.IOException;
import java.io.FileNotFoundException;
import utils.Storage;
import utils.TaskList;
import utils.Ui;
import utils.Parser;
import command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws IOException {
        Command c = Parser.parse(input);
        assert (c instanceof Command) : "Parser did not return a command.";
        return c.execute(tasks, ui, storage);
    }

    public String getStartMessage() {
        return ui.getWelcomeMessage();
    }

    public Duke() {
        String filePath = "src/main/java/data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }
}