import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> taskArrayList = new ArrayList<>();

    public static void start() throws DukeException {
        String input;
        int pos;
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

                        for (int i = 0; i < taskArrayList.size(); i++) {
                            System.out.println(i + 1 + "." + taskArrayList.get(i));
                        }
                        System.out.println(divider);
                        break;

                    case ("done"):
                        System.out.println(divider);
                        pos = sc.nextInt();
                        taskArrayList.get(pos - 1).markAsDone();
                        writeData();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskArrayList.get(pos - 1));
                        System.out.println(divider);
                        break;


                    case ("todo"):
                        System.out.println(divider);
                        String remaining = sc.nextLine().trim();
                        if (remaining.equals("")) {
                            throw new DukeException("☹OOPS!!! The Description of a todo cannot be empty");
                        }
                        Todo t = new Todo(remaining.substring(0));
                        taskArrayList.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
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
                                Event m = new Event(description, time);
                                taskArrayList.add(m);
                                writeData();
                                System.out.println("Got it. I've added this task:");
                                System.out.println(m);
                                System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
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
                                Deadline k = new Deadline(description2, time2);
                                taskArrayList.add(k);
                                writeData();
                                System.out.println("Got it. I've added this task:");
                                System.out.println(k);
                                System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
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
                        System.out.println(taskArrayList.get(position-1));
                        taskArrayList.remove(position-1);
                        writeData();
                        System.out.println("Now you have " + taskArrayList.size() + " tasks in the list");
                        break;

                    default:
                        sc.nextLine();
                        throw new DukeException("☹OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dx) {
                System.out.println(dx.getMessage());
            }
        }
    }

    static void readData() {
        try {
            FileReader fileReader = new FileReader("/Users/lawnce/Desktop/duke/data/duke.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    taskArrayList.add(createTask(line));
                }
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void writeData() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/lawnce/Desktop/duke/data/duke.txt"));
            for (Task task : taskArrayList) {
                bufferedWriter.write(task.getData());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static Task createTask(String text) {
        String[] splitWords = text.split("\\|");
        if (splitWords[0].equals("T")) {
            String todoText = splitWords[2].substring(1); //substring to remove space
            Todo todo = new Todo(todoText);
            if (splitWords[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        } else if (splitWords[0].equals("D")) {
            String deadlineText = splitWords[2].substring(1); //substring to remove space
            String deadlineTime = splitWords[3].substring(1); //substring to remove space
            Deadline deadline = new Deadline(deadlineText, deadlineTime);
            if (splitWords[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        } else { //event scenario
            String eventText = splitWords[2].substring(1); //substring to remove space
            String eventTime = splitWords[3].substring(1); //substring to remove space
            Event event = new Event(eventText, eventTime);
            if (splitWords[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        }
    }

    public static void main(String[] args) throws DukeException{
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
        readData();
        Duke.start();
    }
}


