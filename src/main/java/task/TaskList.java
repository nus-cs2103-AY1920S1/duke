package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> tasks;

    private TaskList() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

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
}
