package duke.storage;

import duke.exception.CorruptedDataException;
import duke.exception.MissingFileExeception;
import duke.exception.WrongDateFormatException;
import duke.task.Task;
import duke.tasklist.MyList;

import java.io.IOException;

public interface DukeStorage {
    void updateList(MyList taskList) throws IOException;
    MyList loadList() throws MissingFileExeception, CorruptedDataException, WrongDateFormatException;
}
