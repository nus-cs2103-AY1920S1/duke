package duke;

import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.MetaData;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

import java.io.File;
import java.io.IOException;

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
        try {
            this.taskList = new TaskList(storage.load());
            this.metaData.writeMetaData(filePath);
            return ui.getFilePathOpenedSuccessfullyResponse(filePath);
        } catch (DukeException e) {
            Response toReturn = ui.getLoadingErrorResponse(filePath);
            if(e.getMessage().equals(LOADING_ERROR)) {
                this.taskList = new TaskList();
                toReturn = createNewDataFilePath(filePath);
            }

            return toReturn;
        }
    }

    private Response createNewDataFilePath(String filePath) {
        File tempFile = new File(filePath);

        try {
            if (!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();

                tempFile.createNewFile();
            }
            this.metaData.writeMetaData(filePath);
            return ui.getLoadingErrorResponse(filePath);
        } catch (IOException e) {
            return ui.getErrorResponse("Error initializing new file " + filePath);
        } catch (DukeException de) {
            return ui.getErrorResponse("Error writing .metadata");
        } catch (NullPointerException ne) {
            return ui.getErrorResponse("Parent directory cannot be null! " +
                    "Try \"data/duke.txt\""
                    + "\n WARNING: Your tasks are NOT being saved.");
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
