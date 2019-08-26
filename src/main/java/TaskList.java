import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /** Adds a new to-do */
    public Task addTodo(String command) throws EmptyFieldException, InvalidCommandFormatException {
        try {
            //parse description from command string
            int commandLength = "todo ".length();
            String description = command.substring(commandLength).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of a task cannot be empty mate.");
            }

            Task task = new Todo(description);

            tasks.add(task);

            return task;
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme a todo with the right format: " +
                    "'todo [description]'");
        }
    }

    /** Adds a new deadline */
    public Task addDeadline(String command) throws EmptyFieldException, InvalidCommandFormatException {
        try {
            int byLength = "/by".length();
            int commandLength = "deadline ".length();
            int byIndex = command.indexOf("/by"); // look for '/by' keyword from command

            // parse description from command
            String description = command.substring(commandLength, byIndex).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of a deadline cannot be empty mate.");
            }

            // parse by-datetime from command
            String by = command.substring(byIndex + byLength).trim();
            if (by.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The date of a deadline cannot be empty mate.");
            }
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
            TemporalAccessor parsedBy = fmt.parseBest(by, LocalDateTime::from, LocalDate::from);

            Task task = null;

            // create task depending on given whether a by-time is given
            if (parsedBy instanceof LocalDateTime) {
                task = new Deadline(description, (LocalDateTime) parsedBy);
            } else if (parsedBy instanceof LocalDate) {
                task = new Deadline(description, ((LocalDate) parsedBy).atStartOfDay(), true );
            }

            tasks.add(task);

            return task;

        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme a deadline with the right format: " +
                    "'deadline [description] /by [datetime]'\n\t" +
                    "datetime format: d/M/yyyy[ HHmm]");
        }
    }

    /** Adds a new event */
    public Task addEvent(String command) throws EmptyFieldException, InvalidCommandFormatException {
        try {
            int atLength = "/at".length();
            int commandLength = "event ".length();
            int atIndex = command.indexOf("/at"); // look for '/at' keyword from command

            // parse description from command
            String description = command.substring(commandLength, atIndex).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of an event cannot be empty mate.");
            }

            // parse at-datetime from command
            String at = command.substring(atIndex + atLength).trim();
            if (at.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The start & end dates of an event cannot be empty mate.");
            }
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
            DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("[d/M/yyyy][ ][HHmm]");
            Task task = null;
            int dashIndex = at.indexOf("-");
            if (dashIndex != -1) {
                String atStart = at.substring(0, dashIndex).trim();
                String atEnd = at.substring(dashIndex + 1).trim();
                TemporalAccessor parsedStart = fmt.parseBest(atStart, LocalDateTime::from, LocalDate::from);
                TemporalAccessor parsedEnd = fmt2.parseBest(atEnd,
                        LocalDateTime::from, LocalDate::from, LocalTime::from);
                if ((parsedStart instanceof LocalDateTime) && (parsedEnd instanceof LocalDateTime)) {
                    task = new Event(description, (LocalDateTime) parsedStart,
                            (LocalDateTime) parsedEnd);
                } else if ((parsedStart instanceof LocalDate) && ((parsedEnd instanceof LocalDate))) {
                    task = new Event(description, ((LocalDate) parsedStart).atStartOfDay(),
                            ((LocalDate) parsedEnd).atStartOfDay(), true);
                } else if ((parsedStart instanceof LocalDate) && (parsedEnd instanceof LocalTime)) {
                    task = new Event(description, ((LocalDate) parsedStart).atStartOfDay(),
                            ((LocalTime) parsedEnd).atDate((LocalDate) parsedStart), true);
                } else {
                    throw new InvalidCommandFormatException("OOPS!!! Please gimme an event with the right format: " +
                            "'event [description] /at [start datetime] - [end datetime]'\n\t" +
                            "datetime format: d/M/yyyy HHmm");
                }
            } else {
                TemporalAccessor parsedStart = fmt.parseBest(at, LocalDateTime::from, LocalDate::from);
                if (parsedStart instanceof LocalDateTime) {
                    task = new Event(description, (LocalDateTime) parsedStart);
                } else if (parsedStart instanceof LocalDate) {
                    task = new Event(description, ((LocalDate) parsedStart).atStartOfDay(), true );
                }
            }

            tasks.add(task);

            return task;

        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme an event with the right format: " +
                    "'event [description] /at [start datetime] - [end datetime]'\n\t" +
                    "datetime format: d/M/yyyy HHmm");
        }
    }

    /** Marks a task as done */
    public Task doneTask(String command) throws InvalidIndexException {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            Task task = tasks.get(taskIndex - 1);
            task.setDone(true);

            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    /** Deletes a task from the list permanently */
    public Task deleteTask(String command) throws InvalidIndexException {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            Task task = tasks.remove(taskIndex - 1);

            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
