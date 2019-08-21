package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(int num) {
        this.tasks = new ArrayList<>(num);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public int numTasks() {
        return tasks.size();
    }

    public void markDone(int index) {
        Task doneTask = tasks.get(index - 1);
        doneTask.markDone();
    }

    @Override
    public String toString() {
        int size = tasks.size();
        if(size == 0) {
            return "Empty list: no tasks added";
        } else {
            int index = 1;
            StringBuilder listOfTasks = new StringBuilder();
            for(Task task : tasks) {
                if(index == 1) {
                    listOfTasks.append(
                            String.format("%d.%s", index, task));
                    index++;
                } else {
                    listOfTasks.append(
                            String.format("\n%d.%s", index, task));
                    index++;
                }
            }
            return listOfTasks.toString();
        }
    }
}
