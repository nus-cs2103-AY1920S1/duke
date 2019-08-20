import java.util.Scanner;

public class Processor {
    String startMessage;
    String exitMessage;
    String bar;
    Scanner sc;

    public Processor() {
        this.startMessage = "\tHello! I'm Duke \n\tWhat can I do for you?";
        this.exitMessage = "\tBye. Hope to see you again soon!";
        this.bar = "\t______________________________";
        this.sc = new Scanner(System.in);
    }
    public void initialise() {
        System.out.println(bar);
        System.out.println(startMessage);
    }
    public void run() {
        String input = sc.next();
        while (!input.equals("quit")) {
            System.out.println(this.bar);
            System.out.println('\t' + input);
            System.out.println(this.bar);
            input = sc.next();
        }
        System.out.println(bar);
        System.out.println(exitMessage);
        System.out.println(bar);
    }
}
