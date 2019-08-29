package weijie.duke.repos;

import weijie.duke.exceptions.DukeIOException;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    int getSize();
    T get(int id);
    void create(T entity) throws DukeIOException;
    void update(int id, T entity) throws DukeIOException;
    void delete(int id);
}
