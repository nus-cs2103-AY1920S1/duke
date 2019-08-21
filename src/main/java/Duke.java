import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        String intro = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.nextLine().trim();
            // Terminate the bot if the 'bye' command is issued
            if (command.equals("bye")) {
                break;
            } else {
                // Echo the input command
                System.out.println(command + "\n");
            }
        }

        // Explicitly closes the Scanner and input stream
        sc.close();
    }
}
