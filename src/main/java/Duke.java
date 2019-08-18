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
        while(programRunning) {
            String[] inputMessage = br.readLine().split(" ");
            System.out.println(lines);
            switch (inputMessage[0]) {
                case "list" :
                    for (int i = 1; i <= inputList.size(); i ++) {
                        System.out.println("     " + i + ". " + inputList.get(i - 1));
                    }
                    System.out.println(lines);
                    System.out.println();
                    break;
                case "bye" :
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println(lines);
                    System.exit(0);
                    break;
                case "done" :
                    System.out.println("     Nice! I've marked this task as done:");
                    int index = Integer.parseInt(inputMessage[1]);
                    inputList.get(index - 1).completeTask();
                    System.out.println("       " + inputList.get(index - 1));
                    System.out.println(lines);
                    System.out.println();
                    break;
                case "todo" :
                    System.out.println("     Got it. I've added this task:");
                    String item = "";
                    for (int i = 1; i < inputMessage.length; i ++) {
                        if (i == inputMessage.length - 1) {
                            item += inputMessage[i];
                        } else {
                            item += inputMessage[i];
                            item += " ";
                        }
                    }
                    inputList.add(new Todo(item));
                    System.out.println("       " + inputList.get(inputList.size() - 1));
                    System.out.println("     Now you have " + inputList.size() + " tasks in the list.");
                    System.out.println(lines);
                    System.out.println();
                    break;
                case "deadline" :
                case "event" :
                    System.out.println("     Got it. I've added this task:");
                    String input = "";
                    int marker = 0;
                    for (int i = 1; i < inputMessage.length; i ++) {
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
                    for (int i = marker + 1; i < inputMessage.length; i ++) {
                        if (i == inputMessage.length - 1) {
                            extraInfo += inputMessage[i];
                        } else {
                            extraInfo += inputMessage[i];
                            extraInfo += " ";
                        }
                    }
                    if (inputMessage[0].equals("deadline")) {
                        inputList.add(new Deadline(input, extraInfo));
                    } else if (inputMessage[0].equals("event")) {
                        inputList.add(new Event(input, extraInfo));
                    }
                    System.out.println("       " + inputList.get(inputList.size() - 1));
                    System.out.println("     Now you have " + inputList.size() + " tasks in the list.");
                    System.out.println(lines);
                    System.out.println();
                    break;
                default :
                    System.out.println("Invalid input");
                    System.out.println(lines);
                    System.out.println();
            }
        }
    }
}
