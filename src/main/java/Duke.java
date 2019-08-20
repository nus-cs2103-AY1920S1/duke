import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        horizontalLine();
        greet();
        horizontalLine();

        while(scanner.hasNext()) {
            String echo = scanner.nextLine();

            if (echo.equals("bye")) {
                horizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
                horizontalLine();
                break;
            }

            horizontalLine();
            System.out.println(echo);
            horizontalLine();

        }

    }

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

}
