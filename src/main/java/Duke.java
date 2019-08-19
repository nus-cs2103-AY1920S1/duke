import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private ArrayList<Task> textEntered;

    public Duke(){
        textEntered = new ArrayList<Task>(100);
    }

    public static void main(String[] args) {
        Duke dukebot = new Duke();
        dukebot.greetUser();
        dukebot.echoUser();
    }

    private void greetUser(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    private void echoUser(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("Bye")){
            String[] inputsplit = input.split(" ", 2);
            if(inputsplit[0].equalsIgnoreCase("list")) {
                printRecord();
            } else if(inputsplit[0].equalsIgnoreCase("done")){
                int num = Integer.parseInt(inputsplit[1]);
                markTaskDone(num);
            } else if(inputsplit[0].equalsIgnoreCase("todo")){
                toDo taskToDo = new toDo(inputsplit[1]);
                addToRecord(taskToDo);
            } else if (inputsplit[0].equalsIgnoreCase("deadline")) {
                String[] descripSplit = inputsplit[1].split(" /by ", 2);
                Deadline taskDead = new Deadline(descripSplit[0] , descripSplit[1] );
                addToRecord(taskDead);
            } else {
                String[] descripSplit = inputsplit[1].split(" /at ", 2);
                Event taskEvent = new Event(descripSplit[0] , descripSplit[1] );
                addToRecord(taskEvent);

            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    private void addToRecord(Task t){
        this.textEntered.add(t);
        System.out.printf("Got it. I've added this task: \n%s\nNow you have %d tasks in the list.%n", t.toString(), textEntered.size());
        System.out.println();
    }

    private void markTaskDone(int num){
        Task t =  textEntered.get(num - 1);
        t.markIsDone();
        System.out.println(" Nice! I've marked this task as done: ");
        System.out.println("  " + t.getStatusIcon() + " " +  t.getDescription() + "\n");
    }

    private void printRecord(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < textEntered.size(); i++){
            System.out.print((i + 1) + textEntered.get(i).toString() + "\n");
        }
        System.out.println();
    }
}
