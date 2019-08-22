import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> myList;

    public Duke() {
        myList = new ArrayList<Task>();
    }

    private void run() {
        boolean canEnd;

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        canEnd = false;
        while (!canEnd) {
            String input, response = "";
            input = myScanner.nextLine();
            if (isDone(input)) {
                continue;
            }
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    canEnd = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list");
                    for (int i = 0; i < myList.size(); i = i + 1) {
                        int number = i + 1;
                        System.out.println(number + ".[" + myList.get(i).getStatusIcon() + "]"
                                + myList.get(i).getDescription());
                    }
                    break;
                default:
                    myList.add(new Task(input));
                    System.out.println("added: " + input);
                    break;
            }
        }
    }

    /**
     * Returns true if input of the form "done <int>"
     * @param input
     */
    private boolean isDone(String input) {
        String[] keywords = input.split(" ");
        if (keywords[0].equals("done") && keywords.length == 2) {
            Scanner s = new Scanner(keywords[1]);
            if (s.hasNextInt()) {
                int index = s.nextInt() - 1;
                if (index >=0 && index < myList.size()) {
                    myList.get(index).setStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + myList.get(index).getStatusIcon()
                            + "] " + myList.get(index).getDescription());
                }
                else {
                    System.out.println("Please enter a valid number");
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
