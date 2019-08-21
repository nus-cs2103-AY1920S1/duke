import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> commands = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if(input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int count = 1;
                for (Task s : commands) {
                    System.out.println(count + ". " + s);
                    count++;
                }
            } else if (input.contains("done")) {
                String[] inArr = input.split(" ");
                int index = Integer.parseInt(inArr[1]) - 1;
                Task doneTask = commands.get(index);
                doneTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + doneTask);
            } else {
                Task t = new Task(input);
                commands.add(t);
                System.out.println("added: " + input);
            }
        }
    }
}
