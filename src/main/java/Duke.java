import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> store = new ArrayList<>();

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        while(true) {
            String next = sc.nextLine();
            if (!next.equals("bye") && !next.equals("list")) {
                store.add(next);
                System.out.println("added: " + next);
            } else if (next.equals("list")) {
                for(int i = 1; i < store.size() + 1; i++) {
                    System.out.println(i + ". " + store.get(i-1));
                }
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
