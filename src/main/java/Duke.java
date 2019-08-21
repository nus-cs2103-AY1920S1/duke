import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> texts = new ArrayList<String>();

    private static void list() {
        for (int i = 0; i < texts.size(); i++) {
            System.out.println((i + 1) + ". " + texts.get(i));
        }
    }

    private static void output(Scanner sc) {
        while (sc.hasNextLine()) {
            String cmmd = sc.nextLine();
            if (cmmd.equals("bye")) {
                break;
            }
            else if (cmmd.equals("list")) {
                list();
            }
            else {
                System.out.println("added: " + cmmd);
                texts.add(cmmd);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String opening = String.format("%s\nHello! I'm Duke\nWhat can I do for you?", logo);
        String closing = "Bye. Hope to see you again soon!";

        System.out.println(opening);
        Scanner sc = new Scanner(System.in);
        output(sc);
        System.out.println(closing);
        System.exit(0);
    }
}
