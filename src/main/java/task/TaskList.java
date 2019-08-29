package task;

import misc.Storage;
import exception.DukeException;
import misc.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public boolean hasTask() {
        return tasks.size() != 0 ;
    }

    public int size() {
        return tasks.size();
    }

    public void markTaskAsDone(int taskNumber) throws DukeException, IOException {
        if (tasks.get(taskNumber).isDone) {
            String errorMessage = Ui.spaces(5) + "task.Task has already been marked done!";
            throw new DukeException(errorMessage);
        }
        
        storage.overwriteLocalSave(tasks);

        tasks.get(taskNumber).isDone = true;
        System.out.println(Ui.spaces(5) + "Nice! I've marked this task as done:\n"
                + Ui.spaces(5) + tasks.get(taskNumber));
    }

    public void listAllTasks() throws DukeException {
        if (tasks.size() == 0) {
            String errorMessage = Ui.spaces(5) + "There are no task(s) to list!";
            throw new DukeException(errorMessage);
        }

        System.out.println(Ui.spaces(5) + "Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%s%d. %s\n", Ui.spaces(7), i + 1, tasks.get(i));
        }
    }

    public void makeNewTask(String description, String dateTime, String taskType) throws IOException {
        Task task;

        switch (taskType) {
            case "event":
                task = new Event(description, dateTime);
                break;
            case "deadline":
                task = new Deadline(description, dateTime);
                break;
            case "todo":
                task = new ToDo(description);
                break;
            default:
                task = null;
                break;
        }

        tasks.add(task);

        storage.addToLocalSave(task);

        System.out.printf("%sGot it! I've added this task for you \uD83D\uDE09\n", Ui.spaces(5));
        System.out.printf("%s%s\n\n", Ui.spaces(7), task);
        System.out.printf("%sNow you have %d task(s) in your list.\n", Ui.spaces(5), tasks.size());
    }

    public void delete(int taskNumber) throws IOException {
        Task removedTask = tasks.remove(taskNumber);
        
        storage.overwriteLocalSave(tasks);

        System.out.println(Ui.spaces(5) + "Noted. I've removed this task:\n"
                + Ui.spaces(7) + removedTask + "\n\n"
                + Ui.spaces(5) + String.format("Now you have %d task(s) in your list.", tasks.size()));
    }
}
