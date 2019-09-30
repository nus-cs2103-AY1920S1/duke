package seedu.duke.commands;

import seedu.duke.priority.Priority;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

public class PriorityCommand extends Command {
    private int taskIndex;
    private Task task;
    private Priority taskPriority;

    public PriorityCommand(String strPriorityLevel, String strTaskIndex) {
        this.taskIndex = Integer.parseInt(strTaskIndex);
        evaluatePriorityLevel(strPriorityLevel);
    }

    private void evaluatePriorityLevel(String strPriorityLevel) {
        switch (strPriorityLevel) {
        case "#1":
            taskPriority =  Priority.HIGH;
            break;
        case "#2":
            taskPriority =  Priority.MEDIUM;
            break;
        case "#3":
            taskPriority =  Priority.LOW;
            break;
        default:
            taskPriority =  null;
        }
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        task = tasks.get(taskIndex - 1);
        task.setPriority(taskPriority);
        return commandReply();
    }

    public String commandReply() {
        String reply = "Alright! Master Duke has set priority " + taskPriority + " for this task: " + task;
        return reply;
    }
}
