import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (true) {
            try {
                run();
            } catch (DukeIllegalActionException | DukeIllegalDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                break;
            }
        }
    }

    private static void run() throws DukeIllegalActionException, DukeIllegalDescriptionException {
        Scanner sc = new Scanner(System.in);
        LinkedList<Task> tasks = new LinkedList<>();
        while(sc.hasNext()) {
            String act = sc.next();
            try {
                switch (Action.valueOf(act)) {
                    case list:
                        System.out.println("Here are the tasks in your list:\n");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(1 + i + "." + tasks.get(i).toString());
                        }
                        break;
                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        sc.close();
                        break;
                    case done:
                        int n = sc.nextInt();
                        tasks.get(n - 1).setDone();
                        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(n - 1).toString());
                        break;
                    case todo:
                        String tdDescription = sc.nextLine();
                        if (tdDescription.isBlank()) {
                            throw new DukeIllegalDescriptionException(act);
                        } else {
                            Task todo = new ToDo(tdDescription);
                            tasks.add(todo);
                            System.out.println("Got it. I've added this task: \n" + todo.toString()
                                    + "\nNow you have " + (tasks.size()) + " tasks in the list.");
                        }
                        break;
                    case deadline:
                        String dlDetail = sc.nextLine();
                        int dlDivision = dlDetail.indexOf("/");
                        try {
                            String dlDescription = dlDetail.substring(0, dlDivision - 1);
                            String by = dlDetail.substring(dlDivision + 1, dlDetail.length());
                            Task dl = new Deadline(dlDescription, by);
                            tasks.add(dl);
                            System.out.println("Got it. I've added this task: \n" + dl.toString()
                                    + "\nNow you have " + (tasks.size()) + " tasks in the list.");
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(act);
                        }
                        break;
                    case event:
                        String eventDetail = sc.nextLine();
                        int eventDivision = eventDetail.indexOf("/");
                        try {
                            String eventDescription = eventDetail.substring(0, eventDivision - 1);
                            String at = eventDetail.substring(eventDivision + 1, eventDetail.length());
                            Task event = new Event(eventDescription, at);
                            tasks.add(event);
                            System.out.println("Got it. I've added this task: \n" + event.toString()
                                    + "\nNow you have " + (tasks.size()) + " tasks in the list.");
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(act);
                        }
                        break;
                    case delete:
                        int d = sc.nextInt() - 1;
                        Task temp = tasks.get(d);
                        tasks.remove(d);
                        System.out.println("Noted. I've removed this task: \n" + temp.toString()
                                + "\nNow you have " + (tasks.size()) + " tasks in the list.");
                        break;
                }
            } catch(IllegalArgumentException e) {
                throw new DukeIllegalActionException();
            }
        }
    }
}

enum Action {
    list, bye, done, todo, deadline, event, delete
}
