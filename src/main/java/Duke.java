import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        try {
            duke.run();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        while (sc.hasNext()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                if (list.size() == 0) {
                    System.out.println("Nothing added yet");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println("" + i + "." + list.get(i - 1));
                    }
                }
            } else if (str.substring(0, 4).equals("done")) {
                Integer taskNum = Integer.valueOf(str.substring(5));
                Task currTask = list.get(taskNum - 1);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:" + "\n" + "[\u2713] " + currTask.getDescription());
            } else if (str.substring(0, 4).equals("todo")) {
                if (str.length() == 4) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String message = "Got it. I've added this task:";
                    Task toDoTask = new Task(str.substring(5));
                    list.add(toDoTask);
                    toDoTask.setTypeOfTask("todo");
                    System.out.println(message);
                    System.out.println(" " + toDoTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                }
            } else if (str.length() >= 8 && str.substring(0, 8).equals("deadline")) {
                if (str.length() == 8) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String message = "Got it. I've added this task:";
                    int index = str.indexOf('/') + 1;
                    Task deadlineTask = new Task(str.substring(9, index - 2));
                    list.add(deadlineTask);
                    deadlineTask.setTypeOfTask("deadline");
                    String preposition = str.substring(index, index + 2);
                    String time = "(" + preposition + ": " + str.substring(index + 3) + ")";
                    deadlineTask.addTime(time);
                    System.out.println(message);
                    System.out.println(" " + deadlineTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
            } else if (str.length() >= 5 && str.substring(0, 5).equals("event")) {
                String message = "Got it. I've added this task:";
                int index = str.indexOf('/') + 1;
                Task eventTask = new Task(str.substring(6, index - 2));
                list.add(eventTask);
                eventTask.setTypeOfTask("event");
                String preposition = str.substring(index, index + 2);
                String time = "(" + preposition + ": " + str.substring(index + 3) + ")";
                eventTask.addTime(time);
                System.out.println(message);
                System.out.println(" " + eventTask);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (str.equals("blah")) {
                throw new DukeException("OOPS!!! I'm sorry, but i don't know what that means :-(");
            } else if (str.substring(0, 6).equals("delete")) {
                Integer index = Integer.valueOf(str.substring(7));
                System.out.println("Noted. I've removed this task:");
                System.out.println(list.get(index));
                list.remove((int)index);
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else {
                System.out.println("added: " + str);
                list.add(new Task(str));
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

}
