import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke d = new Duke();

        d.initDuke();
        d.runDuke();
        d.terminateDuke();
    }

    public void runDuke() {
        Scanner sc = new Scanner(System.in);
        Boolean contRunning = true;

        while (contRunning) {
            String s = sc.nextLine();
            switch (s) {
                case "bye":
                    contRunning = false;
                    break;
                default:
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t" + s);
                    System.out.println("\t____________________________________________________________");
            }
        }
    }

    public void initDuke() {
        System.out.println("\t____________________________________________________________\n"
                          +"\tHello! I'm Duke\n"
                          +"\tWhat can I do for you?\n"
                          +"\t____________________________________________________________\n");
    }

    public void terminateDuke() {
        System.out.println("\t____________________________________________________________\n"
                          +"\tBye. Hope to see you again soon!\n"
                          +"\t____________________________________________________________\n");
    }
}
