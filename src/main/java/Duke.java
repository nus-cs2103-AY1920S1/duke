import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<String> myList;

    public Duke() {
        myList = new ArrayList<String>();
    }

    private void run() {
        boolean canEnd;

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        canEnd = false;
        while (!canEnd) {
            String input, response = "";
            input = myScanner.nextLine();
            if (input.equals("bye")) {
                response = "Bye. Hope to see you again soon!";
                System.out.println(response);
                canEnd = true;
            } else if (input.equals("list")) {
                for (int i = 0; i < myList.size(); i = i + 1) {
                    int number = i + 1;
                    System.out.println(number + ". " + myList.get(i));
                }
            } else {
                myList.add(input);
                response = "added: " + input;
                System.out.println(response);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
