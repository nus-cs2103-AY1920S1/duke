public class Echoer {
    private String formatMessage(String message) {
        return "     " + message + "\n";
    }

    void echo(String... messages) {
        String border = "    ____________________________________________________________\n";
        String toEcho = border;
        for (String message : messages) {
            toEcho += this.formatMessage(message);
        }
        toEcho += border;
        System.out.println(toEcho);
    }

    void greet() {
        this.echo("Hello! I'm Jermi", "What can I do for you?");
    }

    void exit() {
        this.echo("Bye. Hope to see you again soon!");
    }
}