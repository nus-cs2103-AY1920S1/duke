/* Name: Ang Kai Qi
   MatricNo: A0190206N
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> arrList = new ArrayList<String>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();
        Duke.addList();
    }

    static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    static void addList() {
        String s = Duke.sc.nextLine();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                Duke.checkList();
            } else {
                Duke.echo(s);
            }
            s = Duke.sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        Duke.sc.close();
    }

    static void checkList() {
        if (arrList.isEmpty()) {
            return;
        }
        int i = 1;
        for (String s : arrList) {
            System.out.println(i + ". " + s);
            i++;
        }
    }

    static void echo(String s) {
        System.out.println("added: " + s);
        arrList.add(s);
    }
}
