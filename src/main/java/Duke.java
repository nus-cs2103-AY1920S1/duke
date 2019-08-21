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
        Duke.print(intro);

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        TaskList commands = new TaskList();

        while (isRunning && sc.hasNextLine()) {
            String command = sc.nextLine().trim();

            // Catch empty commands (ENTER key pressed)
            if (command.equals("")) {
                Duke.print("No command issued.");
                continue;
            }

            // Otherwise determine type of command from first token
            switch (command.split(" ")[0]) {
            // Terminate the bot if the 'bye' command is issued
            case "bye":
                isRunning = false;
                break;
            // List all stored commands in the TaskList
            case "list":
                Duke.print(commands.toString());
                break;
            // Mark an item in the TaskList as completed by its position (task ID)
            // Discards additional arguments apart from the first
            case "done":
                int id = Integer.valueOf(command.split(" ")[1]);
                Duke.print(commands.getTask(id - 1).complete());
                break;
            // Otherwise store the commands entered in the TaskList
            default:
                Duke.print(commands.addTask(command));
                break;
            }
        }

        // Explicitly closes the Scanner and input stream
        sc.close();
    }

    /** Pretty-prints a given message string to the standard output stream.
     *  Adds indentation and horizontal lines to the message.
     * 
     * @param message a string to embellish then print to <code>System.out</code>.
     */
    public static void print(String message) {
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
        System.out.println(result.toString());
    }
}
