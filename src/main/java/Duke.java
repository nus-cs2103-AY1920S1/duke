import java.util.Scanner;
import java.util.ArrayList;
//Test comment
public class Duke {
    private ArrayList<String> tasks = new ArrayList<String>();
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    */
    public void introduction() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    /*
    public void list() {
        for(int i = 1; i <= this.tasks.size(); i++) {
            System.out.println(i + ". " + this.tasks.get(i - 1));
        }
    }
    public void add(String task) {
        this.tasks.add(task);
        System.out.println("added: " + task);
    }
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        duke.introduction();
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                duke.exit();
                return;
            } else {
                System.out.println(command);
            }
        }
    }
}