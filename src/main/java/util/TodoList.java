package util;

import task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TodoList implements Serializable {
    private ArrayList<Task> list;

    TodoList() {
        this.list = new ArrayList<>(100);
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task markAsDone(int index) {
        Task task = list.get(index - 1);
        task.markAsDone();
        return task;
    }

    public Task delete(int index) {
        return list.remove(index - 1);
    }

    public int length() {
        return list.size();
    }

    @Override
    public String toString() {
        return IntStream.rangeClosed(1, list.size())
                        .mapToObj(index -> String.format("%d. %s", index, list.get(index - 1)))
                        .collect(Collectors.joining("\n"));
    }

    public List<Task> find(String description) {
        return  this.list.stream()
                .filter(task -> task.toString().contains(description))
                .collect(Collectors.toList());
    }
}
