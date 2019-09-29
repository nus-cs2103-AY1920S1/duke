package duke.command;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import java.util.NoSuchElementException;

public abstract class AddTaskCommand implements Command {
    String restOfCommand;

    AddTaskCommand(String restOfCommand) {
        this.restOfCommand = restOfCommand;
//        this.s = new Scanner(command);
//        String taskTypeString = s.next();
//
//        if (taskTypeString.equals("todo")) {
//            return new AddTodoCommand(s.nextLine());
//        } else if (taskTypeString.equals("event")) {
//            this.taskType = TaskType.EVENT;
//        } else if (taskTypeString.equals("deadline")) {
//            this.taskType = TaskType.DEADLINE;
//        } else {
//            throw new DukeException(ExceptionType.INVALID_COMMAND);
//        }
    }

    abstract String getDescription();
    abstract String getDeadline();

    abstract Task createTask();

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = createTask();
            tasks.add(newTask);
        } catch (NoSuchElementException e) {
            // user input after task type is blank
            ui.showError(new DukeException(ExceptionType.DESCRIPTION_BLANK));
        }
    }
}
