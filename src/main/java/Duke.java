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
            //remove first word
            String line = "";
            for (int i = 1; i < words.length; i++) {
                if (i == words.length - 1) {
                    line += words[i];
                } else {
                    line += words[i] + " ";
                }
            }
            //spli


            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")){
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < list.size(); i++) {
                    Task currTask = list.get(i);
//                    System.out.println(currTask.getClass());
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
            } else if (firstWord.equals("todo")){

                Todo task = new Todo(line);
                list.add(task);
                System.out.println("Got it. I've added this task: \n"
                        + task.toString() + "\n Now you have " + list.size() + " tasks in the list");
            } else if (firstWord.equals("deadline")) {
                //split the string by /
                String[] halves = line.split("/by");
                String event = halves[0];
                String by = halves[1];
                Deadline deadline = new Deadline(event, by);
                list.add(deadline);
                System.out.println("Got it. I've added this task: \n"
                        + deadline.toString() + "\n Now you have " + list.size() + " tasks in the list");
            } else if (firstWord.equals("event")) {
                //split the string by /
                String[] halves = line.split("/at");
                String event = halves[0];
                String at = halves[1];
                Event myEvent = new Event(event, at);
                list.add(myEvent);
                System.out.println("Got it. I've added this task: \n"
                        + myEvent.toString() + "\n Now you have " + list.size() + " tasks in the list");
            }
        }
    }
}
