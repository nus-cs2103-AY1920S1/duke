import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String message;
    public static List<Task> myList = new ArrayList<>();
    public static int idx = 0;
    public static String upperLine = "____________________________________________________________\n";
    public static String lowerLine = "____________________________________________________________\n";
    public static String[] oneLine;
    public static String[] timeDate;
    public static String frontSpace = "    ";

    public static void childFeature() throws DukeException {
        System.out.print(frontSpace + upperLine);
        String description;
        String time_date;
        String firstWord = oneLine[0];

        if (oneLine.length == 2 && !oneLine[1].isBlank()) {
            System.out.println(frontSpace + " Got it. I've added this task: ");
            //todo with description
            //deadline may not have /by
            //event may not have /at
            if (firstWord.equals("todo")) {
                myList.add(new Todo(oneLine[1].trim()));
            } else if (firstWord.equals("deadline")) {
                timeDate = oneLine[1].trim().split(" /by ");
                if (timeDate.length == 2) {
                    description = timeDate[0].trim();
                    time_date = timeDate[1].trim();
                    myList.add(new Deadline(description, time_date));
                } else {
                    throw new NoTimeAndDateException("specific date/time for deadline is wrong");
                }
            } else {
                timeDate = oneLine[1].trim().split(" /at ");
                if (timeDate.length == 2) {
                    description = timeDate[0].trim();
                    time_date = timeDate[1].trim();
                    myList.add(new Event(description, time_date));
                } else {
                    throw new NoTimeAndDateException("specific date/time for event is wrong");
                }
            }
        } else {
            throw new DukeException("The description of a " + firstWord + " cannot be empty.");
        }
        System.out.println(frontSpace + "   " + myList.get(idx));
        idx++;
        System.out.println(frontSpace + " Now you have " + myList.size() + " tasks in the list.");
        System.out.println(frontSpace + lowerLine);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void doneFeature() throws DukeException {
        System.out.print(frontSpace + upperLine);
        //done or "done     "
        if (oneLine.length == 1 || (oneLine.length == 2 && oneLine[1].isBlank())) {
            throw new DukeException("The description of a " + oneLine[0].trim() + " cannot be empty.");
        } else if (oneLine.length == 2 && !oneLine[1].isBlank()
                && oneLine[1].trim().split(" ").length == 1 && isNumeric(oneLine[1].trim())) {

            int i = Integer.parseInt(oneLine[1].trim());
            if (i <= myList.size() && i > 0) {
                System.out.println(frontSpace + " Nice! I've marked this task as done: ");
                Task current = myList.get(i - 1);
                current.markAsDone();
                System.out.println(frontSpace + "   " + current);
            } else {
                throw new TaskNotExistException("task does not exist");
            }
            //"done 2 3 " or "done 2 3 4 "
        } else if (oneLine.length == 2 && oneLine[1].trim().split(" ").length != 1) {
            throw new ExtraDescriptionException("There is extra description for done");
            //"done abc"
        } else {
            throw new InvalidNumberException("the description should be a number");
        }
        System.out.println(frontSpace + lowerLine);
    }

    public static void deleteFeature() throws DukeException {
        String deleteMessage1 = " Noted. I've removed this task: ";
        String deleteMessage2 = " Now you have " + (myList.size() - 1) + " tasks in the list.";
        System.out.print(frontSpace + upperLine);
        //make sure it only have one number follow
        if (oneLine.length == 1 || (oneLine.length == 2 && oneLine[1].isBlank())) {
            throw new DukeException("The description of a " + oneLine[0].trim() + " cannot be empty.");
        } else if (oneLine.length != 1 && !oneLine[1].isBlank()
                && oneLine[1].trim().split(" ").length == 1 && isNumeric(oneLine[1].trim())) {
            int i = Integer.parseInt(oneLine[1].trim());
            if (i <= myList.size() && i > 0) {
                System.out.println(frontSpace + deleteMessage1);
                Task delete_task = myList.get(i - 1);
                myList.remove(i - 1);
                System.out.println(frontSpace + "   " + delete_task);
                idx--;
            } else {
                throw new TaskNotExistException("task does not exist");
            }
        }else if (oneLine.length == 2 && oneLine[1].trim().split(" ").length != 1) {
            throw new ExtraDescriptionException("There is extra description for delete");
        } else {
            throw new InvalidNumberException("the description should be a number");
        }
        System.out.println(frontSpace + deleteMessage2);
        System.out.println(frontSpace + lowerLine);
    }

    public static void listFeature() throws DukeException {
        System.out.print(frontSpace + upperLine);
        if (oneLine.length == 1 || oneLine[1].isBlank()) {
            String title = " Here are the tasks in your list:\n";
            System.out.print(frontSpace + title);
            for (int i = 0; i < idx; i++) {
                System.out.println(frontSpace + " " + (i + 1) + ". " + myList.get(i));
            }
        } else {
            throw new ExtraDescriptionException("There is extra description for list");
        }
        System.out.println(frontSpace + lowerLine);
    }

    public static void byeFeature() throws DukeException {
        System.out.print(frontSpace + upperLine);
        if (oneLine.length == 1) {
            message = " Bye. Hope to see you again soon!\n";
            System.out.print(frontSpace + message);
        } else {
            throw new ExtraDescriptionException("There is extra description for bye");
        }
        System.out.println(frontSpace + lowerLine);
    }

    public static void main(String[] args) {
        String greet = "     Hello! I'm Duke\n"
                + "     What can I do for you?\n";
        greet = frontSpace + upperLine + greet + frontSpace + lowerLine;
        System.out.println(greet);
        while (true) {
            try {
                String cmd = sc.nextLine();
                oneLine = cmd.split(" ", 2);
                String firstWord = oneLine[0];

                if (firstWord.equals("bye")) {
                    byeFeature();
                    break;
                } else if (firstWord.equals("list")) {
                    listFeature();
                } else if (firstWord.equals("done")) {
                    doneFeature();
                } else if (firstWord.equals("delete")) {
                    deleteFeature();
                } else if (firstWord.equals("todo") || firstWord.equals("deadline")
                        || firstWord.equals("event")) {
                    childFeature();
                } else {
                    //situation of firstWord is invalid
                    System.out.print(frontSpace + upperLine);

                    throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
                System.out.println(frontSpace + lowerLine);
            }
        }
    }
}