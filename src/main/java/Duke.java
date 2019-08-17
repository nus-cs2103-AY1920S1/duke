import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Echoer echoer = new Echoer();
        String command;

        echoer.greet();

        command = scanner.nextLine();
        while (!command.equals("bye")) {
            echoer.echo(command);
            command = scanner.nextLine();
        }

        echoer.exit();
    }
}
