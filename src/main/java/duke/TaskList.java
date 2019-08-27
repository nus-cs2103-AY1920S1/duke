package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(String s) throws DukeException {
        String[] lines = s.split("\n");
        tasks = new ArrayList<>();
        for (String line : lines) {
            addTask(Task.fromImportFormat(line));
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public Task getTask(int taskNum) throws DukeException {
        try {
            return tasks.get(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds.");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNum) throws DukeException {
        try {
            tasks.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds.");
        }
    }

    @Override
    public String toString() {
        StringBuilder myBuilder = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task myTask = tasks.get(i - 1);
            myBuilder.append(i + "." + myTask);
            if (i < tasks.size()) {
                myBuilder.append("\n");
            }
        }
        return myBuilder.toString();
    }
}
