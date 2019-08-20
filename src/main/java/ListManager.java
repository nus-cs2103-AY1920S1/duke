import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ListManager {
    ArrayList<Task> actualList;
    String startMessage;
    String exitMessage;
    String bar;
    Scanner scanner;

    public ListManager(Scanner sc) {
        this.startMessage = "\tHello! I'm Duke \n\tWhat can I do for you?";
        this.exitMessage = "\tBye. Hope to see you again soon!";
        this.bar = "\t______________________________";
        this.actualList = new ArrayList<>();
        this.scanner = sc;
    }

    public void add(String input) {
        String[] strArray = input.split(" ", 0);
        if(strArray[0].equals("todo")) {
            String[] stringBreaker = input.split("todo",2);
            if (!stringBreaker[1].equals("")) {
                ToDos todo = new ToDos(stringBreaker[1]);
                actualList.add(todo);
                this.successfulAdd();
            } else {
                System.out.println(bar);
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println(bar);
            }
        } else if (strArray[0].equals("deadline")) {
            String newString = input.substring(9);
            String[] stringBreaker = newString.split("/by",2);
            Deadlines deadline = new Deadlines(stringBreaker[0], stringBreaker[1]);
            actualList.add(deadline);
            this.successfulAdd();
        } else if (strArray[0].equals("event")) {
            String newString = input.substring(6);
            String[] stringBreaker = newString.split("/at",2);
            Events event = new Events(stringBreaker[0], stringBreaker[1]);
            actualList.add(event);
            this.successfulAdd();
        } else {
            System.out.println(bar);
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(bar);
        }
    }

    public void successfulAdd() {
        System.out.println(bar);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + actualList.get(actualList.size() - 1));
        System.out.println("\tNow you have " + actualList.size() + " tasks in the list.");
        System.out.println(bar);
    }
    public void iterate() {
        if (this.actualList.isEmpty()) {
            System.out.println(bar);
            System.out.println("\tYou have nothing on your to-do list!");
            System.out.println(bar);
        } else {
            System.out.println(bar);
            System.out.println("\tHere are the tasks in your list:");
            for(int i = 0; i < actualList.size(); i++) {
                System.out.print('\t');
                System.out.print(i+1 + ".");
                System.out.println(actualList.get(i));
            }
            System.out.println(bar);
        }
    }

    public void done(int index) {
        System.out.println(bar);
        if (index <= actualList.size()) {
            actualList.get(index - 1).done = true;
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  [✓] " + actualList.get(index - 1).name);
        } else {
            System.out.println("\tTask does not exist!");
        }
        System.out.println(bar);
    }
}
