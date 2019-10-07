package weomucat.doko.storage;

import java.io.File;
import weomucat.doko.exception.StorageException;

/**
 * Storage is responsible for storing data to a fixed path in a local disk.
 *
 * @param <T> type of data to store
 */
public abstract class Storage<T> {

  // Path of "database"
  String path;

  /**
   * Default constructor.
   *
   * @param path fixed path of database
   */
  Storage(String path) {
    assert path != null;

    this.path = path;
  }

  /**
   * Check if the path exists in the file system.
   *
   * @return if database exists
   */
  public boolean exists() {
    return new File(path).exists();
  }

  /**
   * Handles saving of data to disk.
   *
   * @param t data to save
   * @throws StorageException If anything went wrong with saving data.
   */
  abstract void save(T t) throws StorageException;

  /**
   * Handles loading of data from disk.
   *
   * @return data from database
   * @throws StorageException If anything went wrong with loading data.
   */
  abstract T load() throws StorageException;
}
