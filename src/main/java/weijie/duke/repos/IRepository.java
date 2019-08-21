package weijie.duke.repos;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    void create(T entity);
}
