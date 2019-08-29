package main;

import task.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task>{

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> existingTaskList) {
        this.tasks = existingTaskList;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task addTask(Task newTask) {
        tasks.add(newTask);
        return newTask;
    }

    public Task deleteTask(int taskNum) {
        Task removedTask = this.tasks.remove(taskNum-1);
        return removedTask;
    }

    public Task finishTask(int taskNum) {
        Task completedTask = this.tasks.get(taskNum-1);
        completedTask.finishTask();
        return completedTask;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task with the corresponding task ID.
     * @param taskID is the ACTUAL task ID, with index starting from 1
     * @return Task Object with the corresponding task ID.
     */
    public Task getTask(int taskID) {
        return tasks.get(taskID-1);
    }

    public void clearAll() {
        this.tasks.clear();
    }

    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Task> iterator() {

        Iterator<Task> iter = new Iterator<>() {

            private int currIdx = 0;

            @Override
            public boolean hasNext() {
                return currIdx < tasks.size() && tasks.get(currIdx) != null;
            }

            @Override
            public Task next() {
                return tasks.get(currIdx++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iter;
    }
    /*
    Note to self: the out of bounds exception should be caught earlier under the part where you process command!
     */
}
