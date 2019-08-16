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
                StringJoiner taskListDisplay = new StringJoiner(System.lineSeparator());
                int listIdx = 1;

                taskListDisplay.add("Here are the tasks in your list:");
                for (Task task : tasks) {
                    final String formattedTask = String.format("%d.[%s] %s", listIdx, task.getStatusIcon(), task.getDescription());
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

                    StringJoiner successMessage = new StringJoiner(System.lineSeparator());
                    successMessage.add("Nice! I've marked this task as done:");
                    final String formattedTask = String.format("  [%s] %s", t.getStatusIcon(), t.getDescription());
                    successMessage.add(formattedTask);

                    ui.printBlock(successMessage.toString());
                } else {
                    Task t = new Task(input);
                    tasks.add(t);
                    ui.printBlock(String.format("added: %s", t.getDescription()));
                    ui.println();
                }
            }
        }
    }
}
