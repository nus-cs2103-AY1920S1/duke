package weomucat.doko.command;

import java.util.Collection;
import java.util.function.Supplier;
import weomucat.doko.DokoConsumer;
import weomucat.doko.HeterogeneousContainers;
import weomucat.doko.command.listener.CommandListener;
import weomucat.doko.command.parameter.ParameterOptions;
import weomucat.doko.exception.DokoException;

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

  abstract DokoConsumer<E> getListenerConsumer();

  /**
   * Update listeners defined in getListenersClass().
   *
   * @throws DokoException If there is anything wrong with processing.
   */
  public void run(HeterogeneousContainers<CommandListener> classListeners) throws DokoException {
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
