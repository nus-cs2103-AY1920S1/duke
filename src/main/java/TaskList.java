import java.util.ArrayList;
import java.io.Serializable;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks = new ArrayList<>();

    //Adds a task to the end of the TaskList
    public void add(Task task) {
        tasks.add(task);
    }

    //Deletes the task at the specified index by decreasing the index of all subsequent entries by 1.
    public Task deleteAt(int index) throws DukeException {
        int realIndex = index - 1;

        if(realIndex < 0) {
            throw new DukeException(DukeTextFormatter.makeFormattedText(DukeUi.ERROR_LIST_INDEX_SMALL));
        }

        if(realIndex >= tasks.size()) {
            String exceptionMessage
                = DukeTextFormatter.makeFormattedText(String.format(DukeUi.ERROR_LIST_INDEX_BIG, index));
            throw new DukeException(exceptionMessage);
        }

        Task deletedTask = tasks.get(realIndex);
        tasks.remove(realIndex);
        return deletedTask;
    }

    //Deletes all the tasks in the TaskList, leaving it empty
    public void deleteAllTasks() {
        tasks = new ArrayList<Task>();
    }
    
    //Replaces the task at the specified index with a clone marked as done
    public Task markAsDone(int index) throws DukeException {
        int realIndex = index - 1;

        if(realIndex < 0) {
            throw new DukeException(DukeTextFormatter.makeFormattedText(DukeUi.ERROR_LIST_INDEX_SMALL));
        }

        if(realIndex >= tasks.size()) {
            String exceptionMessage
                = DukeTextFormatter.makeFormattedText(String.format(DukeUi.ERROR_LIST_INDEX_BIG, index));
            throw new DukeException(exceptionMessage);
        }

        tasks.set(realIndex, tasks.get(realIndex).getTaskMarkedAsDone());
        return tasks.get(realIndex);
    }

    //Returns whether or not the TaskList is empty
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    //Returns the number of elements in the TaskList
    public int size() {
        return tasks.size();
    }

    @Override
    //Converts the TaskList into a human-readable String
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iterator = 1;

        for (Task t : tasks) {
            //For each Task t, print out one line of "X.[<Status>] Description"
            sb.append(String.format("%d.%s\n", iterator++, t.toString()));
        }

        return sb.toString();
    }
}
