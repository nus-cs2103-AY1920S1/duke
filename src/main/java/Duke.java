import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static ArrayList<String> listOfInputs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }

            switch (input) {
            case "list":
                handleList();
                break;
            default:
                handleInput(input);
                break;
            }
        }

        sc.close();

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleList() {
        int index = 1;
        for (String i : listOfInputs) {
            System.out.printf("%d. %s\n", index, i);
            index++;
        }
    }

    private static void handleInput(String input) {
        listOfInputs.add(input);
        System.out.println(input);
    }
}
