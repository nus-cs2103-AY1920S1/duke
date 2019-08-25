package weomucat.duke;

import java.io.*;
import java.util.ArrayList;

public class Storage {
	private String path;

	public Storage(String path) {
		this.path = path;
	}

	public void save(ArrayList<Task> tasks) throws StorageException {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			// Serialize tasks
			objectOutputStream.writeObject(tasks);

			objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			throw new StorageException(String.format("I cannot create a file at '%s'", path));
		} catch (IOException e) {
			throw new StorageException("An I/O error occurred.");
		}
	}

	public ArrayList<Task> load() throws StorageException {
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			// Deserialize tasks
			ArrayList<Task> tasks = (ArrayList<Task>) objectInputStream.readObject();

			objectInputStream.close();
			fileInputStream.close();

			return tasks;
		} catch (FileNotFoundException e) {
			throw new StorageException(String.format("I cannot read a file at '%s'", path));
		} catch (ClassNotFoundException e) {
			throw new StorageException("I do not know how to deserialize this file.");
		} catch (IOException e) {
			throw new StorageException("An I/O error occurred.");
		}
	}
}
