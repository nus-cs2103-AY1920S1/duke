import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> array = new ArrayList<>();

        int num = 1;

        while (sc.hasNext()) {
            String word = sc.nextLine();
            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (word.equals("list")) {
                Task.printList(array);
            } else if (word.contains("done")) {
                String arr[] = word.split(" ");
                int taskNum = Integer.parseInt(arr[1]);
                Task.markAsDone(taskNum, array);
            } else {
                Task.addList(num, word,array);
                num++;
            }
        }
    }
}
