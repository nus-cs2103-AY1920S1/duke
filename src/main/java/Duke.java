import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> listOfInputs = new ArrayList<>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                int index = 1;
                for (String i : listOfInputs) {
                    System.out.printf("%d. %s\n", index, i);
                    index++;
                }
                listOfInputs.add(input);
            } else {
                listOfInputs.add(input);
                System.out.println(input);
            }
        }

        sc.close();

        System.out.println("Bye. Hope to see you again soon!");
    }
}
