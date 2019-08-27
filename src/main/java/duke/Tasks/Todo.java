package duke.Tasks;

import duke.DukeException;

/**
 * The Todo subclass of the Task superclass.
 * @Extends duke.Tasks.Task
 */
public class Todo extends Task {

    /**
     * Constructor of the class, same as its super class.
     * @param taskName the task name.
     * @throws DukeException If the task name is empty.
     */
    public Todo(String taskName) throws DukeException {
        super(taskName);
    }

    /**
     * This method returns the information of the task FOR THE USER to see.
     * Output of this method is usually handled by Ui class.
     * @return The information of the task, in form [type][finished] task name. For example, [T][X] Eat dinner.
     */
    @Override
    public String taskInfo() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[T]" + indicator + getName();
    }

    /**
     * This method returns the information of the task FOR SAVING INTO A FILE.
     * Output of this method is usually handled by the task list.
     * @return The information of the task, in form type|finished|task name. For example, T|0|Eat dinner.
     */
    @Override
    public String recordInfo() {
        if (isFinished()) return "T|" + "1|" + getName();
        else return "T|" + "0|" + getName();
    }
}
