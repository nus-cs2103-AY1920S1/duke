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

    /**
     * Constructs a TaskList with an empty ArrayList of tasks and Storage.
     * @param storage The storage.
     */
    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
        temporarySearchList = null;
    }

    /**
     * Constructs a TaskList with the specified ArrayList of tasks and Storage.
     * @param tasks The ArrayList of tasks.
     * @param storage The Storage.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        temporarySearchList = null;
    }

    /**
     * Indicates if there are existing tasks in the task list.
     * @return A boolean.
     */
    public boolean hasTask() {
        return tasks.size() != 0;
    }

    /**
     * Marks a task to be done upon user request.
     * @param taskNumber The position of the task to be marked "done" in the ArrayList.
     * @param useTempSearchList A boolean that determines if a task is to be marked done
     *     off the original or search result list.
     * @throws DukeException if the task has already been marked done.
     * @throws IOException if the completion status of the task cannot be updated in a local save file.
     */
    public String markTaskAsDone(int taskNumber, boolean useTempSearchList) throws DukeException, IOException {
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

        if (errorMessage != null) {
            throw new DukeException(errorMessage);
        }

        if (taskListToUse.get(taskNumber - 1).isDone) {
            errorMessage = "Task has already been marked done!";
            throw new DukeException(errorMessage);
        }

        taskListToUse.get(taskNumber - 1).isDone = true;
        storage.overwriteLocalSave(tasks);
        temporarySearchList = null;

        return ("Nice! I've marked this task as done:\n" + taskListToUse.get(taskNumber - 1));
    }

    /**
     * Lists all tasks upon user request.
     * @param type A boolean that determines if tasks are listed from the original or search result list.
     * @throws DukeException if there are no task to list.
     */
    public String listAllTasks(String type) throws DukeException {
        if (tasks.size() == 0) {
            String errorMessage = "There are no task(s) to list!";
            throw new DukeException(errorMessage);
        }

        ArrayList<Task> taskListToUse;
        StringBuilder output = new StringBuilder();

        if (type.equals("list")) {
            taskListToUse = tasks;
            output.append("Here are the tasks in your list:\n\n");
        } else if (type.equals("find")) {
            taskListToUse = temporarySearchList;
            output.append("Here are the matching tasks in your list:\n\n");
        } else {
            return null;
        }

        for (int i = 0; i < taskListToUse.size(); i++) {
            output.append(String.format("%d. %s\n", i + 1, taskListToUse.get(i)));
        }

        return output.toString();
    }

    /**
     * Creates a new task with its description and Date/Time (if any).
     * @param description The description of the task to be created.
     * @param dateTime The Date/Time of the task (if any) to be created.
     * @param taskType The type of the task (e.g. Todo, Event etc)
     * @throws IOException if task created cannot be saved to a local save file.
     */
    public String makeNewTask(String description, String dateTime, String taskType) throws IOException {
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

        StringBuilder output = new StringBuilder();

        output.append("Got it! I've added this task for you \uD83D\uDE09\n");
        output.append(String.format("%s\n\n", task));
        output.append(String.format("Now you have %d task(s) in your list.\n", tasks.size()));

        return output.toString();
    }

    /**
     * Deletes a task upon user request.
     * @param taskNumber The position of the task in the ArrayList to be deleted.
     * @param useTempSearchList A boolean that determines if a task is deleted from a search result list.
     * @throws IOException if a local save file cannot be overwritten to reflect changes in user tasks.
     * @throws IncorrectDukeCommand if the task number given does not fall within the correct range.
     */
    public String delete(int taskNumber, boolean useTempSearchList) throws IOException, IncorrectDukeCommand {
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

        if(taskListToUse == temporarySearchList) {
            tasks.remove(removedTask);
        }
        
        storage.overwriteLocalSave(tasks);
        temporarySearchList = null;

        return ("Noted. I've removed this task:\n"
                + removedTask + "\n\n"
                + String.format("Now you have %d task(s) in your list.", tasks.size()));
    }

    /**
     * Searches matching tasks with user specified keyword.
     * @param keyword The keyword to search for in all tasks.
     * @throws DukeException if the original task list is empty or there are no search results.
     */
    public String find(String keyword) throws DukeException {
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
            return listAllTasks("find");
        }
    }
}
