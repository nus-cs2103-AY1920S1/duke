package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task doTask(String input) throws DukeException {
        if (input.split(" ").length > 1) {
            try {
                int itemIndex = Integer.parseInt(input.split(" ")[1]);
                if (itemIndex > tasks.size() || itemIndex < 1) {
                    throw new DukeException("The task number specified is not within the list.");
                } else {
                    Task currTask = tasks.get(itemIndex - 1);
                    currTask.doTask();
                    return currTask;
                }
            } catch (NumberFormatException e) {
                throw new DukeException("The task specified is not a number.");
            }
        } else {
            throw new DukeException("The task to be done is not specified.");
        }
    }

    public void addTodo(Todo todo) throws DukeException {
        tasks.add(todo);
    }

    public void addDeadline(Deadline deadline) throws DukeException {
        tasks.add(deadline);
    }

    public void addEvent(Event event) throws DukeException {
        tasks.add(event);
    }

    public Task deleteTask(String input) throws DukeException {
        if (input.split(" ").length > 1) {
            try {
                int deleteIndex = Integer.parseInt(input.split(" ")[1]);
                if (deleteIndex > tasks.size() || deleteIndex < 1) {
                    throw new DukeException("The task number specified is not within the list.");
                }
                Task deleted = tasks.remove(deleteIndex - 1);
                return deleted;
            } catch (NumberFormatException e) {
                throw new DukeException("The task specified is not a number.");
            }
        } else {
            throw new DukeException("The task to be deleted is not specified.");
        }
    }
}
