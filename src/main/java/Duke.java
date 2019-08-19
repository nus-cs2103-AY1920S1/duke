import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String TOP_SEPARATOR =
            "\t____________________________________________________________\n";
    private static final String BOTTOM_SEPARATOR =
            "\t____________________________________________________________\n";
    private static final String GREET_MESSAGE =
            "\tHello! I'm Duke. What can I do for you?\n";
    private static final String EXIT_MESSAGE =
            "\tBye. Hope to see you again soon!\n";

    public LinkedList<String> tasks;

    public Duke() {
        this.tasks = new LinkedList<>();
    }

    public void run() {
        System.out.println(LOGO + TOP_SEPARATOR + GREET_MESSAGE + BOTTOM_SEPARATOR);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            switch (cmd) {
                case "list":
                    System.out.println(TOP_SEPARATOR + formatTasks(tasks) + BOTTOM_SEPARATOR);
                    break;
                case "bye":
                    System.out.println(TOP_SEPARATOR + EXIT_MESSAGE + BOTTOM_SEPARATOR);
                    return;
                default:
                    tasks.addLast(cmd);
                    System.out.println(TOP_SEPARATOR + "\t" + cmd + "\n" + BOTTOM_SEPARATOR);
                    break;
            }
        }
    }

    // Takes a list of Strings and numbers them in order, in a top-down list format.
    public String formatTasks(LinkedList<String> tasks) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String task: tasks) {
            i++;
            sb.append("\t").append(i).append(". ").append(task).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
