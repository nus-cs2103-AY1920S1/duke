import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(formatMessage("Hello, I'm Duke\nWhat can I do for you?"));

        String command = sc.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                String list = "";
                for (int i = 0; i < tasks.size(); i++) {
                    list += String.format(
                            "%d.[%s]" + tasks.get(i).getName() + "\n",
                            i + 1,
                            tasks.get(i).getMark());
                }
                System.out.println(formatMessage(list));
            } else if (command.length() > 3 && command.substring(0, 4).equals("done")) {
                int taskNo = Integer.parseInt(command.split(" ")[1]);
                tasks.get(taskNo - 1).setDone(true);
                String message = "Nice! I've marked this task as done:\n";
                message += String.format(
                        "  [%s]" + tasks.get(taskNo - 1).getName() + "\n",
                        tasks.get(taskNo - 1).getMark());
                System.out.println(formatMessage(message));
            } else {
                tasks.add(new Task(command));
                System.out.println(formatMessage("added: " + command));
            }
            command = sc.nextLine();
        }
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
    }

    private static String formatMessage(String message) {
        String formatted = "    ____________________________________________________________\n";
        String[] lines = message.split("\n");
        for (String line : lines) {
            formatted += "     " + line + "\n";
        }
        formatted += "    ____________________________________________________________\n";

        return formatted;
    }
}
