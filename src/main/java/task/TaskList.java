package task;

import exception.DukeException;
import exception.IncorrectDukeCommand;

import java.io.IOException;
import java.util.ArrayList;

import misc.Storage;
import misc.Ui;

/**
 * Represents a TaskList object that can add, delete, list and mark tasks as done upon user request.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    public ArrayList<Task> temporarySearchList;

    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
        temporarySearchList = null;
    }

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        temporarySearchList = null;
    }

    public boolean hasTask() {
        return tasks.size() != 0;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task to be done upon user request.
     * @param taskNumber The position of the task to be marked "done" in the ArrayList.
     * @param useTempSearchList A boolean that determines if a task is to be marked done off the original or search result list.
     * @throws DukeException if the task has already been marked done.
     * @throws IOException if the completion status of the task cannot be updated in a local save file.
     */
    public void markTaskAsDone(int taskNumber, boolean useTempSearchList) throws DukeException, IOException {
        ArrayList<Task> taskListToUse;
        String errorMessage = null;

        if (useTempSearchList) {
            taskListToUse = temporarySearchList;
        } else {
            taskListToUse = tasks;
        }

        if (taskNumber <= 0) {
            errorMessage = "Number cannot be negative!";
        } else if (taskListToUse.size() == 0) {
            errorMessage = "You don't have any tasks yet!";
        } else if (taskNumber > taskListToUse.size()) {
            errorMessage = "You don't have that many tasks!";
        }
        
        if (taskListToUse.get(taskNumber - 1).isDone) {
            errorMessage = Ui.spaces(5) + "task.Task has already been marked done!";
        }

        if (errorMessage != null) {
            throw new DukeException(errorMessage);
        }
        
        taskListToUse.get(taskNumber - 1).isDone = true;
        storage.overwriteLocalSave(tasks);

        System.out.println(Ui.spaces(5) + "Nice! I've marked this task as done:\n"
                + Ui.spaces(7) + taskListToUse.get(taskNumber - 1));
    }

    /**
     * Lists all tasks upon user request.
     * @param type A boolean that determines if tasks are listed from the original or search result list.
     * @throws DukeException if there are no task to list.
     */
    public void listAllTasks(String type) throws DukeException {
        if (tasks.size() == 0) {
            String errorMessage = Ui.spaces(5) + "There are no task(s) to list!";
            throw new DukeException(errorMessage);
        }

        ArrayList<Task> taskListToUse;

        if (type.equals("list")) {
            taskListToUse = tasks;
            System.out.println(Ui.spaces(5) + "Here are the tasks in your list:");
        } else if (type.equals("find")) {
            taskListToUse = temporarySearchList;
            System.out.println(Ui.spaces(5) + "Here are the matching tasks in your list:");
        } else {
            return;
        }

        for (int i = 0; i < taskListToUse.size(); i++) {
            System.out.printf("%s%d. %s\n", Ui.spaces(7), i + 1, taskListToUse.get(i));
        }
    }

    /**
     * Creates a new task with its description and Date/Time (if any).
     * @param description The description of the task to be created.
     * @param dateTime The Date/Time of the task (if any) to be created.
     * @param taskType The type of the task (e.g. Todo, Event etc)
     * @throws IOException if task created cannot be saved to a local save file.
     */
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

    /**
     * Deletes a task upon user request.
     * @param taskNumber The position of the task in the ArrayList to be deleted.
     * @param useTempSearchList A boolean that determines if a task is deleted from a search result list.
     * @throws IOException if a local save file cannot be overwritten to reflect changes in user tasks.
     * @throws IncorrectDukeCommand if the task number given does not fall within the correct range.
     */
    public void delete(int taskNumber, boolean useTempSearchList) throws IOException, IncorrectDukeCommand {
        String errorMessage;
        ArrayList<Task> taskListToUse;

        if (useTempSearchList) {
            taskListToUse = temporarySearchList;
        } else {
            taskListToUse = tasks;
        }

        if (taskNumber <= 0) {
            errorMessage = "Number cannot be negative!";
        } else if (taskListToUse.size() == 0) {
            errorMessage = "You don't have any tasks yet!";
        } else if (taskNumber > taskListToUse.size()) {
            errorMessage = "You don't have that many tasks!";
        } else {
            errorMessage = null;
        }

        if (errorMessage != null) {
            throw new IncorrectDukeCommand(errorMessage);
        }
        
        Task removedTask = taskListToUse.remove(taskNumber - 1);
        
        storage.overwriteLocalSave(tasks);

        System.out.println(Ui.spaces(5) + "Noted. I've removed this task:\n"
                + Ui.spaces(7) + removedTask + "\n\n"
                + Ui.spaces(5) + String.format("Now you have %d task(s) in your list.", tasks.size()));
    }

    public void find(String keyword) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("There is nothing to search from!");
        }
        
        ArrayList<Task> tempList = new ArrayList<>();

        for (Task t : tasks) {
            if (t.toString().contains(keyword)) {
                tempList.add(t);
            }
        }

        if (tempList.isEmpty()) {
            throw new DukeException("There isn't a task that can be found with that keyword!");
        } else {
            temporarySearchList = tempList;
            listAllTasks("find");
        }
    }
}
