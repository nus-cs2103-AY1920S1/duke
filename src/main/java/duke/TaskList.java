package duke;

import duke.task.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task> {
    public void insertByCommand(Ui ui, String taskName, String line) throws DukeException {
        String data = line.replaceFirst("^.*?\\s","");
        //If no change, then it's either empty, or invalid command
        if (line.equals(data)) {
            if (line.equals("todo") || line.equals("event") || line.equals("deadline")) {
                data = "";
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
        String[] splitData;
        try {
            switch (taskName) {
            case "todo":
                if (Task.validateData(data, "todo"))
                    insertNewTask(ui, new Todo(data));
                break;
            case "event":
                splitData = TaskWithDate.extractDataFromLine(data, " /at ");
                if (TaskWithDate.validateData(splitData, "event"))
                    insertNewTask(ui, new Event(splitData[0], splitData[1]));
                break;
            case "deadline":
                splitData = TaskWithDate.extractDataFromLine(data, " /by ");
                if (TaskWithDate.validateData(splitData, "deadline"))
                    insertNewTask(ui, new Deadline(splitData[0], splitData[1]));
                break;
            }
        } catch (ParseException e) {
            throw new DukeException("Failed to add task because date is not in the right format (should be in dd/MM/yyyy HHmm)");
        }
    }

    public void insertNewTask(Ui ui, Task taskToInsert) throws DukeException {
        if(taskToInsert != null) {
            this.add(taskToInsert);
            ui.println("Got it. I've added this task: ");
            ui.println("  " + taskToInsert.toString());
            ui.println("Now you have " + this.size() + " tasks in the list.");
        } else {
            throw new DukeException("Task to insert is undefined");
        }
    }
}
