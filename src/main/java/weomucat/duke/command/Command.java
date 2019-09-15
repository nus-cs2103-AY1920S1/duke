package weomucat.duke.command;

import java.util.Collection;
import java.util.function.Supplier;
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

  private Supplier<Command<?>> runBefore;
  private Supplier<Command<?>> runAfter;

  /**
   * Gets the keyword of this Command.
   *
   * @return the name
   */
  public abstract String getKeyword();

  abstract String getDescription();

  /**
   * Gets the parameter options of this Command.
   *
   * @return the parameter options
   */
  public abstract ParameterOptions getParameterOptions();

  abstract Class<E> getListenerClass();

  abstract DukeConsumer<E> getListenerConsumer();

  /**
   * Update listeners defined in getListenersClass().
   *
   * @throws DukeException If there is anything wrong with processing.
   */
  public void run(HeterogeneousContainers<CommandListener> classListeners) throws DukeException {
    if (runBefore != null) {
      runBefore.get().run(classListeners);
    }

    Class<E> c = getListenerClass();

    // Get listeners from containers.
    Collection<E> listeners = classListeners.getAll(c);
    for (E listener : listeners) {
      getListenerConsumer().accept(listener);
    }

    if (runAfter != null) {
      runAfter.get().run(classListeners);
    }
  }

  void setRunBefore(Supplier<Command<?>> runBefore) {
    this.runBefore = runBefore;
  }

  void setRunAfter(Supplier<Command<?>> runAfter) {
    this.runAfter = runAfter;
  }
}
