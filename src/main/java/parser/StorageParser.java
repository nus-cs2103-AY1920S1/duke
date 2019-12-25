package parser;

import exception.ParseFileException;
import task.Event;
import task.Task;
import task.ToDo;
import task.Deadline;

/**
 * Parser for reading contents of state file and creating the relevant tasks to add into TaskList.
 */
public class StorageParser {

    /**
     * Reads a single String line (from the saved state file) to create the relevant task.
     * @param line the line in the state file that represents a task
     * @return Task based on the task created by parsing the line in state file
     * @throws ParseFileException if there is an exception while trying to parse the line in the state file and
     *     create the relevant task
     */
    public Task parseLine(String line) throws ParseFileException {
        String[] splited = line.split("//");
        int length = splited.length;
        //Preliminary check to ensure state file was not corrupted by checking line size (not exhaustive)
        assert (length == 3 || length == 4);
        String taskType = splited[0];
        Task currTask = null;
        switch (taskType) {
        case "T":
            currTask = new ToDo(splited[1], Boolean.parseBoolean(splited[2]));
            break;
        case "D":
            currTask = new Deadline(splited[1], Boolean.parseBoolean(splited[2]), splited[3]);
            break;
        case "E":
            currTask = new Event(splited[1], Boolean.parseBoolean(splited[2]), splited[3]);
            break;
        default:
            throw new ParseFileException("Exception while reading contents of state file!");
        }
        return currTask;
    }
}