package seedu.duke.commands;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    protected void execute() {
        TaskList matchingTasks = new TaskList();
        String reply = "Here are the matching tasks in your list:\n\t ";
        for (Task task: taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        for (int i = 1; i <= matchingTasks.size(); i++) {
            reply += i + "." + matchingTasks.get(i - 1);
            if (i < matchingTasks.size()) {
                reply += "\n\t ";
            }
        }
        ui.printReply(reply);
    }
}
