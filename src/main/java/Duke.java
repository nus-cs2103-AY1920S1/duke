import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printline("Hello! I'm Duke\n     What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            printline(input);
            input = sc.next();
        }
        printline("Bye. Hope to see you again soon!");
    }

    public static void printline(String s) {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        System.out.println("     " + s);
        System.out.println(indentedline);
    }
}
