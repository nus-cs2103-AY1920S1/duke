import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        CommandHandler commandHandler = new CommandHandler(tasks);

        OutputUtilities.sayHi();

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s");
            String cmd = parts[0];
            // remove leading white space
            // " borrow books" => "borrow books"
            String text = input.substring(cmd.length()).replaceFirst("\\s", "");
            commandHandler.executeCommand(cmd, text);
        }



    }
}
