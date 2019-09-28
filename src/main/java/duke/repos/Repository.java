package duke.repos;

import duke.task.Task;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    void create(T entity) throws IOException;

    List<Task> find(String query);

    Task delete(int index) throws IOException;

    void done(int index) throws IOException;

    List<Task> sort();
}
