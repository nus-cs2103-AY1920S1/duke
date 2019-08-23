import java.util.Arrays;
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
        this.taskList = new ListManager(sc);
    }
    public void initialise() {
        System.out.println(bar);
        System.out.println(startMessage);
    }
    public void run() {
        String input = sc.nextLine();
        while (!input.equals("quit")) {
            String[] strArray = input.split(" ",0);
            System.out.println(bar);
            if (input.equals("list")) {
                taskList.iterate();
                input = sc.nextLine();
            } else if (strArray[0].equals("done")) {
                taskList.done(Integer.parseInt(strArray[1]));
                input = sc.nextLine();
            } else if (strArray[0].equals("delete")) {
                taskList.delete(Integer.parseInt(strArray[1]));
                input = sc.nextLine();
            } else {
                taskList.add(input);
                input = sc.nextLine();
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
