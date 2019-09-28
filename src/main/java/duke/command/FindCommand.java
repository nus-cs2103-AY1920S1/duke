package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String taskName;
    private static final Commands FIND_COMMAND_TYPE = Commands.FIND;

    /**
     * Constructor.
     * @param taskName - Name of task to search for
     */
    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns Command type of command.
     * @return Command type
     */
    public Commands getCommandType() {
        return FIND_COMMAND_TYPE;
    }

    /**
     * Execute find command on given task and save into taskList.
     * @param taskList - list containing all existing tasks
     */
    @Override
    public String execute(TaskList taskList) {
        List<Task> list = new ArrayList<Task>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName().contains(this.taskName)) {
                list.add(taskList.get(i));
            }
        }

        if (list.isEmpty()) {
            return this.getNoKeywordMessage();
        }

        // If there are several responses found, print them all
        String response = this.getFoundKeywordMessage();
        AtomicInteger index = new AtomicInteger();
        response += list.stream()
                .map(str -> {
                    int idx = index.getAndIncrement();
                    return (idx + 1) + "." + list.get(idx) + "\n";
                })
                .collect(Collectors.joining());
        return response;
    }

    /**
     * Prints out message after tasks are found matching the given keyword.
     * @return successful find message
     */
    private String getFoundKeywordMessage() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Prints out message after no tasks are found matching the given keyword.
     * @return unsuccessful find message
     */
    private String getNoKeywordMessage() {
        return "☹ OOPS!!! There are no matching tasks in the list with the keyword: \""
                + this.taskName + "\".";
    }
}
