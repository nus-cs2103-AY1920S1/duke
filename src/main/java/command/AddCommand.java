package command;

import error.task.TaskCreationException;
import error.UnknownCommandException;
import task.Task;
import task.TaskListController;
import task.tasks.TaskKeyword;
import util.DukeMessage;
import util.DukeOutput;

import java.util.Optional;
import java.util.stream.Stream;

public class AddCommand implements Command {
    private TaskListController taskListController;
    private TaskKeyword taskKeyword;
    private String arguments;

    AddCommand(TaskListController taskListController, String keyword, String arguments) throws UnknownCommandException {
        this.taskListController = taskListController;
        this.taskKeyword = parseKeyword(keyword);
        this.arguments = arguments;
    }

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
