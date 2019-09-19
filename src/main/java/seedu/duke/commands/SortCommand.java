package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.InvalidSortFlagException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortCommand extends Command {
    public SortCommand(String arg, TaskList taskList) {
        super(arg, taskList);
    }

    @Override
    public String execute() throws DukeException {
        return sort(arg);
    }

    /**
     * Sorts the {@code taskList}.
     * @param arg The flags.
     * @return The response.
     */
    private String sort(String arg) throws DukeException {
        String[] strs = arg.split(" ");
        if (strs.length > 1) {
            throw new InvalidSortFlagException();
        }

        Optional<SortFlag> sortFlag = Stream.of(SortFlag.values()).filter(x -> x.flag.equals(arg)).findFirst();
        if (sortFlag.isEmpty()) {
            // no match with any flags
            throw new InvalidSortFlagException();
        }

        taskList.sort(sortFlag.get().getComparator());
        return "I have sorted your tasks!\n" + taskList.toString();
    }

    public static String getModes() {
        return Stream.of(SortFlag.values()).map(x -> x.flag).collect(Collectors.joining(", "));
    }

    /**
     * The possible sorting modes.
     */
    enum SortFlag {
        CHRONO("chrono", (x, y) -> {
            if (x.getDate() != null && y.getDate() != null) {
                return x.getDate().compareTo(y.getDate());
            } else if (x.getDate() != null) {
                return -1;
            } else if (y.getDate() != null) {
                return 1;
            } else {
                return 0;
            }
        }),
        LEXI("lexi", (x, y) -> {
            return x.getDescription().compareTo(y.getDescription());
        }),
        DONE("done", (x, y) -> {
            if (x.isDone() && !y.isDone()) {
                return 1;
            } else if (!x.isDone() && y.isDone()) {
                return -1;
            } else {
                return 0;
            }
        }),
        DEADLINE("deadline", (x, y) -> {
            if (x instanceof Deadline && y instanceof Deadline) {
                return x.getDate().compareTo(y.getDate());
            } else if (x instanceof Deadline) {
                return -1;
            } else if (y instanceof Deadline) {
                return 1;
            } else {
                return 0;
            }
        }),
        EVENT("event", (x, y) -> {
            if (x instanceof Event && y instanceof Event) {
                return x.getDate().compareTo(y.getDate());
            } else if (x instanceof Event) {
                return -1;
            } else if (y instanceof Event) {
                return 1;
            } else {
                return 0;
            }
        }),
        TODO("todo", (x, y) -> {
            if (x instanceof Todo && !(y instanceof Todo)) {
                return -1;
            } else if (y instanceof Todo && !(x instanceof Todo)) {
                return 1;
            } else {
                return 0;
            }
        });

        private String flag;
        private Comparator<Task> comparator;

        SortFlag(String flag, Comparator<Task> comparator) {
            this.flag = flag;
            this.comparator = comparator;
        }

        public String getFlag() {
            return flag;
        }

        public Comparator<Task> getComparator() {
            return comparator;
        }
    }
}
