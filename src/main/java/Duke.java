import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private static String indent = "    ";
    private static String line = "____________________________________________________________";
    private static String greeting = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    private static String bye = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________\n";

    public static void main(String[] args) throws FileNotFoundException, IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        greet();
        loadData();
        readData();
    }

    private static void loadData() throws FileNotFoundException, ParseException {
        File f = new File("duke.txt"); // create a File for the given file path
        Scanner sc = new Scanner(f); // create a Scanner using the File as the source
        while (sc.hasNext()) {
            Task t = new Task(""); // dummie task
            String type = sc.next();
            int done = sc.nextInt();
            String name = sc.next();

            switch(type) {
                case "T":
                    t = new Todo(name);
                    break;
                case "D":
                    t = new Deadline(name, sc.next());
                    break;
                case "E":
                    t = new Event(name, sc.next());
                    break;
            }

            if (done == 1) {
                t.markAsDone();
            }
            taskList.add(t);
        }
    }

    private static void greet() {
        System.out.println(greeting);
    }

    private static void readData() throws IOException{
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.next();

            if (command.equals("done")) {
                if (!sc.hasNextInt()) {
                    System.out.println(indent + line);
                    System.out.println(indent + new NoTaskException());
                    System.out.println(indent + line + "\n");
                    continue;
                }
                int index = sc.nextInt() - 1;
                if (index >= taskList.size()) {
                    System.out.println(indent + line);
                    System.out.println(indent + new NoTaskException());
                    System.out.println(indent + line + "\n");
                    continue;
                }
                Task t = taskList.get(index);
                t.markAsDone();
                System.out.println(indent + line);
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println(indent + "  " + t);
                System.out.println(indent + line + "\n");
            } else if (command.equals("delete")) {
                if (!sc.hasNextInt()) {
                    System.out.println(indent + line);
                    System.out.println(indent + new NoTaskException());
                    System.out.println(indent + line + "\n");
                    continue;
                }
                int index = sc.nextInt() - 1;
                if (index >= taskList.size()) {
                    System.out.println(indent + line);
                    System.out.println(indent + new NoTaskException());
                    System.out.println(indent + line + "\n");
                    continue;
                }
                Task t = taskList.get(index);
                taskList.remove(index);
                System.out.println((indent + line));
                System.out.println("     Noted. I've removed this task:");
                System.out.println(indent + "  " + t);
                System.out.println(indent + " Now you have "  + taskList.size() + " tasks in the list.");
                System.out.println(indent + line + "\n");

            } else if (command.equals("bye")) {
                System.out.println(bye);
            } else if (command.equals("list")) {
                int counter = 1;
                System.out.println(indent + line);
                System.out.println("     Here are the tasks in your list:");
                for (Task t: taskList) {
                    System.out.println(indent + " " + counter + "." + t) ;
                    counter++;
                }
                System.out.println(indent + line + "\n");
            } else {
                Task t;
                String words = sc.nextLine();

                if (command.equals("todo")) {
                    if (words.split(" ").length == 0 || words.equals("")) {
                        System.out.println(indent + line);
                        System.out.println(indent + new EmptyCommandException("todo"));
                        System.out.println(indent + line + "\n");
                        continue;
                    }
                    t = new Todo(words.trim());
                } else if (command.equals("event")) {
                    if (words.split(" ").length == 0 || words.equals("")) {
                        System.out.println(indent + line);
                        System.out.println(indent + new EmptyCommandException("event"));
                        System.out.println(indent + line + "\n");
                        continue;
                    }
                    String[] details = words.split(" /at ");
                    if (details.length == 1) {
                        System.out.println(indent + line);
                        System.out.println(indent + new NoDateException("event"));
                        System.out.println(indent + line + "\n");
                        continue;
                    }
                    t = new Event(details[0].trim(), details[1].trim());
                } else if (command.equals("deadline")){
                    if (words.split(" ").length == 0  || words.equals("")) {
                        System.out.println(indent + line);
                        System.out.println(indent + new EmptyCommandException("deadline"));
                        System.out.println(indent + line + "\n");
                        continue;
                    }
                    String[] details = words.split(" /by ");
                    if (details.length == 1) {
                        System.out.println(indent + line);
                        System.out.println(indent + new NoDateException("deadline"));
                        System.out.println(indent + line + "\n");
                        continue;
                    }
                    t = new Deadline(details[0].trim(), details[1].trim());
                } else {
                    System.out.println(indent + line);
                    System.out.println(indent + new UnknownCommandExeption());
                    System.out.println(indent + line + "\n");
                    continue;
                }
                taskList.add(t);
                System.out.println(indent + line);
                System.out.println("     Got it. I've added this task:");
                System.out.println(indent + "  " + t);
                System.out.println(indent + " Now you have "  + taskList.size() + " tasks in the list.");
                System.out.println(indent + line + "\n");
            }
            saveData();
        }
        sc.close();
    }

    private static void saveData() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        for (Task task : taskList) {
            String name = task.getDescription();
            int status = task.getStatus()? 1 : 0;
            if (task instanceof Todo) {
                fw.write("T " + status + " " + name + "\n");
            } else if (task instanceof Deadline) {
                fw.write("D " + status + " " + name + " " + ((Deadline) task).getBy() + "\n");
            } else {
                fw.write("E " + status + " " + name + " " +((Event) task).getAt() + "\n");
            }
        }
        fw.close();
    }
}
