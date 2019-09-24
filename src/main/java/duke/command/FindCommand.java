package duke.command;

import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    protected String searchPhrase;

    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder("Here are the matching task(s) in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int k = i + 1;
            if (task.description.toLowerCase().contains(searchPhrase.toLowerCase())) {
                if (i > 0) {
                    sb.append("\n");
                }
                sb.append(k);
                sb.append(". ");
                sb.append(task);
            }
        }
        response = sb.toString();
    }
}
