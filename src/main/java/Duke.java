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
        boolean isRunning = true;

        while (isRunning && sc.hasNextLine()) {
            String command = sc.nextLine().trim();

            switch (command) {
            // Terminate the bot if the 'bye' command is issued
            case "bye":
                isRunning = false;
                break;
            // Catch empty commands (ENTER key pressed)
            case "":
                System.out.println(Duke.format("No command issued."));
                break;
            // Otherwise echo the input command
            default:
                System.out.println(Duke.format(command));
                break;
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
