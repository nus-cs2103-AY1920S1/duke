import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lines = "    ____________________________________________________________";
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(lines);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> inputList = new ArrayList<Task>();
        boolean programRunning = true;
        while (programRunning) {
            String[] inputMessage = br.readLine().split(" ");
            System.out.println(lines);
            String taskCount = "";
            if (inputList.size() > 1) {
                taskCount = "tasks";
            } else {
                taskCount = "task";
            }
            try {
                switch (inputMessage[0]) {
                    case "list":
                        if (inputList.size() == 0) {
                            throw new DukeException("     The list is empty!");
                        }
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 1; i <= inputList.size(); i++) {
                            System.out.println("     " + i + ". " + inputList.get(i - 1));
                        }
                        System.out.println(lines);
                        System.out.println();
                        break;
                    case "bye":
                        System.out.println("     Bye. Hope to see you again soon!");
                        System.out.println(lines);
                        System.exit(0);
                        break;
                    case "done":
                        int index = Integer.parseInt(inputMessage[1]);
                        if (index > inputList.size() || index <= 0) {
                            throw new DukeException("     Such task does not exist!");
                        }
                        inputList.get(index - 1).completeTask();
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + inputList.get(index - 1));
                        System.out.println(lines);
                        System.out.println();
                        break;
                    case "todo":
                        if (inputMessage.length == 1) {
                            throw new DukeException("     OOPS!! The description of a todo cannot be empty");
                        }
                        System.out.println("     Got it. I've added this task:");
                        String item = "";
                        for (int i = 1; i < inputMessage.length; i++) {
                            if (i == inputMessage.length - 1) {
                                item += inputMessage[i];
                            } else {
                                item += inputMessage[i];
                                item += " ";
                            }
                        }
                        inputList.add(new Todo(item));
                        System.out.println("       " + inputList.get(inputList.size() - 1));
                        System.out.println("     Now you have " + inputList.size() + " " + taskCount + " in the list.");
                        System.out.println(lines);
                        System.out.println();
                        break;
                    case "deadline":
                    case "event":
                        String input = "";
                        int marker = 0;
                        for (int i = 1; i < inputMessage.length; i++)  {
                            if (i + 1 >= inputMessage.length) {
                                throw new DukeException("     Please provide more information");
                            }
                            if (inputMessage[i + 1].equals("/by") || inputMessage[i + 1].equals("/at")) {
                                input += inputMessage[i];
                                marker = i + 1;
                                break;
                            } else {
                                input += inputMessage[i];
                                input += " ";
                            }
                        }
                        String extraInfo = "";
                        for (int i = marker + 1; i < inputMessage.length; i++) {
                            if (i == inputMessage.length - 1) {
                                extraInfo += inputMessage[i];
                            } else {
                                extraInfo += inputMessage[i];
                                extraInfo += " ";
                            }
                        }
                        if (extraInfo.equals("")) {
                            throw new DukeException("     Please provide more information");
                        }
                        if (inputMessage[0].equals("deadline")) {
                            if (!inputMessage[marker].equals("/by")) {
                                throw new DukeException("     Wrong syntax, should be using /by for deadline");
                            }
                            inputList.add(new Deadline(input, extraInfo));
                        } else if (inputMessage[0].equals("event")) {
                            if (!inputMessage[marker].equals("/at")) {
                                throw new DukeException("     Wrong syntax, should be using /at for event");
                            }
                            inputList.add(new Event(input, extraInfo));
                        }
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + inputList.get(inputList.size() - 1));
                        System.out.println("     Now you have " + inputList.size() + " " + taskCount + " in the list.");
                        System.out.println(lines);
                        System.out.println();
                        break;
                    case "delete" :
                        int indexToDelete = Integer.parseInt(inputMessage[1]);
                        if(indexToDelete > inputList.size() || indexToDelete <= 0) {
                            throw new DukeException("     Such task does not exist!");
                        }
                        Task toBeDeleted = inputList.get(indexToDelete - 1);
                        inputList.remove(indexToDelete - 1);
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + toBeDeleted);
                        if (inputList.size() == 1) {
                            taskCount = "task";
                        } else {
                            taskCount = "tasks";
                        }
                        System.out.println("     Now you have " + inputList.size() + " " + taskCount + " in the list.");
                        System.out.println(lines);
                        System.out.println();
                        break;
                    default:
                        throw new DukeException("     OOPS!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error);
                System.out.println(lines);
                System.out.println();
            }
        }
    }
}
