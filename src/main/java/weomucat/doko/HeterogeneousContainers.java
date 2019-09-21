package weomucat.doko;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Custom implementation of heterogeneous containers.
 * See Effective Java 29: typesafe heterogeneous containers
 * I built on it such that it can store multiple elements of the same class.
 *
 * @param <E> subclasses of E can be stored in the container
 */
public class HeterogeneousContainers<E> {

  private ArrayList<HashMap<Class<?>, Object>> containers;

  public HeterogeneousContainers() {
    this.containers = new ArrayList<>();
    this.containers.add(new HashMap<>());
  }

  /**
   * Adds an element to the container.
   * Associates this element by class type to be retrieved with get().
   *
   * @param type    class type of the element
   * @param element the element
   * @param <T>     type of the element
   */
  public <T extends E> void add(Class<T> type, T element) {
    for (HashMap<Class<?>, Object> container : this.containers) {
      if (container.get(type) == null) {
        // Casting to ensure runtime type safety.
        container.put(type, type.cast(element));
        return;
      }
    }

    // No free containers, append a new one.
    HashMap<Class<?>, Object> container = new HashMap<>();
    container.put(type, element);
    this.containers.add(container);
  }

  /**
   * Gets all elements from the container associated with a class type.
   *
   * @param type class type of the element
   * @param <T>  type of the element
   */
  public <T extends E> Collection<T> getAll(Class<T> type) {
    ArrayList<T> objects = new ArrayList<>();
    for (HashMap<Class<?>, Object> container : containers) {
      if (container.get(type) == null) {
        break;
      } else {
        objects.add(type.cast(container.get(type)));
      }
    }
    return objects;
  }
}
