package seedu.duke.model.task;

import seedu.duke.commons.exceptions.historyexceptions.CannotRedoException;
import seedu.duke.commons.exceptions.historyexceptions.CannotUndoException;

import java.util.ArrayList;

/**
 * Defines a TaskList history object that stores all created instances of TaskLists in memory.
 * Saves all TaskList until program exits.
 */
public class TaskListHistory {
    private int curr = -1;
    private int size;
    private ArrayList<TaskList> listOfTaskLists;

    /**
     * Creates a TaskListHistory instance.
     * Initialises an empty list of TaskLists that will be used to store TaskLists.
     */
    public TaskListHistory() {
        this.listOfTaskLists = new ArrayList<>();
    }

    /**
     * Adds a TaskList to TaskListHistory.
     * @param taskList TaskList that is to be added to this TaskListHistory object.
     */
    public void add(TaskList taskList) {
        TaskList copy = new TaskList(taskList);
        if (curr == listOfTaskLists.size() - 1) {
            listOfTaskLists.add(copy);
            curr++;
        } else {
            curr++;
            listOfTaskLists.set(curr, copy);

        }
        size = curr + 1;
    }

    /**
     * Returns the previous version oft the TaskList from the list of stored TaskLists.
     * @return Next older TaskList from the list of saved TaskLists.
     * @throws CannotUndoException If there are no older versions of TaskLists stored in TaskList history
     *                             to be returned.
     */
    public TaskList undo() throws CannotUndoException {
        if (curr <= 0) {
            throw new CannotUndoException("Cannot undo. There are no older versions of the TaskList stored in "
                    + "memory.");
        } else {
            curr--;
            return new TaskList(listOfTaskLists.get(curr));
        }
    }

    /**
     * Returns the next newer version oft the TaskList from the list of stored TaskList before it was reverted.
     * @return Next most recent TaskList from the list of saved TaskLists.
     * @throws CannotRedoException If there are no newer versions of TaskLists stored in TaskList history
     *                             to be returned.
     */
    public TaskList redo() throws CannotRedoException {
        if (curr == size - 1) {
            throw new CannotRedoException("Cannot redo. There are no newer versions of the TaskList stored in "
                    + "memory.");
        } else {
            curr++;
            return new TaskList(listOfTaskLists.get(curr));
        }
    }

    /**
     * Returns the index of the current active copy of the TaskList.
     * Index ranges from 0 to the size of TaskListHistory - 1.
     * @return Index of current active copy of TaskList in TaskListHistory.
     */
    public int getCurrIndex() {
        return this.curr;
    }

    /**
     * Returns the size of TaskListHistory.
     * @return Number of stored TaskLists in TaskListHistory.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns String representation of TaskListHistory.
     * Displays all tasks stored in the the saved list of Tasks and the current version of TaskList being used.
     * @return String representation of list of all Tasks stored in TaskListHistory.
     */
    @Override
    public String toString() {
        String s = "-------------------------TASKLIST HISTORY START-------------------------";
        int n = 1;
        for (TaskList taskList : listOfTaskLists) {
            s += "\nTASKLIST NUMBER " + n + "\n";
            if (n == curr + 1) {
                s += "CURRENT IS HERE\n";
            }
            s += taskList;
            n++;
        }
        s += "\n-------------------------TASKLIST HISTORY END----------------------------";
        return s;
    }

}
