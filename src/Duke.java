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
        List<String> greeting = new ArrayList<>();
        greeting.add("Hello! I'm Duke");
        greeting.add("What can I do for you?");
        printLinesWithDivider(greeting);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (input.contains("bye")) {
                String byeMessage = "Bye. Hope to see you again soon!";
                printWithDivider(byeMessage);
                break;
            } else {
                printWithDivider(input);
            }
        }

    }

    private static void printWithDivider(String line) {
        System.out.print(divider);
        System.out.print("\t " + line + "\n");
        System.out.print(divider);
        System.out.print("\n");
    }

    private static void printLinesWithDivider(List<String> lines) {
        System.out.print(divider);
        for (int i = 0; i < lines.size(); i++) {
            System.out.print("\t " + lines.get(i) + "\n");
        }
        System.out.print(divider);
        System.out.print("\n");
    }
}
