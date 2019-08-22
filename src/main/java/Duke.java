import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Duke {
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    private final TaskSerializer storage;
    private TaskList tasks;
    private final UserInterface ui;

    public Duke(String filePath) {
        ui = new UserInterface();
        storage = new TaskSerializer(Path.of(filePath));

        try {
            tasks = new TaskList(storage.load());
        } catch (FileIOException | TokenParseError exc) {
            if (!(exc.getCause() instanceof NoSuchFileException)) {
                ui.printBlock(" ☹ OOPS!!! " + exc.getMessage());
            }
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printBlock("Hello! I'm Duke\n" +
                "What can I do for you?");
        ui.println();

        boolean isDone = false;
        while (!isDone) {
            final String input = ui.nextLine();
            boolean inputNeedsParsing = false;

            switch (input) {
            case "bye":
                isDone = true;
                ui.printBlock("Bye. Hope to see you again soon!");
                break;
            case "list":
                int listIdx = 1;

                StringJoiner taskListDisplay = UserInterface.createStringJoiner("Here are the tasks in your list:");
                for (Task task : tasks) {
                    final String formattedTask = String.format("%d.%s", listIdx, UserInterface.formatTask(task));
                    taskListDisplay.add(formattedTask);
                    listIdx++;
                }
                ui.printBlock(taskListDisplay.toString());
                break;
            default:
                inputNeedsParsing = true;
            }

            if (inputNeedsParsing) {
                if (input.startsWith("done ")) {
                    final String[] command = input.split(" ");
                    final int taskIndex = Integer.parseInt(command[1]);

                    Task t = tasks.get(taskIndex - 1);
                    t.markAsDone();

                    storage.save(tasks);

                    StringJoiner successMessage = UserInterface.createStringJoiner("Nice! I've marked this task as done:");
                    successMessage.add("  " + UserInterface.formatTask(t));

                    ui.printBlock(successMessage.toString());
                } else if (input.startsWith("delete ")) {
                    final String[] command = input.split(" ");
                    final int taskIndex = Integer.parseInt(command[1]);

                    Task t = tasks.get(taskIndex - 1);
                    tasks.remove(taskIndex - 1);

                    storage.save(tasks);

                    StringJoiner successMessage = UserInterface.createStringJoiner("Noted. I've removed this task:");
                    successMessage.add("  " + UserInterface.formatTask(t));
                    successMessage.add(String.format("Now you have %d tasks in the list.", tasks.size()));

                    ui.printBlock(successMessage.toString());
                } else {
                    try{
                        Task t = null;

                        final String COMMAND_TOKEN_TODO = "todo ";
                        final String COMMAND_TOKEN_DEADLINE = "deadline ";
                        final String COMMAND_TOKEN_EVENT = "event ";
                        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

                        if (input.startsWith(COMMAND_TOKEN_TODO)) {
                            String todoDescription = input.substring(COMMAND_TOKEN_TODO.length());

                            if(todoDescription.isEmpty()){
                                throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
                            }

                            t = new Todo(todoDescription);
                        } else if (input.startsWith(COMMAND_TOKEN_DEADLINE)) {
                            final String[] deadlineArgs = input.substring(COMMAND_TOKEN_DEADLINE.length()).split(" /by ");
                            final String deadlineDescription = deadlineArgs[0];
                            final LocalDateTime deadlineDue = LocalDateTime.parse(deadlineArgs[1], dateTimeFormatter);

                            t = new Deadline(deadlineDescription, deadlineDue);
                        } else if (input.startsWith(COMMAND_TOKEN_EVENT)) {
                            final String[] eventArgs = input.substring(COMMAND_TOKEN_EVENT.length()).split(" /at ");
                            final String eventDescription = eventArgs[0];
                            final LocalDateTime eventDateTime = LocalDateTime.parse(eventArgs[1], dateTimeFormatter);

                            t = new Event(eventDescription, eventDateTime);
                        } else {
                            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                        }

                        tasks.add(t);

                        storage.save(tasks);

                        StringJoiner successMessage = UserInterface.createStringJoiner("Got it. I've added this task: ");
                        successMessage.add("  " + UserInterface.formatTask(t));
                        successMessage.add(String.format("Now you have %d tasks in the list.", tasks.size()));
                        ui.printBlock(successMessage.toString());
                        ui.println();
                    } catch (DukeException exc){
                        ui.printBlock(" ☹ OOPS!!! " + exc.getMessage());
                        ui.println();
                    }
                }
            }
        }
    }
}
