import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String s;
        List<String> ls = new ArrayList<>();
        while (!(s = sc.nextLine()).equals("bye")) {
            if (s.equals("list")) {
                for (int i = 1; i <= ls.size(); i++) {
                    System.out.println(String.format("%d. %s", i, ls.get(i - 1)));
                }
            } else {
                ls.add(s);
                System.out.println("added: " + s);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
