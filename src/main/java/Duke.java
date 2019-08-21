import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);

        String input = "";
        ArrayList<Task> lst = new ArrayList();
        while(!input.equals("bye")) {
            input = scanner.nextLine();

            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < lst.size(); i++){
                    System.out.println(i+1 + ". " + lst.get(i));
                }
            } else if (input.equals("bye")) {
                break;
            }
            else {
                String[] inputArr = input.split(" ", 2);
                String command = inputArr[0];
                if(command.equals("done")){
                    int i = Integer.parseInt(inputArr[1]) - 1;
                    lst.get(i).done();
                    String nice = "Nice! I've marked this task as done:";
                    System.out.println(nice + "\n\t" + lst.get(i));
                } else {
                    String detail = inputArr[1];
                    Task task = new Task("");
                    switch (command) {
                        case "todo":
                            task = new Todo(detail);
                            break;
                        case "deadline":
                            String[] deadlineArr = detail.split("/", 2);
                            String at = deadlineArr[1];
                            task = new Deadline(deadlineArr[0], at.split(" ", 2)[1]);
                            break;
                        case "event":
                            String[] eventArr = detail.split("/", 2);
                            String by = eventArr[1];
                            task = new Event(eventArr[0], by.split(" ", 2)[1]);
                            break;
                    }
                    lst.add(task);
                    String gotit = "Got it. I've added this task:\n\t" + task + "\nNow you have " + lst.size() + " tasks in the list.";
                    System.out.println(gotit);
                }

            }
        }


        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
