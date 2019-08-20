import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> books = new ArrayList<>();

        horizontalLine();
        greet();
        horizontalLine();
        System.out.println();

        while(scanner.hasNext()) {
            String echo = scanner.nextLine();

            if (echo.equals("bye")) {
                horizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
                horizontalLine();
                break;
            }

            if (echo.equals("list")) {
                horizontalLine();
                int counter = 0;
                for (String items : books) {
                    counter++;
                    System.out.println(counter + ". " + items);
                }
                horizontalLine();
            } else {
                horizontalLine();
                books.add(echo);
                System.out.println("added: " + echo);
                horizontalLine();
                System.out.println();
            }

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
