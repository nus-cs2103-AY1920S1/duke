import java.util.*;

public class Duke {

    public static void runDuke() {
        String line = "    _____________________________________________";
        String currentCommand = "";
        ArrayList<String> addedItems = new ArrayList<>();

        System.out.println(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (!currentCommand.equals("bye")) {
            currentCommand = scanner.nextLine();
            if (!currentCommand.equals("bye")) {
                if (!currentCommand.equals("list")) {
                    addedItems.add(currentCommand);
                    System.out.println(line);
                    System.out.println("     added: " + currentCommand);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    for (int i = 0; i < addedItems.size(); i++) {
                        System.out.println("     " + (i + 1) + ". " + addedItems.get(i));
                    }
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
