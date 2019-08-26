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
            String text = input.substring(cmd.length()).stripLeading();
            commandHandler.executeCommand(cmd, text);
        }



    }
}
