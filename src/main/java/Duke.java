import java.util.*;

public class Duke {

    public static ArrayList<Task> storage = new ArrayList<>();

    public static void main(String[] args) {

        printOutput("Hello! I'm Duke\nWhat can i do for you?", false);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")) { //Read input until 'bye' command is entered
            //ADD TODO, DEADLINE, EVENT TASKS
            if(input.contains("todo") || input.contains("deadline") || input.contains("event")){
                String command = input.substring(0, input.indexOf(" "));
                String remaining = input.substring(input.indexOf(" ") + 1);
                String item;
                String date;

                switch(command){
                    case "todo":
                        Todo todo = new Todo(remaining);
                        storage.add(todo);
                        printOutput("  " + todo, true);
                    break;
                    case "deadline":
                        item = remaining.substring(0, remaining.indexOf("/by") - 1);
                        date = remaining.substring(remaining.indexOf("/by") + 4, remaining.length());

                        Deadline deadline = new Deadline(item, date);
                        storage.add(deadline);
                        printOutput("  " + deadline, true);
                    break;
                    case "event":
                        item = remaining.substring(0, remaining.indexOf("/at") - 1);
                        date = remaining.substring(remaining.indexOf("/at") + 4, remaining.length());

                        Event event = new Event(item, date);
                        storage.add(event);
                        printOutput("  " + event, true);
                    break;
                    default:;
                    break;
                }
            }
            else if(input.contains("done")){ //DONE
                int taskNo = Integer.parseInt(
                                input.replace("done", "")
                                .replace(" ", "")); //Removing 'done' and empty spaces
                Task task = storage.get(taskNo - 1); //Minus 1 because the displayed list starts at 1
                task.markAsDone();

                printOutput("Nice! I've marked this task as done: \n  " + task, false);
            }
            else if (input.equals("list")) { //LIST ITEMS
                String listOutput = "";
                for (int i = 0; i < storage.size(); i++) {
                    //Get tasks
                    Task task = storage.get(i);

                    listOutput += (i + 1) + "." + task;

                    if (i + 1 != storage.size()) {
                        listOutput += "\n";
                    }
                }
                printOutput(listOutput, false);
            } else { //ADD DEFAULT TASK
                Task newTask = new Task(input);
                storage.add(newTask);
                printOutput("added: " + input, false);
            }
            input = sc.nextLine();
        }
        printOutput("Bye. Hope to see you again soon!", false);
    }

    private static void printOutput(String s, boolean isTask){
        System.out.println("    ____________________________________________________________");
        if(isTask)
            System.out.println("    " + "Got it. I've added this task: ");
        System.out.println("    " + s.replace("\n","\n    "));
        if(isTask)
            System.out.println("    " + "Now you have " + storage.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
}
