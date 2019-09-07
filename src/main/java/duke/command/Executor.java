package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;

public class Executor {
    public static String execute(Parser commandAnalyzer, Ui ui, TaskList tasks, Storage storage) {
        String result = "";
        if (commandAnalyzer.isValid()) {
            String type = commandAnalyzer.getType();
            if (type.equals("done")) {
                int index = Integer.parseInt(commandAnalyzer.getList().get(0)) - 1;
                try {
                    if (index >= tasks.size() || index < 0) {
                        throw new DukeException(" :( OOPS!!! Requested task number is not available");
                    }
                    Task temp = tasks.get(index);
                    temp.markAsDone();
                    result = ui.showTaskDone(temp);
                    storage.update(tasks);
                } catch (DukeException | IOException de) {
                    result = ui.showException(de);
                } catch (NumberFormatException nfe) {
                    result = ui.showNumberFormatError("done");
                }
            } else if (type.equals("bye")) {
                result = ui.showGoodBye();
            } else if (type.equals("list")) {
                result = ui.showTasks(tasks);
            } else if (type.equals("find")) {
                result = ui.showTasks(tasks.find(commandAnalyzer.getList().get(0)));
            } else {
                if (type.equals("todo")) {
                    try {
                        Task temp = new ToDo(commandAnalyzer);
                        tasks.add(temp);
                        result = ui.showTaskCreated(temp, tasks.size()); //change arr to TaskList
                        storage.update(tasks);
                    } catch (IOException de) {
                        result = ui.showException(de);
                    }
                } else if (type.equals("deadline")) {
                    try {
                        Task temp = new Deadline(commandAnalyzer);
                        tasks.add(temp);
                        result += ui.showTaskCreated(temp, tasks.size());
                        storage.update(tasks);
                    } catch (IOException de) {
                        result += ui.showException(de);
                    }
                } else if (type.equals("event")) {
                    try {
                        Task temp = new Event(commandAnalyzer);
                        tasks.add(temp);
                        result += ui.showTaskCreated(temp, tasks.size());
                        storage.update(tasks);
                    } catch (IOException de) {
                        result += ui.showException(de);
                    }
                } else if (type.equals("delete")) {
                    try {
                        int index = Integer.parseInt(commandAnalyzer.getList().get(0)) - 1;
                        if (index >= tasks.size() || index < 0) {
                            throw new DukeException(" :( OOPS!!! Task to be deleted is not available");
                        } else {
                            Task temp = tasks.remove(index);
                            result += ui.showTaskDeleted(temp, tasks.size());
                        }
                        storage.update(tasks);
                    } catch (DukeException | IOException de) {
                        result += ui.showException(de);
                    } catch (NumberFormatException nfe) {
                        result += ui.showNumberFormatError("delete");
                    }
                }
            }
        } else {
            try {
                throw new DukeException(" :( OOPS!!! I'm sorry but I don't know what that means :-(");
            } catch (DukeException de) {
                result += ui.showException(de);
            }
        }
        return result;
    }
}
