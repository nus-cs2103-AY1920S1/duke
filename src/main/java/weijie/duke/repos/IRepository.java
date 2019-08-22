package weijie.duke.repos;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    T get(int id);
    void create(T entity);
    void update(int id, T entity);
}
