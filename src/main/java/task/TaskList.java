package task;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     A singleton that helps to manage the list of task and provides
 *     various operations on tasks.
 * </p>
 */
public class TaskList {
    private static List<Task> tasks;

    private TaskList() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * <p>
     *     Returns a new TaskList object that holds a reference to the
     *     working list.
     * </p>
     *
     * @return an instance of a TaskList object
     */
    public static TaskList newInstance() {
        return new TaskList();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void clear() {
        tasks.clear();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task addNewTodoTask(String taskName, boolean isDone) {
        Task newTask = new Todo(taskName);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    public Task addNewEventTask(String taskName, String additionalInfo, boolean isDone) {
        Task newTask = new Event(taskName, additionalInfo);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    public Task addNewDeadlineTask(String taskName, String additionalInfo, boolean isDone) {

        Task newTask = new Deadline(taskName, additionalInfo);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    public Task deleteTask(int idx) {
        Task task = tasks.remove(idx);
        return task;
    }

    public void markAsDone(int idx) {
        tasks.get(idx).setDone();
    }

    void markAsNotDone(int idx) {
        tasks.get(idx).setNotDone();
    }

    @Override
    public boolean equals(Object obj) {
        return tasks.equals(((TaskList)obj).getTasks());
    }

    public List<Task> generateListByKeyword(String keyword) {
        List<Task> findResult = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().contains(keyword) ||
                    tasks.get(i).getAdditionalInfo().contains(keyword)) {
                findResult.add(tasks.get(i));
            }
        }
        return findResult;
    }
}
