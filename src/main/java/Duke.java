import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    static ArrayList<Task> memory = new ArrayList<>();
    static DateTime DT = new DateTime();
    static int index;
    static String filePath = "../duke/data/duke.txt";

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"; */
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        activeChat();
    }

    /*
    This method queries for user input and returns the desired result.
     */
    public static void activeChat() {
        loadInfoFromDrive();
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    printList();
                } else if (input.equals("done")) {
                    int taskNum = sc.nextInt();
                    doneTask(taskNum);
                } else if (input.equals("todo")) {
                    String TodoTask = sc.nextLine();
                    if (TodoTask.equals("")) {
                        throw new DukeException("Oops! The description of a todo cannot be empty.");
                    } else {
                        toDoTask(TodoTask);
                    }
                } else if (input.equals("event")) {
                    String EventTask = sc.nextLine();
                    if (EventTask.equals("")) {
                        throw new DukeException("Oops! The description of an event cannot be empty.");
                    } else {
                        eventTask(EventTask);
                    }
                } else if (input.equals("deadline")) {
                    String DeadlineTask = sc.nextLine();
                    if (DeadlineTask.equals("")) {
                        throw new DukeException("Oops! The description of a deadline cannot be empty.");
                    } else {
                        deadlineTask(DeadlineTask);
                    }
                } else if (input.equals("delete")) {
                    int deleteNum = sc.nextInt();
                    deleteTask(deleteNum);
                } else {
                    throw new DukeException("Oops! I'm sorry, but I don't know what that means :(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e2) {
                System.out.println("Something went wrong: "  + e2.getMessage());
            } finally {
                input = sc.next();
            }
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    /*
    This method loads the previously saved information on the hard disk onto Duke.
     */
    private static void loadInfoFromDrive() {
        try {
            Scanner s = new Scanner(new File(filePath));
            while (s.hasNext()) {
                Task newTask = convertToTask(s.nextLine());
                memory.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static Task convertToTask(String taskLine) {
        String[] allInfo = taskLine.split("[|]", 4);
        Task toReturn;
        if (taskLine.charAt(0) == 'T') {
            toReturn = remakeTodo(allInfo);
        } else if (taskLine.charAt(0) == 'D') {
            allInfo = taskLine.split("[|]", 5);
            toReturn = remakeDeadline(allInfo);
        } else {
            allInfo = taskLine.split("[|]", 7);
            toReturn = remakeEvent(allInfo);
        }
        return toReturn;
    }

    private static ToDo remakeTodo(String[] info) {
        //System.out.println(info[1]);
        ToDo ret = new ToDo(info[2].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    private static Deadline remakeDeadline(String[] info) {
        //System.out.println(info[1]);
        Deadline ret = new Deadline(info[2].trim(), info[3].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    private static Event remakeEvent(String[] info) {
        //System.out.println(info[1]);
        Event ret = new Event(info[2].trim(), info[3].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    /*
    This method generates the string of the data to be saved into the hard disk.
     */
    private static void saveToDrive() throws IOException {
        if (memory.size() > 0) {
            String text = "";
            Task firstTask = memory.get(0);
            if (firstTask instanceof ToDo) {
                ToDo first = (ToDo) firstTask;
                text = first.format();
            } else if (firstTask instanceof Deadline) {
                Deadline first = (Deadline) firstTask;
                text = first.format();
            } else {
                Event first = (Event) firstTask;
                text = first.format();
            }
            int numTasks = memory.size();
            for (int i = 1; i < numTasks; i++) {
                String text2 = "";
                Task specTask = memory.get(i);
                if (specTask instanceof ToDo) {
                    ToDo spec = (ToDo) specTask;
                    text2 = spec.format();
                } else if (specTask instanceof Deadline) {
                    Deadline spec = (Deadline) specTask;
                    text2 = spec.format();
                } else {
                    Event spec = (Event) specTask;
                    text2 = spec.format();
                }
                text += System.lineSeparator() + text2;
            }
            writeToFile(filePath, text);
        } else {
            writeToFile(filePath, "");
        }
    }

    /*
    This method overwrites the information from Duke to the hard disk. This is done to automatically update all
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /*
    This method marks the targeted task as done and provides confirmation to the user through output.
     */
    public static void doneTask(int taskNum) throws DukeException, IOException {
        index = memory.size();
        if (taskNum > index) {
            throw new DukeException("Oops! This task number does not exist.");
        } else {
            Task target = memory.get(taskNum - 1);
            target.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target);
            saveToDrive();
        }
    }

    /*
    This method iterates through the data collected and prints them out in the desired format.
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        int id = 1;
        for (Task current : memory) {
            System.out.println(id + "." + current.toString());
            id++;
        }
    }

    /*
    This method creates a ToDo object and adds it into memory.
     */
    public static void toDoTask(String a) throws IOException {
        ToDo newTodo = new ToDo(a);
        memory.add(newTodo);
        printTask(newTodo);
        saveToDrive();
    }

    /*
    This method creates an Event object and adds it into memory.
    If command does not include a timing, then user is prompted to enter the command again.
     */
    public static void eventTask(String b) throws DukeException, IOException {
        String[] details = b.split("/at");
        if (details.length < 2) {
            throw new DukeException("Oops! Please include the event timing and resubmit that command.");
        } else {
            String[] eventTime = details[1].trim().split(" ");
            if (eventTime.length < 2) {
                throw new DukeException("Oops! Please write the event timing such as 29/2/2019 1800-2000");
            } else {
                try {
                    String[] hoursMin = eventTime[1].split("-");
                    String hM = DT.getTime(hoursMin[0]) + "-" + DT.getTime(hoursMin[1]);
                    String formattedTime = DT.getDate(eventTime[0]) + hM;
                    Event newEvent = new Event(details[0], formattedTime);
                    memory.add(newEvent);
                    printTask(newEvent);
                    saveToDrive();
                } catch (DateException e) {
                    throw new DukeException("Oops! " + e.getMessage() + " Please write the event timing such as 29/2/2019 1800-2000");
                }
            }

        }
    }

    /*
    This method creates a deadlineTask and adds it into memory.
    If command does not include a deadline, then user is prompted to enter the command again.
     */
    public static void deadlineTask(String c) throws DukeException, IOException {
        String[] details = c.split("/by");
        if (details.length < 2) {
            throw new DukeException("Oops! Please include the deadline and resubmit that command.");
        } else {
            String[] time = details[1].trim().split(" ");
            if (time.length < 2) {
                throw new DukeException("Oops! Please write the deadline such as 29/2/2019 1800");
            } else {
                try {
                    String formattedTime = DT.getDate(time[0]) + DT.getTime(time[1]);
                    Deadline newDeadline = new Deadline(details[0], formattedTime);
                    memory.add(newDeadline);
                    printTask(newDeadline);
                    saveToDrive();
                } catch (DateException e) {
                    throw new DukeException("Oops! " + e.getMessage() + " Please write the deadline such as 29/2/2019 1800");
                }
            }

        }
    }

    /*
    This method prints the task that was just added to the list.
    */
    public static void printTask(Task task) {
        index = memory.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    /*
    This method removes the target task from the list and prompts the user the number of remaining tasks saved.
    @param int deleteNum the index of the task to be deleted
     */
    public static void deleteTask(int deleteNum) throws DukeException, IOException {
        index = memory.size();
        if (deleteNum > index) {
            throw new DukeException("Oops! This task number does not exist.");
        } else {
            Task removed = memory.remove(deleteNum - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removed);
            index = memory.size();
            System.out.println("Now you have " + index + " tasks in the list.");
            saveToDrive();
        }
    }
}

