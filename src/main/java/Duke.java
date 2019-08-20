import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static String divider = "    " + "-".repeat(61);
    private static List<String> dataList = new ArrayList<>();
    private static int dataSize = 0;

    private static void listData() {
        String[] dataStrings = new String[dataSize];
        dukeRespond(dataList.toArray(dataStrings));
    }
    private static void addData(String data) {
        dataSize++;
        dataList.add(String.format("%d. %s", dataSize, data));
        dukeRespond("added: " + data);
    }

    private static void dukeRespond(String... inputs) {
        System.out.println(divider);
        for (String str : inputs) {
            System.out.println("     " + str);
        }
        System.out.println(divider);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeRespond("Hello! I'm Duke", "What can I do for you?");

        //start listening for user input
        Scanner sc = new Scanner(System.in);
        String userCmd = sc.nextLine();
        //System.out.println(userCmd);
        while (!userCmd.equals("bye")) {
            /*lvl1: echo cmd
            dukeRespond(userCmd);
            */
            //lvl2: store and display when required
            if (userCmd.equals("list")) {
                listData();
            } else {
                addData(userCmd);
            }

            userCmd = sc.nextLine();
        }
        dukeRespond("Bye. Hope to see you again soon!");

        //clear resources
        sc.close();
    }
}
