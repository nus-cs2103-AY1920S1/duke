package duke.command;

import duke.Task;
import duke.TaskList;

public class ListCommand extends Command {
    public ListCommand(String[] parts) {
        super(parts);
    }

    @Override
    public void execute(TaskList tasks) {
        // List
        System.out.println("Here are the tasks in your list:");
        // Output current items in list
        for (int i = 0; i < tasks.size(); i++) {
            int currentItemNumber = i + 1;
            Task currentTask = tasks.get(i);
            System.out.println(currentItemNumber + "." + currentTask);
        }
    }
}
