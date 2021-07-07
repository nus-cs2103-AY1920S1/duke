package weijie.duke.repos;

import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();

    int getSize();

    T get(int id);

    List<T> find(String searchParam);

    void create(T entity) throws DukeIoException;

    void insert(int id, T entity) throws DukeIoException;

    void update(int id, T entity) throws DukeIoException;

    void delete(int id) throws DukeIoException;

    void delete(Task task) throws DukeIoException;
}
