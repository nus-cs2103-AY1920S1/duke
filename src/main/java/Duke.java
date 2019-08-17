import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FRONTSPACES = "     "; // 5 spaces
    private static final String LINE = "    ____________________________________________________________\n";

    private List<Task> taskList;

    public Duke() {
        taskList = new LinkedList<>();
    }

    public static void main(String[] args) {
        new Duke().start();
    }

    private void start() {
        printWelcomeMessage();
        receiveCommand();
    }

    public void receiveCommand() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine().trim();

            if (command.startsWith("todo")) {
                String topic = command.substring(4).trim();
                addTask(new ToDo(taskList.size() + 1, topic));
            } else if (command.startsWith("deadline")) {
                String[] details = command.substring(8).trim().split("/by");
                String topic = details[0].stripTrailing();
                String deadline = details[1].stripLeading();
                addTask(new Deadline(taskList.size() + 1, topic, deadline));
            } else if (command.startsWith("event")) {
                String[] details = command.substring(5).trim().split("/at");
                String topic = details[0].stripTrailing();
                String date = details[1].stripLeading();
                addTask(new Event(taskList.size() + 1, topic, date));
            } else if ("list".equals(command)) {
                echo(taskList, "Here are the tasks in your list:");
            } else if (command.startsWith("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                markTaskAsDone(index);
           } else if ("bye".equals(command)) {
                echo("Bye. Hope to see you again!");
                break;
            } else {

            }
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
        List<String> middle = new LinkedList<>();
        middle.add(String.format("  %s", task.getDescription()));
        echo(new String[]{"Got it. I've added this task:"},
                middle,
                new String[]{String.format("Now you have %d tasks in the list.", taskList.size())});
    }

    public void markTaskAsDone(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        echo("Nice! I've marked this task as done:",
                String.format("  [\u2713] %s", task.getTitle()));
    }

    /**
     * print the strings provided line by line, enclosed within two lines.
     */
    public void echo(String... strings) {
        echo(strings, null, null);
    }

    /**
     * print the strings provided followed by the list provided line by line, enclosed within two lines.
     * if list is null, then print strings only (and vice versa).
     * @param list list of items to be printed.
     * @param strings strings to be printed.
     */
    public void echo(List<?> list, String... strings) {
        echo(strings, list, null);
    }

    private void echo(String[] beginning, List<?> list, String[] ending) {
        System.out.print(LINE);

        if (beginning != null) {
            for (String string : beginning) {
                System.out.print(FRONTSPACES + string + "\n");
            }
        }

        if (list != null) {
            list.forEach(thing -> {
                System.out.print(String.format("%s%s\n", FRONTSPACES, thing));
            });
        }

        if (ending != null) {
            for (String string : ending) {
                System.out.print(FRONTSPACES + string + "\n");
            }
        }

        System.out.print(LINE);
        System.out.print("\n");
    }

    private void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        echo(logo, "Hello! I'm Duke", "What can I do for you?");
    }
}
