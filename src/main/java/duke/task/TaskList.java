package duke.task;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasksWithKeywords(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasKeywordsInDescription(keyword)) {
                foundTasks.add(tasks.get(i));
            }
        }
        return foundTasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task setIsDone(int index) throws DukeException {
        try {
            tasks.get(index).isDone = true;
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! There is no task number " + (index + 1));
        }

    }

    public Task removeTask(int index) throws DukeException {
        try {
            return tasks.remove(index);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! There is no task number " + (index + 1));
        }

    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
