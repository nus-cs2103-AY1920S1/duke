import java.util.Scanner;

public class Duke {

    private static String line = "    ____________________________________________________________";

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // print a list of strings with horizontal lines and indentation
    private static void format_print(String[] lists) {
        System.out.println(line);
        for (String s : lists) {
            System.out.println("     " + s);
        }
        System.out.println(line);
    }

    // print a string with horizontal lines and indentation
    private static void format_print(String s) {
        System.out.println(line);
        System.out.println("     " + s);
        System.out.println(line);
    }

    // echos with input string
    private static void echo(String s) {
        format_print(s);
    }

    public static void main(String[] args) {
        // initialize objects
        Scanner sc = new Scanner(System.in);

        // greetings
        String[] greetings = {"Hello! I'm Duke", "What can I do for you?"};
        format_print(greetings);

        // echos until the input is bye
        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                format_print("Bye. Hope to see you again soon!");
                break;
            } else {
                echo(s);
            }
        }
    }
}
