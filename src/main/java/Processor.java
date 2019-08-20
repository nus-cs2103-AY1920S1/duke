import java.util.Scanner;
import java.util.ArrayList;

public class Processor {
    String startMessage;
    String exitMessage;
    String bar;
    Scanner sc;
    ListManager taskList;

    public Processor() {
        this.startMessage = "\tHello! I'm Duke \n\tWhat can I do for you?";
        this.exitMessage = "\tBye. Hope to see you again soon!";
        this.bar = "\t______________________________";
        this.sc = new Scanner(System.in);
        this.taskList = new ListManager();
    }
    public void initialise() {
        System.out.println(bar);
        System.out.println(startMessage);
    }
    public void run() {
        String input = sc.next();
        while (!input.equals("quit")) {
            if (input.equals("list")) {
                taskList.iterate();
                input = sc.next();
            } else {
                System.out.println(this.bar);
                System.out.println('\t' + "added: " + input);
                taskList.add(input);
                System.out.println(this.bar);
                input = sc.next();
            }
        }
        this.exit();
    }

    public void exit() {
        System.out.println(bar);
        System.out.println(exitMessage);
        System.out.println(bar);
    }
}
