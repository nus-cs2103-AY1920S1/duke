package logic;

import java.util.List;

public interface DukeList<T> {
    List<T> getList();

    void add(T t);

    List<T> find(String keyword);

    void delete(String num) throws DukeException;
}
