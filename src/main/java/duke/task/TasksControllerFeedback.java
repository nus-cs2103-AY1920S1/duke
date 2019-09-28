package duke.task;

import duke.command.sort.TaskSorts;
import duke.task.Task;
import util.strings.OutputBuilder;

import java.util.List;
import java.util.Optional;

class TasksControllerFeedback {
    String displayAllTasks(List<Task> tasks) {
        OutputBuilder builder = new OutputBuilder();

        if (tasks.size() == 0) {
            builder.append("0 tasks remaining you have.");
            return builder.build();
        }

        builder.append(tasks.size() + " tasks remaining:")
                .newLine()
                .appendTasks(tasks);

        if (tasks.stream().filter(task -> !task.isTaskDone()).count() >= 10) {
            builder.newLine()
                    .newLine()
                    .append("You have more than 10 undone tasks on your list.")
                    .newLine()
                    .append("I have you now. Give yourself to the Dark Side.")
                    .newLine()
                    .append("/vader/");

            return builder.build();
        }

        if (tasks.stream().filter(task -> !task.isTaskDone()).count() >= 5) {
            builder.newLine()
                    .newLine()
                    .append("Hmmm. More than 5 tasks, I sense undone.")
                    .newLine()
                    .append("Once you start down the dark path, forever will it dominate your destiny, "
                            + "consume you it will.");

            return builder.build();
        }

        return builder.build();
    }

    String displayMatchingTasks(List<Task> tasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Matching tasks these are, I sense:")
                .newLine()
                .appendTasks(tasks);

        return builder.build();
    }

    String displayTaskAdded(Task task, Optional<Integer> numTasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Do or do not. There is no try. Added this task I have:")
                .newLine()
                .indent()
                .append(task.getTaskDescription());

        numTasks.ifPresent(num -> builder
                .newLine()
                .append(String.format("%d tasks now you have", num)));

        return builder.build();
    }

    String displayTaskSetToDone(Optional<Task> modifiedTask) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Praise you I must! Done is this task:");

        modifiedTask.ifPresent(task -> builder
                .newLine()
                .indent()
                .append(task.getTaskDescription()));

        return builder.build();
    }

    String displayTaskSetToUndone(Optional<Task> modifiedTask) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Not if anything to say about it I have! Undone is this task:");

        modifiedTask.ifPresent(task -> builder
                .newLine()
                .indent()
                .append(task.getTaskDescription()));

        return builder.build();
    }


    String displayTaskDeleted(Task deletedTask, Optional<Integer> numTasks) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Herh herh herh. Deleted this task I have:")
                .newLine()
                .indent()
                .append(deletedTask.getTaskDescription());

        numTasks.ifPresent(num -> builder
                .newLine()
                .append(String.format("%d tasks now you have", num)));

        return builder.build();
    }

    String displayTasksSorted(TaskSorts sortingMethod) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Underestimate the force you will not. Sorted these tasks are. ");

        return builder.build();
    }

    String displayAllTasksDeleted() {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Gone they are! Yes, hmmm.")
                .newLine()
                .append("0 tasks left you have.");

        return builder.build();
    }

    public String displayTaskReplaced(Task modifiedTask) {
        OutputBuilder builder = new OutputBuilder();
        builder.append("Replaced your task, I have done:")
                .newLine()
                .indent()
                .append(modifiedTask.getTaskDescription());

        return builder.build();
    }
}
