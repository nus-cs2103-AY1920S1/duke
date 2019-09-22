package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.EmptyFindArgException;
import seedu.duke.task.Task;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public FindCommand(String arg, TaskList taskList) {
        super(arg, taskList);
    }

    @Override
    public String execute() throws DukeException {
        return find(arg, taskList);
    }

    /**
     * Finds and lists the tasks containing {@code keyword}.
     * @param keyword The keyword to find.
     * @return The response.
     */
    String find(String keyword, TaskList taskList) throws DukeException {
        assert keyword != null;
        if (keyword.isEmpty()) {
            throw new EmptyFindArgException();
        }
        List<Task> list = taskList
                .stream()
                .filter(x -> x.getDescription().contains(keyword))
                .collect(Collectors.toList());
        TaskList matchTaskList = new TaskList(null);
        matchTaskList.addAll(list);
        return "Here are the matching tasks in your list:\n"
                + matchTaskList.toString() + "\n";
    }
}
