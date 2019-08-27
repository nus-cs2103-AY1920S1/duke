import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class representing a list of <code>Tasks</code>
 */
public class TaskList implements Serializable {
    private static final long serialVersionUID = 163417L;
    
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * @param task The <code>Task</code> to be added to this <code>TaskList</code>
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * @param index The index at which a <code>Task</code> in the list will be deleted
     * @return The deleted <code>Task</code>
     * @throws DukeException If the chosen index does not exist in the list
     */
    public Task deleteAt(int index) throws DukeException {
        int realIndex = index - 1;

        checkIndexExists(realIndex);

        Task deletedTask = tasks.get(realIndex);
        tasks.remove(realIndex);
        return deletedTask;
    }

    /**
     * Deletes all the elements in this list, leaving it empty.
     */
    public void deleteAllTasks() {
        tasks = new ArrayList<Task>();
    }
    
    /**
     * @param index The index at which a <code>Task</code> in the list will be marked as done
     * @return The <code>Task</code> marked as done
     * @throws DukeException If the chosen index does not exist in the list
     */
    public Task markAsDone(int index) throws DukeException {
        int realIndex = index - 1;

        checkIndexExists(realIndex);

        tasks.set(realIndex, tasks.get(realIndex).getTaskMarkedAsDone());
        return tasks.get(realIndex);
    }

    /**
     * @return <code>true</code> if the list is empty and <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * @return The number of elements in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * @param searchTerm The term to match for in the description
     * @return The <code>String</code> representation of a list of tasks whose description contains the 
     * <code>searchTerm</code>
     */
    public String getMatchingTasksAsString (String searchTerm) {
        StringBuilder sb = new StringBuilder();
        int iterator = 1;

        for(Task t : tasks) {
            //For each Task t, print out one line of "X.[<Status>] Description", if it matches the search term
            if(t.descriptionContainsTerm(searchTerm)) {
                sb.append(String.format("%d.%s\n", iterator++, t.toString()));
            }
        }

        //If there's no matches found, feedback to the user
        String retval = sb.toString();
        if(retval.isEmpty()) {
            retval = DukeUi.FEEDBACK_FIND_NOTHING;
        }

        return retval;
    }

    /**
     * @return The <code>String</code> representation of this <code>TaskList</code>, containing all tasks in order of
     * addition marked by its position in the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iterator = 1;

        for (Task t : tasks) {
            //For each Task t, print out one line of "X.[<Status>] Description"
            sb.append(String.format("%d.%s\n", iterator++, t.toString()));
        }

        return sb.toString();
    }

    private void checkIndexExists(int realIndex) throws DukeException {
        if(realIndex < 0) {
            throw new DukeException(DukeTextFormatter.makeFormattedText(DukeUi.ERROR_LIST_INDEX_SMALL));
        }

        if(realIndex >= tasks.size()) {
            throw new DukeException(DukeTextFormatter.makeFormattedText(
                String.format(DukeUi.ERROR_LIST_INDEX_BIG, realIndex + 1)));
        }
    }
}
