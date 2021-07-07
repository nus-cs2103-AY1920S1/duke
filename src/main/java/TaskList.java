import tasks.Task;

import java.util.ArrayList;

/**
 * Encapsulates the TaskList object that is responsible of maintaining a temporary list of existing
 * task. The list of task can be subjected to edits.
 */
public class TaskList {
    ArrayList<Task> taskStorage;

    /**
     * Constructs the TaskList object. A list of task can be fed to this constructor to build a list
     * of existing tasks.
     *
     * @param listOfTask A list of tasks is going to be maintained by TaskList.
     */
    public TaskList(ArrayList<Task> listOfTask) {
        this.taskStorage = listOfTask;
    }

    /**
     * Provides the Task at the position specified.
     *
     * @param pos Indicates the position to get.
     * @return The Task that is held at the position specified in the TaksList.
     */
    public Task get(int pos) {
        return taskStorage.get(pos);
    }

    /**
     * Provides the entire TaskList in the form of an ArrayList.
     *
     * @return The list of existing tasks currently held in the TaskList.
     */
    public ArrayList<Task> get() {
        return taskStorage;
    }

    /**
     * Provides the number of Tasks that are held in the TaskList.
     *
     * @return The number of existing tasks in the TaskList.
     */
    public int size() {
        return taskStorage.size();
    }

    /**
     * Adds a Task to the existing list of tasks.
     *
     * @param task The task that will be added to the existing TaskList.
     */
    public void add(Task task) {
        taskStorage.add(task);
    }

    /**
     * Removes task from the list.
     *
     * @param index The index of task that the user wishes to delete.
     */
    public void removeTask(int index) {
        taskStorage.remove(index);
    }

    public String keywordSearch(String input) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task task : taskStorage) {
            String description = task.getDescription();
            String[] subject = description.split(" ");

            for (String word : subject) {
                if (word.contains(input)) {
                    result.add(task);
                }
            }
        }

        Ui ui = new Ui();
        if (result.size() > 0) {
            return ui.announceMatchingTask(result);
        }
        return ui.announceNoneMatchingTask();
    }

    /**
     * Operation that checks if a task is same as any of the existing
     * tasks stored in the list.
     * @param task task subject.
     * @return true when there is a same task that exist, false when the task
     * is new and can be added.
     */
    public Boolean checkForSameTask(Task task) {
        for (Task t : taskStorage) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clears the list of tasks. The action is non-reversible.
     */
    public void clear() {
        taskStorage.clear();
    }
}
