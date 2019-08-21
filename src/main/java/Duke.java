import java.util.Scanner;

public class Duke {
    /**
     * The duke project.
     */

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "    __________________________________";

        System.out.println(line);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        String echo = "";
        if (sc.hasNextLine()) {
            echo = sc.nextLine();

            while (!echo.equals("bye")) {
                System.out.println(line);
                System.out.println("    " + echo);
                System.out.println(line);
                echo = sc.nextLine();
            }

            if (echo.equals("bye")) {
                System.out.println(line);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(line);
            }
        }

    }
}
