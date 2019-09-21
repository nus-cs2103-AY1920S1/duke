package weomucat.doko;

import weomucat.doko.exception.DokoException;

public interface DokoConsumer<E> {
  void accept(E listener) throws DokoException;
}
