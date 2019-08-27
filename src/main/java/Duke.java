import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private ArrayList<Task> tasks;

    public Duke() {
        tasks = new ArrayList<>();
    }
    
    private String makeSpace(int n) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str.append(' ');
        }
        return str.toString();
    }
    
    public void greet() {
        System.out.print(HORIZONTAL_LINE);
        
        String logo = makeSpace(5) + " ____        _        \n"
                + makeSpace(5) + "|  _ \\ _   _| | _____ \n"
                + makeSpace(5) + "| | | | | | | |/ / _ \\\n"
                + makeSpace(5) + "| |_| | |_| |   <  __/\n"
                + makeSpace(5) + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMessage = String.format("%s\n%sHello! I'm Duke!\n%sWhat can I do for you?", logo, makeSpace(5), 
                makeSpace(5));
        
        System.out.printf("%s\n%s\n", welcomeMessage, HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.printf("%sBye! Hope to see you again soon! \u263A\n", makeSpace(5));
        System.out.println(HORIZONTAL_LINE);
    }

    public void evaluate(String instruction) {
        System.out.println(HORIZONTAL_LINE);

        Scanner userInput = new Scanner(instruction);
        String command = userInput.next();

        if (command.equals("list")) {
            listAllTasks();
        } else if (command.equals("done")) {
            int taskNumber = userInput.nextInt();
            markTaskAsDone(taskNumber - 1);
        } else {
            System.out.printf("%sadded: %s\n", makeSpace(5), instruction);
            tasks.add(new Task(instruction));
        }
        
        System.out.println(HORIZONTAL_LINE);
    }

    private void markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber).isDone = true;
        
        System.out.printf("%sNice! I've marked this task as done:\n", makeSpace(5));
        System.out.printf("%s%s\n", makeSpace(7), tasks.get(taskNumber));
    }

    private void listAllTasks() {
        System.out.printf("%sHere are the tasks in your list:\n", makeSpace(5));

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%s%d. %s\n", makeSpace(7), i + 1, tasks.get(i));
        }
    }
}
