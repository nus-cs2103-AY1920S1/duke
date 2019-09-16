package duke.task;

import duke.extension.InsertionSortComparator;
import duke.parser.FileToTaskParser;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This is the list of task items. This supports add, edit, delete operations. When a add, edit or delete operation is
 * to be performed, the <code>TaskManager</code> class provides an abstraction to support these operations.
 */
public class Schedule {

    /**
     * This is the list of task items.
     */

    private List<Task> todoTaskList;
    private List<Task> schedule;

    /**
     * Constructs a new list of tasks.
     */
    public Schedule() {
        this.schedule = new ArrayList();
        this.todoTaskList = new ArrayList();
    }

    /**
     * Constructs a new list of tasks from a stream of line. This stream of lines will be parsed by
     * {@link FileToTaskParser} to each individual tasks that will be added individually to form a list of tasks.
     * @param lines the stream of lines to be parsed.
     */

    /**
     * Appends the task to the end of the list of the tasks.
     * @param task the task to be appended to the list
     */
    public void add(Task task) {

            schedule.add(task);
            Comparator<Task> comparator = new InsertionSortComparator();
            for (int i = schedule.size() - 1; i > 0; i--) {
                int prevIndex = i - 1;
                int currIndex = i;

                Task prev = schedule.get(prevIndex);
                Task curr = schedule.get(currIndex);

                if (comparator.compare(prev, curr) > 0) {
                    Collections.swap(schedule, prevIndex, currIndex);
                } else {
                    break;
                }
            }

    }

    /**
     * Removes the task at the specified index from the list of tasks.
     * @param index the index of the task to be removed
     * @return a string representation of the task removed to be printed on a user interface.
     */
    public void delete(Task task) {

            schedule.remove(task);

    }

    /**
     * Returns the number of tasks in the list.
     * @return the number of tasks in the list.
     */

    /**
     * Returns a string representation of the list of tasks.
     * @return a string representation of the list of tasks
     */
    public String list() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ========================== TODO ==========================\n");
        String date = "";
        for(int i = 0; i <  schedule.size(); i++) {
            String curr = schedule.get(i).displayTaskDateIfPresent();
            if(date.equals(curr)) {
                outputBuilder.append("    ");
                outputBuilder.append(schedule.get(i).showFullInformation());
                outputBuilder.append("\n");
            } else {
                date = curr;
                outputBuilder.append("    ======================== ");
                outputBuilder.append(date);
                outputBuilder.append("  =======================\n");
                outputBuilder.append("    ");
                outputBuilder.append(schedule.get(i).showFullInformation());
                outputBuilder.append("\n");
            }
        }
        outputBuilder.append("    ==========================================================");
        return outputBuilder.toString();
    }
}
