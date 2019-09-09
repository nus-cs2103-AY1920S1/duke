package seedu.duke;

import java.util.ArrayList;

public class FindCommand extends Command {
    protected static String toFind;

    public FindCommand(String str) {
        toFind = str;
    }
    @Override
    public String execute(TaskList t, Ui u, Storage s) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task: t.list) {
            String title = task.description;
            if (title.contains(toFind)) {
                list.add(task);
            }
        }
        assert list.size() <= t.list.size(): "List of found items should be less than or equal to the total list";
        return ui.listMatching(list);
    }
}
