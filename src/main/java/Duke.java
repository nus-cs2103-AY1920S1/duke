import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            echo(input);
            input = sc.next();
        }

        exit();
    }

    public static void greet() {
        echo("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String s) {
        String[] arr = s.split("\n");
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        for (String str : arr) {
            System.out.println("     " + str);
        }
        System.out.println(indentedline);
    }
}
