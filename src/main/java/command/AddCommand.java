package command;

import java.io.IOException;

import utils.TaskList;
import utils.Ui;
import utils.Storage;
import tasks.DeadLine;
import tasks.Event;
import tasks.ToDo;

/**
 * AddCommand is a Command, specifically for todo, event and deadlines.
 */
public class AddCommand extends Command {

    String type;
    String taskDescription;
    String time;

    /**
     * Constructor for AddCommand, for type of: event or deadlines.
     */
    public AddCommand(String type, String taskDescription, String time) {
        this.type = type;
        this.taskDescription = taskDescription;
        this.time = time;
    }

    /**
     * Constructor for AddCommand, for type of: todo.
     */
    public AddCommand(String type, String taskDescription) {
        this.type = type;
        this.taskDescription = taskDescription;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        String output = "";

        if (type.equals("todo")) {
            // Create ToDo object
            ToDo t = new ToDo(taskDescription);
            tasks.addTask(t);

            // Saving to text file
            String textToAdd = "T | 0 | " + taskDescription + "\n";
            storage.saveTask(textToAdd);

            // Save output as String
            output += ui.getTopBorder();
            output += "\n\tGot it! I've added this task: ";
            output += "\n\t" + t.toString();
            output += "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
            output += ui.getBottomBorder();

        } else if (type.equals("deadline")) {
            // Create DeadLine object
            DeadLine d = new DeadLine(taskDescription, time);
            tasks.addTask(d);

            // Saving to text file
            String textToAdd = "D | 0 | " + taskDescription + " | " + time + "\n";
            storage.saveTask(textToAdd);

            // Save output as String
            output += ui.getTopBorder();
            output += "\n\tGot it! I've added this task: ";
            output += "\n\t" + d.toString();
            output += "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
            output += ui.getBottomBorder();

        } else if (type.equals("event")) {
            // Create Event object
            Event e = new Event(taskDescription, time);
            tasks.addTask(e);

            // Saving to text file
            String textToAdd = "E | 0 | " + taskDescription + " | " + time + "\n";
            storage.saveTask(textToAdd);

            // Save output as String
            output += ui.getTopBorder();
            output += "\n\tGot it! I've added this task: ";
            output += "\n\t" + e.toString();
            output += "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
            output += ui.getBottomBorder();
        }

        return output;
    }
}