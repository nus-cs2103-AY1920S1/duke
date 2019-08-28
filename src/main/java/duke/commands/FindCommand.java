package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.Arrays;

public class FindCommand extends Command{
    private static final String MISSING_ARGUMENTS = "The find command needs at least an argument.";
    private static final String RESULT_PREPEND = "Here are the matching tasks in your list:";
    private static final String NO_MATCHES = "No matching tasks found...";

    private String searchString;

    public static Command create(String input, String[] args) throws DukeException {
         if(args.length == 1) {
             throw new DukeException(MISSING_ARGUMENTS);
         }
         String searchString = input.split(" ", 2)[1];
         return new FindCommand(searchString);
    }

    private FindCommand(String searchString) {
        super();
        this.searchString = searchString;
    }

    public void execute(Storage storage, Ui ui, TaskList tasklist) throws DukeException {
        ArrayList<Task> matchingTasks = tasklist.getMatchingTasks(searchString);
        int size = matchingTasks.size();
        if(size == 0) {
            throw new DukeException(NO_MATCHES);
        } else {
            int index = 1;
            StringBuilder sb = new StringBuilder();
            sb.append(RESULT_PREPEND);
            sb.append("\n");
            for(Task task : matchingTasks) {
                if(index == 1) {
                    sb.append(
                            String.format("%d.%s", index, task));
                    index++;
                } else {
                    sb.append(
                            String.format("\n%d.%s", index, task));
                    index++;
                }
            }
            ui.say(sb.toString());
        }
    }
}
