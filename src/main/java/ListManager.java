import java.util.ArrayList;

public class ListManager {
    ArrayList<Task> actualList;
    String startMessage;
    String exitMessage;
    String bar;

    public ListManager() {
        this.startMessage = "\tHello! I'm Duke \n\tWhat can I do for you?";
        this.exitMessage = "\tBye. Hope to see you again soon!";
        this.bar = "\t______________________________";
        this.actualList = new ArrayList<>();
    }

    public void add(String input) {
        Task t = new Task(input);
        actualList.add(t);
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
                if (actualList.get(i).done) {
                    System.out.print("[✓] ");
                } else {
                    System.out.print("[X] ");
                }
                System.out.println(actualList.get(i).name);
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
