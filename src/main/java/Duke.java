import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String horizontalLine = "____________________________________________________________";
        String indentation = "    ";
        String logo = indentation + " ____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        String input = "";
        boolean bye = false;
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Hello from\n" + logo);
        System.out.println(indentation + "What can I do for you?");
        System.out.println(indentation + horizontalLine + "\n");
        while (!bye) {
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                System.out.println(indentation + horizontalLine);
                System.out.println(indentation + input);
                System.out.println(indentation + horizontalLine + "\n");
            }
            else {
                bye = true;
            }
        }
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(indentation + horizontalLine);
    }
}