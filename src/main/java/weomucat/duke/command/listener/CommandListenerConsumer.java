package weomucat.duke.command.listener;

import weomucat.duke.exception.DukeException;

@FunctionalInterface
public interface CommandListenerConsumer<E extends CommandListener> {
  void accept(E listener) throws DukeException;
}
