package parser;

import exception.ParseFileException;
import task.Event;
import task.Task;
import task.ToDo;
import task.Deadline;

public class StorageParser {

    public Task parseLine(String line) throws ParseFileException {
        String[] splited = line.split("//");
        if(splited[0].equals("T")) {
            ToDo curr_task = new ToDo(splited[1], Boolean.parseBoolean(splited[2]));
            return curr_task;
        } else if(splited[0].equals("D")) {
            Deadline curr_task = new Deadline(splited[1], Boolean.parseBoolean(splited[2]), splited[3]);
            return curr_task;
        } else if(splited[0].equals("E")) {
            Event curr_task = new Event(splited[1], Boolean.parseBoolean(splited[2]), splited[3]);
            return curr_task;
        } else {
            throw new ParseFileException("Exception while reading contents of state file!");
        }
    }
}