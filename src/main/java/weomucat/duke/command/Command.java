package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.CommandListener;
import weomucat.duke.command.listener.CommandListenerConsumer;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

/**
 * A Command takes in certain parameters, then performs a set of instructions.
 *
 * @param <E> command listeners that this command should update when run
 */
public abstract class Command<E extends CommandListener> {

  private Collection<E> listeners;

  Command(Collection<E> listeners) {
    this.listeners = listeners;
  }

  void forEachListener(CommandListenerConsumer<E> consumer) throws DukeException {
    for (E listener : this.listeners) {
      consumer.accept(listener);
    }
  }

  /**
   * Gets the parameter options of this Command.
   *
   * @return the parameter options
   */
  public abstract ParameterOptions getParameterOptions();

  /**
   * Perform some instructions.
   *
   * @throws DukeException If there is anything wrong with processing.
   */
  public abstract void run() throws DukeException;
}
