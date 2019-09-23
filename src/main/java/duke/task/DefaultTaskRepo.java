package duke.task;

import error.storage.StorageException;
import error.task.TaskRepoException;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The default implementation of the TaskRepo required by the program to read and perform operations on the user's
 * tasks. This default implementation reads and writes the user's tasks from a Storage instance. Each method call to
 * retrieve task information by its clients will result in a read from the Storage instance. Each method call to update
 * task information will also result in a a direct write to the Storage instance.
 */
public class DefaultTaskRepo implements ITaskRepo {
    private Storage storage;

    private static final String INVALID_INDEX_MESSAGE = "Please enter a valid index";

    public DefaultTaskRepo(Storage storage) {
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

    @Override
    public List<Task> getCurrentTasks() throws TaskRepoException {
        try {
            List<Task> storedTasks = storage.getTasks();

            return produceTaskListCopy(storedTasks);
        } catch (StorageException | CloneNotSupportedException e) {
            throw new TaskRepoException("Failed to retrieve tasks.");
        }
    }

    @Override
    public void setNewTasks(List<Task> tasks) throws TaskRepoException {
        try {
            List<Task> tasksToWrite = this.produceTaskListCopy(tasks);

            storage.writeTasks(tasksToWrite);
        } catch (StorageException | CloneNotSupportedException e) {
            throw new TaskRepoException("Failed to write tasks.");
        }
    }

    @Override
    public void deleteAllTasks() throws TaskRepoException {
        try {
            storage.writeTasks(new ArrayList<>());
        } catch (StorageException e) {
            throw new TaskRepoException("Failed to delete tasks.");
        }
    }

    @Override
    public int getCurrentTasksCount() throws TaskRepoException {
        List<Task> tasks = this.getCurrentTasks();
        return tasks.size();
    }

    @Override
    public Task getTaskFromListIndex(int index) throws TaskRepoException {
        List<Task> tasks = this.getCurrentTasks();

        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskRepoException(INVALID_INDEX_MESSAGE);
        }
    }

    @Override
    public List<Task> searchTasks(String parameter) throws TaskRepoException {
        String lowerCaseParameter = parameter.toLowerCase();

        List<Task> tasks = this.getCurrentTasks();
        return tasks.stream()
                .filter(task -> task.getTaskDetails().toLowerCase().contains(lowerCaseParameter))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(int index) throws TaskRepoException {
        List<Task> tasks = this.getCurrentTasks();

        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskRepoException(INVALID_INDEX_MESSAGE);
        }

        this.setNewTasks(tasks);
    }


    @Override
    public void addTask(Task task) throws TaskRepoException {
        try {
            Task taskToAdd = task.clone();

            List<Task> tasks = this.getCurrentTasks();
            tasks.add(taskToAdd);
            this.setNewTasks(tasks);
        } catch (CloneNotSupportedException e) {
            throw new TaskRepoException("Failed to add new task");
        }
    }

    @Override
    public void addTaskToIndex(int index, Task task) throws TaskRepoException {
        try {
            Task taskToAdd = task.clone();

            List<Task> tasks = this.getCurrentTasks();
            tasks.add(index, taskToAdd);
            this.setNewTasks(tasks);
        } catch (CloneNotSupportedException e) {
            throw new TaskRepoException("Failed to add new task");
        }
    }


    @Override
    public void updateTask(int index, Task task) throws TaskRepoException {
        try {
            Task taskToUpdate = task.clone();
            List<Task> tasks = this.getCurrentTasks();

            if (task.getUniqueCharCode() != tasks.get(index).getUniqueCharCode()) {
                throw new TaskRepoException("Incompatible task types.");
            }

            tasks.remove(index);

            tasks.add(index, taskToUpdate);
            this.setNewTasks(tasks);
        } catch (CloneNotSupportedException e) {
            throw new TaskRepoException("Failed to update task");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskRepoException(INVALID_INDEX_MESSAGE);
        }
    }


    @Override
    public void updateTaskDoneStatus(int index, boolean isDone) throws TaskRepoException {
        List<Task> tasks = this.getCurrentTasks();

        if (tasks.get(index).isTaskDone() == isDone) {
            throw new TaskRepoException("Task's done status is already set to " + isDone);
        }

        tasks.get(index).setTaskAsDone(isDone);
        this.setNewTasks(tasks);
    }
}
