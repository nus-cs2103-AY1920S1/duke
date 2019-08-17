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
            String command = sc.nextLine();

            if ("list".equals(command)) {
                echo(taskList);
            } else if (command.startsWith("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                Task task = taskList.get(index - 1);
                task.markAsDone();
                echo("Nice! I've marked this task as done:",
                        String.format("  [\u2713] %s", task.getDetails()));
            } else if ("bye".equals(command)) {
                echo("Bye. Hope to see you again!");
                break;
            } else {
                taskList.add(new Task(taskList.size() + 1, command));
                echo("added: " + command);
            }
        }
    }

    public void echo(String... strings) {
        echo(null, strings);
    }

    public void echo(List<?> list) {
        echo(list, new String[1]);
    }

    private void echo(List<?> list, String... strings) {
        System.out.print(LINE);

        if (list != null) {
            list.forEach(thing -> {
                System.out.print(String.format("%s%s\n", FRONTSPACES, thing));
            });
        } else {
            for (String string : strings) {
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
