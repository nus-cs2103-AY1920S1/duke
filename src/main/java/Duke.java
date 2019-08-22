import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>(100);

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            int i = input.indexOf(' ');
            String first = getFirstWord(input);
            if(input.equals("list")) {
                readList();
            } else if (first.equals("done")) {
                int taskNo = Integer.parseInt(input.substring(i + 1));
                list.get(taskNo).markAsDone();
            } else if(input.equals("bye")) {
                exit();
            } else {
                addList(input);
            }
        }
    }

    private static void readList() {
        System.out.println("Here are the tasks in your list:\n");
        for(int i = 1;i <= list.size();i++) {
            System.out.println(i + ". " + "[" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).description);
        }
    }

    private static void addList(String input) {
        Task t = new Task(input);
        list.add(t);
        System.out.println("added: " + input);
    }


    private static void exit() {
        System.out.println("Bye! Hope to see you again soon!");
        System.exit(0);
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static String getFirstWord(String input) {
        if (input.indexOf(" ") > -1) {
            return input.substring(0, input.indexOf(" "));
        } else {
            return input;
        }
    }
}
