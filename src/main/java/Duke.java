import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        while (sc.hasNext()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                if (list.size() == 0) {
                    System.out.println("Nothing added yet");
                } else {
                    for (int i = 1; i <= list.size(); i++) {
                        String line = "" + i + ". " + list.get(i-1);
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("added: " + str);
                list.add(str);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
