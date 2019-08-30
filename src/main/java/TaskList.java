package duke.component;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private Storage storage;
    private ArrayList<Task> todoList;

    public TaskList(ArrayList<Task> todoList) {

        this.todoList = todoList;
    }

    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        todoList.add(newTask);
    }


    public Task deleteTask(int index) {

        return todoList.remove(index - 1);
    }


    public Task getTask(int taskNum) {
        return this.todoList.get(taskNum - 1);
    }


    public int size() {
        return this.todoList.size();
    }

    public void markTaskDone(int taskNum) {
        Task updatedTask = this.getTask(taskNum);
        updatedTask.markAsDone();
    }
}






