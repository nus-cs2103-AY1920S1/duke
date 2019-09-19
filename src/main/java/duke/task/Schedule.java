package duke.task;

import duke.extension.InsertionSortComparator;
import duke.parser.FileToTaskParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is the schedule of task items. This supports add, edit, delete operations. When a add, edit or delete
 * operation is to be performed, the <code>TaskManager</code> class provides an abstraction to support these operations.
 * The tasks are insertion into the schedule by the order of the date field for the task date.
 */
public class Schedule {
    /**
     * This is the list of task items. It acts as the schedule
     */
    private List<Task> schedule;

    /**
     * Constructs a new schedule.
     */
    public Schedule() {
        this.schedule = new ArrayList<>();
    }

    /**
     * Inserts the task into the schedule.
     * @param task the task to be inserted into the schedule
     */
    public void add(Task task) {
        assert(task != null);
        schedule.add(task);
        insertionSortSchedule();
    }

    /**
     * Removes the task from the schedule.
     * @param task the task to be deleted from the schedule
     */
    public void delete(Task task) {
        assert(task != null);
        schedule.remove(task);
    }

    /**
     * Views the current schedule for the specified date
     * @param date the specified date for schedule view
     * @return the date for the schedule to be viewed
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
     * Views the schedule.
     * @return a string representation of the schedule of task.
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

    /**
     * Creates a new header to display for view.
     * @param title the title of the header
     * @return a string representation of the header
     */
    private String createNewHeaderToDisplay(String title) {
        StringBuilder outputBuilder = new StringBuilder("    ======================== ");
        outputBuilder.append(title);
        outputBuilder.append("  =======================\n");
        return outputBuilder.toString();
    }

    /**
     * Adds the task to view.
     * @param task the task to be added to view
     * @return a string representation of the task to be added to view
     */
    private String addTaskToView(Task task) {
        assert(task != null);
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ");
        outputBuilder.append(task.showFullInformation());
        outputBuilder.append("\n");
        return outputBuilder.toString();
    }

    /**
     * Runs insertion sort on the schedule.
     */
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
