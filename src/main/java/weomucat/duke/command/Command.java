package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.DukeConsumer;
import weomucat.duke.HeterogeneousContainers;
import weomucat.duke.command.listener.CommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

/**
 * A Command takes in certain parameters, then performs a set of instructions.
 *
 * @param <E> command listeners that this command should update when run
 */
public abstract class Command<E extends CommandListener> {

  /**
   * Gets the parameter options of this Command.
   *
   * @return the parameter options
   */
  public abstract ParameterOptions getParameterOptions();

  abstract Class<E> getListenersClass();

  abstract DukeConsumer<E> getListenerConsumer();

  /**
   * Update listeners defined in getListenersClass().
   *
   * @throws DukeException If there is anything wrong with processing.
   */
  public void run(HeterogeneousContainers<CommandListener> classListeners) throws DukeException {
    Class<E> c = getListenersClass();

    // Get listeners from containers.
    Collection<E> listeners = classListeners.get(c);
    for (E listener : listeners) {
      getListenerConsumer().accept(listener);
    }
  }
}
