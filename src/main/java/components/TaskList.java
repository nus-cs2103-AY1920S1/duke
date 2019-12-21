package components;

import commands.DukeException;
import tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TaskList {
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
    public String[] addTask(Task task) {
        String[] temp = new String[3];
        temp[0] = "Got it. I've added this task:";
        temp[1] = task.toString();
        arr.add(task);
        temp[2] = "Now you have " + arr.size() + (arr.size() == 1 ? " task" : " tasks") + " in the list.";
        return temp;
    }

    /**
     * Returns the tasks.Task that was removed, or null in the case of an IndexOutOfBoundsException.
     *
     * @param index of the task to be removed.
     * @return the tasks.Task or null
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return arr.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! You have entered a number out of range.");
        }
    }

    /**
     * Delete a batch of tasks.
     * @param indicesToDeleteAt containing all the indices of the tasks to delete.
     * @return an arraylist of deleted tasks.
     * @throws DukeException exception.
     */
    public ArrayList<Task> batchDelete(ArrayList<Integer> indicesToDeleteAt) throws DukeException {
        try {
            ArrayList<Task> deleted = new ArrayList<>(indicesToDeleteAt
                    .stream()
                    .map(i -> arr.get(i)).collect(Collectors.toList()));
            arr.removeAll(deleted);
            return deleted;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! You have entered a number out of range.");
        }
    }

    /**
     * Delete all items currently in the list.
     *
     * @return a string array with a success message.
     */
    public String[] deleteAll() {
        arr.clear();
        return new String[]{"All items have been deleted."};
    }

    /**
     * Switch a cross to a tick to mark a task as done.
     *
     * @param index of the task to be marked as done.
     */
    public String[] markAsDone(int index) throws DukeException {
        try {
            String[] temp = new String[2];
            arr.get(index).markAsDone();
            temp[0] = "Nice! I've marked this task as done:";
            temp[1] = arr.get(index).toString();
            return temp;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! You have entered a number out of range.");
        }

    }


    /**
     * Display all tasks on the list.
     */
    public String[] displayList() {
        ArrayList<String> t = new ArrayList<>();
        if (arr.size() == 0) {
            t.add("You currently have no tasks in your list!");
            return t.toArray(new String[0]);
        }
        t.add("Here are the current tasks in your list:");
        t.addAll(generateList());
        return t.toArray(new String[0]);

    }

    private ArrayList<String> generateList() {
        ArrayList<String> temp = new ArrayList<>();
        int i = 0;
        for (Task t : arr) {
            temp.add(++i + ". " + t);
        }
        return temp;
    }

    /**
     * Scan through array and delete completed tasks.
     * @return a String array of all tasks that were deleted.
     */
    public String[] removeCompletedTasks() {
        arr.removeIf(t -> t.isDone);
        ArrayList<String> temp = new ArrayList<>();
        temp.add("I've removed all the tasks marked as done!");
        String[] list = displayList();
        temp.addAll(Arrays.asList(list));
        return temp.toArray(new String[0]);
    }

    /**
     * Finds all tasks that match a keyword.
     * @param str keyword to find.
     * @return a String array of these tasks
     */
    public String[] findTaskByKeywordAndPrintList(String str) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Placeholder for first line");
        for (Task task : arr) {
            if (task.getDescription().contains(str)) {
                list.add(task.toString());
            }
        }
        if (list.size() == 1 && list.get(0).equals("Placeholder for first line")) {
            list.set(0, "I could not find any matching tasks with this keyword: " + str);
            return list.toArray(new String[0]);
        }
        list.set(0, "Here are the matching tasks in your list:");
        return list.toArray(new String[0]);
    }
}
