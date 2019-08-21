import java.util.Scanner;
import java.lang.StringBuilder;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        String intro = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(Duke.format(intro));

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.nextLine().trim();
            // Terminate the bot if the 'bye' command is issued
            if (command.equals("bye")) {
                break;
            } else {
                // Echo the input command
                System.out.println(Duke.format(command));
            }
        }

        // Explicitly closes the Scanner and input stream
        sc.close();
    }

    /** Returns a new string from pretty-printing the original message string.
     *  Adds indentation and horizontal lines to the message.
     * 
     * @param message a string to embellish.
     * @return a formatted String to print as output.
     */
    public static String format(String message) {
        StringBuilder result = new StringBuilder();

        String divider = "    ________________________________________________________________\n";
        result.append(divider)
              .append("\n");

        // Retrieves each individual line in the message
        String[] lines = message.split("\n");
        for (String line: lines) {
            result.append("     ")
                  .append(line)
                  .append("\n");
        }
        
        result.append(divider);
        return result.toString();
    }
}
