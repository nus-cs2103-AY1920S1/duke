import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (sc.hasNextLine()) {
            System.out.println("____________________________________________________________");
            String input = sc.nextLine();
            String[] words = input.split(" ");
            if (words[0].equals("done")) {
                int index = Integer.parseInt(words[1]);
                Task t = list.get(index - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + t.toString());
            } else {
                    switch (input) {
                        case "bye":
                            System.out.println("Bye. Hope to see you again soon!");
                            System.out.println("____________________________________________________________");
                            break;
                        case "list":
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 1; i <= list.size(); i++) {
                                System.out.println(i + "." + list.get(i-1).toString());
                            }
                            break;
                        default:
                            list.add(new Task(input));
                            System.out.println("added: " + input);

                    }
            }
        }
    }
}
