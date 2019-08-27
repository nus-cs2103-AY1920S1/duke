import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = "   _     _      _     _      _     _       _     _      _     _      _     _      _     _   \n" +
                "  (c).-.(c)    (c).-.(c)    (c).-.(c)     (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)  \n" +
                "   / ._. \\      / ._. \\      / ._. \\       / ._. \\      / ._. \\      / ._. \\      / ._. \\   \n" +
                " __\\( Y )/__  __\\( Y )/__  __\\( Y )/__   __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__ \n" +
                "(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._) (_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)\n" +
                "   || T ||      || A ||      || I ||       || P ||      || I ||      || N ||      || G ||   \n" +
                " _.' `-' '._  _.' `-' '._  _.' `-' '._   _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._ \n" +
                "(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.) (.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)\n" +
                " `-'     `-'  `-'     `-'  `-'     `-'   `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-' ";
        System.out.println("Hello from\n" + logo);
        String greet = "____________________________________________________________\n" +
                "     Hello! I'm Tai Ping\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________";
        String bye = "____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greet);

        ArrayList<Task> taskList = new ArrayList<Task>();

        BufferedReader TasksFile = new BufferedReader(new FileReader("TaskList.txt"));
        String LineFile = "";
        while ((LineFile = TasksFile.readLine()) != null) {
            String[] WordsFile = LineFile.split("`");
            switch (WordsFile[0]) {
            case ("todo") :
                Task todoFile = new Todo(WordsFile[2]);
                taskList.add(todoFile);
                if (WordsFile[1].equals("\u2713")) {
                    todoFile.markAsDone(todoFile);
                }
                break;

            case ("event") :
                Task eventFile = new Event(WordsFile[2], WordsFile[3]);
                taskList.add(eventFile);
                if (WordsFile[1].equals("\u2713")) {
                    eventFile.markAsDone(eventFile);
                }
                break;

            case ("deadline") :
                Task deadlineFile = new Deadline(WordsFile[2], WordsFile[3]);
                taskList.add(deadlineFile);
                if (WordsFile[1].equals("\u2713")) {
                    deadlineFile.markAsDone(deadlineFile);
                }
                break;

            default :
                System.out.println(WordsFile[0] + LineFile + "error");
            }
        }

        Scanner sc = new Scanner(System.in);
        String TaskLine = sc.nextLine();

        outLoop:
        while (TaskLine != null) {
            String[] words = TaskLine.split(" ");
            //ErrorCheck(words);
            switch (words[0]) {
            case ("bye") :
                System.out.println(bye);
                break outLoop;

            case ("list") :
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                int count = 1;
                for (Task t : taskList) {
                    System.out.println(count + "." + t.toString());
                    //System.out.printf("[%s]%d. %s%n", t.getStatusIcon(), count, t.description);
                    count++;
                }
                System.out.println("____________________________________________________________\n");
                TaskLine = sc.nextLine();
                break;

            case ("done") :
                int taskNo = Character.getNumericValue(TaskLine.charAt(TaskLine.length() - 1));
                System.out.println("____________________________________________________________\n" +
                        "Congratulations! I've marked this task as done: ");
                taskList.get(taskNo - 1).markAsDone(taskList.get(taskNo - 1));
                System.out.println("[" + taskList.get(taskNo - 1).getStatusIcon() + "] " + taskList.get(taskNo - 1).getDescription());
                System.out.println("____________________________________________________________\n");
                TaskLine = sc.nextLine();
                break;

            case ("todo") :
                if (TaskLine.length() == 4) {
                    System.out.println("____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: ");
                    Task todoTask = new Todo(TaskLine.substring(5));
                    taskList.add(todoTask);
                    System.out.println(todoTask.toString());
                    System.out.println("Now you have " +  taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                }
                TaskLine = sc.nextLine();
                break;

            case ("deadline") :
                if (TaskLine.length() == 8) {
                    System.out.println("____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                            "____________________________________________________________");
                } else {
                    int indexOfSlash = TaskLine.indexOf("/");
                    Task deadlineTask = new Deadline(TaskLine.substring(9, indexOfSlash - 1), TaskLine.substring(indexOfSlash + 4));
                    taskList.add(deadlineTask);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: ");
                    System.out.println(deadlineTask.toString());
                    System.out.println("Now you have " +  taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                }
                TaskLine = sc.nextLine();
                break;

            case ("event") :
                if (TaskLine.length() == 5) {
                    System.out.println("____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of an event cannot be empty.\n" +
                            "____________________________________________________________");
                } else {
                    int indexOfSlash = TaskLine.indexOf("/");
                    Task eventTask = new Event(TaskLine.substring(6, indexOfSlash - 1), TaskLine.substring(indexOfSlash + 4));
                    taskList.add(eventTask);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: ");
                    System.out.println(eventTask.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                }
                TaskLine = sc.nextLine();
                break;

            case ("delete") :
                int taskNoDelete = Character.getNumericValue(TaskLine.charAt(7));
                System.out.println("____________________________________________________________\n" +
                        "Noted. I've removed this task: \n" +
                        taskList.get(taskNoDelete - 1).toString() + "\n" +
                        "Now you have " + (taskList.size() - 1) + " tasks in the list.\n" +
                        "____________________________________________________________");
                taskList.remove(taskNoDelete - 1);
                TaskLine = sc.nextLine();
                break;

            default :
                System.out.println("____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________");
                TaskLine = sc.nextLine();
                break;
            }
        }
        try (PrintStream out = new PrintStream(new FileOutputStream("TaskList.txt"))) {
            for (Task t : taskList) {
                if (t.getType().equals("todo")) {
                    out.print(t.getType() + "`" + t.getStatusIcon() + "`" + t.getDescription() + "`" + "\n" );
                } else {
                    out.print(t.getType() + "`" + t.getStatusIcon() + "`" + t.getDescription() + "`" + t.getDate() + "\n" );
                }
            }
        }
    }
    /*public static void ErrorCheck(String[] words ) throws IOException {
        if (words[0] == "" && words.length == 1) {
            System.out.println("____________________________________________________________\n" +
                    "     ☹ Hey! Empty input does not lead me anywhere :-(\n" +
                    "____________________________________________________________");
        } else if (words[0] == "done" && words.length == 1) {
            System.out.println("____________________________________________________________\n" +
                    "     ☹ Hey! You done nothing? :-(\n" +
                    "____________________________________________________________");
        } else if (words[0] == "delete" && words.length == 1) {
            System.out.println("____________________________________________________________\n" +
                    "     ☹ Hey! Delete nothing? :-(\n" +
                    "____________________________________________________________");
        }
    }*/
}
