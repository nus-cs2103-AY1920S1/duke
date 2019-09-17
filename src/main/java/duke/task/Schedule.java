package duke.task;

import duke.extension.InsertionSortComparator;
import duke.parser.FileToTaskParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is the list of task items. This supports add, edit, delete operations. When a add, edit or delete operation is
 * to be performed, the <code>TaskManager</code> class provides an abstraction to support these operations.
 */
public class Schedule {

    /**
     * This is the list of task items.
     */

    private List<Task> schedule;

    /**
     * Constructs a new list of tasks.
     */
    public Schedule() {
        this.schedule = new ArrayList<>();
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
        assert(task != null);
        schedule.add(task);
        insertionSortSchedule();
    }

    /**
     * Removes the task at the specified index from the list of tasks.
     * @param index the index of the task to be removed
     * @return a string representation of the task removed to be printed on a user interface.
     */
    public void delete(Task task) {
        assert(task != null);
        schedule.remove(task);
    }

    /**
     * Returns the number of tasks in the list.
     * @return the number of tasks in the list.
     */
    public String view(String date) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(createNewHeaderToDisplay(date));
        outputBuilder.append(schedule.stream()
                             .filter(x -> x.displayTaskDateIfPresent().equals(date))
                             .map(x -> addTaskToView(x))
                             .reduce("", (x, y) -> x + y));
        outputBuilder.append("    ==========================================================");
        return outputBuilder.toString();
    }

    /**
     * Returns a string representation of the list of tasks.
     * @return a string representation of the list of tasks
     */
    public String view() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(createNewHeaderToDisplay("   TODO  "));
        String date = "";
        for (int i = 0; i <  schedule.size(); i++) {
            Task task = schedule.get(i);
            String curr = task.displayTaskDateIfPresent();
            if (date.equals(curr)) {
                outputBuilder.append(addTaskToView(task));
            } else {
                date = curr;
                outputBuilder.append(createNewHeaderToDisplay(date));
                outputBuilder.append(addTaskToView(task));
            }
        }
        outputBuilder.append("    ==========================================================");
        return outputBuilder.toString();
    }

    private String createNewHeaderToDisplay(String title) {
        StringBuilder outputBuilder = new StringBuilder("    ======================== ");
        outputBuilder.append(title);
        outputBuilder.append("  =======================\n");
        return outputBuilder.toString();
    }

    private String addTaskToView(Task task) {
        assert(task != null);
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ");
        outputBuilder.append(task.showFullInformation());
        outputBuilder.append("\n");
        return outputBuilder.toString();
    }

    private void insertionSortSchedule() {
        Comparator<Task> comparator = new InsertionSortComparator();
        for (int i = schedule.size() - 1; i > 0; i--) {
            int prevIndex = i - 1;
            int currIndex = i;

            Task prev = schedule.get(prevIndex);
            Task curr = schedule.get(currIndex);

            if (comparator.compare(prev, curr) <= 0) {
                break;
            }
            Collections.swap(schedule, prevIndex, currIndex);
        }
    }

}
