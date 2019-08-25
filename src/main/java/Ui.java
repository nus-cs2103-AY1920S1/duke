import java.util.Scanner;

public class Ui {

    Scanner scanner = new Scanner(System.in);

    public void showWelcomeMessage(){
        String message = "Hello! I'm Slave! Your very own productivity application.\n" +
                "     What can I do for you?";
        System.out.println(Formatter.formatMessage(message));
    }

    public void showByeMessage(){
        String message = "Bye! Thanks for using me! Will be right here when you need me.";
        System.out.println(Formatter.formatMessage(message));
    }

    public void showErrorMessage(DukeException e){
        System.out.println(Formatter.formatMessage(e.getMessage()));
    }

    public String readCommand(){
        return scanner.nextLine();
    }

    public void printAddDeadlineCommand(Deadline deadlineTask, TaskList taskList){
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Got it. I've added this task:"));
        System.out.println(Formatter.indentLine("  " + deadlineTask));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }

    public void printAddEventCommand(Event eventTask, TaskList taskList){
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Got it. I've added this task:"));
        System.out.println(Formatter.indentLine("  " + eventTask));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }

    public void printAddToDoCommand(ToDo toDoTask, TaskList taskList){
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Got it. I've added this task:"));
        System.out.println(Formatter.indentLine("  " + toDoTask));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }

    public void printDeleteCommand(Task toRemove, TaskList taskList){
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Noted. I've removed this task:"));
        System.out.println(Formatter.indentLine("  " + toRemove));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }

    public void printDoneCommand(Task task, TaskList taskList){
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Nice! I've marked this task as done:"));
        System.out.println(Formatter.indentLine("  " + task));
        Formatter.printLine();
    }

    public void printListCommand(TaskList taskList){
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Here are the tasks in your list:"));
        for (Task task: taskList.getList()){
            System.out.println(Formatter.indentLine(task.id + "." + task.toString()));
        }
        Formatter.printLine();
    }
}
