package command;

import error.task.TaskCreationException;
import error.UnknownCommandException;
import task.tasks.Task;
import task.TaskListController;
import task.tasks.TaskKeyword;
import util.DukeMessage;
import util.DukeOutput;

import java.util.Optional;
import java.util.stream.Stream;

/***
 * <p>
 * Command to add tasks.
 * </p>
 */
public class AddCommand implements Command {
    private TaskListController taskListController;
    private TaskKeyword taskKeyword;
    private String arguments;

    /***
     * <p>
     * AddCommand constructor.
     * </p>
     * @param keyword identifier for task types.
     * @param arguments corresponding arguments for task type.
     * @param taskListController controller for task list to be added to.
     * @throws UnknownCommandException if keyword does not correspond to existing task.
     */
    AddCommand(String keyword, String arguments, TaskListController taskListController) throws UnknownCommandException {
        this.taskListController = taskListController;
        this.taskKeyword = parseKeyword(keyword);
        this.arguments = arguments;
    }

    /***
     * <p>
     * Adds task to memory.
     * </p>
     * @return listen command.
     */
    @Override
    public Optional<Command> execute() {
        try {
            Task task = taskKeyword.taskProducer.getTask(arguments);
            taskListController.addTask(task);
        } catch (TaskCreationException e) {
            DukeOutput.printMessage(new DukeMessage(e.getTaskErrorMessage()));
            return Optional.of(new ListenCommand(taskListController));
        }

        return Optional.of(new ListenCommand(taskListController));
    }

    private TaskKeyword parseKeyword(String keyword) throws UnknownCommandException {
        return Stream.of(TaskKeyword.values())
                .filter(taskKeyWord -> taskKeyWord.keyword.equals(keyword))
                .findFirst()
                .orElseThrow(UnknownCommandException::new);
    }
}
