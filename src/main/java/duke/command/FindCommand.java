package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        String message = "";
        try {
            String target = command.substring(5);
            ArrayList<Task> foundList = new ArrayList<>();

            for (Task t : tasks.getList()) {
                if (t.toString().contains(target)) {
                    foundList.add(t);
                }
            }

            if (foundList.size() > 0) {
                message += ("Here are the matching tasks in your list:" + System.lineSeparator());
                for (int i = 1; i <= foundList.size(); i++) {
                    message += (i + ". " + foundList.get(i - 1) + System.lineSeparator());
                }
            } else {
                message += "No matching tasks.";
            }
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            return ("Error [FindCommand] 0x0000012:" +
                    System.lineSeparator() + "Invalid Operation.");
        }
        return message;
    }
}
