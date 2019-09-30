import commands.Command;
import commands.DukeException;
import components.Parser;
import components.Storage;
import components.TaskList;

import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private String initializationErrorMessage;

    /**
     * Constructs a Duke instance.
     *
     * @param filepath refers to where items should be kept in memory.
     */
    Duke(String filepath) throws DukeException {
        storage = new Storage(filepath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            initializationErrorMessage = e.getMessage();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    String[] deleteCompletedTasks() {
        return taskList.removeCompletedTasks();
    }

    String[] deleteAll() {
        return taskList.deleteAll();
    }

    String[] run(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(storage, taskList);
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }

    String[] getWelcomeMessage() {
        if (initializationErrorMessage == null) {
            return new String[]{"Hello! I'm Duke. How can I help you?"};
        }
        return new String[]{"Hello! I'm Duke. How can I help you?", initializationErrorMessage};

    }

    void loadTasksWithNoOverwrite(String filepath) throws DukeException {
        Storage tempStorage = new Storage(filepath);
        this.taskList.getArr().addAll(tempStorage.load());
        storage.save(this.taskList.getArr());
    }

    void loadTasksWithOverwrite(String filepath) throws DukeException {
        Storage tempStorage = new Storage(filepath);
        this.taskList = new TaskList(tempStorage.load());
        storage.save(this.taskList.getArr());
    }

}


