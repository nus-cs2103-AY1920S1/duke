import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void printWithIndentation(String content) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + content + "\n" +
                "    ____________________________________________________________\n    ");
    }

    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.matches("bye")) {
                printWithIndentation("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.matches("list")) {
                StringBuilder builder = new StringBuilder("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    builder.append("\n" + "     ");
                    builder.append(i + 1).append(".[").append(tasks.get(i).getStatusIcon())
                            .append("] ").append(tasks.get(i).getDescription());
                }
                printWithIndentation(builder.toString());
            } else if (userInput.matches("done .*")) {
                int doneNo = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(doneNo).markAsDone();
                StringBuilder builder = new StringBuilder("Nice! I've marked this task as done: ");
                builder.append("\n" + "       ");
                builder.append("[").append(tasks.get(doneNo).getStatusIcon())
                        .append("] ").append(tasks.get(doneNo).getDescription());
                printWithIndentation(builder.toString());
            } else {
                tasks.add(new Task(userInput));
                printWithIndentation("added: " + userInput);
            }
        }
    }
}