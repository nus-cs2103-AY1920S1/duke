package command;

import tasks.DeadLine;
import tasks.Event;
import tasks.ToDo;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.io.IOException;

/**
 * AddCommand is a Command, specifically for todo, event and deadlines.
 */
public class AddCommand extends Command {

    private String type;
    private String taskDescription;
    private String time;

    /**
     * Constructor for AddCommand, for type of: event or deadlines.
     */
    public AddCommand(String type, String taskDescription, String time) {
        this.type = type;
        this.taskDescription = taskDescription;
        this.time = time;
    }

    /**
     * Constructor for AddCommand, for type of: Todo.
     */
    public AddCommand(String type, String taskDescription) {
        this.type = type;
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the given add task.
     * @param tasks A {@Code: TaskList} object
     * @param ui A {@Code: Ui} object
     * @param storage A {@Code: Storage} object
     * @return A String representing the information of the executed task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        String output = "";

        switch (type) {
        case "todo": {
            // Create ToDo object
            ToDo t = new ToDo(taskDescription);
            tasks.addTask(t);

            // Saving to text file
            String textToAdd = "T | 0 | " + taskDescription + "\n";
            storage.saveTask(textToAdd);

            // Save output as String
            output += "\n\tGot it! I've added this task: ";
            output += "\n\t" + t.toString();
            output += "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
            break;
        }
        case "deadline": {
            // Create DeadLine object
            DeadLine d = new DeadLine(taskDescription, time);
            tasks.addTask(d);

            // Saving to text file
            String textToAdd = "D | 0 | " + taskDescription + " | " + time + "\n";
            storage.saveTask(textToAdd);

            // Save output as String
            output += "\n\tGot it! I've added this task: ";
            output += "\n\t" + d.toString();
            output += "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
            break;
        }
        case "event": {
            // Create Event object
            Event e = new Event(taskDescription, time);
            tasks.addTask(e);

            // Saving to text file
            String textToAdd = "E | 0 | " + taskDescription + " | " + time + "\n";
            storage.saveTask(textToAdd);

            // Save output as String
            output += "\n\tGot it! I've added this task: ";
            output += "\n\t" + e.toString();
            output += "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
            break;
        }
        default:
            assert (false) : "Something went wrong in AddCommand";
        }
        return output;
    }
}