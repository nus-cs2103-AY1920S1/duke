import java.util.Scanner;

public class Duke {
    private static int pointer = 0;
    private static final String border = "____________________________________________________________";
    private static final String upperBorder = border + "\n\n";
    private static final String lowerBorder = border + "\n";
    private static Task[] taskList = new Task[100];

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
            String str = sc.nextLine();
            String[] keywords = str.split(" ");
            if (keywords[0].equals("bye")) {
                break;
            } else if (keywords[0].equals("list")) {
                outputList(pointer, taskList);
            } else if (keywords[0].equals("done")) {
                System.out.println(doneTask(Integer.parseInt(keywords[1]), taskList));
            } else if (keywords[0].equals("todo")) {
                String temp = "";
                for (int i = 1; i < keywords.length; i++) {
                    temp = temp + " " + keywords[i];
                }
                System.out.println(todo(temp.strip()));
                pointer++;
            } else if (keywords[0].equals("deadline")) {
                String temp = "";
                String date = "";
                boolean byFlag = false;
                for (int i = 1; i < keywords.length; i++) {
                    if (byFlag) {
                        date = date + " " + keywords[i];
                    } else if (keywords[i].equals("/by")) {
                        byFlag = true;
                    } else {
                        temp = temp + " " + keywords[i];
                    }
                }
                System.out.println(deadline(temp.strip(), date.strip()));
                pointer++;
            } else if (keywords[0].equals("event")) {
                String temp = "";
                String date = "";
                boolean atFlag = false;
                for (int i = 1; i < keywords.length; i++) {
                    if (atFlag) {
                        date = date + " " + keywords[i];
                    } else if (keywords[i].equals("/at")) {
                        atFlag = true;
                    } else {
                        temp = temp + " " + keywords[i];
                    }
                }
                System.out.println(event(temp.strip(), date.strip()));
                pointer++;
            }
        }

        System.out.println(upperBorder + "Bye. Hope to see you again soon!\n" + lowerBorder);

        sc.close();

    }

    public static String addToList(int pointer, String string) {
        taskList[pointer] = new Task(string);
        return upperBorder + "added: " + string + "\n" + lowerBorder;
    }

    public static void outputList(int pointer, Task[] taskList) {
        System.out.println(border + "\n");
        for (int i = 1; i < pointer + 1; i++) {
            System.out.println(i + ". " + taskList[i - 1]);
        }
        System.out.println(lowerBorder);
    }

    public static String doneTask(int pointer, Task[] taskList) {
        taskList[pointer - 1].setDone();
        return upperBorder + "Nice! I've marked this task as done:\n"
            + taskList[pointer - 1] + "\n" + lowerBorder;
    }

    public static String todo(String string) {
        taskList[pointer] = new Todo(string);
        return taskWrap(taskList[pointer]);
    }

    public static String deadline(String string, String by) {
        taskList[pointer] = new Deadline(string, by);
        return taskWrap(taskList[pointer]);
    }

    public static String event(String string, String at) {
        taskList[pointer] = new Event(string, at);
        return taskWrap(taskList[pointer]);
    }

    public static String taskWrap(Task task) {
        return upperBorder + "Got it. I've added this task:\n" + task
        + "\n" + "Now you have " + (pointer + 1) + " tasks in the list.\n" + lowerBorder;
    }

}
