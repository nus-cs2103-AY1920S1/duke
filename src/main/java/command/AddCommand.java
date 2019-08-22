package command;

import task.Task;
import task.TaskListController;
import task.tasks.TaskKeyword;
import task.tasks.ToDo;
import util.DukeMessage;
import util.DukeOutput;

import java.util.Optional;
import java.util.stream.Stream;

public class AddCommand implements Command {
    private Task task;
    private TaskListController taskListController;

    public AddCommand(String keyword, String arguments, TaskListController taskListController) {
        this.taskListController = taskListController;
        this.task = parseTaskType(keyword).taskProducer.apply(arguments);
    }

    @Override
    public Optional<Command> execute() {
        taskListController.addTask(task);

        return Optional.of(new ListenCommand(taskListController));
    }

    private TaskKeyword parseTaskType(String keyword) {
        return Stream.of(TaskKeyword.values())
                .filter(taskKeyWord -> taskKeyWord.keyword.equals(keyword))
                .findFirst()
                .orElseThrow();
    }
}
