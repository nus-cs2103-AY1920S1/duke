import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> repeatList = new ArrayList();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean byeHit = false;
        String repeatStr = "";

        while (!byeHit) {
            repeatStr = sc.nextLine();
            if (repeatStr.equals("bye")) {
                byeHit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (repeatStr.equals("list")) {
                for (int i = 1; i <= repeatList.size(); i++) {
                    System.out.println(i + ". " + repeatList.get(i - 1));
                }
            } else {
                repeatList.add(repeatStr);
                System.out.println("added: " + repeatStr);
            }
        }
    }
}
