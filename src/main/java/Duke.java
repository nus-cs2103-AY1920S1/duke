import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static int pointer = 0;
    private static final String border = "____________________________________________________________";
    private static final String upperBorder = border + "\n\n";
    private static final String lowerBorder = border + "\n";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n"
                + upperBorder + "Hello! I'm Duke\n" + "What can I do for you?\n" + lowerBorder);

        while (true) {
            try {
                String str = sc.nextLine();
                String[] keywords = str.split(" ");
                if (keywords[0].equals("bye")) {
                    break;
                } else if (keywords[0].equals("list")) {
                    outputList(taskList);
                } else if (keywords[0].equals("done")) {
                    System.out.println(doneTask(Integer.parseInt(keywords[1])));
                } else if (keywords[0].equals("todo")) {
                    String temp = parseTodo(keywords);
                    System.out.println(todo(temp.strip()));
                    pointer++;
                } else if (keywords[0].equals("deadline")) {
                    String[] temp = parseTaskTime(keywords, "deadline");
                    System.out.println(deadline(temp[0].strip(), temp[1].strip()));
                    pointer++;
                } else if (keywords[0].equals("event")) {
                    String[] temp = parseTaskTime(keywords, "event");
                    System.out.println(event(temp[0].strip(), temp[1].strip()));
                    pointer++;
                } else if (keywords[0].equals("delete")) {
                    System.out.println(deleteTask(Integer.parseInt(keywords[1])));
                    pointer--;
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                System.out.println(upperBorder + ex.getMessage() + "\n" + lowerBorder);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println(upperBorder
                        + "☹ OOPS!!! I'm sorry, but this task does not exist.\n" + lowerBorder);
            }
        }

        System.out.println(upperBorder + "Bye. Hope to see you again soon!\n" + lowerBorder);

        sc.close();

    }

    // public static String addToList(int pointer, String string) {
    //     taskList[pointer] = new Task(string);
    //     return upperBorder + "added: " + string + "\n" + lowerBorder;
    // }

    public static void outputList(ArrayList<Task> taskList) {
        System.out.println(border + "\n");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ". " + taskList.get(i - 1));
        }
        System.out.println(lowerBorder);
    }

    public static String doneTask(int pointer) throws DukeException {
        try {
            taskList.get(pointer - 1).setDone();
            return upperBorder + "Nice! I've marked this task as done:\n"
                    + taskList.get(pointer - 1) + "\n" + lowerBorder;
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but this task does not exist.");
        }
    }

    public static String todo(String string) {
        taskList.add(new Todo(string));
        return taskWrap(taskList.get(pointer), "add");
    }

    public static String deadline(String string, String by) {
        taskList.add(new Deadline(string, by));
        return taskWrap(taskList.get(pointer), "add");
    }

    public static String event(String string, String at) {
        taskList.add(new Event(string, at));
        return taskWrap(taskList.get(pointer), "add");
    }

    public static String taskWrap(Task task, String type) {
        switch (type) {
            case "add":
                return upperBorder + "Got it. I've added this task:\n" + task
                + "\n" + "Now you have " + (pointer + 1) + " tasks in the list.\n" + lowerBorder;

            case "delete":     
                return upperBorder + "Noted. I've removed this task:\n" + task
                + "\n" + "Now you have " + (pointer - 1) + " tasks in the list.\n" + lowerBorder;
        }
        return "";
    }

    public static String parseTodo(String[] keywords) throws DukeException {
        if (keywords.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }else {
            String temp = "";
            for (int i = 1; i < keywords.length; i++) {
                temp = temp + " " + keywords[i];
            }
            return temp;
        }
    }

    public static String[] parseTaskTime(String[] keywords, String dateTimeType) throws DukeException {
       if (keywords.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a " + dateTimeType + " cannot be empty.");
        } else {
            String temp = "";
            String date = "";
            boolean flag = false;
            switch (dateTimeType) {
                case "deadline":
                for (int i = 1; i < keywords.length; i++) {
                    if (flag) {
                        date = date + " " + keywords[i];
                    } else if (keywords[i].equals("/by")) {
                        flag = true;
                    } else {
                        temp = temp + " " + keywords[i];
                    }
                }
                break;

                case "event":
                for (int i = 1; i < keywords.length; i++) {
                    if (flag) {
                        date = date + " " + keywords[i];
                    } else if (keywords[i].equals("/at")) {
                        flag = true;
                    } else {
                        temp = temp + " " + keywords[i];
                    }
                }
                break;

                default:
                break;
            }
            if (date.equals("")) {
                switch (dateTimeType) {
                    case "deadline":
                    throw new DukeException("☹ OOPS!!! Your deadline does not have a /by.");

                    case "event":
                    throw new DukeException("☹ OOPS!!! Your event does not have an /at.");

                    default:
                    break;
                }
            }
            return new String[] {temp, date};
        }
    }

    public static String deleteTask(int pointer) throws DukeException {
        try {
            return taskWrap(taskList.remove(pointer - 1), "delete");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but this task does not exist.");
        }
    }

}
