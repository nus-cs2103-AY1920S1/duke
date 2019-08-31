import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) {

        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greet);

        Task[] tasks = new Task[100];
        int numOfItems = 0;
        int count = 0;
        int index = 0;

        while(true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();

                switch(input) {
                    case "list":
                        System.out.println("____________________________________________________________\n");
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < numOfItems; i++) {
                            count++;
                            assert tasks[i] != null;
                            System.out.println(count + ". " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription().toString());
                        }
                        System.out.println("____________________________________________________________\n");
                        break;

                    case "bye":
                        System.out.println("____________________________________________________________\n"
                                + "Bye. Hope to see you again soon!\n"
                                + "____________________________________________________________\n");
                        System.exit(0);
                        break;

                    default:
                        int i = input.indexOf(' ');
                        String word = input.substring(0, i);
                        String rest = input.substring(i);
                        String second = input.split(" ")[1];

                        if (word.equals("done")) {
                            int select = Integer.parseInt(second);
                            tasks[select-1].markAsDone(tasks[select-1]);
                            System.out.println("____________________________________________________________\n"
                                    + "Nice! I've marked this task as done:\n"
                                    + "[" + tasks[select-1].getStatusIcon() + "] " + tasks[select-1].getDescription().toString() + "\n"
                                    + "____________________________________________________________\n");
                        } else {

                            Task currTask = new Task(input);
                            tasks[numOfItems++] = currTask;
                            System.out.println("____________________________________________________________\n");
                            System.out.println("added: " + input);
                            System.out.println("____________________________________________________________\n");
                        }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
