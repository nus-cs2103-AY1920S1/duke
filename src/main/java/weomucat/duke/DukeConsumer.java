package weomucat.duke;

import weomucat.duke.exception.DukeException;

public interface DukeConsumer<E> {
  void accept(E listener) throws DukeException;
}
