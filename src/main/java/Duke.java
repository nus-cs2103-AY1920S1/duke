import command.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.VocabularyList;

/**
 * Main Duke class.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private VocabularyList vocabularyList;
    private Ui ui;
    private boolean readyToExit = false;

    /**
     * Constructor for Duke Class.
     */
    public Duke() {
        String filepath = "duke.txt";
        String vocabpath = "vocabulary.txt";
        ui = new Ui();

        try {
            storage = new Storage(filepath, vocabpath);
            vocabularyList = new VocabularyList(storage.loadVocabulary());
        } catch (DukeException duke) {
            ui.showLoadingError(duke);
        }

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException duke) {
            ui.showLoadingError(duke);
            tasks = new TaskList();
        }
    }

    /**
     * Returns a String response by the bot based on the user input.
     *
     * @param input Input by the user.
     * @return Output response of String type.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(vocabularyList.format(input));
            String response;
            response = c.getResponse(tasks, ui, storage, vocabularyList);
            if (c.isExit()) {
                this.setExit();
            }
            return response;
        } catch (DukeException e) {
            return ui.showLoadingError(e);
        }
    }

    /**
     * Sets the state of the program to be ready to exit
     * by the next user command.
     */
    private void setExit() {
        this.readyToExit = true;
    }

    /**
     * Ready to exit method.
     *
     * @return True if the User is ready to exit.
     */
    public boolean readyToExit() {
        return this.readyToExit;
    }

}
