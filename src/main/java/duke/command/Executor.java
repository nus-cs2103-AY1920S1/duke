package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class Executor {
    /**
     * Analyzes a Parser's data and returns a string that responds to the command in the parser using Ui's methods.
     * While executing a command, method also updates TaskList and the database file accordingly.
     * @param commandAnalyzer parser object containing command information
     * @param ui interface that can provide responses to the command
     * @param tasks TaskList to be updated according to the command
     * @param storage Storage object that updates database file 'history'
     * @return string that represents Duke's response to the User's command
     */
    public static String execute(Parser commandAnalyzer, Ui ui, TaskList tasks, Storage storage) {
        String result = "";
        boolean addedTask = true;
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
            } else if (type.equals("deletedone")) {
                try {
                    ArrayList<Task> deletedTasks = new ArrayList<>();
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        if (task.isDone()) {
                            deletedTasks.add(tasks.remove(i));
                            i--;
                        }
                    }
                    if (deletedTasks.size()==0) {
                        result = "No tasks are currently done!";
                    } else {
                        result = ui.showDeletedTasks(deletedTasks, tasks.size());
                    }
                    storage.update(tasks);
                } catch (IOException ie) {
                    result = ui.showException(ie);
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
                        addedTask = tasks.add(temp);
                        if (addedTask) {
                            result = ui.showTaskCreated(temp, tasks.size()); //change arr to TaskList
                            storage.update(tasks);
                        } else {
                            result = ui.showDuplicateTaskMessage(temp);
                        }
                    } catch (IOException de) {
                        result = ui.showException(de);
                    }
                } else if (type.equals("deadline")) {
                    try {
                        Task temp = new Deadline(commandAnalyzer);
                        addedTask = tasks.add(temp);
                        if (addedTask) {
                            result += ui.showTaskCreated(temp, tasks.size());
                            storage.update(tasks);
                        } else {
                            result += ui.showDuplicateTaskMessage(temp);
                        }
                    } catch (IOException de) {
                        result += ui.showException(de);
                    }
                } else if (type.equals("event")) {
                    try {
                        Task temp = new Event(commandAnalyzer);
                        addedTask = tasks.add(temp);
                        if (addedTask) {
                            result += ui.showTaskCreated(temp, tasks.size());
                            storage.update(tasks);
                        } else {
                            result += ui.showDuplicateTaskMessage(temp);
                        }
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
