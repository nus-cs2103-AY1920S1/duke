package duke.task;

import duke.task.tasks.entities.TimeFrame;
import error.storage.StorageException;
import error.task.TaskAddToModelException;
import error.task.TaskInvalidIndexException;
import error.task.TaskModificationException;
import error.task.TaskNotFoundInModelException;
import error.task.TaskRetrievalFromModelException;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The default implementation of the TaskModel required by the program to read and perform operations on the user's
 * tasks. This default implementation reads and writes the user's tasks from a Storage instance. Each method call to
 * retrieve task information by its clients will result in a read from the Storage instance. Each method call to update
 * task information will also result in a a direct write to the Storage instance.
 */
public class DefaultTaskModel implements TasksModel {
    Storage storage;

    public DefaultTaskModel(Storage storage) {
        this.storage = storage;
    }

    private List<Task> produceTaskListCopy(List<Task> originalList) throws CloneNotSupportedException {
        List<Task> copy = new ArrayList<>();

        for (Task task : originalList) {
            Task clone = task.clone();
            copy.add(clone);
        }

        return copy;
    }

    /**
     * Method used by the program to simply read the user's current tasks. Tasks are currently represented
     * in the form of a list. The list returned by this method SHOULD be READ-ONLY, which means that any
     * changes to it should not affect the user's tasks in any way. The order of the tasks returned MUST be consistent
     * if no modifications is made to the tasks.
     *
     * @return a list containing the user's current tasks.
     * @throws TaskRetrievalFromModelException if tasks cannot be retrieved
     */
    @Override
    public List<Task> getCurrentTasks() throws TaskRetrievalFromModelException {
        try {
            List<Task> storedTasks = storage.getTasks();

            return produceTaskListCopy(storedTasks);
        } catch (StorageException e) {
            throw new TaskRetrievalFromModelException("Failed to access storage.");
        } catch (CloneNotSupportedException e) {
            throw new TaskRetrievalFromModelException("Failed to read tasks.");
        }
    }

    /**
     * Method used by the program to retrieve the number of tasks that a user has.
     *
     * @return the number of tasks that a user currently has.
     * @throws TaskRetrievalFromModelException if tasks cannot be read.
     */
    @Override
    public int getCurrentTasksCount() throws TaskRetrievalFromModelException {
        try {
            List<Task> tasks = storage.getTasks();
            return tasks.size();
        } catch (StorageException e) {
            throw new TaskRetrievalFromModelException("Failed to access storage.");

        }
    }

    /**
     * Method used by the program to retrieve a specific task. The task to be returned is identified by its UUID
     * and its uuid MUST match the uuid that is specified.
     *
     * @param taskUuid the uuid of the task to be retrieved.
     * @return the matching task.
     * @throws TaskNotFoundInModelException    if the task is not found in the user's current tasks.
     * @throws TaskRetrievalFromModelException if the task cannot be retrieved.
     */
    @Override
    public Task getTaskFromUuid(UUID taskUuid) throws TaskNotFoundInModelException, TaskRetrievalFromModelException {
        try {
            List<Task> tasks = storage.getTasks();

            return tasks.stream()
                    .filter(task -> task.getUuid().equals(taskUuid))
                    .findFirst()
                    .orElseThrow(() -> new TaskNotFoundInModelException("Task not found."));

        } catch (StorageException e) {
            throw new TaskRetrievalFromModelException("Failed to access storage.");

        }
    }

    /**
     * Method used by the program to retrieve the UUID of a particular task based on its index in the list of
     * tasks returned by the getCurrentTasks() method. The task UUID returned MUST correspond to the task that is
     * found at the specified index.
     *
     * @return the UUID of a task found at the specified index.
     * @throws TaskRetrievalFromModelException if tasks cannot be retrieved.
     * @throws TaskInvalidIndexException if the index provided is invalid.
     */
    @Override
    public UUID getTaskUUIDFromListIndex(int index) throws TaskRetrievalFromModelException, TaskInvalidIndexException {
        List<Task> tasks = this.getCurrentTasks();

        try {
            Task selectedTask = tasks.get(index);
            return selectedTask.getUuid();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskInvalidIndexException("An invalid index was entered.");
        }
    }

    /**
     * Method used by the program to search for tasks. Tasks are considered matching if a substring of its details
     * contains the parameter. A list of matching tasks is to be returned. The list returned by this method SHOULD be
     * READ-ONLY, which means that any changes to it should not affect the user's tasks in any way.
     *
     * @param parameter substring to be used to search for matching tasks.
     * @return a list of the matching tasks.
     * @throws TaskRetrievalFromModelException if tasks cannot be retrieved
     */
    @Override
    public List<Task> searchTasks(String parameter) throws TaskRetrievalFromModelException {
        try {
            String lowerCaseParameter = parameter.toLowerCase();

            List<Task> matchingStoredTasks = storage.getTasks().stream()
                    .filter(task -> task.getTaskDetails().toLowerCase().contains(lowerCaseParameter))
                    .collect(Collectors.toList());

            return produceTaskListCopy(matchingStoredTasks);
        } catch (StorageException e) {
            throw new TaskRetrievalFromModelException("Failed to access storage.");
        } catch (CloneNotSupportedException e) {
            throw new TaskRetrievalFromModelException("Failed to read tasks.");
        }
    }

    /**
     * Method used by the program to delete a specific task. Tasks are identified by their UUIDs. The task that that
     * contains the matching UUID should be deleted from the user's current tasks.
     *
     * @param taskUuid the UUID of the task to be deleted.
     * @throws TaskNotFoundInModelException if the task specified is not found in the user's current tasks.
     * @throws TaskModificationException    if the task is unable to be deleted.
     */
    @Override
    public void deleteTask(UUID taskUuid) throws TaskNotFoundInModelException, TaskModificationException {
        try {
            List<Task> tasks = storage.getTasks();

            Task taskToBeDeleted = tasks.stream()
                    .filter(task -> task.getUuid().equals(taskUuid))
                    .findFirst()
                    .orElseThrow(() -> new TaskNotFoundInModelException("Task not found."));

            tasks.remove(taskToBeDeleted);

            storage.writeTasks(tasks);
        } catch (StorageException e) {
            throw new TaskModificationException("Failed to access storage.");
        }
    }

    /**
     * Method used by the program to add a new Task to the user's existing Tasks. The Task being added SHOULD NOT be
     * modified in anyway before it is added.
     *
     * @param task the task to be added.
     * @throws TaskAddToModelException if the task is unable to be added.
     */
    @Override
    public void addTask(Task task) throws TaskAddToModelException {
        try {
            List<Task> tasks = storage.getTasks();
            tasks.add(task);

            storage.writeTasks(tasks);
        } catch (StorageException e) {
            throw new TaskAddToModelException("Failed to access storage.");
        }
    }

    /**
     * Method used by the program to update a currently existing task's details field. The task to be updated is
     * identified by its UUID. The model should only update the task that corresponds to the specified UUID.
     *
     * @param taskUuid the UUID of the task to be updated.
     * @param details  new details for the specified task to be updated with.
     * @throws TaskNotFoundInModelException if the task to be updated is not found in the user's current tasks.
     * @throws TaskModificationException    if the task is unable to be updated.
     */
    @Override
    public void updateTaskDetails(UUID taskUuid, String details) throws TaskNotFoundInModelException, TaskModificationException {
        try {
            List<Task> tasks = storage.getTasks();

            Task taskToBeUpdated = tasks.stream()
                    .filter(task -> task.getUuid().equals(taskUuid))
                    .findFirst()
                    .orElseThrow(() -> new TaskNotFoundInModelException("Task not found."));

            taskToBeUpdated.setTaskDetails(details);

            storage.writeTasks(tasks);
        } catch (StorageException e) {
            throw new TaskModificationException("Failed to access storage.");
        }
    }

    /**
     * Method used by the program to update a currently existing task's TimeFrame. The task to be updated is
     * identified by its UUID. The model should only update the task that corresponds to the specified UUID. It is
     * IMPORTANT to check that the TimeFrame that the task is to be updated to corresponds to the task's requirements.
     *
     * @param taskUuid  the UUID of the task to be updated.
     * @param timeFrame the new TimeFrame that the task is to be updated with.
     * @throws TaskModificationException    if the TimeFrame provided is not compatible with the task.
     * @throws TaskNotFoundInModelException if the task to be updated is not found in the user's current tasks.
     */
    @Override
    public void updateTaskTimeFrame(UUID taskUuid, TimeFrame timeFrame) throws TaskModificationException, TaskNotFoundInModelException {
        try {
            List<Task> tasks = storage.getTasks();

            Task taskToBeUpdated = tasks.stream()
                    .filter(task -> task.getUuid().equals(taskUuid))
                    .findFirst()
                    .orElseThrow(() -> new TaskNotFoundInModelException("Task not found."));

            taskToBeUpdated.setTaskTimeFrame(timeFrame);

            storage.writeTasks(tasks);
        } catch (StorageException e) {
            throw new TaskModificationException("Failed to access storage.");

        }
    }

    /**
     * Method used by the program to update a currently existing task's done status. The task to be updated is identified
     * by its UUID. The model should only udpate the task that corresponds to the specified UUID. It is IMPORTANT to
     * check that the previous done status of the task is the opposite of the new update. If not, a
     * TaskModificationException SHOULD be thrown.
     *
     * @param taskUuid the task to be updated.
     * @param isDone   the new done status of the task to be updated.
     */
    @Override
    public void updateTaskDoneStatus(UUID taskUuid, boolean isDone) throws TaskModificationException, TaskNotFoundInModelException {
        try {
            List<Task> tasks = storage.getTasks();

            Task taskToBeUpdated = tasks.stream()
                    .filter(task -> task.getUuid().equals(taskUuid))
                    .findFirst()
                    .orElseThrow(() -> new TaskNotFoundInModelException("Task not found."));

            if (taskToBeUpdated.isTaskDone() == isDone) {
                throw new TaskModificationException("Task completion status is already set to " + isDone);
            }

            taskToBeUpdated.setTaskAsDone(isDone);

            storage.writeTasks(tasks);
        } catch (StorageException e) {
            throw new TaskModificationException("Failed to access storage.");

        }
    }

    /**
     * Method used by the program to completely update the user's existing tasks to a new list of tasks. The old task
     * information will be erased.
     *
     * @param tasks the list of new tasks.
     * @throws TaskModificationException if the rewrite fails.
     */
    @Override
    public void setNewTasks(List<Task> tasks) throws TaskModificationException {
        try {
            storage.writeTasks(tasks);
        } catch (StorageException e) {
            throw new TaskModificationException("Unable to write tasks.");
        }
    }

    /**
     * Method used by the program to delete ALL of the user's existing tasks. All of the old task information will be
     * erased.
     *
     * @throws TaskModificationException if the deletion fails.
     */
    @Override
    public void deleteALlTasks() throws TaskModificationException {
        try {
            storage.writeTasks(new ArrayList<>());
        } catch (StorageException e) {
            throw new TaskModificationException("Unable to delete tasks.");
        }
    }
}
