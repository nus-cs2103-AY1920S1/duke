package duke.command;

import duke.Task;
import duke.TaskList;

public class FindCommand extends Command {
    public FindCommand(String[] parts) {
        super(parts);
    }

    @Override
    public void execute(TaskList tasks) {
        // Find
        System.out.println("Here are the matching tasks in your list:");
        String keyPhrase = parts[1];
        boolean hasMatch = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            int currentItemNumber = 0;
            if (currentTask.getName().contains(keyPhrase)) {
                hasMatch = true;
                currentItemNumber += 1;
                System.out.println(currentItemNumber + "." + currentTask);
            }
        }
        if (!hasMatch) {
            System.out.println("Nothing matches :(");
        }
    }
}
