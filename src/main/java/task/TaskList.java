package task;

import exceptions.DukeException;
import exceptions.InvalidInputException;
import exceptions.InvalidItemException;
import exceptions.MissingInputException;
import ui.MessageGenerator;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> taskList = new ArrayList<>();
    MessageGenerator msgGenerator = new MessageGenerator();

    /**
     * Creates task list and adds a placeholder value for easier tracking.
     */
    public TaskList() {
        taskList.add(null);
    }

    private int noTasks() {
        return taskList.size() - 1;
    }

    /**
     * Adds task to task list.
     * Prints message when task is added via user input.
     *
     * @param task task to be added.
     * @return program message when task is added.
     */
    public String addTask(Task task) {
        taskList.add(task);
        return msgGenerator.getAddMessage(task, noTasks());
    }

    /**
     * Adds task to task List when task is loaded from the file.
     *
     * @param task task loaded from the file that is to be added.
     */
    public void loadTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task of given identification number from list.
     * Prints message when task is removed.
     * Method is only called when task is a valid number.
     *
     * @param taskNo identification number of task in list.
     * @return message to indicate task is removed.
     */
    private String removeTask(int taskNo) {
        Task deleted = taskList.get(taskNo);
        taskList.remove(taskNo);
        assert !(taskList.contains(taskList.get(taskNo)));
        return msgGenerator.getRemoveMessage(deleted, noTasks());
    }

    /**
     * Formats task list into list of strings for printing/writing.
     *
     * @param tasks List of Tasks.
     * @return List of Strings.
     */
    private List<String> listify(List<Task> tasks) {
        List<String> list = new ArrayList<String>();
        for (Task task: tasks) {
            if (task != null) {
                list.add(task.toString());
            }
        }
        return list;
    }

    /**
     * Returns list of tasks.
     *
     * @return task list.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Prints list of tasks using the message generator.
     *
     * @return String containing list of tasks in format.
     */
    public String getList() {
        return msgGenerator.getList(listify(this.taskList));
    }

    /**
     * Checks if given task number exists in task list.
     *
     * @param taskNo identification number for task.
     * @return whether or not task number exists.
     */
    private boolean invalidTaskNo(int taskNo) {
        return taskNo >= taskList.size();
    }

    /**
     * Marks a task as done and returns the done message.
     *
     * @param taskNo identification number for task.
     * @return done message.
     */
    public String setDone(int taskNo) {
        try {
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
            taskList.get(taskNo).setDone();
            return msgGenerator.getDoneMessage(taskList.get(taskNo));
        } catch (DukeException e) {
            return e.getErrorMessage();
        }
    }

    /**
     * Deletes task with given identification number.
     *
     * @param taskNo identification number for task.
     * @return String with delete message.
     */
    public String deleteTask(int taskNo) {
        try {
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
            return removeTask(taskNo);
        } catch (DukeException e) {
            return e.getErrorMessage();
        }
    }

    /**
     * Updates task depending on what type of instruction.
     * Returns update message when task is updated.
     *
     * @param strings Variable number of strings taken in.
     * @return String containing message on task updated.
     */
    public String updateTask(String...strings) throws InvalidInputException, MissingInputException {
        String updateType = strings[1];
        String info = strings[2];
        int taskNo;
        try {
            taskNo = Integer.parseInt(strings[0]);
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
        } catch (NumberFormatException | InvalidItemException e) {
            throw new InvalidInputException("Task number should be given!");
        }
        switch (updateType) {
        case "desc":
            return updateTaskDesc(taskNo, info);
        case "time":
            return updateTaskTime(taskNo, info);
        case "date":
            return updateTaskDate(taskNo, info);
        default:
            assert false; //update types should be filtered out.
            return "Wrong update type!";
        }
    }

    /**
     * Updates Task Description.
     *
     * @param taskNo identification number for task.
     * @return message when task is updated.
     */
    public String updateTaskDesc(int taskNo, String desc) {
        taskList.get(taskNo).updateTaskDesc(desc);
        return msgGenerator.getUpdateMessage(taskList.get(taskNo), noTasks());
    }

    /**
     * Updates Task Timing.
     *
     * @param taskNo identification number for task.
     * @return message when task is updated.
     */
    public String updateTaskTime(int taskNo, String time) throws InvalidInputException, MissingInputException {
        taskList.get(taskNo).updateTaskTime(time);
        return msgGenerator.getUpdateMessage(taskList.get(taskNo), noTasks());
    }

    /**
     * Updates Task Date.
     *
     * @param taskNo identification number for task.
     * @return message when task is updated.
     */
    public String updateTaskDate(int taskNo, String date) throws MissingInputException, InvalidInputException {
        taskList.get(taskNo).updateTaskDate(date);
        return msgGenerator.getUpdateMessage(taskList.get(taskNo), noTasks());
    }

    /**
     * Searches and prints matching tasks in task list
     * .
     * @param keyword word that task must contain to be printed.
     * @return String containing list of tasks.
     */
    public String findMatchingTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task != null && task.getTaskInfo().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return msgGenerator.getMatchingList(listify(matchingTasks));
    }

}
