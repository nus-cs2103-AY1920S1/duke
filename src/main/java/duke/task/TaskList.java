package duke.task;

import duke.DukeException;
import duke.task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    List<Task> tasks = new ArrayList<>();

    public TaskList() {

    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void markDone(int oneIndex) throws DukeException {
        tasks.get(oneIndex - 1).markDone();
    }

    public Task delete(int oneIndex) {
        return tasks.remove(oneIndex - 1);
    }

    public Task get(int oneIndex) {
        return tasks.get(oneIndex - 1);
    }

    public int size() {
        return tasks.size();
    }

    public List<Integer> findKeywordOneIndices(String keyword, boolean isCaseSensitive) {
        if (!isCaseSensitive) {
            keyword = keyword.toLowerCase();
        }

        List<Integer> foundTaskIndices = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i).toString();
            if (!isCaseSensitive) {
                taskString = taskString.toLowerCase();
            }
            if (taskString.contains(keyword)) {
                foundTaskIndices.add(i+1);
            }
        }
        return foundTaskIndices;
    }

    public List<Task> findKeywordTasks(String keyword, boolean isCaseSensitive) {
        return findKeywordOneIndices(keyword, isCaseSensitive)
                .stream()
                .map(oneIndex -> get(oneIndex))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s = s + String.format("%d. %s\n", (i + 1), tasks.get(i).toString());
        }
        return s;
    }
}
