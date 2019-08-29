package duke.storage;

import duke.exception.CorruptedDataException;
import duke.exception.MissingFileExeception;
import duke.exception.WrongDateFormatException;
import duke.task.Task;
import duke.tasklist.MyList;

import java.io.IOException;

public interface DukeStorage {
    public void updateList(MyList taskList) throws IOException;
    public MyList loadList() throws MissingFileExeception, CorruptedDataException, WrongDateFormatException;
}
