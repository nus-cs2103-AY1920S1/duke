import java.util.*;

public class Duke {

    public static void runDuke() {
        String line = "    ____________________________________________________________";
        String currentCommand = "";
        ArrayList<Task> addedItems = new ArrayList<>();

        System.out.println(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (!currentCommand.equals("bye")) {
            currentCommand = scanner.nextLine();
            if (!currentCommand.equals("bye")) {

                if (currentCommand.equals("list")) {
                    System.out.println(line);
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < addedItems.size(); i++) {
                        System.out.println("     " + (i + 1) + "." + addedItems.get(i));
                    }
                    System.out.println(line);
                } else if (currentCommand.substring(0, 4).equals("done")) {
                    int taskNumber = Integer.parseInt(currentCommand.substring(5)) - 1;
                    addedItems.get(taskNumber).markAsDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + addedItems.get(taskNumber));
                    System.out.println(line);
                } else {
                    addedItems.add(new Task(currentCommand));
                    System.out.println(line);
                    System.out.println("     added: " + currentCommand);
                    System.out.println(line);
                }

            }
        }

        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        runDuke();
    }
}
