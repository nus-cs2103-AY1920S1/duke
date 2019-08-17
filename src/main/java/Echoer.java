public class Echoer {
    private String formatMessage(String message) {
        return "     " + message + "\n";
    }

    void echo(String... message) {
        String border = "    ____________________________________________________________\n";
        String toEcho = border;
        for (String string : message) {
            toEcho += this.formatMessage(string);
        }
        toEcho += border;
        System.out.print(toEcho);
    }

    void greet() {
        this.echo("Hello! I'm Duke", "What can I do for you?");
    }

    void exit() {
        this.echo("Bye. Hope to see you again soon!");
    }
}