package duke;

import java.util.ArrayList;
import java.util.List;

class TaskList {

    List<Task> taskList = new ArrayList<>();
    MessageGenerator msgGenerator = new MessageGenerator();

    /**
     * Creates task list and adds a placeholder value for easier tracking.
     */
    TaskList() {
        //placeholder value in task
        taskList.add(null);
    }

    /**
     * @return Number of tasks in list.
     */
    private int noTasks() {
        return taskList.size() - 1;
    }

    /**
     * Adds task to task list.
     * Prints message when task is added via user input.
     * @param task task to be added.
     */
    void addTask(Task task) {
        taskList.add(task);
        msgGenerator.printAdd(task, noTasks());
    }

    /**
     * Adds task to task List when task is loaded from the file.
     * @param task task loaded from the file that is to be added.
     */
    void loadTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task of given identification number from list.
     * Prints message when task is removed.
     * @param taskNo identification number of task in list.
     */
    private void removeTask(int taskNo) {
        msgGenerator.printRemove(taskList.get(taskNo), noTasks()-1);
        taskList.remove(taskNo);
    }

    /**
     * Formats task list into list of strings for printing/writing.
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
     * @return task list.
     */
    List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Prints list of tasks using the message generator.
     */
    void printList() {
        msgGenerator.printList(listify(this.taskList));
    }

    /**
     * Checks if given task number exists in task list.
     * @param taskNo identification number for task.
     * @return whether or not task number exists.
     */
    private boolean invalidTaskNo(int taskNo) {
        return taskNo >= taskList.size();
    }

    /**
     * Marks a task as done.
     * @param taskNo identification number for task.
     */
    void setDone(int taskNo) {
        try {
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
            taskList.get(taskNo).setDone();
            msgGenerator.printDone(taskList.get(taskNo));
        } catch (DukeException e) {
            e.printError();
        }
    }

    /**
     * Deletes task with given identification number.
     * @param taskNo identification number for task.
     */
    void deleteTask(int taskNo) {
        try {
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
            removeTask(taskNo);
        } catch (DukeException e) {
            e.printError();
        }
    }

    /**
     * Searches and prints matching tasks in task list.
     * @param keyword word that task must contain to be printed.
     */
    void findMatchingTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task != null && task.getTaskInfo().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        msgGenerator.printMatchingList(listify(matchingTasks));
    }

}
