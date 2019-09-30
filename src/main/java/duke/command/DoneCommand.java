package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.util.List;
import java.util.function.IntFunction;

public class DoneCommand implements Command {
    private TaskList tasks;
    private Storage storage;
    private IntFunction<List<String>> action = index -> {
        Task task = tasks.get(index);
        task.markAsDone();
        return List.of("Nice! I've marked this task as done:", "  " + task);
    };

    public DoneCommand(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    DoneCommand(TaskList tasks, Storage storage, IntFunction<List<String>> action) {
        this(tasks, storage);
        this.action = action;
    }

    /**
     * Marks a task as done and returns a message about the task.
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
        } else if (tasks.size() == 1 && index != 1) {
            return List.of("Invalid task number. Please use 1 to choose the only task.");
        } else if (index < 0 || index >= tasks.size()) {
            return List.of("Invalid task number. Please use a number from 1 to " + tasks.size() + ".");
        }
        List<String> message = action.apply(index);
        storage.store(tasks.getAsLines());
        return message;
    }
}
