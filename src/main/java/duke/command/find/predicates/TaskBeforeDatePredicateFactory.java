package duke.command.find.predicates;

import duke.model.Task;

import java.time.LocalDateTime;
import java.util.function.Predicate;

class TaskBeforeDatePredicateFactory extends TaskDateBasedPredicateFactory {
    TaskBeforeDatePredicateFactory() {
        super("/before");
    }

    @Override
    public Predicate<Task> createPredicate(String command) {
        final LocalDateTime userDateTime = extractDateTimeArgument(command, (enteredDate) -> {
            return enteredDate.atTime(23, 59, 59);
        });

        return (task) -> {
            final LocalDateTime taskDate = getTaskDate(task);
            if (taskDate == null) {
                return false;
            }

            return taskDate.isBefore(userDateTime);
        };
    }
}
