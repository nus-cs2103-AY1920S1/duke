import java.util.Scanner;

public class Duke {

    public Duke() {
        printLine();
        System.out.println("Hello, I'm Duke\nWhat can I do for you?");
        printLine();
    }
    private void printLine() {
        System.out.println("--------------------");
    }

    private void  echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("ur mom gay")) {
                printLine();
                System.out.println("no u");
                printLine();
                input = sc.nextLine();
                continue;
            }
            printLine();
            System.out.println(input);
            printLine();
            input = sc.nextLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        Duke d = new Duke();
        d.echo();
    }
}
