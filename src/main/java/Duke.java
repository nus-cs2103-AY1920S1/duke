import java.util.Scanner;

public class Duke {
    private static final String filePath = "C:/Users/jxken/Desktop/Github/duke/data/duke.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        LocalStorage storage = new LocalStorage(filePath);
        CommandHandler commandHandler = new CommandHandler(tasks, storage);

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
