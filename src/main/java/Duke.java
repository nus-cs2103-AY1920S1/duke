import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static void start() throws DukeException, ParseException {
        String input;
        int pos;
        ArrayList<Task> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String divider = "_____________________________________________________";
        while(sc.hasNext()) {
            input = sc.next();
            try {
                switch (input) {
                    case ("bye"):
                        System.out.println(divider);
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(divider);

                        return;

                    case ("list"):
                        System.out.println(divider);
                        System.out.println("Here are the tasks in your list:");

                        for (int i = 0; i < arr.size(); i++) {
                            System.out.println(i + 1 + "." + arr.get(i));
                        }
                        System.out.println(divider);
                        break;

                    case ("done"):
                        System.out.println(divider);
                        pos = sc.nextInt();
                        arr.get(pos - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(arr.get(pos - 1));
                        System.out.println(divider);
                        break;


                    case ("todo"):
                        System.out.println(divider);
                        String remaining = sc.nextLine().trim();
                        if (remaining.equals("")) {
                            throw new DukeException("☹OOPS!!! The Description of a todo cannot be empty");
                        }
                        Todo t = new Todo(remaining.substring(0));
                        arr.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        System.out.println(divider);
                        break;

                    case ("event"):
                        System.out.println(divider);
                        String remainingStuff = sc.nextLine();
                        int end = remainingStuff.indexOf('/');
                        if (end > 0) {
                            String description = remainingStuff.substring(1, remainingStuff.indexOf('/'));
                            String time = remainingStuff.substring(end + 4).trim();
                            if (time.isEmpty()) {
                                throw new DukeException("☹OOPS!!! Wrong format'");
                            }
                            else {
                                Event m = new Event(description, makeDate(time));
                                arr.add(m);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(m);
                                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                                System.out.println(divider);
                            }
                        }
                        else {
                            throw new DukeException("☹OOPS!!! Wrong format'");
                        }
                        break;


                    case ("deadline") :
                        System.out.println(divider);
                        String remainingStuff2 = sc.nextLine();
                        int end2 = remainingStuff2.indexOf('/');
                        if (end2 > 0) {

                            String description2 = remainingStuff2.substring(1, remainingStuff2.indexOf('/'));
                            String time2 = remainingStuff2.substring(end2 + 4).trim();
                            if (time2.isEmpty()) {
                                throw new DukeException("☹OOPS!!! Wrong format'");
                            }
                            else {
                                Deadline k = new Deadline(description2, makeDate(time2));
                                arr.add(k);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(k);
                                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                                System.out.println(divider);
                            }
                        }
                        else {
                            throw new DukeException("☹OOPS!!! Wrong format");
                        }
                        break;

                    case ("delete") :
                        int position = sc.nextInt();
                        System.out.println("Noted. I've removed this task.");
                        System.out.println(arr.get(position-1));
                        arr.remove(position-1);
                        System.out.println("Now you have " + arr.size() + " tasks in the list");
                        break;

                    default:
                        sc.nextLine();
                        throw new DukeException("☹OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dx) {
                System.out.println(dx);
            }
        }
    }

    static String makeDate(String input) {
        try {
            String ordinalIndicator;
            Date date = DATE_FORMAT.parse(input);
            String day = new SimpleDateFormat("dd").format(date);
            String month = new SimpleDateFormat("MMMMMMMMMMM").format(date);
            String year = new SimpleDateFormat("yyyy").format(date);
            String time = new SimpleDateFormat("h:mma").format(date).toLowerCase();

            int int_day = Integer.parseInt(day);
            if (int_day >= 11 && int_day <= 13) {
                ordinalIndicator = "th";
            } else if (int_day % 10 == 1) {
                ordinalIndicator = "st";
            } else if (int_day % 10 == 2) {
                ordinalIndicator = "nd";
            } else if (int_day % 10 == 3) {
                ordinalIndicator = "rd";
            } else {
                ordinalIndicator = "th";
            }

            String outputDate = int_day + ordinalIndicator + " of " + month + " " + year + ", " + time;
            return outputDate;

        } catch (ParseException p){
            System.out.println(p);
        }
        return "";
    }

    public static void main(String[] args) throws DukeException, ParseException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        System.out.println("_____________________________________________________");
        Duke.start();
    }
}
