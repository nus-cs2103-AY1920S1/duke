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
        printLine();

        if (list != null) {
            list.forEach(thing -> {
                System.out.print(String.format("%s%s\n", FRONTSPACES, thing));
            });
        } else {
            for (String string : strings) {
                System.out.print(FRONTSPACES + string + "\n");
            }
        }

        printLine();
    }

    private void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "   |  _ \\ _   _| | _____ \n"
                + "   | | | | | | | |/ / _ \\\n"
                + "   | |_| | |_| |   <  __/\n"
                + "   |____/ \\__,_|_|\\_\\___|\n";

        echo(logo, "Hello! I'm Duke", "What can I do for you?");
    }

    // Print a line made up of underscore _ characters
    private void printLine() {
        System.out.print(LINE);
    }
}
