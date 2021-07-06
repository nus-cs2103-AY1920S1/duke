package duke.tasklist;

import duke.command.exceptions.InvalidDeleteDukeException;
import duke.date.EventConflictChecker;
import duke.task.Event;
import duke.task.InvalidTaskDukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Deals with storing, adding, deleting, finding of tasks. Stores their state of completion.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String EMPTY_LIST_MSG = "Oops! The list is empty.";

    /**
     * Removes the task corresponding to the task ID from the list.
     * @param id ID of the task to be removed.
     * @return A message confirming task removal along with the removed task.
     * @throws InvalidDeleteDukeException If the task ID is invalid.
     */
    public String removeTaskFromList(int id) throws InvalidDeleteDukeException {
        try {
            Task t = tasks.remove(id - 1);
            updateIntervals(t);
            return getRemoveTaskMessage(t);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDeleteDukeException("Invalid \"delete\" command. Please enter a valid task ID.");
        }
    }

    private void updateIntervals(Task task) {
        if (task instanceof Event) {
            EventConflictChecker.deleteInterval((Event) task);
        }
    }

    private String getRemoveTaskMessage(Task t) {
        return "Nice! I've removed this task from the list:\n"
                + "  " + t.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
    /**
     * Gets text confirmation after a task has been marked as done.
     * @param listOfTaskIds IDs of tasks to be marked done.
     * @return String
     * @throws IndexOutOfBoundsException If ID/IDs are invalid.
     */

    public String getDoneUpdates(ArrayList<Integer> listOfTaskIds) throws IndexOutOfBoundsException {
        StringBuilder finalOutput = initializeFinalOutput(listOfTaskIds);
        developFinalOutput(finalOutput, listOfTaskIds);
        return finalOutput.toString();
    }

    private StringBuilder initializeFinalOutput(ArrayList<Integer> listOfTaskIds) {
        StringBuilder finalOutput = listOfTaskIds.size() == 1
                ? new StringBuilder("Nice! I've marked this task as done:\n")
                : new StringBuilder("Nice! I've marked these tasks as done:\n");
        return finalOutput;
    }

    private void developFinalOutput(StringBuilder finalOutput, ArrayList<Integer> listOfTaskIds) {
        for (int i = 0; i < listOfTaskIds.size(); i++) {
            Task t = tasks.get(listOfTaskIds.get(i) - 1);
            t.setDone(true);
            updateIntervals(t);
            finalOutput.append("  " + t.toString());
            if (i != listOfTaskIds.size() - 1) {
                finalOutput.append("\n");
            }
        }
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     * @return A confirmation along with the new task's description.
     * @throws InvalidTaskDukeException If the task is invalid.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return getAddTaskMessage();
    }

    private String getAddTaskMessage() {
        return "Nice! I've added this task to the list:\n"
                + "  " + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a list of the tasks, ordered by ID.
     * @return String representing ordered list.
     */
    public String getListOfTasks() {
        String finalOutput = getFinalListOfTasks();
        if (finalOutput.isBlank()) {
            return EMPTY_LIST_MSG;
        }
        return finalOutput;
    }

    private String getFinalListOfTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1) + ". " + tasks.get(i));
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        String finalOutput = output.toString();
        return finalOutput;
    }

    /**
     * Finds the tasks whose descriptions match the input string.
     * @param descriptionToMatch Description to search for.
     * @return String representing found tasks.
     */
    public String findTasks(String descriptionToMatch) {
        String findResults = getFindResult(descriptionToMatch);
        return findResults;
    }

    private String getFindResult(String descriptionToMatch) {
        StringBuilder result = new StringBuilder();
        boolean taskExists = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(descriptionToMatch.toLowerCase())) {
                if (taskExists) {
                    result.append("\n");
                }
                result.append((i + 1) + ". " + tasks.get(i).toString());
                taskExists = true;
            }
        }
        return result.toString();
    }

    /**
     * Adds all tasks to the current list.
     * @param tasks Tasks to be added.
     */
    public void addAllTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Gets the list of tasks.
     * @return List of tasks.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks.clone();
    }
}
