package duke.command;

import java.io.IOException;
import java.util.List;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand implements Command {
    private final TaskList tasks;
    private Storage storage;

    public DoneCommand(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public List<String> run(String[] words) throws IOException {
        Task task = tasks.get(Integer.parseInt(words[1]) - 1);
        task.markAsDone();
        storage.store(tasks.getAsLines());
        return List.of("Nice! I've marked this task as done:", "  " + task);
    }
}
