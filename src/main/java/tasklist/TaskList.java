package tasklist;

import notes.Note;
import ui.TextUi;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents an entire tasklist and contains methods to edit and display the list.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private TextUi ui;
    private ArrayList<Task> uiTaskList;
    private ArrayList<Note> uiNoteList;

    /**
     * Constructor for tasklist that initializes all the variables.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
        ui = new TextUi();
        uiNoteList = new ArrayList<Note>();
        uiTaskList = new ArrayList<>();
    }

    /**
     * prints the ui after adding a new task.
     */
    public void printNewTask() {
        ui.printAddedItem(tasks.get(tasks.size() - 1).getOverallStatus(), tasks.size());
    }

    /**
     * prints the ui for listing tasks.
     */
    public void listTasks() {
        ui.printItemList();
        resetUiTasks();
    }

    public void shownotes(Integer tasknum) {
        uiNoteList = tasks.get(tasknum).getNotes();
        ui.printNoteList(tasks.get(tasknum).getSourceName());
    }

    /**
     * Changes the completion status of the task.
     * @param index tells the method which task has been completed
     */
    public void completeTask(int index) {
        tasks.get(index).completeTask();
        ui.printCompletedTask(tasks.get(index).getOverallStatus());
        resetUiTasks();
    }

    /**
     * Method to add a task to the tasklist according to the correct type.
     * @param taskType determines type of task to add
     * @param description description of the task
     * @param completionStatus sets the completion status of the task
     * @param date sets the date of the task
     */
    public void addTask(String taskType, String description, boolean completionStatus, LocalDateTime date) {
        if (!description.isEmpty()) {
            int index = tasks.size() + 1;
            switch (taskType) {
            case "todo":
                tasks.add(new Todo(index, description, completionStatus, date));
                break;
            case "deadline":
                tasks.add(new Deadline(index, description, completionStatus, date));
                break;
            case "event":
                tasks.add(new Event(index, description, completionStatus, date));
                break;
            case "notebook":
                tasks.add(new Notebook(index, description, completionStatus, date));
                break;
            default:
                // not necessary as tasktype can only be the above 3
            }
            resetUiTasks();
        } else {
            ui.printDescriptionError();
            clearUI();
        }
    }

    /**
     * Deletes the task as specified by user.
     * @param index determines the task to be deleted
     */
    public void removeTask(int index) {
        if (index == -1) {
            tasks.clear();
            ui.printRemovedItem("All tasks", 0);
        } else {
            ui.printRemovedItem(tasks.get(index).getOverallStatus(), tasks.size() - 1);
            tasks.remove(index);
            updateTaskIndex();
        }
        resetUiTasks();
    }

    public void resetUiTasks() {
        uiTaskList = tasks;
    }

    /**
     * Searches through whole tasklist to try and find a match with the user input.
     */

    public void findTasks(String search) {
        uiTaskList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(search)) {
                uiTaskList.add(task);
            }
        }
        ui.printFoundTasks(uiTaskList.size());
    }

    /**
     * Method to find matching notes with  descriptions matching a key string.
     * @param description contains the string to match
     */
    public void findNotes(String description) {
        uiNoteList = new ArrayList<>();
        for (Task task : tasks) {
            for (Note notes : task.getNotes()) {
                if (notes.getDescription().contains(description)) {
                    uiNoteList.add(notes);
                }
            }
        }
        ui.printFoundTasks(uiNoteList.size());
    }

    /**
     * method to update the index of the tasks after a deletion.
     */
    public void updateTaskIndex() {
        int i = 1;
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                task.setIndex(i);
                task.updateNoteIndex();
                i++;
            }
        }
    }

    public ArrayList<Task> getUiTaskList() {
        return uiTaskList;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Note> getUiNoteList() {
        return uiNoteList;
    }

    public void clearUI() {
        uiTaskList = new ArrayList<>();
        uiNoteList = new ArrayList<>();
    }
}

