package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class FindCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String command) {
        try {
            String target = command.substring(5);
            ArrayList<Task> foundList = new ArrayList<>();
            for (Task t : tasks.getList()) {
                if (t.toString().contains(target)) {
                    foundList.add(t);
                }
            }

            if (foundList.size() > 0) {
                System.out.println("Here are the matching tasks in your list: " + System.lineSeparator());
                for (int i = 1; i <= foundList.size(); i++) {
                    System.out.println(i + ". " + foundList.get(i - 1));
                }
            } else {
                System.out.println("No matching tasks.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("Error [FindCommand] 0x0000012: Invalid Operation.");
        }
    }
}
