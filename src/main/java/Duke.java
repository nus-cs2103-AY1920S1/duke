import java.util.Scanner;

public class Duke {
    public static final String filePath = "C:/Users/jxken/Desktop/Github/duke/data/duke.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LocalStorage storage = new LocalStorage(filePath);
        TaskList tasks = new TaskList(storage);
        CommandHandler commandHandler = new CommandHandler(tasks, storage);
        OutputUtilities outputUtilities = new OutputUtilities(tasks, storage);

        outputUtilities.sayHi();


        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s");
            String cmd = parts[0];
            String text = input.substring(cmd.length()).stripLeading();
            commandHandler.executeCommand(cmd, text);
            if (cmd.equals("bye")) System.exit(0);
        }



    }
}
