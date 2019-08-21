import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String dividerLine = new String("\u2501").repeat(80).concat("\n");
        String startMessage = "  Hello! I'm Duke\n"
                + "  What can I do for you?\n";
        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | | | | | | | |/ / _ \\\n"
                + "  | |_| | |_| |   <  __/\n"
                + "  |____/ \\__,_|_|\\_\\___|\n"
                + startMessage
                + dividerLine;
        System.out.print(dividerLine + "  Hello from\n" + logo);

        //Take in input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        //Check if bye
        while (!input.equals("bye")){
            // Else echo input
            System.out.print(dividerLine
                    + "  "
                    + input
                    + "\n"
                    + dividerLine);

            input = sc.nextLine();
        }
        // Exit if bye
        System.out.println(dividerLine
                + "  Bye. Hope to see you again soon!\n"
                + dividerLine);

    }
}
