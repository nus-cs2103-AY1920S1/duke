package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> ls;

    public TaskList() {
        this.ls = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> load) {
        this.ls = load;
    }

    public void add(Task t) {
        ls.add(t);
    }

    /**
     * Deleted task is return to DeleteCommand for printing purposes.
     * @param index
     * @return task in ArrayList ls that was removed.
     */
    //@@author CarbonGrid
    public Task delete(int index) {
        Task t = ls.remove(index - 1);
        return t;
    }

    public int size() {
        return ls.size();
    }

    String generateListForFile() {
        StringBuilder sb = new StringBuilder();
        for (Task task : ls) {
            sb.append(task.getTaskTypeLetter()).append(" | ").append(task.getStatusIcon()).append(" | ")
                    .append(task.getDescription()).append("\n");
        }
        return sb.toString();
    }

    public Task setDone(int index) {
        Task t = ls.get(index - 1);
        t.markIsDone();
        return t;
    }

    //@@Adapted from author: CarbonGrid (not sure why todo tasks do not give "\n"
    public String toUiString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int i = 1;
        Iterator<Task> it = ls.iterator();
        String temp;
        while (it.hasNext()) {
            sb.append("      ")
                    .append(i++)
                    .append(".");
            temp = it.next().toString();
            temp = temp.replaceAll("\n", "");
            sb.append(temp);
            sb.append("\n");
        }
        return sb.toString();
    }
    //@@author

}
