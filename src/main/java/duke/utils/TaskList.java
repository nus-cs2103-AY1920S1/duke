package duke.utils;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public Task addToDo(String taskDescription) {
        Task t = new ToDo(taskDescription);
        this.allTasks.add(t);
        return t;
    }

    public Task addDeadline(String taskDescription, String deadline) throws DukeException {
        Task t = new Deadline(taskDescription, deadline);
        this.allTasks.add(t);
        return t;
    }

    public Task addEvent(String taskDescription, String startTime, String endTime) throws DukeException {
        Task t = new Event(taskDescription, startTime, endTime);
        this.allTasks.add(t);
        return t;
    }

    public Task deleteTask(int taskNum) throws DukeException {
        //Returns removed Task
        try {
            Task t = this.allTasks.get(taskNum - 1);
            this.allTasks.remove(taskNum - 1);

            return t;
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task number is invalid!");
        }
    }

    public Task completeTask(int taskNum) throws DukeException{
        try {
            Task t = this.allTasks.get(taskNum - 1); //Because storedTasks is zero-indexed
            t.markAsDone();
            return t;
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task number is invalid!");
        }
    }

    public int size() {
        return this.allTasks.size();
    }

    public ArrayList<Task> getArrayList() {
        return this.allTasks;
    }
}
