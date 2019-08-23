package consoles;

public class CliConsole implements Console {
    /**
     * Prints an output to the command line including both the wrapper and the messages,
     * separated by a newline. The wrapper adds a line to the top and bottom of the
     * message and adds tab indentation.
     *
     * @param lines An array of messages to be wrapped.
     */
    public void print(String ...lines) {
        System.out.println("\t____________________________________________________________");
        for (String line: lines) {
            System.out.println("\t " + line);
        }
        System.out.println("\t____________________________________________________________");
    }
}
