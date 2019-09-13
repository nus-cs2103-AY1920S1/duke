package weomucat.duke.command;

import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.CommandListener;
import weomucat.duke.command.listener.CommandListenerConsumer;
import weomucat.duke.exception.DukeException;
import weomucat.duke.parser.CommandParser;

/**
 * A Command takes in certain parameters, then performs a set of instructions.
 *
 * @param <E> command listeners that this command should update when run
 */
public abstract class Command<E extends CommandListener> {

  private Collection<E> listeners;

  public Command(Collection<E> listeners) {
    this.listeners = listeners;
  }

  void forEachListener(CommandListenerConsumer<E> consumer) throws DukeException {
    for (E listener : this.listeners) {
      consumer.accept(listener);
    }
  }

  /**
   * The parameters that this Command takes in.
   */
  public abstract String[] getParameterOptions();

  /**
   * Sets the parameter of this Command to those from user input.
   *
   * @param body       the body from user input. See {@link CommandParser}
   * @param parameters the parameters from user input
   * @throws DukeException If any parameter given is wrong.
   */
  public abstract void setParameters(String body, HashMap<String, String> parameters)
      throws DukeException;

  /**
   * Perform some instructions.
   *
   * @throws DukeException If there is anything wrong with processing.
   */
  public abstract void run() throws DukeException;
}
