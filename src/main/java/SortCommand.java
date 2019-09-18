import java.util.Collections;
import java.util.ArrayList;

public class SortCommand extends Command {
    private String sortType;

    public SortCommand(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {

        switch (sortType) {
        case "type":
            Collections.sort(tasks.getTaskList(), Task.typeComparator);
            break;

        case "date":
            sortByDate(tasks);
            break;

        default:
            throw new DukeException("Please specify whether you want to sort by type / date!");
        }

        return "Use 'List' command to view sorted list of tasks!";
    }

    private void sortByDate(TaskList tasks) {
        ArrayList<Task> sorted = new ArrayList<Task>();
        ArrayList<Task> noSort = new ArrayList<Task>();

        for (int i = 0; i< tasks.size(); i++) {
            if (tasks.get(i).type.equals("D") || tasks.get(i).type.equals("E")) {
                sorted.add(tasks.get(i));
            } else {
                noSort.add(tasks.get(i));
            }
        }

        tasks.getTaskList().clear();
        Collections.sort(sorted, Task.dateComparator);
        tasks.getTaskList().addAll(sorted);
        tasks.getTaskList().addAll(noSort);
    }
}
