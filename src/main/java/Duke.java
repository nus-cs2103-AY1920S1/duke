import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String indent = "    ";
        Scanner sc = new Scanner(System.in);

        printIndentedString("Hello! I'm Duke\n" +
                indent + " " + "What can I do for you?", indent);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            printIndentedString(input, indent);
            input = sc.nextLine();
        }
        printIndentedString("Bye. Hope to see you again soon!", indent);
    }

    public static void printIndentedString(String string, String indent) {
        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + " " + string);
        System.out.println(indent + "____________________________________________________________");
        System.out.println();
    }
}
