public class Duke{

    private static TaskList todoList = new TaskList();
    private static UserInterface UI = new UserInterface(todoList);
    /**
     * Main driver class for Duke.
     *
     */
    public static void main(String[] args) {
        dukePrint("Hello! I'm Duke", "What can I do for you?");
        UI.read();
        dukePrint("Bye. Hope to see you again soon!");
    }

    /**
     * Echos string.
     * @param echoedString targeted String to be echoed
     */
    private static void echo(String echoedString) {
        dukePrint(echoedString);
    }

    /**
     * Prints what Duke says in correct format.
     * @param texts any number of String arguments
     *              each prints on a new line.
     */

    private static void dukePrint(Object... texts) {
        System.out.println("    _____________________________");
        for (Object text : texts) {
            System.out.println("     " + text);
        }
        System.out.println("    _____________________________");

    }
}
