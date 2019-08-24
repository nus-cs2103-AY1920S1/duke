public class Echoer {
    
    private static String formatMessage(String message) {
        return "     " + message + "\n";
    }

    static void echo(String... messages) {
        String border = "    ____________________________________________________________\n";
        String toEcho = border;
        for (String message : messages) {
            toEcho += formatMessage(message);
        }
        toEcho += border;
        System.out.println(toEcho);
    }

    static void greet() {
        echo("Hello! I'm Jermi", "What can I do for you?");
    }

    static void exit() {
        echo("Bye. Hope to see you again soon!");
    }
}