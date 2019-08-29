package weomucat.duke.storage;

import java.io.File;
import weomucat.duke.exception.StorageException;

/**
 * Storage is responsible for storing data to a fixed path in a local disk.
 *
 * @param <T> type of data to store
 */
public abstract class Storage<T> {

  protected String path;

  /**
   * @param path fixed path of database
   */
  public Storage(String path) {
    this.path = path;
  }

  /**
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
