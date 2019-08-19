import java.util.Scanner;

public class Duke {
    private static final String border = "____________________________________________________________";
    private static String[] strList = new String[100];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int pointer = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n"
                + border + "\n\nHello! I'm Duke\n" + "What can I do for you?\n" + border + "\n");

        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                outputList(pointer, strList);
            } else {
                System.out.println(addToList(pointer, str));
                pointer++;
            }
        }

        System.out.println(border + "\n\nBye. Hope to see you again soon!" + "\n" + border);

        sc.close();

    }

    public static String addToList(int pointer, String string) {
        strList[pointer] = string;
        return border + "\n\n" + "added: " + string + "\n" + border + "\n";
    }

    public static void outputList(int pointer, String[] strList) {
        System.out.println(border + "\n");
        for (int i = 1; i < pointer + 1; i++) {
            System.out.println(i + ". " + strList[i - 1]);
        }
        System.out.println(border + "\n");
    }

}
