import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Duke {
    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();
        UserInterface ui = new UserInterface();

        ui.printBlock("Hello! I'm Duke\n" +
                "What can I do for you?");
        ui.println();

        boolean isDone = false;
        while (!isDone) {
            final String input = ui.nextLine();

            switch (input) {
            case "bye":
                isDone = true;
                ui.printBlock("Bye. Hope to see you again soon!");
                break;
            case "list":
                StringJoiner taskListDisplay = new StringJoiner(System.lineSeparator());
                int listIdx = 1;
                for (String task : tasks) {
                    String formattedTask = String.format("%d. %s", listIdx, task);
                    taskListDisplay.add(formattedTask);
                    listIdx++;
                }
                ui.printBlock(taskListDisplay.toString());
                break;
            default:
                tasks.add(input);
                ui.printBlock(String.format("added: %s", input));
                ui.println();
            }
        }
    }
}
