import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Duke {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        UserInterface ui = new UserInterface();

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
                    final String formattedTask = String.format("%d.%s", listIdx, formatTask(task));
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

                    StringJoiner successMessage = UserInterface.createStringJoiner("Nice! I've marked this task as done:");
                    successMessage.add("  " + formatTask(t));

                    ui.printBlock(successMessage.toString());
                } else if (input.startsWith("delete ")) {
                    final String[] command = input.split(" ");
                    final int taskIndex = Integer.parseInt(command[1]);

                    Task t = tasks.get(taskIndex - 1);
                    tasks.remove(taskIndex - 1);

                    StringJoiner successMessage = UserInterface.createStringJoiner("Noted. I've removed this task:");
                    successMessage.add("  " + formatTask(t));
                    successMessage.add(String.format("Now you have %d tasks in the list.", tasks.size()));

                    ui.printBlock(successMessage.toString());
                } else {
                    try{
                        Task t = null;

                        final String COMMAND_TOKEN_TODO = "todo ";
                        final String COMMAND_TOKEN_DEADLINE = "deadline ";
                        final String COMMAND_TOKEN_EVENT = "event ";

                        if (input.startsWith(COMMAND_TOKEN_TODO)) {
                            String todoDescription = input.substring(COMMAND_TOKEN_TODO.length());

                            if(todoDescription.isEmpty()){
                                throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
                            }

                            t = new Todo(todoDescription);
                        } else if (input.startsWith(COMMAND_TOKEN_DEADLINE)) {
                            final String[] deadlineArgs = input.substring(COMMAND_TOKEN_DEADLINE.length()).split(" /by ");
                            final String deadlineDescription = deadlineArgs[0];
                            final String deadlineDue = deadlineArgs[1];

                            t = new Deadline(deadlineDescription, deadlineDue);
                        } else if (input.startsWith(COMMAND_TOKEN_EVENT)) {
                            final String[] eventArgs = input.substring(COMMAND_TOKEN_EVENT.length()).split(" /at ");
                            final String eventDescription = eventArgs[0];
                            final String eventDateTime = eventArgs[1];

                            t = new Event(eventDescription, eventDateTime);
                        } else {
                            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                        }

                        tasks.add(t);

                        StringJoiner successMessage = UserInterface.createStringJoiner("Got it. I've added this task: ");
                        successMessage.add("  " + formatTask(t));
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

    /**
     * Convenience function to format a Task differently based on its subtype (i.e. Todo/Deadline/Event).
     *
     * @param t Task instance to represent as a string, can be Todo, Deadline or Event.
     * @return A textual representation of the given Task.
     */
    private static String formatTask(Task t) {
        String taskType = null;
        String description = null;

        if (t instanceof Todo) {
            taskType = "T";
            description = t.getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            taskType = "D";
            description = String.format("%s (by: %s)", d.getDescription(), d.getDeadline());
        } else if (t instanceof Event) {
            Event e = (Event) t;
            taskType = "E";
            description = String.format("%s (at: %s)", e.getDescription(), e.getEventDateTime());
        }

        return String.format("[%s][%s] %s", taskType, t.getStatusIcon(), description);
    }
}
