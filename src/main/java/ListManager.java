import java.util.ArrayList;

public class ListManager {
    ArrayList<String> actualList;
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
        actualList.add(input);
    }

    public void iterate() {
        if (this.actualList.isEmpty()) {
            System.out.println(bar);
            System.out.println("\tYou have nothing on your to-do list!");
            System.out.println(bar);
        } else {
            System.out.println(bar);
            for(int i = 0; i < actualList.size(); i++) {
                System.out.print('\t');
                System.out.println((i+1) +". " + actualList.get(i));
            }
            System.out.println(bar);
        }
    }
}
