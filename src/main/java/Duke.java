import java.io.*;
import java.util.*;

public class Duke {

    private static List<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        File f = new File("/Users/stephenchua/duke/src/main/data/duke.txt");
        try {
            if (!f.createNewFile()) {
//                System.out.println("New file created");
//            } else {
//                System.out.println("File already exists");

                //read file contents into List
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line = br.readLine()) != null){
                    //process the line
                    String[] lines = line.split(" \\| ");
//                    System.out.println(Arrays.toString(lines));
                    boolean isDone;
                    if (lines[1].equals("1")) {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    if (lines[0].equals("T")) {
                        tasks.add(new Todo(lines[2], isDone));
                    } else if (lines[0].equals("D")) {
                        tasks.add(new Deadline(lines[2], lines[3], isDone));
                    } else if (lines[0].equals("E")) {
                        tasks.add(new Event(lines[2],lines[3], isDone));
                    } else {
                        System.out.println("Corrupted data.");
                    }
                }

                br.close();
                fr.close();
//                System.out.println(tasks);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("There has been an IOException while creating the data file.");
        }


        Scanner userInput = new Scanner(System.in);
        while (true) {
            try {
                String s = userInput.nextLine().trim();
                if (s.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    userInput.close();
                    break;
                } else if (s.equals("list")){
                    printTasks();
                } else if (s.matches("done ([1-9]|[1-9][0-9]|100)")) {
                    int displayNumber = Integer.parseInt(s.substring(5));
                    markTaskAsDone(displayNumber - 1);
                } else if (s.startsWith("todo")) {
                    try {
                        s = s.substring(5);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException(" ☹ OOPS!!! Todos must have a description");
                    }
                    addTask(new Todo(s));
                } else if (s.startsWith("deadline")) {
                    try {
                        s = s.substring(9);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException(" ☹ OOPS!!! Deadlines must have a description");
                    }

                    String[] arguments = s.split("/by");
                    if (arguments.length != 2) {
                        throw new DukeException(" ☹ OOPS!!! Deadlines must have exactly one deadline");
                    }
                    addTask(new Deadline(arguments[0].trim(), DateTimeHelper.convertDateToString(arguments[1].trim())));
                } else if (s.startsWith("event")) {
                    try {
                        s = s.substring(6);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException(" ☹ OOPS!!! Events must have a description");
                    }

                    String[] arguments = s.split("/at");
                    if (arguments.length != 2) {
                        throw new DukeException(" ☹ OOPS!!! Events must have exactly one time");
                    }
                    addTask(new Event(arguments[0].trim(), DateTimeHelper.convertDateToString(arguments[1].trim())));
                } else if (s.matches("delete ([1-9]|[1-9][0-9]|100)")) {
                    int deleteIndex = Integer.parseInt(s.substring(7));
                    delete(deleteIndex - 1);
                } else {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                saveDataToFile(f);
//                System.out.println(tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void saveDataToFile(File file) throws DukeException {
        //write to a completely new file
        try {
            FileWriter fw = new FileWriter(file, false);
            Iterator<Task> itr = tasks.iterator();
            while (itr.hasNext()) {
//                System.out.println("called");
                String s = itr.next().dataFormat();
                fw.write(s + "\n");
//                System.out.println(s);
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to datafile.");
        }
    }

    private static void delete(int index) throws DukeException {
        try {
            Task t = tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            if (tasks.size() == 1) {
                System.out.println("Now you have 1 task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! There is no task number " + (index + 1));
        }
    }

    private static void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        tasks.add(task);
        System.out.println(task);
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }

    }

    private static void markTaskAsDone(int index) throws DukeException {
        try {
            Task t = tasks.get(index);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! There is no task number " + (index + 1));
        }
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
}

