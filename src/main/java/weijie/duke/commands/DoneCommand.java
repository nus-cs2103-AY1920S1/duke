package weijie.duke.commands;

import weijie.duke.exceptions.DukeIoException;
import weijie.duke.exceptions.DukeInvalidInputException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;
import weijie.duke.views.Ui;

import java.util.Collections;

public class DoneCommand implements ITaskCommand {
    private IRepository<Task> repo;

    public DoneCommand(IRepository<Task> repo, Ui view) {
        this.repo = repo;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public TaskResponse execute(String... args) {
        if (args.length < 2) {
            return new TaskResponse(
                    new DukeInvalidInputException("☹ OOPS!!! Please input the number of the task to mark as done."));
        }

        int id = Integer.parseInt(args[1]) - 1;

        if (id > repo.getSize()) {
            return new TaskResponse(
                    new DukeInvalidInputException("☹ OOPS!!! Task with that number does not exist!"));
        }
        Task task = repo.get(id);
        task.markAsDone();
        try {
            repo.update(id, task);
        } catch (DukeIoException e) {
            return new TaskResponse(e);
        }

        String responseFormat = "Nice! I've marked this task as done:\n  " + task.getDescription();
        return new TaskResponse(responseFormat, Collections.singletonList(task));
    }
}
