import java.util.Scanner;
import java.util.ArrayList;
import java.text.DateFormatSymbols;

public class Duke {


    public static void printList(ArrayList<Task> list) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(i + "." + task.toString());
            i += 1;
        }
    }

    public static String getMonth(String month) throws DukeException {
        int monthIndex = Integer.parseInt(month);
        if (monthIndex > 12) {
            throw new DukeException("☹ OOPS!!! The month you inputted is not valid.");
        }
        return new DateFormatSymbols().getMonths()[monthIndex - 1];
    }

    public static void validateHour(int hour) throws DukeException {
        if (hour > 24 || hour < 1) {
            throw new DukeException("☹ OOPS!!! The time you inputted is not valid.");
        }
    }

    public static void validateMinute(int minute) throws DukeException {
        if (minute > 59 || minute < 0) {
            throw new DukeException("☹ OOPS!!! The time you inputted is not valid.");
        }
    }

    public static String parseTime(String timeDetail) throws DukeException {
        int hour = (Integer.parseInt(timeDetail)/100);
        validateHour(hour);
        String actualHour = hour % 12 == 0 ? "12" : String.valueOf(hour % 12); 
        int minute = Integer.parseInt(timeDetail) % 100;
        validateMinute(minute);
        String meridiem = hour >= 12 && hour < 24 ? "pm" : "am";
        String processedMinute = minute == 0 ? "" : "." + String.valueOf(minute);
        return actualHour + processedMinute + meridiem;
    }

    public static String parseDate(String dateDetail) throws DukeException {
        String[] dateBreakup = dateDetail.split("/");
        String month = getMonth(dateBreakup[1]);    
        int lastDigitOfDay = Integer.parseInt(dateBreakup[0]) % 10;
        String dayEnding = lastDigitOfDay == 1 ? "st" : lastDigitOfDay == 2 ? "nd" : 
                lastDigitOfDay == 3 ? "rd" : "th";
        return dateBreakup[0] + dayEnding + " of " + month + " " + dateBreakup[2]; 
    }

    public static String processDeadlineDate(String dateDetails) throws DukeException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        return parseDate(date) + ", " + parseTime(time);
    }

    public static String processEventDate(String dateDetails) throws DukeException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        String[] timeDetails = time.split("-");
        return parseDate(date) + ", " + parseTime(timeDetails[0]) + "-" + parseTime(timeDetails[1]);
    }

    public static void taskDone(int i, ArrayList<Task> list) throws Exception {
        try {
            list.get(i - 1).markAsDone();
        } 
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The item specified does not exist.");
        }
        
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list.get(i - 1).toString());

    }

    public static void deleteTask(int i, ArrayList<Task> list) throws Exception {
        try {
            list.get(i - 1);
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The item specified does not exist.");
        }
        
        System.out.println("Noted. I've removed this task: ");
        System.out.println(list.get(i - 1).toString());
        list.remove(i - 1);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void validateDetail(String[] detail) throws DukeException {
        if (detail.length == 0) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (! detail[0].equals("todo") && ! detail[0].equals("event") &&! detail[0].equals("deadline")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (detail.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a " + detail[0] + " cannot be empty.");
        }
    }

    public static void validateHour(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("☹ OOPS!!! The due date of a deadline must be specified.");
        }
    }

    public static void validateDeadlineDetails(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("☹ OOPS!!! The due date of a deadline must be specified.");
        }
    }

    public static void validateEventDetails(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("☹ OOPS!!! The timeline of an event must be specified.");
        }
    }

    public static void addTask(String input, ArrayList<Task> list) throws Exception {
        Task task;
        String detail;
        String dueDetail;
        String[] inputAsArr = input.split(" ");
        validateDetail(inputAsArr);
        String command = inputAsArr[0];
        String rest = input.substring(input.indexOf(" ") + 1);

        if (command.equals("todo")) {
            task = new ToDos(rest);
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + task.toString());
        } else if (command.equals("deadline")) {
            String[] detAsArr = rest.split(" /by ");
            validateDeadlineDetails(detAsArr);
            detail = detAsArr[0];
            dueDetail = detAsArr[1];
            task = new Deadline(detail, processDeadlineDate(dueDetail));
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + task.toString());
        } else {
            String[] detAsArr = rest.split(" /at ");
            validateEventDetails(detAsArr);
            detail = detAsArr[0];
            dueDetail = detAsArr[1];
            task = new Event(detail, processEventDate(dueDetail));
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + task);
        }
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Task> list = new ArrayList<>();
        String input;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        
        input = scanner.nextLine();

        while (! input.equals("bye")) {
            try {
                String[] command = input.split(" ");
                
                if (input.equals("list")) {
                    printList(list);
                } else if (command[0].equals("done")) {
                    if (command.length == 1) {
                        throw new DukeException("☹ OOPS!!! The completed task's index must be mentioned.");
                    } else {
                        try {
                            taskDone(Integer.parseInt(command[1]), list);
                        } 
                        catch (NumberFormatException nfe) {
                            throw new DukeException("☹ OOPS!!! The completed task's index must be a number.");
                        }
                    }
                } else if (command[0].equals("delete")) {
                    if (command.length == 1) {
                        throw new DukeException("☹ OOPS!!! The index of task to be deleted must be mentioned.");
                    } else {
                        try {
                            deleteTask(Integer.parseInt(command[1]), list);
                        } 
                        catch (NumberFormatException nfe) {
                            throw new DukeException("☹ OOPS!!! The index of task to be deleted must be a number.");
                        }
                    }
                } else {
                   addTask(input, list); 
                }
            } 
            catch (Exception e) {
                System.err.println(e);
            }
            finally {
                input = scanner.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
