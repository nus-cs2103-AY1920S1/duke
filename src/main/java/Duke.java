import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Integer num; //number in list which is done
        Task currTask; //refers to current task in list
        String currEvent;
        ArrayList list = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            String temp = ""; //current task being added
            String time = ""; //current time of current task being added
            String[] words = line.split(" ");
            currEvent = words[0];
            if (line.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                line = sc.nextLine();
            } else if (words[0].equals("done")) {
                num = Integer.valueOf(words[1]);
                currTask = (Task) list.get(num - 1);
                currTask.setStatusIcon(true);
                System.out.println("Nice! I've marked this task as done: \n  " + currTask);
                line = sc.nextLine();
            } else if (currEvent.equals("todo")) {
                for (int i = 1; i < words.length; i++) {
                    if (i == 1) {
                        temp += words[i];
                    } else {
                        temp += " " + words[i];
                    }
                }
                list.add(new ToDos(temp));
                System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                        + "\nNow you have " + list.size() + " tasks in the list.");
                line = sc.nextLine();
            } else if (currEvent.equals("deadline")) {
                boolean check = false;
                int count = 0;
                for (int i = 1; i < words.length; i++) {
                    if (words[i].equals("/by")) {
                        check = true;
                        continue;
                    }

                    if (check) {
                        if (count == 0) {
                            time += words[i];
                            count++;
                        } else {
                            time += " " + words[i];
                        }
                    } else {
                        if (i == 1) {
                            temp += words[i];
                        } else {
                            temp += " " + words[i];
                        }
                    }
                }
                list.add(new Deadline(temp, time));
                System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                        + "\nNow you have " + list.size() + " tasks in the list.");
                line = sc.nextLine();
            } else if (currEvent.equals("event")) {
                boolean check = false;
                int count = 0;
                for (int i = 1; i < words.length; i++) {
                    if (words[i].equals("/at")) {
                        check = true;
                        continue;
                    }

                    if (check) {
                        if (count == 0) {
                            time += words[i];
                            count++;
                        } else {
                            time += " " + words[i];
                        }
                    } else {
                        if (i == 1) {
                            temp += words[i];
                        } else {
                            temp += " " + words[i];
                        }
                    }
                }
                list.add(new Event(temp, time));
                System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                        + "\nNow you have " + list.size() + " tasks in the list.");
                line = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
