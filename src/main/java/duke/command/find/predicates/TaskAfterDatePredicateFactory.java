package duke.command.find.predicates;

import duke.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

class TaskAfterDatePredicateFactory extends TaskDateBasedPredicateFactory {
    TaskAfterDatePredicateFactory() {
        super("/after");
    }

    @Override
    public Predicate<Task> createPredicate(String command) {
        final LocalDateTime userDateTime = extractDateTimeArgument(command, LocalDate::atStartOfDay);

        return (task) -> {
            final LocalDateTime taskDate = getTaskDate(task);
            if (taskDate == null) {
                return false;
            }

            return taskDate.isAfter(userDateTime);
        };
    }
}
