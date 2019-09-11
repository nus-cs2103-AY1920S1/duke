package command;

import java.io.FileNotFoundException;
import java.io.IOException;

import utils.TaskList;
import utils.Storage;
import utils.Ui;
import tasks.Task;

/**
 * Done Command is a Command, specifically for a done operation.
 */
public class DoneCommand extends Command {

    int index;

    public DoneCommand(int index) {
        this.index = index - 1;
    }


    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        String output = "";

        if (index < 0 || index > tasks.getSize() - 1) {
            return new InvalidCommand("Integer supplied should be within range of list.").execute(tasks, ui, storage);
        }

        // Execute Command
        tasks.getTaskList().get(index).setAsDone();

        Task doneTask = tasks.getTaskList().get(index);

        // Save output as String
        output += ui.getTopBorder();
        output += "\n\tNice! I have marked this task as done: ";
        output += "\n\t" + doneTask;
        output += ui.getBottomBorder();

        // Saving to file
        storage.updateAsDone(index);

        return output;

    }
}