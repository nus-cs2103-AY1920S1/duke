import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Empty task list is created if there is no previous session of Duke, or no tasks in the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * New task list is filled with tasks from previous session of Duke.
     *
     * @param savedTasks Task list from the previous session of Duke.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        tasks = new ArrayList<>();
        tasks.addAll(savedTasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints out the task list for the user.
     */
    public void printTasks(Ui ui) {
        ui.show("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.show((i + 1) + "." + tasks.get(i));
        }
    }

    public void completeTask(int taskNumber, Ui ui) {
        tasks.get(taskNumber - 1).markAsDone();
        ui.show("Nice! I've marked this task as done:");
        ui.show(tasks.get(taskNumber - 1).toString());
    }

    public void deleteTask(int taskNumber, Ui ui) {
        String taskDescription = tasks.get(taskNumber - 1).toString();
        tasks.remove(taskNumber - 1);
        ui.show("Noted. I've removed this task:");
        ui.show(taskDescription);
        ui.show(getListSize());
    }

    public void findTasks(String searchTerm, Ui ui) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(searchTerm)) {
                searchResults.add(task);
            }
        }
        if (searchResults.isEmpty()) {
            ui.show("OOPS!!! You don't have any tasks containing the term \"" + searchTerm + "\".");
        } else {
            ui.show("Here are the matching tasks in your list:");
            for (int i = 0; i < searchResults.size(); i++) {
                ui.show((i + 1) + "." + searchResults.get(i));
            }
        }
    }

    /**
     * Prints out a message informing user of the current list size.
     */
    public String getListSize() {
        if (tasks.size() == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + tasks.size() + " tasks in the list.";
        }
    }

    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.show("Got it. I've added this task:");
        ui.show(task.toString());
        ui.show(getListSize());
    }

}