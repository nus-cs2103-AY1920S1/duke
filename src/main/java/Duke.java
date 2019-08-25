import java.util.Scanner;

public class Duke {
    static final String INDENTATION = "     ";
    static final String HORIZONTAL_LINE = INDENTATION + "____________________________________________________________\n";

    public static void main(String[] args) {
        String greeting = HORIZONTAL_LINE + INDENTATION + "Hello! I'm Duke\n"
                + INDENTATION + "What can I do for you?\n" + HORIZONTAL_LINE;
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(wrapper("Bye. Hope to see you again soon!"));
                return;
            }
            System.out.println(wrapper(command));
        }
    }

    private static String wrapper(String string) {
        return HORIZONTAL_LINE + INDENTATION + string + "\n" + HORIZONTAL_LINE;
    }
}
