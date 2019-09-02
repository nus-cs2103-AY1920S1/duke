package duke.component;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a TaskList object that stores all task in a list.
 * The 'TaskList' class supports operators (i) adding a task to list,
 * (ii) deleting a task from list,
 * (iii) getting a specific task from list,
 * (iv) finding size of list, and
 * (v) marking a task in list as done.
 */
public class TaskList {

    /**
     * List of tasks.
     */
    private ArrayList<Task> todoList;

    /**
     * Initialises a new TaskList object with existing ArrayList of Tasks.
     *
     * @param todoList ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> todoList) {

        this.todoList = todoList;
    }

    /**
     * Initialises a new TaskList object when there is
     * no existing list of Tasks.
     */
    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    /**
     * Adds a new task to list of tasks.
     *
     * @param newTask Task to be added.
     */
    public void addTask(Task newTask) {
        todoList.add(newTask);
    }

    /**
     * Deletes task from list of tasks.
     *
     * @param index Task number of task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {

        return todoList.remove(index - 1);
    }


    /**
     * Gets specific task from list of task.
     *
     * @param taskNum Task number of task that user wants to get.
     * @return Task that is gotten from list of tasks.
     */
    public Task getTask(int taskNum) {
        return this.todoList.get(taskNum - 1);
    }

    /**
     * Returns number of tasks in list of task.
     *
     * @return Number of task from list of task.
     */
    public int size() {
        return this.todoList.size();
    }

    /**
     * Marks a specific task in list of task as done.
     *
     * @param taskNum Task number of task that user wants to mark as done.
     */
    public void markTaskDone(int taskNum) {
        Task updatedTask = this.getTask(taskNum);
        updatedTask.markAsDone();
    }

    /**
     * Finds a specific task from list of task.
     *
     * @param keyword Keyword to find tasks that matches keyword.
     * @return List of string representation of task.
     */
    public List<String> findTask(String keyword) {

        return this.todoList
                .stream()
                .map(x -> x.toString())
                .filter(y -> y.contains(keyword))
                .collect(Collectors.toList());


    }
}






