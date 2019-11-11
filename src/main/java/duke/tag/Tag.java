package duke.tag;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    String name;
    List<Task> tasks = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {

    }

    @Override
    public String toString() {
        return "Tag: " + getName();
    }
}
