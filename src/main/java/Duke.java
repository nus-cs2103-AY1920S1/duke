import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello from\n" + logo + "\n What can I do for you? \n" );

        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            String[] words = userInput.split(" ");
            String firstWord = words[0];
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")){
                for (int i = 0; i < list.size(); i++) {
                    Task currTask = list.get(i);
                    System.out.print((i+1) + ". " + currTask.toString() + "\n");
                }
            } else if (firstWord.equals("done")) {
                try {
                    int position = Integer.parseInt(words[1]) - 1;
                    Task currTask = list.get(position);
                    currTask.doTask();
                    System.out.println("Nice! I've marked this task as done: \n " +
                            currTask.toString() + "\n");
                } catch (Exception ex) {
                    System.out.println("Please try again.");

                }
            } else {
                Task task = new Task(userInput);
                list.add(task);
                System.out.println("added: " + userInput + "\n");
            }
        }
    }
}
