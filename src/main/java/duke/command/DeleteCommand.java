package duke.command;

import java.io.IOException;
import java.util.List;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand implements Command {
    private final TaskList tasks;
    private Storage storage;

    public DeleteCommand(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public List<String> run(String[] words) throws IOException {
        Task task = tasks.remove(Integer.parseInt(words[1]) - 1);
        storage.store(tasks.getAsLines());
        return List.of("Noted. I've removed this task:", "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
