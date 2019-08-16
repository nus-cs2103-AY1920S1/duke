import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static int NOT_DONE = 0;
    private static int DONE = 1;

    private static String divider = "\t____________________________________________________________\n";
    private static List<Integer> itemStatus = new ArrayList<>();
    private static List<String> itemList = new ArrayList<>();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        List<String> greeting = new ArrayList<>();
        greeting.add("Hello! I'm Duke");
        greeting.add("What can I do for you?");
        printLinesWithDivider(greeting, false, false, null);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.contains("bye")) {
                String response = "Bye. Hope to see you again soon!";
                printWithDivider(response);
                break;
            } else if (input.contains("list")) {
                List<String> response = new ArrayList<>();
                String header = "Here are the tasks in your list:";
                printLinesWithDivider(itemList, true, true, header);

            } else if (input.contains("done")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                if (idx < 0 || idx > itemList.size()) {
                    printWithDivider("Index out of range. Please try again.");
                }
                itemStatus.set(idx, DONE);
                List<String> response = new ArrayList<>();
                String header = "Nice! I've marked this task as done: ";
                response.add(itemList.get(idx));
                printLinesWithDivider(response, false, true, header);

            } else {
                String response = "added: " + input;
                itemList.add(input);
                itemStatus.add(NOT_DONE);
                printWithDivider(response);
            }
        }

    }

    private static void printWithDivider(String line) {
        System.out.print(divider);
        System.out.print("\t " + line + "\n");
        System.out.print(divider);
        System.out.print("\n");
    }

    private static void printLinesWithDivider(List<String> lines, boolean includeIndex, boolean includeStatus, String header) {
        System.out.print(divider);
        if (header != null) {
            System.out.print("\t " + header + "\n");
        }
        for (int i = 0; i < lines.size(); i++) {
            System.out.print("\t ");
            if (includeIndex) {
                System.out.print((i+1) + ".");
            }
            if (includeStatus) {
                System.out.print(generateItemStatus(itemList.indexOf(lines.get(i))) + " ");
            }
            System.out.print(lines.get(i) + "\n");
        }
        System.out.print(divider);
        System.out.print("\n");
    }

    private static String generateItemStatus(int idx) {
        if (itemStatus.get(idx) == DONE) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }
}
