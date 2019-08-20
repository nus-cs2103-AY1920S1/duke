import java.util.Scanner;

public class Duke {
    private static String divider = "    " + "-".repeat(61);

    private static void dukeRespond(String... inputs) {
        System.out.println(divider);
        for (String str : inputs) {
            System.out.println("    " + str);
        }
        System.out.println(divider);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeRespond("Hello! I'm Duke", "What can I do for you?");

        //start listening for user input
        Scanner sc = new Scanner(System.in);
        String userCmd = sc.nextLine();
        //System.out.println(userCmd);
        while (!userCmd.equals("bye")) {
            //lvl1: echo cmd
            dukeRespond(userCmd);
            userCmd = sc.nextLine();
        }
        dukeRespond("Bye. Hope to see you again soon!");

        //clear resources
        sc.close();
    }
}
