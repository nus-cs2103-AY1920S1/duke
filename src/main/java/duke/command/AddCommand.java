package duke.command;

import error.task.TaskCreationException;
import error.UnknownCommandException;
import duke.task.tasks.Task;
import duke.task.TasksController;
import duke.task.tasks.TaskKeyword;
import util.OutputBuilder;
import util.DukeOutput;

import java.util.stream.Stream;

/***
 * <p>
 * Command to add tasks.
 * </p>
 */
public class AddCommand implements Command {
    private TasksController tasksController;
    private TaskKeyword taskKeyword;
    private String arguments;

    /***
     * <p>
     * AddCommand constructor.
     * </p>
     * @param keyword identifier for duke.task types.
     * @param arguments corresponding arguments for duke.task type.
     * @param tasksController controller for duke.task list to be added to.
     * @throws UnknownCommandException if keyword does not correspond to existing duke.task.
     */
    AddCommand(String keyword, String arguments, TasksController tasksController) throws UnknownCommandException {
        this.tasksController = tasksController;
        this.taskKeyword = parseKeyword(keyword);
        this.arguments = arguments;
    }

    /***
     * <p>
     * Adds duke.task to memory.
     * </p>
     * @return listen duke.command.
     */
    @Override
    public void execute() {
        try {
            Task task = taskKeyword.taskProducer.getTask(arguments);
            tasksController.addTask(task);
        } catch (TaskCreationException e) {
            DukeOutput.printMessage(new OutputBuilder(e.getTaskErrorMessage()));
        }
    }

    private TaskKeyword parseKeyword(String keyword) throws UnknownCommandException {
        return Stream.of(TaskKeyword.values())
                .filter(taskKeyWord -> taskKeyWord.keyword.equals(keyword))
                .findFirst()
                .orElseThrow(UnknownCommandException::new);
    }
}
