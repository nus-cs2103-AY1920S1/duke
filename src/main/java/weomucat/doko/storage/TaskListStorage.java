package weomucat.doko.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import weomucat.doko.exception.StorageException;
import weomucat.doko.task.TaskList;
import weomucat.doko.task.listener.TaskListStorageListener;

/**
 * TaskListStorage is responsible for serializing and deserializing a TaskList,
 * before saving and loading from disk respectively.
 */
public class TaskListStorage extends Storage<TaskList> implements TaskListStorageListener {

  public TaskListStorage(String path) {
    super(path);
  }

  @Override
  public void save(TaskList tasks) throws StorageException {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(this.path);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

      // Serialize tasks
      objectOutputStream.writeObject(tasks);

      objectOutputStream.close();
      fileOutputStream.close();
    } catch (IOException e) {
      throw new StorageException(String.format("I could not save Tasks at '%s'.", this.path));
    }
  }

  @Override
  public TaskList load() throws StorageException {
    // If storage does not exist.
    if (!exists()) {
      return new TaskList();
    }

    try {
      FileInputStream fileInputStream = new FileInputStream(this.path);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

      // Deserialize tasks
      TaskList tasks = (TaskList) objectInputStream.readObject();

      objectInputStream.close();
      fileInputStream.close();

      return tasks;
    } catch (FileNotFoundException e) {
      throw new StorageException(String.format("I could not read Tasks at '%s'.", this.path));
    } catch (ClassNotFoundException | ClassCastException | IOException e) {
      throw new StorageException(String.format("I could not load Tasks at '%s'.", this.path));
    }
  }

  @Override
  public TaskList loadTaskListUpdate() throws StorageException {
    return load();
  }

  @Override
  public void saveTaskListUpdate(TaskList tasks) throws StorageException {
    save(tasks);
  }
}
