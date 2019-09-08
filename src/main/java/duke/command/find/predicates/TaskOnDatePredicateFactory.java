package duke.command.find.predicates;

import duke.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.function.Predicate;

class TaskOnDatePredicateFactory extends TaskDateBasedPredicateFactory {
    TaskOnDatePredicateFactory() {
        super("/on");
    }

    private static Predicate<LocalDateTime> createOccurredOnDatePredicate(LocalDate date) {
        final LocalDateTime dateStartTime = date.atStartOfDay();
        final LocalDateTime dateEndTime = date.atTime(23, 59, 59);
        return (givenDateTime) -> {
            return givenDateTime.isAfter(dateStartTime) && givenDateTime.isBefore(dateEndTime);
        };
    }

    @Override
    public Predicate<Task> createPredicate(String command) {
        final TemporalAccessor userPartialDateTime = extractDateTimeArgument(command);
        Predicate<LocalDateTime> taskDateTimePredicate = null;

        if (userPartialDateTime instanceof LocalDateTime) {
            final LocalDateTime userDateTime = ((LocalDateTime) userPartialDateTime);
            taskDateTimePredicate = userDateTime::isEqual;
        } else if (userPartialDateTime instanceof LocalDate) {
            final LocalDate userDate = (LocalDate) userPartialDateTime;
            taskDateTimePredicate = createOccurredOnDatePredicate(userDate);
        }

        final Predicate<LocalDateTime> finalTaskDateTimePredicate = taskDateTimePredicate;
        return (task) -> {
            LocalDateTime taskDate = getTaskDate(task);
            if (taskDate == null) {
                return false;
            }

            return finalTaskDateTimePredicate.test(taskDate);
        };
    }
}
