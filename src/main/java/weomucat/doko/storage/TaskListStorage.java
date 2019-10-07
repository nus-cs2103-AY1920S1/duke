package weomucat.doko.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.Collections;
import weomucat.doko.date.Date;
import weomucat.doko.date.DateRange;
import weomucat.doko.date.Interval;
import weomucat.doko.exception.StorageException;
import weomucat.doko.task.DeadlineTask;
import weomucat.doko.task.EventTask;
import weomucat.doko.task.TaskList;
import weomucat.doko.task.TodoTask;
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
      TaskList taskList = new TaskList();
      taskList.add(new TodoTask("Find a Job"));
      taskList.add(new DeadlineTask("CS2105 Assignment",
          Date.now().plus(new Interval(Duration.ofDays(1))), null));
      taskList.add(new EventTask("CS2103T Lecture", Collections.singleton(new DateRange(
          Date.now().plus(new Interval(Duration.ofHours(2))),
          Date.now().plus(new Interval(Duration.ofHours(4))))),
          new Interval(Duration.ofDays(7))));
      return taskList;
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
