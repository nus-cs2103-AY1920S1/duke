package weomucat.duke.storage;

import java.io.File;
import weomucat.duke.exception.StorageException;

public abstract class Storage<T> {

  protected String path;

  public Storage(String path) {
    this.path = path;
  }

  public boolean exists() {
    return new File(path).exists();
  }

  abstract void save(T t) throws StorageException;

  abstract T load() throws StorageException;
}
