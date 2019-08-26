package weomucat.duke;

import java.io.*;

public class TaskListStorage extends Storage<TaskListTasks> implements AddTaskListener, DeleteTaskListener, DoneTaskListener {
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
