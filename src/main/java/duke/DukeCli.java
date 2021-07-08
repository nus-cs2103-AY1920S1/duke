package duke;


import duke.commands.Command;
import duke.core.Parser;
import duke.core.Storage;
import duke.core.TaskList;
import duke.exceptions.DukeException;
import duke.ui.Clui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DukeCli {
    private TaskList taskList;
    private Clui clui;
    private Storage storage = new Storage(new File("data"));

    /**
     * Main function for Duke CLI application.
     */
    public void run() {
        clui = new Clui();
        taskList = new TaskList();
        boolean taskFileExists = true;
        boolean storageInitialised = true;

        try {
            storageInitialised = storage.initialise();
        } catch (SecurityException e) {
            clui.echoException(e);
        }

        /** Try to load data */
        try {
            taskList.loadData(storage.getCurrentTasks());
        } catch (FileNotFoundException e) {
            taskFileExists = false;
            try {
                storage.createFile();
            } catch (IOException e2) {
                clui.echoException(e2);
            }
        } catch (DukeException e) {
            clui.echoException(e);
        }

        clui.greet(taskFileExists, storageInitialised);

        /** Interaction with User */
        boolean isByeBye = false;
        while (!isByeBye) {
            try {
                String inputCommand = clui.readCommand(); // Initial Input
                Command c = Parser.parseCommand(inputCommand);
                c.execute(storage, taskList, clui);
                if (c.isExit) {
                    isByeBye = true;
                }
            } catch (IndexOutOfBoundsException e) {
                clui.echoException(new DukeException("Index Out of Bounds Exception Caught"));
            } catch (DukeException e) {
                clui.echoException(e);
            } catch (Exception e) {
                clui.echoException((new DukeException(e.getMessage())));
            }
        }
    }
}
