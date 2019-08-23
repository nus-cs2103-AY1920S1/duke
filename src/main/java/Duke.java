import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String str = sc.next();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                int index = 0;
                for (String element : list) {
                    index++;
                    System.out.println(index + ". " + element);
                }
                str = sc.next();
            } else {
                System.out.println("added: " + str);
                list.add(str);
                str = sc.next();
            }
        }
    }
}
