import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    private static List<String> userInputs = new ArrayList<>(100);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String s = userInput.nextLine().trim();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                userInput.close();
                break;
            } else if (s.equals("list")){
                printUserInputs();
            } else {
                setUserInputs(s);
            }
        }
    }

    private static void setUserInputs(String input) {
        userInputs.add(input);
        System.out.println("added: " + input);
    }

    private static void printUserInputs() {
        for (int i = 0; i < userInputs.size(); i++) {
            System.out.println((i + 1) + ". " + userInputs.get(i));
        }
    }
}

