import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<String> storedInput = new ArrayList<String>();

    public static void main(String[] args) {
        Duke d = new Duke();

        d.initDuke();
        d.runDuke();
        d.terminateDuke();
    }

    public void runDuke() {
        Scanner sc = new Scanner(System.in);
        boolean contRunning = true;

        while (contRunning) {
            String s = sc.nextLine();
            switch (s) {
                case "bye":
                    contRunning = false;
                    break;
                case "list":
                    listStoredInput();
                    break;
                default:
                    addInputToStore(s);
            }
        }
    }

    public void addInputToStore(String input) {
        this.storedInput.add(input);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + input);
        System.out.println("\t____________________________________________________________");
    }

    public void listStoredInput() {
        System.out.println("\t____________________________________________________________");

        int counter = 1;
        for (String input : this.storedInput) {
            System.out.println("\t" + counter + ". " + input);
            counter++;
        }

        System.out.println("\t____________________________________________________________");
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
