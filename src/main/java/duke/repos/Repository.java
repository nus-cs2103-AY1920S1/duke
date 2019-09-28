package duke.repos;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    void create(T entity) throws IOException;

    void insert (int index, T entity);

    void delete(int index);
}
