package duke.task;

import duke.task.tasks.entities.TimeFrame;
import error.task.TaskAddToModelException;
import error.task.TaskInvalidIndexException;
import error.task.TaskModificationException;
import error.task.TaskNotFoundInModelException;
import error.task.TaskRetrievalFromModelException;

import java.util.List;
import java.util.UUID;

/**
 * An interface to encapsulate the user's current tasks. It includes operations required by the
 * program to read and modify these tasks accordingly.
 */
public interface TasksModel {
    /**
     * Method used by the program to simply read the user's current tasks. Tasks are currently represented
     * in the form of a list. The list returned by this method SHOULD be READ-ONLY, which means that any
     * changes to it should not affect the user's tasks in any way. The order of the tasks returned MUST be consistent
     * if no modifications is made to the tasks.
     * @return a list containing the user's current tasks.
     * @throws TaskRetrievalFromModelException if tasks cannot be retrieved.
     */
    public List<Task> getCurrentTasks() throws TaskRetrievalFromModelException;

    /**
     * Method used by the program to retrieve the number of tasks that a user has.
     * @return the number of tasks that a user currently has.
     * @throws TaskRetrievalFromModelException if tasks cannot be read.
     */
    public int getCurrentTasksCount() throws TaskRetrievalFromModelException;

    /**
     * Method used by the program to retrieve a specific task. The task to be returned is identified by its UUID
     * and its uuid MUST match the uuid that is specified.
     * @param taskUuid the uuid of the task to be retrieved.
     * @return the matching task.
     * @throws TaskNotFoundInModelException if the task is not found in the user's current tasks.
     * @throws TaskRetrievalFromModelException if the task cannot be retrieved.
     */
    public Task getTaskFromUuid(UUID taskUuid) throws TaskNotFoundInModelException, TaskRetrievalFromModelException;

    /**
     * Method used by the program to retrieve the UUID of a particular task based on its index in the list of
     * tasks returned by the getCurrentTasks() method. The task UUID returned MUST correspond to the task that is
     * found at the specified index.
     * @param index the index of the task whose UUID is to be retrieved.
     * @return the UUID of a task found at the specified index.
     * @throws TaskRetrievalFromModelException if tasks cannot be retrieved.
     * @throws TaskInvalidIndexException if the index provided is invalid.
     */
    public UUID getTaskUUIDFromListIndex(int index) throws TaskRetrievalFromModelException, TaskInvalidIndexException;

    /**
     * Method used by the program to search for tasks. Tasks are considered matching if a substring of its details
     * contains the parameter. A list of matching tasks is to be returned. The list returned by this method SHOULD be
     * READ-ONLY, which means that any changes to it should not affect the user's tasks in any way.
     * @param parameter substring to be used to search for matching tasks.
     * @return a list of the matching tasks.
     * @throws TaskRetrievalFromModelException if tasks cannot be retrieved.
     */
    public List<Task> searchTasks(String parameter) throws TaskRetrievalFromModelException;

    /**
     * Method used by the program to delete a specific task. Tasks are identified by their UUIDs. The task that that
     * contains the matching UUID should be deleted from the user's current tasks.
     * @param taskUuid the UUID of the task to be deleted.
     * @throws TaskNotFoundInModelException if the task specified is not found in the user's current tasks.
     * @throws TaskModificationException if the task is unable to be deleted.
     */
    public void deleteTask(UUID taskUuid) throws TaskNotFoundInModelException, TaskModificationException;

    /**
     * Method used by the program to add a new Task to the user's existing Tasks. The Task being added SHOULD NOT be
     * modified in anyway before it is added.
     * @param task the task to be added.
     * @throws TaskAddToModelException if the task is unable to be added.
     */
    public void addTask(Task task) throws TaskAddToModelException;

    /**
     * Method used by the program to update a currently existing task's details field. The task to be updated is
     * identified by its UUID. The model should only update the task that corresponds to the specified UUID.
     * @param taskUuid the UUID of the task to be updated.
     * @param details new details for the specified task to be updated with.
     * @throws TaskNotFoundInModelException if the task to be updated is not found in the user's current tasks.
     * @throws TaskModificationException if the task is unable to be updated.
     */
    public void updateTaskDetails(UUID taskUuid, String details) throws TaskNotFoundInModelException, TaskModificationException;

    /**
     * Method used by the program to update a currently existing task's TimeFrame. The task to be updated is
     * identified by its UUID. The model should only update the task that corresponds to the specified UUID. It is
     * IMPORTANT to check that the TimeFrame that the task is to be updated to corresponds to the task's requirements.
     * @param taskUuid the UUID of the task to be updated.
     * @param timeFrame the new TimeFrame that the task is to be updated with.
     * @throws TaskModificationException if the TimeFrame provided is not compatible with the task.
     * @throws TaskNotFoundInModelException if the task to be updated is not found in the user's current tasks.
     */
    public void updateTaskTimeFrame(UUID taskUuid, TimeFrame timeFrame) throws TaskNotFoundInModelException, TaskModificationException;

    /**
     * Method used by the program to update a currently existing task's done status. The task to be updated is identified
     * by its UUID. The model should only udpate the task that corresponds to the specified UUID. It is IMPORTANT to
     * check that the previous done status of the task is the opposite of the new update. If not, a
     * TaskModificationException SHOULD be thrown.
     * @param taskUuid the uuid of the task to be updated
     * @param isDone the new done status of the task to be updated.
     * @throws TaskModificationException if the new done status specified is invalid.
     * @throws TaskNotFoundInModelException if the task to be updated is not found in the user's current tasks.
     */
    public void updateTaskDoneStatus(UUID taskUuid, boolean isDone) throws TaskModificationException, TaskNotFoundInModelException;

    /**
     * Method used by the program to completely update the user's existing tasks to a new list of tasks. The old task
     * information will be erased.
     * @param tasks the list of new tasks.
     * @throws  TaskModificationException if the rewrite fails.
     */
    public void setNewTasks(List<Task> tasks) throws TaskModificationException;

    /**
     * Method used by the program to delete ALL of the user's existing tasks. All of the old task information will be
     * erased.
     * @throws TaskModificationException if the deletion fails.
     */
    public void deleteALlTasks() throws TaskModificationException;
}
