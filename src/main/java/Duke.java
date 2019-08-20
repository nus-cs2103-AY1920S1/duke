import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();

        final String welcome = "Hello! I'm Duke. What can I do for you?";
        final String farewell = "Bye. Hope to see you again soon!";

        print(welcome);
        prompt();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            process(input, storage);

            prompt();
        }

        print(farewell);
        sc.close();
    }

    private static void process(String input, Storage storage) {
        String command = input.split(" ")[0];

        switch (command) {
            case "list": {
                ArrayList<String> items = storage.retrieve();

                StringBuilder output = new StringBuilder();
                for (int i = 0; i < items.size(); i++) {
                    output.append(String.format("%d. %s%n", i + 1, items.get(i)));
                }
                print(output.toString().trim());

                break;
            }

            default: {
                if (storage.addItem(input)) {
                    print(String.format("Added: %s", input));
                } else {
                    print("Error");
                }
            }
        }
    }

    private static void prompt() {
        System.out.print("> ");
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
