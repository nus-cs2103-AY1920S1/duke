package weomucat.duke.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.StorageException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;
import weomucat.duke.task.listener.AddTaskListener;
import weomucat.duke.task.listener.DeleteTaskListener;
import weomucat.duke.task.listener.DoneTaskListener;

/**
 * TaskListStorage is responsible for serializing TaskListTasks and deserializing TaskListTasks,
 * before saving to disk and loading from disk respectively.
 */
public class TaskListStorage extends Storage<TaskListTasks> implements AddTaskListener,
    DeleteTaskListener, DoneTaskListener {

  public TaskListStorage(String path) {
    super(path);
  }

  @Override
  public void save(TaskListTasks tasks) throws StorageException {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(this.path);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

      // Serialize tasks
      objectOutputStream.writeObject(tasks);

      objectOutputStream.close();
      fileOutputStream.close();
    } catch (FileNotFoundException e) {
      throw new StorageException(String.format("I cannot create a file at '%s'", this.path));
    } catch (IOException e) {
      throw new StorageException("An I/O error occurred.");
    }
  }

  @Override
  public TaskListTasks load() throws StorageException {
    try {
      FileInputStream fileInputStream = new FileInputStream(this.path);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

      // Deserialize tasks
      TaskListTasks tasks = (TaskListTasks) objectInputStream.readObject();

      objectInputStream.close();
      fileInputStream.close();

      return tasks;
    } catch (FileNotFoundException e) {
      throw new StorageException(String.format("I cannot read a file at '%s'", this.path));
    } catch (ClassNotFoundException | ClassCastException e) {
      throw new StorageException("I do not know how to deserialize this file.");
    } catch (IOException e) {
      throw new StorageException("An I/O error occurred.");
    }
  }

  @Override
  public void addTaskUpdate(TaskListTasks tasks, Task task) throws DukeException {
    save(tasks);
  }

  @Override
  public void deleteTaskUpdate(TaskListTasks tasks, Task task) throws DukeException {
    save(tasks);
  }

  @Override
  public void doneTaskUpdate(TaskListTasks tasks, Task task) throws DukeException {
    save(tasks);
  }
}
