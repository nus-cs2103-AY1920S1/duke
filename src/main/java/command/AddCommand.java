package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.*;

public class AddCommand extends Command {

    private String taskType;
    private String taskName;
    private DateTime dateTime;

    public AddCommand(String taskType, String taskDetails) throws DukeException {
        this.taskType = taskType;
        String[] arr = taskDetails.split("/",2);
        this.taskName = arr[0].trim();
        if (taskName.equals("")) {
            throw new DukeException("The description of a " + taskType + " cannot be empty");
        }
        if (!taskType.equals("todo")) {
            try {
                this.dateTime = new DateTime(arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("No date and time found!");
            }
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        switch(taskType){
        case "todo":
            newTask = new ToDo(taskName);
            break;
        case "deadline":
            newTask = new Deadline(taskName, dateTime);
            break;
        case "event":
            newTask = new Event(taskName, dateTime);
            break;
        default:
            throw new DukeException("Invalid task format"); // should never reach here :)
        }
        tasks.addTask(newTask);
        ui.dukeEcho("Got it. I've added this task:",
                newTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }

}
