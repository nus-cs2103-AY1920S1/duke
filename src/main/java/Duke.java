import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hLine = "    ____________________________________________________________";

        System.out.println(hLine);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.printf("%s\n\n", hLine);

        while(true) {

            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println(hLine);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.printf("%s\n\n", hLine);
                break;
            }
            System.out.println(hLine);
            System.out.printf("    %s\n", command);
            System.out.printf("%s\n\n", hLine);

        }

    }
}
