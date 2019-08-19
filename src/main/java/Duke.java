import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        int i = 1;
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    for (String s: list) {
                        System.out.println(i + ". " + s);
                        i++;
                    }
                    break;
                default:
                    list.add(input);
                    System.out.println("added: " + input);
            }
        }
    }
}
