import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        OutputHandler.sayHi();

        while (true) {
            String cmd = sc.next();
            if (cmd.equals("bye")) break;
            OutputHandler.echo(cmd);
        }

        OutputHandler.sayBye();

    }
}
