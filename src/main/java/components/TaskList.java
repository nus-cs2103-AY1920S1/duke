package components;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_YELLOW = "\u001B[33m";
    private ArrayList<Task> arr;

    public ArrayList<Task> getArr() {
        return arr;
    }

    /**
     * Initialises a Tasklist with files loaded from storage.
     */
    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Adds a task to arraylist. Does not save changes to file. Use Storage to commit changes.
     *
     * @param task refers to the task to add.
     */
    public void addTask(Task task) {
        Ui.print("Got it. I've added this task:");
        Ui.print(task.toString());
        arr.add(task);
        Ui.print("Now you have " + arr.size() + (arr.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Returns the tasks.Task that was removed, or null in the case of an IndexOutOfBoundsException.
     *
     * @param index of the task to be removed.
     * @return the tasks.Task or null
     */
    public Task deleteTask(int index) {
        try {
            return arr.remove(index);
        } catch (IndexOutOfBoundsException e) {
            Ui.printErr("Oops! You have entered a number out of range.");
            return null;
        }
    }

    /**
     * Switch a cross to a tick to mark a task as done.
     * @param index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        try {
            arr.get(index).markAsDone();
            Ui.print("Nice! I've marked this task as done:");
            Ui.print(arr.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            Ui.printErr("Oops! You have entered a number out of range.");
        }
    }

    /**
     * Display all tasks on the list.
     */
    public void displayList() {
        if (arr.size() == 0) {
            Ui.print("You have no tasks in your list!");
            return;
        }
        Ui.print("Here are the tasks in your list:");
        printListOnly();

    }

    private void printListOnly() {
        int i = 0;
        for (Task t : arr) {
            Ui.print(++i + "." + t);
        }
    }

    public void findTaskByKeywordAndPrintList(String str) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : arr) {
            if (task.getDescription().contains(str)) {
                temp.add(task);
            }
        }
        if (temp.size() == 0) {
            Ui.print("I could not find any matching tasks with this keyword: " + ANSI_YELLOW + str + ANSI_RESET);
            return;
        }
        Ui.print("Here are the matching tasks in your list:");
        printListOnly();
    }
}
