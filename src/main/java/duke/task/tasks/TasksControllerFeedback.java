package duke.task.tasks;

import duke.command.entities.TaskSorts;
import duke.task.Task;
import util.OutputBuilder;

import java.util.List;
import java.util.Optional;

public class TasksControllerFeedback {
    public String displayAllTasks(List<Task> tasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Here are the tasks in your list:")
                .newLine()
                .appendTasks(tasks);

        return builder.build();
    }

    public String displayMatchingTasks(List<Task> tasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Here are the matching tasks in your list:")
                .newLine()
                .appendTasks(tasks);

        return builder.build();
    }

    public String displayTaskAdded(Task task, Optional<Integer> numTasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Got it. I've added this task:")
                .newLine()
                .indent()
                .append(task.getTaskDescription());

        numTasks.ifPresent(num -> builder
                .newLine()
                .append(String.format("Now you have %d tasks in the list", num)));

        return builder.build();
    }

    public String displayTaskSetToDone(Optional<Task> modifiedTask) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Nice! I've marked this task as done!");

        modifiedTask.ifPresent(task -> builder
                .newLine()
                .indent()
                .append(task.getTaskDescription()));

        return builder.build();
    }

    public String displayTaskDeleted(Task deletedTask, Optional<Integer> numTasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I've removed this task:")
                .newLine()
                .indent()
                .append(deletedTask.getTaskDescription());

        numTasks.ifPresent(num -> builder
                .newLine()
                .append(String.format("Now you have %d tasks in the list", num)));

        return builder.build();
    }

    public String displayTasksSorted(TaskSorts sortingMethod) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I have sorted your tasks according to " + sortingMethod.keyword);

        return builder.build();
    }

    public String displayAllTasksDeleted() {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Noted. I have deleted all your tasks.")
                .newLine()
                .append("Now you have 0 tasks in the list.");

        return builder.build();
    }
}
