package duke.task;

import error.task.TaskRepoException;

import java.util.List;

/**
 * An interface to encapsulate the accessing of a user's tasks. It includes operations required by the
 * program to read and modify these tasks accordingly.
 */
public interface ITaskRepo {
    /**
     * Method used by the program to simply read the user's current tasks. Tasks are currently represented
     * in the form of a list. The list returned by this method SHOULD be READ-ONLY, which means that any
     * changes to it should not affect the user's tasks in any way. The order of the tasks returned MUST be consistent
     * if no modifications is made to the tasks.
     * @return a list containing the user's current tasks.
     * @throws TaskRepoException if tasks cannot be retrieved.
     */
    public List<Task> getCurrentTasks() throws TaskRepoException;

    /**
     * Method used by the program to retrieve the number of tasks that a user has.
     * @return the number of tasks that a user currently has.
     * @throws TaskRepoException if tasks cannot be read.
     */
    public int getCurrentTasksCount() throws TaskRepoException;

    /**
     * Method used by the program to retrieve the task based on its index in the list of
     * tasks returned by the getCurrentTasks() method. The task returned MUST correspond to the task that is
     * found at the specified index.
     * @param index the index of the task whose UUID is to be retrieved.
     * @return the UUID of a task found at the specified index.
     * @throws TaskRepoException if tasks cannot be retrieved or if the index provided is invalid.
     */
    public Task getTaskFromListIndex(int index) throws TaskRepoException;

    /**
     * Method used by the program to search for tasks. Tasks are considered matching if a substring of its details
     * contains the parameter. A list of matching tasks is to be returned. The list returned by this method SHOULD be
     * READ-ONLY, which means that any changes to it should not affect the user's tasks in any way.
     * @param parameter substring to be used to search for matching tasks.
     * @return a list of the matching tasks.
     * @throws TaskRepoException if tasks cannot be retrieved.
     */
    public List<Task> searchTasks(String parameter) throws TaskRepoException;

    /**
     * Method used by the program to delete a specific task. The task is identified based on its index in the list
     * of tasks returned by the getCurrentTasks() method. The task deleted MUST correspond to the task that is found
     * at the specified index.
     * @param index the index of the task to be deleted.
     * @throws TaskRepoException if the index specified is out of bounds or if the task is unable to be deleted..
     */
    public void deleteTask(int index) throws TaskRepoException;

    /**
     * Method used by the program to add a new Task to the user's existing Tasks. The Task being added SHOULD NOT be
     * modified in anyway before it is added.
     * @param task the task to be added.
     * @throws TaskRepoException if the task is unable to be added.
     */
    public void addTask(Task task) throws TaskRepoException;

    /**
     * Method used by the program to update a currently existing task's details field. The task to be updated is
     * identified by its index in the list of tasks returned by the getCurrentTasks() method. The model should only
     * update the task that corresponds to the task found at the specified index. The task to be updated SHOULD be of
     * the same task type as the previous task.
     * @param index the index of the task to be updated.
     * @param task new task to be updated.
     * @throws TaskRepoException if the index specified is out of bounds or if the task is unable to be updated.
     */
    public void updateTask(int index, Task task) throws TaskRepoException;

    /**
     * Method used by the program to update a currently existing task's done status. The task is identified by its index
     * in the list of tasks returned by the getCurrentTasks() method. This method SHOULD check if the new done status
     * is opposite of the current done status of the task.
     * @param index the index of the taks to be updated
     * @param isDone the new done status of the task to be updated.
     * @throws TaskRepoException if the index specified is out of bounds or if the new done status is invalid.
     */
    public void updateTaskDoneStatus(int index, boolean isDone) throws TaskRepoException;

    /**
     * Method used by the program to completely update the user's existing tasks to a new list of tasks. The old task
     * information will be erased.
     * @param tasks the list of new tasks.
     * @throws  TaskRepoException if the rewrite fails.
     */
    public void setNewTasks(List<Task> tasks) throws TaskRepoException;

    /**
     * Method used by the program to delete ALL of the user's existing tasks. All of the old task information will be
     * erased.
     * @throws TaskRepoException if the deletion fails.
     */
    public void deleteAllTasks() throws TaskRepoException;
}
