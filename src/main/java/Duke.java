import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke Chat Class.
 *
 * A Personal Assistant Chatbot that helps to keep track of various things.
 *
 * @author Marcus Ong
 */
public class Duke {
    private static final Path DATA_FILE_PATH = Paths.get(System.getProperty("user.dir") + "/data/duke.txt");
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        try {
            loadTasks();
            chat();
        } catch (DukeIOException e) {
            reply(e.getMessage());
        }
    }

    /** Handles user chat interaction */
    public static void chat() {
        // Create default hi/bye strings
        String logo = " ____        _        \n\t"
                    + "|  _ \\ _   _| | _____ \n\t"
                    + "| | | | | | | |/ / _ \\\n\t"
                    + "| |_| | |_| |   <  __/\n\t"
                    + "|____/ \\__,_|_|\\_\\___|\n\t";
        String greeting = logo + "G'day mate! I'm Duke.\n\tWhatcha need help with?";
        String bye = "Bye mate. Catch you later!";

        Scanner sc = new Scanner(System.in);
        String command = "";

        reply(greeting); //say greetings

        // Begin chat interaction
        do {
            try {
                command = sc.nextLine();
                if (command.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (command.toLowerCase().startsWith("done")) {
                    doneTask(command);
                } else if (command.toLowerCase().startsWith("todo")) {
                    addTodo(command);
                } else if (command.toLowerCase().startsWith("deadline")) {
                    addDeadline(command);
                } else if (command.toLowerCase().startsWith("event")) {
                    addEvent(command);
                } else if (command.toLowerCase().startsWith("delete")) {
                    deleteTask(command);
                } else if (command.equalsIgnoreCase("bye")) {
                    reply(bye); //say goodbye
                } else {
                    throw new UnknownCommandException("OOPS!!! Sorry mate, I don't geddit.");
                }
                saveTasks(); // gracefully save tasks after every command, invalid or not.
            } catch (DukeException e) {
                reply(e.getMessage());
            }
        } while (!command.equalsIgnoreCase("bye"));
    }

    /** Add a new to-do */
    private static void addTodo(String command) throws EmptyFieldException, InvalidCommandFormatException {
        try {
            //parse description from command string
            int commandLength = "todo ".length();
            String description = command.substring(commandLength).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of a task cannot be empty mate.");
            }

            // add task and reply user
            Task task = new Todo(description);
            tasks.add(task);
            replyAddTask(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme a todo with the right format: " +
                    "'todo [description]'");
        }
    }

    /** Add a new deadline */
    private static void addDeadline(String command) throws EmptyFieldException, InvalidCommandFormatException {
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

            // add task and reply user
            tasks.add(task);
            replyAddTask(task);

        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme a deadline with the right format: " +
                    "'deadline [description] /by [datetime]'\n\t" +
                    "datetime format: d/M/yyyy[ HHmm]");
        }
    }

    /** Add a new event */
    private static void addEvent(String command) throws EmptyFieldException, InvalidCommandFormatException {
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

            // add task and reply user
            tasks.add(task);
            replyAddTask(task);

        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme an event with the right format: " +
                    "'event [description] /at [start datetime] - [end datetime]'\n\t" +
                    "datetime format: d/M/yyyy HHmm");
        }
    }

    /**
     * Prints out reply message for adding a task to the user.
     *
     * @param task Task to print out reply for.
     */
    private static void replyAddTask(Task task) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Got it mate. I've added this task:\n\t" + task + "\n\t");
        messageBuilder.append("Now you have " + tasks.size() + " tasks in the list mate.");
        reply(messageBuilder.toString());
    }

    /** Mark a task as done */
    private static void doneTask(String command) throws InvalidIndexException {
        try {
            StringBuilder messageBuilder = new StringBuilder();

            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            Task task = tasks.get(taskIndex - 1);
            task.setDone(true);

            messageBuilder.append("Nice one mate! I've marked this task as done:\n\t");
            messageBuilder.append("  " + task);

            reply(messageBuilder.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    /** Permanently delete a task from the list */
    private static void deleteTask(String command) throws InvalidIndexException {
        try {
            StringBuilder messageBuilder = new StringBuilder();

            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            Task task = tasks.remove(taskIndex - 1);

            messageBuilder.append("Noted mate! I've removed this task:\n\t")
                .append("  " + task + "\n\t")
                .append("Now you have " + tasks.size() + " tasks in the list mate.");

            reply(messageBuilder.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    /** Display list of tasks */
    private static void listTasks() {
        StringBuilder messageBuilder = new StringBuilder();

        if (tasks.size() > 0) {
            messageBuilder.append("Here are the tasks in your list:\n\t");
            for (int i = 0; i < tasks.size(); i++) {
                messageBuilder.append((i+1) + ". " + tasks.get(i) + "\n\t");
            }
            messageBuilder.setLength(messageBuilder.length() - 2); // strip trailing \n\t
        } else {
            messageBuilder.append("No tasks in your list. Add some tasks and get to work mate!");
        }

        reply(messageBuilder.toString());
    }

    /** Save tasks to hard disk */
    private static void saveTasks() throws DukeIOException {
        try {
            if (!Files.exists(DATA_FILE_PATH)) {
                Files.createFile(DATA_FILE_PATH);
            }

            BufferedWriter writer = Files.newBufferedWriter(DATA_FILE_PATH);
            StringBuilder sb = new StringBuilder();

            // write tasks into file
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);

                StringBuilder rowBuilder = new StringBuilder(
                        String.format("%s|%s|%s", task.type.getTag(), task.isDone ? "1" : "0", task.description));

                if (task instanceof Deadline) {
                    rowBuilder.append(String.format("|%s",
                            ((Deadline) task).by.format(DateTimeFormatter.ISO_DATE_TIME)));
                    rowBuilder.append(String.format("|%s",
                            ((Deadline) task).isAllDay ? "1" : "0"));
                } else if (task instanceof Event) {
                    rowBuilder.append(String.format("|%s|%s",
                            ((Event) task).startDateTime.format(DateTimeFormatter.ISO_DATE_TIME),
                            ((Event) task).endDateTime.format(DateTimeFormatter.ISO_DATE_TIME)));
                    rowBuilder.append(String.format("|%s",
                            ((Event) task).isAllDay ? "1" : "0"));
                }

                rowBuilder.append("\n");
                sb.append(rowBuilder);
            }
            writer.write(sb.toString());

            writer.close();
        } catch (IOException e) {
            throw new DukeIOException("OOPS!!! Error trying to save data to file.");
        }
    }

    /** Load tasks from hard disk */
    private static void loadTasks() throws DukeIOException {
        try {
            if (Files.exists(DATA_FILE_PATH)) {
                BufferedReader reader = Files.newBufferedReader(DATA_FILE_PATH);

                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    String type = parts[0];
                    boolean isDone = "1".equals(parts[1]);
                    String description = parts[2];

                    if (type.equalsIgnoreCase(TaskType.TODO.getTag())) {
                        Task task = new Todo(description);
                        task.setDone(isDone);
                        tasks.add(task);
                    } else if(type.equalsIgnoreCase(TaskType.DEADLINE.getTag())) {
                        LocalDateTime byDate = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_DATE_TIME);
                        boolean isAllDay = "1".equals(parts[4]);
                        Task task = new Deadline(description, byDate, isAllDay);
                        task.setDone(isDone);
                        tasks.add(task);
                    } else if (type.equalsIgnoreCase(TaskType.EVENT.getTag())) {
                        LocalDateTime startDate = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_DATE_TIME);
                        LocalDateTime endDate = LocalDateTime.parse(parts[4], DateTimeFormatter.ISO_DATE_TIME);
                        boolean isAllDay = "1".equals(parts[5]);
                        Task task = new Event(description, startDate, endDate, isAllDay);
                        task.setDone(isDone);
                        tasks.add(task);
                    }
                }

                reader.close();
            }
        } catch (IOException e) {
            throw new DukeIOException("OOPS!!! Error trying to load data from file.");
        }
    }

    /**
     * Prints out reply message to the user.
     *
     * @param message String containing reply message.
     */
    public static void reply(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }
}
