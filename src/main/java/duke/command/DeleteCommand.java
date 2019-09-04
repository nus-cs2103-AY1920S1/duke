package duke.command;

import duke.exception.InvalidTaskIndexDukeException;
import duke.task.TaskList;

public class DeleteCommand extends CommandWithIndex {
    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            checkIfIndexIsOutOfBounds(tasks);
            String taskInfo = tasks.getTaskDsc(this.index);
            tasks.deleteTask(this.index);
            return createSuccessfulDeleteMsg(tasks, taskInfo);
        } catch (InvalidTaskIndexDukeException e) {
            Command c = new InvalidCommand("Index is out of bounds");
            return c.execute(tasks);
        }
    }

    private String createSuccessfulDeleteMsg(TaskList tasks, String taskInfo) {
        StringBuilder returnString = new StringBuilder();

        returnString.append("Noted. I've removed this task:\n");
        returnString.append(taskInfo);
        returnString.append("\n");
        returnString.append(createTotalNumOfTaskMsg(tasks));

        return returnString.toString();
    }
}
