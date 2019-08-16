import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static String divider = "\t____________________________________________________________\n";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        List<String> itemList = new ArrayList<>();
        List<String> greeting = new ArrayList<>();
        greeting.add("Hello! I'm Duke");
        greeting.add("What can I do for you?");
        printLinesWithDivider(greeting, false);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.contains("bye")) {
                String response = "Bye. Hope to see you again soon!";
                printWithDivider(response);
                break;
            } else if (input.contains("list")) {
                printLinesWithDivider(itemList, true);

            } else {
                String response = "added: " + input;
                itemList.add(input);
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

    private static void printLinesWithDivider(List<String> lines, boolean includeIndex) {
        System.out.print(divider);
        for (int i = 0; i < lines.size(); i++) {
            System.out.print("\t ");
            if (includeIndex) {
                System.out.print((i+1) + ". ");
            }
            System.out.print(lines.get(i) + "\n");
        }
        System.out.print(divider);
        System.out.print("\n");
    }
}
