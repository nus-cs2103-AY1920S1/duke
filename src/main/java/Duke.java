import java.util.ArrayList;

public class Duke {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/

    private ArrayList<String> texts;

    Duke() {
        texts = new ArrayList<>();
    }

    void startDuke() {
        line();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        line();
    }

    void add(String s) {
        texts.add(s);
    }

    void listAll() {
        int counter = 1;

        //list out all the texts from the user
        line();
        for (String t: texts) {
            System.out.println("\t" + counter + ". " + t);
            counter++;
        }
        line();
    }

    void echo(String s) {
        line();
        System.out.println("\tadded: " + s);
        line();
    }

    void endDuke() {
        line();
        System.out.println("\tBye. Hope to see you again soon!");
        line();
    }

    private void line() {
        System.out.println("\t____________________________________________________________");
    }
}
