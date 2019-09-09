package duke;

import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.MetaData;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

import static duke.dukeexception.DukeException.*;

/**
 * Class that serves as the main driver for the Duke application.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private MetaData metaData;

    /**
     * Class constructor that specifies file path to load storage from.
     *
     * @param filepath File path to load storage from.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
    }

    /**
     * Class constructor that assumes file path to load storage from is in
     * data/duke.txt.
     */
    public Duke() {
        this.ui = new Ui();
        this.metaData = new MetaData("data/.metadata");
    }

    public Response setStorageByFilePath(String filePath) {
        this.storage = new Storage(filePath);
        System.out.println(filePath);
        try {
            this.taskList = new TaskList(storage.load());
            this.metaData.writeMetaData(filePath);
            return ui.getFilePathOpenedSuccessfullyResponse(filePath);
        } catch (DukeException e) {
            if(e.getMessage().equals(LOADING_ERROR)) {
                this.taskList = new TaskList();
            }
            return ui.getLoadingErrorResponse();
        }
    }

    /**
     * Returns the Ui object handle inputs and outputs for this Duke instance.
     *
     * @return This Duke instance's Ui object.
     */
    public Ui getUI(){
        return ui;
    }

    private Response process(String input) {
        Response response;

        if(storage == null || taskList == null) {
            return setStorageByFilePath(input);
        }

        try {
            assert this.storage != null : "Storage not initialized";
            assert this.ui != null : "Ui not initialized";
            assert this.taskList != null : "TaskList not initialized";
            Command c = Parser.parse(input);
            response = c.execute(taskList, ui, storage);
        } catch (DukeException de) {
            response = ui.getErrorResponse(de.getMessage());
        }

        assert response != null : "No Response returned by Command";

        return response;
    }

    /**
     * Returns the response after passing user input through Duke's logic.
     *
     * @param input User input from GUI.
     * @return Response returned by Duke's logic.
     */
    public Response getResponse(String input) {
        Response response = process(input);
        return response;
    }

    public String getLastOpenedFile() {
        try {
            return Parser.parseAndGetLastOpenedFile(metaData.readMetaData());
        } catch (DukeException de) {
            return null;
        }
    }
}
