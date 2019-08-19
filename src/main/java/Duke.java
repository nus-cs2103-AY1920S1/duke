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

    private void echoUser() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String exceptionString;
        while(!input.equalsIgnoreCase("Bye")) {
            try {
                String[] inputsplit = input.split(" ", 2);
                if (inputsplit[0].equalsIgnoreCase("list")) {
                    printRecord();
                } else if (inputsplit[0].equalsIgnoreCase("done")) {
                    handleDone(input);
                } else if (inputsplit[0].equalsIgnoreCase("todo") || inputsplit[0].equalsIgnoreCase("deadline") ||
                    inputsplit[0].equalsIgnoreCase("event")) {
                    handleNewTask(inputsplit[0], input);
                } else if (inputsplit[0].equalsIgnoreCase("delete")){
                    handleDelete(input);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n");
            } finally {
                input = scanner.nextLine();
                continue;
            }
        }
            System.out.println("Bye. Hope to see you again soon!\n");

    }

    private void handleDone(String doneInfo) throws DukeException {
        String[] inputsplit = doneInfo.split(" ", 2);
        if(inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of done must have a value.");
        } else if (Integer.parseInt(inputsplit[1]) > textEntered.size() || Integer.parseInt(inputsplit[1]) <= 0 ){
            throw new DukeException("OOPS!!! Invalid value for task done!");
        } else {
            int num = Integer.parseInt(inputsplit[1]);
            markTaskDone(num);
        }
    }

    private void handleDelete(String deleteInfo) throws DukeException{
        String[] inputsplit = deleteInfo.split(" ", 2);
        if(inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of delete must have a value.");
        } else if (Integer.parseInt(inputsplit[1]) > textEntered.size() || Integer.parseInt(inputsplit[1]) <= 0 ){
            throw new DukeException("OOPS!!! Invalid value for task delete!");
        } else {
            int num = Integer.parseInt(inputsplit[1]);
            Task t =  textEntered.remove(num - 1);
            System.out.println("Noted. I've removed this task: \n" + t.toString() + "\n"
                    + "Now you have " + textEntered.size() + " tasks in the list.");
        }
    }

    private void handleNewTask(String typeTask, String taskInfo) throws DukeException{
        String[] inputsplit = taskInfo.split(" ", 2);
        if(inputsplit.length <= 1 ){
            throw new DukeException("OOPS!!! The description of a " + typeTask + " cannot be empty.");
        }
        Task taskToAdd;
        if (typeTask.equalsIgnoreCase("todo")) {
             taskToAdd = new toDo(inputsplit[1]);
        } else if (typeTask.equalsIgnoreCase("deadline")) {
            String[] descripSplit = inputsplit[1].split(" /by ", 2);
             taskToAdd = new Deadline(descripSplit[0], descripSplit[1]);
        } else {
            String[] descripSplit = inputsplit[1].split(" /at ", 2);
             taskToAdd = new Event(descripSplit[0], descripSplit[1]);
        }
        addToRecord(taskToAdd);
    }

    private void addToRecord(Task t){
        this.textEntered.add(t);
        System.out.printf("Got it. I've added this task: \n  %s\nNow you have %d tasks in the list.%n", t.toString(), textEntered.size());
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
            System.out.print((i + 1) + "." + textEntered.get(i).toString() + "\n");
        }
        System.out.println();
    }
}
