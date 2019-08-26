package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(String s) throws DukeException {
        String[] lines = s.split("\n");
        list = new ArrayList<>();
        for (String line : lines) {
            addTask(Task.fromImportFormat(line));
        }
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }

    public Task getTask(int taskNum) throws DukeException {
        try {
            return list.get(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds.");
        }
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int taskNum) throws DukeException {
        try {
            list.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds.");
        }
    }

    @Override
    public String toString() {
        StringBuilder myBuilder = new StringBuilder();
        for (int i = 1; i <= list.size(); i++) {
            Task myTask = list.get(i - 1);
            myBuilder.append(i + "." + myTask);
            if (i < list.size()) {
                myBuilder.append("\n");
            }
        }
        return myBuilder.toString();
    }
}
