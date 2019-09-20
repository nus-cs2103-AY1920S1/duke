package seedu.duke;

import java.util.ArrayList;

/** Class for find commands. */
public class FindCommand extends Command {
    protected static String toFind;

    /** Constructor. */
    public FindCommand(String str) {
        toFind = str;
    }

    /**
     * Finds all items containing the keyword as inputted by users.
     * @param t TaskList.
     * @param u Ui.
     * @param s Storage.
     * @return a String containing the find message.
     */
    @Override
    public ChatDisplay execute(TaskList t, Ui u, Storage s) {
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
