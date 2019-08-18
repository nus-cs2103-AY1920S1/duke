import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Duke Class.
 */
public class Duke {
    /**
     * Main Method.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            greetEchoExit(br);
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Prints out greetings, then echos commands by user, and prints exit response when command is "bye".
     * @param br BufferedReader to read user commands.
     */
    private static void greetEchoExit(BufferedReader br) throws IOException {
        greeting();
        String input = br.readLine();
        while (input != null && !input.equals("bye")) {
            echo(input);
            input = br.readLine();
        }
        exit();
    }

    /**
     * Formats a response with indentation and lines.
     * @param response The string that is being formatted.
     * @return The formatted string from the response parameter.
     */
    private static String formatResponse(String response) {
        String indent = "    ";
        String line = indent + "____________________________________________________________";
        String output = indent + " " + response;
        return String.format("%s\n%s\n%s\n", line, output, line);
    }

    /**
     * Prints out the greeting.
     */
    private static void greeting() {
        String response = "Hello! I'm Duke\n     What can I do for you?";
        System.out.println(formatResponse(response));
    }

    /**
     * Echo string response by printing formatted response.
     * @param response The string that will be in the response.
     */
    private static void echo(String response) {
        System.out.println(formatResponse(response));
    }

    /**
     * Exit string response by printing the standard bye response.
     */
    private static void exit() {
        System.out.println(formatResponse("Bye. Hope to see you again soon!"));
    }
}
