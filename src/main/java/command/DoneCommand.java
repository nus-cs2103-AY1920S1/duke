package command;

import tasks.Task;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.io.IOException;

/**
 * Done Command is a Command, specifically for a done operation.
 */
public class DoneCommand extends Command {

    int index;

    public DoneCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the given task and prints the respective output.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        String output = "";

        if (index < 0 || index > tasks.getSize() - 1) {
            return new InvalidCommand("Integer supplied should be within range of list.").execute(tasks, ui, storage);
        }

        // Execute Command
        tasks.getTaskList().get(index).setAsDone();

        Task doneTask = tasks.getTaskList().get(index);

        // Save output as String
        output += "\n\tNice! I have marked this task as done: ";
        output += "\n\t" + doneTask;

        // Saving to file
        storage.updateAsDone(index);

        return output;

    }
}