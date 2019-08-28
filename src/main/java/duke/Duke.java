
package duke;

import duke.command.Command;
import duke.parser.Paser;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.UiText;

import java.io.FileNotFoundException;

/**
 * Duke is  a Persional Assistant Chatbot thats
 * helps a person to keep track of various things.
 * @author Yang Shuting
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UiText ui;

    /**
     * constructor to create a Duke Chatbot
     *
     * @param filePath The path for the text file that storages
     *                 the data
     */
    public Duke(String filePath) {
        ui = new UiText();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    private void run() {
        try {
            ui.greeting();
            storage.printFileContents();
            boolean isExit = false;
            while (!isExit) {
                try {
                    String fullCommand = ui.readCommand();
                    ui.showLine();
                    Command c = Paser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                } catch (DukeException e) {
                    ui.printlnMsg(e.getMessage());
                } finally {
                    ui.showLine();
                }
            }
        }catch (FileNotFoundException e){
                    ui.showLoadingError();
        }
    }


    public static void main(String[] args) throws Exception {
        Duke duke = new Duke("src/main/data/duke.txt");
        duke.run();
    }
}
