package command;

import command.Command;
import date.Date;
import storage.Storage;
import task.*;
import ui.Ui;

/**
 * Represents the adder command to add the task to the task list.
 */
public class CommandAdd extends Command {

    /**
     * The description of the command.
     */
    private String description;

    /**
     * The type of command.
     */
    private String type;

    /**
     * The date stored.
     */
    private Date date;

    /**
     * The task stored.
     */
    private Task task;

    /**
     * Gets the task stored.
     * @return the task stored.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Constructor of the adder command.
     * @param str the string of full command
     */
    public CommandAdd(String str) {
        this.type = str.split(" ", 2)[0];
        if (type.equals("todo")) {
            this.description = str.split(" ", 2)[1];
            Todo temp = new Todo(description);
            this.task = temp;
        } else if (this.type.equals("deadline")){
            String[] firstSplit = new String[2];
            firstSplit = str.split(" /by ");
            // description
            String tempStr = firstSplit[0].split(" ")[1];
            for (int i = 2; i < firstSplit[0].split(" ").length; i++){
                tempStr = tempStr + " " + firstSplit[0].split(" ")[i];
            }
            description = tempStr;
            // timing
            Deadline temp = new Deadline(description, firstSplit[1]);
            this.task = temp;
            this.date = temp.getDate();
        } else {
            String[] firstSplit = new String[2];
            firstSplit = str.split(" /at ");
            // description
            String tempStr = firstSplit[0].split(" ")[1];
            for (int i = 2; i < firstSplit[0].split(" ").length; i++){
                tempStr = tempStr + " " + firstSplit[0].split(" ")[i];
            }
            description = tempStr;
            // timing
            Event temp = new Event(description, firstSplit[1]);
            this.task = temp;
            this.date = temp.getDate();
        }
        this.string = type + description + date;
    }

    /**
     * Executes the add command.
     * @param tasks the task list
     * @param ui the UI
     * @param storage the storage writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Got it. I've added this task:");
        tasks.add(task);
        String tempStr;
        tempStr = task.toString();
        ui.printString(tempStr);
        ui.printString("Now you have " + tasks.getNoOfTasks() + " tasks in the list.");
        storage.save(tasks.getList(), tasks.getNoOfTasks());
    }

    /**
     * Executes the add command in GUI.
     * @param tasks the task list
     * @param storage the storage writer
     * @return string
     */
    public String executeForGUI(TaskList tasks, Storage storage) {
        String string = "Got it. I've added this task:" + "\n";
        tasks.add(task);
        string = string + task.toString() + "\n";
        string = string + "Now you have " + tasks.getNoOfTasks() + " tasks in the list.";
        storage.save(tasks.getList(), tasks.getNoOfTasks());
        return string;
    }

    /**
     * Returns the string representation of the task command.
     * @return the task stored.
     */
    @Override
    public String toString() {
        return this.string;
    }

}
