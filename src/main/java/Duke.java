<<<<<<< HEAD
import java.io.*;
=======
import java.text.ParseException;
>>>>>>> Level-8
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
                readData();
                run();
            } catch (DukeIllegalActionException | DukeIllegalDescriptionException | FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                break;
            }
        }
    }
    private static LinkedList<Task> tasks = new LinkedList<>();
    private static String basePath = new File("").getAbsolutePath();
    private static void run() throws DukeIllegalActionException, DukeIllegalDescriptionException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);
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
                        saveData();
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
                            saveData();
                        }
                        break;
                    case deadline:
                        String dlDetail = sc.nextLine();
                        int dlDivision = dlDetail.indexOf("/");
                        try {
                            String dlDescription = dlDetail.substring(0, dlDivision - 1);
<<<<<<< HEAD
                            String by = dlDetail.substring(dlDivision + 4, dlDetail.length());
=======
                            String by = dlDetail.substring(dlDivision + 3);
>>>>>>> Level-8
                            Task dl = new Deadline(dlDescription, by);
                            tasks.add(dl);
                            System.out.println("Got it. I've added this task: \n" + dl.toString()
                                    + "\nNow you have " + (tasks.size()) + " tasks in the list.");
                            saveData();
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(act);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case event:
                        String eventDetail = sc.nextLine();
                        int eventDivision = eventDetail.indexOf("/");
                        try {
                            String eventDescription = eventDetail.substring(0, eventDivision - 1);
<<<<<<< HEAD
                            String at = eventDetail.substring(eventDivision + 4, eventDetail.length());
=======
                            String at = eventDetail.substring(eventDivision + 3);
>>>>>>> Level-8
                            Task event = new Event(eventDescription, at);
                            tasks.add(event);
                            System.out.println("Got it. I've added this task: \n" + event.toString()
                                    + "\nNow you have " + (tasks.size()) + " tasks in the list.");
                            saveData();
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(act);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case delete:
                        int d = sc.nextInt() - 1;
                        Task temp = tasks.get(d);
                        tasks.remove(d);
                        System.out.println("Noted. I've removed this task: \n" + temp.toString()
                                + "\nNow you have " + (tasks.size()) + " tasks in the list.");
                        saveData();
                        break;
                }
            } catch(IllegalArgumentException e) {
                throw new DukeIllegalActionException();
            }
        }
    }

    private static void readData() {
        try {
            FileReader in = new FileReader(new File(basePath + "/data/duke.txt"));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ( (line = br.readLine()) != null) {
                String type = line.substring(line.indexOf(".") + 2, line.indexOf(".") + 3);
                String state = line.substring(line.indexOf(".") + 5, line.indexOf(".") + 6);
                String content = line.substring(line.indexOf(".") + 8);
                String time = "0";
                if (line.indexOf("(") > 0) {
                    content = line.substring(line.indexOf(".") + 8, line.indexOf("(") - 1);
                    time = line.substring(line.indexOf(":") + 2, line.indexOf(")"));
                }
                switch (type) {
                    case "T":
                        Task todo = new ToDo(content);
                        if (state.contentEquals("Y")) {
                            todo.setDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(content, time);
                        if (state.contentEquals("Y")) {
                            deadline.setDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(content, time);
                        if (state.contentEquals("Y")) {
                            event.setDone();
                        }
                        tasks.add(event);
                        break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveData() throws FileNotFoundException {
        File folder = new File(basePath + "/data");
        File file = new File(basePath + "/data/duke.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            for(Task task:tasks) {
                out.println(task.toString());
            }
        }
    }
}

enum Action {
    list, bye, done, todo, deadline, event, delete
}
