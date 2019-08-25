package duke.task;

import java.util.ArrayList;
import duke.exception.*;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int addTodoTask(String toDoData) {
        ToDoTask newTask = new ToDoTask(toDoData);
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    public int addDeadlineTask(String taskName, String taskDate) {
        DeadlineTask newTask = new DeadlineTask(taskName, taskDate);
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    public int addEventTask(String taskName, String taskDate){
        EventTask newTask = new EventTask(taskName, taskDate);
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    public void completeTask(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            task.isCompleted= true;
        }
    }

    public Task deleteTask(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            return task;
        }
    }

    public ArrayList<Integer> findMatchingTasks(String keyword) {
        ArrayList<Integer> matchingIndexes = new ArrayList<>();
        for (int i = 0; i < getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            if (task.todo.contains(keyword)) {
                matchingIndexes.add(i);
            }
        }

        return matchingIndexes;
    }

    public static int getNumberOfTasks() {
        return tasks.size();
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }
}
