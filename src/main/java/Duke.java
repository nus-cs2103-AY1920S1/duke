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
        } else if (command.equals("todo")) {
            String details = userInput.nextLine().strip();
            makeNewTodo(details);
        } else if (command.equals("deadline")) {
            userInput.useDelimiter(" by ");

            String taskDescription = userInput.next().strip();
            String[] dateTime = userInput.next().split(" ");

            if (dateTime.length == 1) {
                makeNewDeadline(taskDescription, dateTime[0], "");
            } else if (dateTime.length == 2) {
                makeNewDeadline(taskDescription, dateTime[0], dateTime[1]);
            } else if (dateTime.length == 3) {
                makeNewDeadline(taskDescription, dateTime[0] + " " + dateTime[1], dateTime[2]);
            } else {
                return;
            }
        }  else if (command.equals("event")) {
            userInput.useDelimiter(" at ");

            String taskDescription = userInput.next().strip();
            String[] dateTime = userInput.next().split(" ");

            if (dateTime.length == 2) {
                makeNewEvent(taskDescription, dateTime[0], dateTime[1]);
            } else if (dateTime.length == 3) {
                makeNewEvent(taskDescription, dateTime[0] + " " + dateTime[1], dateTime[2]);
            } else {
                return;
            }
        } else {
            return;
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

    private void makeNewTodo(String description) {
        ToDo currentTodo = new ToDo(description);
        tasks.add(currentTodo);

        System.out.printf("%sGot it! I've added this task:\n", makeSpace(5));
        System.out.printf("%s%s\n\n", makeSpace(7), currentTodo);
        System.out.printf("%sNow you have %d task(s) in your list.\n", makeSpace(5), tasks.size());
    }

    private void makeNewDeadline(String desc, String date, String time) {
        Deadline currentDeadline = new Deadline(desc, date, time);
        tasks.add(currentDeadline);

        System.out.printf("%sGot it! I've added this task:\n", makeSpace(5));
        System.out.printf("%s%s\n\n", makeSpace(7), currentDeadline);
        System.out.printf("%sNow you have %d task(s) in your list.\n", makeSpace(5), tasks.size());
    }

    private void makeNewEvent(String desc, String date, String time) {
        Event currentEvent = new Event(desc, date, time);
        tasks.add(currentEvent);

        System.out.printf("%sGot it! I've added this task:\n", makeSpace(5));
        System.out.printf("%s%s\n\n", makeSpace(7), currentEvent);
        System.out.printf("%sNow you have %d task(s) in your list.\n", makeSpace(5), tasks.size());
    }
}
