package duke.command;

import java.io.IOException;
import java.util.List;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand implements Command {
    private TaskList tasks;
    private Storage storage;

    public DeleteCommand(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Removes a task, and returns a message about the removed task and number of remaining tasks.
     *
     * @param words Array of words from the input line.
     * @return Message to show the user.
     * @throws IOException If the tasks cannot be saved.
     */
    @Override
    public List<String> run(String[] words) throws IOException {
        int index = Integer.parseInt(words[1]) - 1;
        if (tasks.size() == 0) {
            return List.of("There are no tasks in the list.");
        } else if (tasks.size() == 1 && index != 0) {
            return List.of("Invalid task number. Please use 1 to choose the only task.");
        } else if (index < 0 || index >= tasks.size()) {
            return List.of("Invalid task number. Please use a number from 1 to " + tasks.size() + ".");
        }
        Task task = tasks.remove(index);
        storage.store(tasks.getAsLines());
        return List.of("Noted. I've removed this task:", "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
