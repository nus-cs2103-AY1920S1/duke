package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(this.keyword);
        if (matchingTasks.isEmpty()) {
            System.out.println("There are no tasks matching your query :(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            matchingTasks.printList();
        }
    }
}
