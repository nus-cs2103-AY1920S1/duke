package command;

import tasks.Task;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.io.IOException;

/**
 * Delete Command is a Command, specifically for delete operation.
 */
public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
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

        // Execute command
        Task removed = tasks.getTaskList().get(index);
        tasks.getTaskList().remove(index);

        // Save output as String
        output += "\n\tNoted. I have removed this task: " + "\n\t" + removed;
        output += "\n\tNow you have " + tasks.getSize() + " tasks in the list.";

        // Save in .txt file
        storage.deleteTask(index);

        return output;

    }
}