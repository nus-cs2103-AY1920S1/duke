import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String text = sc.nextLine();
            if (text.equals("list")) {
                int length = list.size();
                for (int i = 1; i <= length; i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else if (text.equals("blah")) {
                System.out.println("blah");
            } else if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                list.add(text);
                System.out.println("added: " + text);
            }
        }

        sc.close();
    }
}
