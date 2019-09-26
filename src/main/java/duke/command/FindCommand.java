package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;

import static duke.ui.Message.NO_TASK_FOUND_MESSAGE;

public class FindCommand extends Command {
    public FindCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert super.description != null;
        if (description.length < 2) {
            throw new DukeException("☹ OOPS!!! The keyword cannot be empty.");
        }

        String keyword = description[1];
        return findTasks(tasks, keyword);

    }

    private String findTasks(TaskList tasks, String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task: tasks.getList()) {
            String d = task.getDescription();
            if (d.contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }

        int matchingNumber = matchingTasks.numberOfTasks();
        if (matchingNumber == 0) {
            return NO_TASK_FOUND_MESSAGE;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matchingNumber - 1; i++) {
            sb.append(matchingTasks.getList().get(i));
            sb.append(System.lineSeparator());
        }
        sb.append(matchingTasks.getList().get(matchingNumber - 1));
        return sb.toString();
    }
}
