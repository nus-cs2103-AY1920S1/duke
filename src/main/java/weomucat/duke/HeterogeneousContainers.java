package weomucat.duke;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Custom implementation of heterogeneous containers.
 * See Effective Java 29: typesafe heterogeneous containers
 * I built on it such that it can store multiple objects of the same class.
 *
 * @param <E> subclasses of E can be stored in the container
 */
public class HeterogeneousContainers<E> {

  private ArrayList<HashMap<Class<?>, Object>> containers;

  public HeterogeneousContainers() {
    this.containers = new ArrayList<>();
    this.containers.add(new HashMap<>());
  }

  public <T extends E> void add(Class<T> c, T listener) {
    for (HashMap<Class<?>, Object> container : this.containers) {
      if (container.get(c) == null) {
        container.put(c, listener);
        return;
      }
    }

    // No free containers, append a new one.
    HashMap<Class<?>, Object> container = new HashMap<>();
    container.put(c, listener);
    this.containers.add(container);
  }

  public <T extends E> Collection<T> get(Class<T> c) {
    ArrayList<T> objects = new ArrayList<>();
    for (HashMap<Class<?>, Object> container : containers) {
      if (container.get(c) == null) {
        break;
      } else {
        objects.add(c.cast(container.get(c)));
      }
    }
    return objects;
  }
}
