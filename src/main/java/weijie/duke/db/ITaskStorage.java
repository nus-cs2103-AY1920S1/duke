package weijie.duke.db;

import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;

import java.util.List;

public interface ITaskStorage {

    List<Task> read() throws DukeIoException;

    void write(List<Task> tasks) throws DukeIoException;
}
