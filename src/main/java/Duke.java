import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static List<String> dukeList = new ArrayList<>();

    public static void main(String[] args) {
        String initialMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(initialMessage);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                displayDukeList();
            } else {
                dukeList.add(input);
                System.out.println("added: " + input);
            }
        }
    }

    public static void displayDukeList() {
        for (int i = 0; i < dukeList.size(); i++) {
            int itemIndex = i + 1;
            String itemDisplay = itemIndex + ". " + dukeList.get(i);
            System.out.println(itemDisplay);
        }
    }

}
