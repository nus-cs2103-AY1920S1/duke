import java.util.Scanner;

import java.io.PrintStream;

import java.nio.charset.StandardCharsets;

class Ui {
    private Scanner scanner;
    private PrintStream printer;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.printer = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printEmpty() {
        System.out.println();
    }

    public void printWelcome() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("If you need help, type \'help\' anytime");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    public void printWhatToDo() {
        System.out.println("What would you like to do?");
    }

    public void printEmptyList() {
        System.out.println("Your list is empty. How about adding something inside first?");
    }

    public void printListStarter() {
        System.out.println("Here are the tasks in your list:");
    }

    public void printDone(Task task) {
        printer.println("Nice! I've marked this task as done: ");
        printer.println("  " + task.toString());
    }

    public void printAlreadyCompleted() {
        System.out.println("Task is already completed!");
    }

    public void printDelete(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task: ");
        printer.println("  " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void printAddTask(Task task, int totalTasks) {
        printer.println("Got it. I've added this task: ");
        printer.println("  " + task.toString());
        printer.println("Now you have " + totalTasks +  " tasks in the list.");
    }

    public void printTask(int index, Task task) {
        printer.println((index + 1) + ". " + task.toString());
    }

    public void exit() {
        System.out.println("Alright then. See you later.");
    }

    public void throwGeneralError() throws DukeException {
        throw new DukeException("Oof. I apologize, but I do not understand.");
    }

    public void throwApologyError() throws DukeException {
        throw new DukeException("Oof. I apologize, but no such command exists.");
    }

    public void throwMissingNumberError(String action) throws DukeException {
        throw new DukeException("Oof. " + action + " requires a number behind.");
    }

    public void throwMissingTaskError() throws DukeException {
        throw new DukeException("Oof. The given task number is not found.");
    }

    public void throwSerializeError() throws DukeException {
        throw new DukeException("Oof. Unable to serialize the list to Tasks.sav. " 
                + "If there is already a Tasks.sav, please delete it.");
    }

    public void throwCorruptedSavFileError() throws DukeException {
        throw new DukeException("Oof. Corrupted save file. "
                    + "I have rewrote the old save file with a new one. "
                    + "Please restart me again.");
    }

    public void throwTaskFormatError(Action action) throws DukeException {
        switch (action) {
            case DEADLINE :
                throw new DukeException("Oof. There seems to be an error with your deadline format. "
                        + "Here's an example: \'deadline Handup Quiz /by 17/05/2019 14:05\'");
            case EVENT :
                throw new DukeException("Oof. There seems to be an eror with your event format" 
                        + "Here's an example: \'event Go to class /at 17/05/2019 14:05\'");
            default :
                throw new DukeException("Oof. I apologize but I don't understand.");
        }
    }

    public void throwInputError(Action action) throws DukeException {
        switch (action) {
            case TODO :
                throw new DukeException("Oof. The description of a todo cannot be empty.");
            case DEADLINE :
                throw new DukeException("Oof. The description of a deadline cannot be empty.");
            case EVENT :
                throw new DukeException("Oof. The description of a event cannot be empty."); 
            case DONE :
                throw new DukeException("Oof. The description of a done cannot be empty.");
            case DELETE :
                throw new DukeException("Oof. The description of a delete cannot be empty.");
            default :
                throw new DukeException("Oof. I apologize but I don't understand.");
        }
    }

    public void printHelp() {
        System.out.println("Hello this is Duke's help page.");
        System.out.println("There are 7 main features excluding help");
        System.out.println("Type in the number respective to what you want to know. ");
        System.out.println("Otherwise, type in any other thing to return.");
        System.out.println("1. list 2. todo 3. deadline 4. event 5. done 6. delete 7. bye");
        while (scanner.hasNextLine()) {
            switch(scanner.nextLine()) {
                case "1" :
                    System.out.println("Type \'list\' to print your current tasks.");
                    break;
                case "2" :
                    System.out.println("Type \'todo myTask\' to keep track of a to-do.");
                    break;
                case "3" :
                    System.out.println("Type \'deadline myTask /by myDate\' to record a deadline.");
                    System.out.println("Format of myDate can be either in date or time or both: ");
                    System.out.println("DD/MM/YYYY HH:mm");
                    System.out.println("DD/MM/YYYY");
                    System.out.println("hh:mm");
                    break;
                case "4" :
                    System.out.println("Type \'event myTask /at myDate\' to record an event.");
                    System.out.println("Format of myDate can be either in date or time or both: ");
                    System.out.println("DD/MM/YYYY HH:mm");
                    System.out.println("DD/MM/YYYY");
                    System.out.println("hh:mm");
                    break;
                case "5" :
                    System.out.println("Type \'done number\' to complete the task of the number.");
                    System.out.println("If unsure of the task's number, use \'list\' to check first.");
                    break;
                case "6" :
                    System.out.println("Type \'delete number\' to remove the task of the number.");
                    System.out.println("If unsure of the task's number, use \'list\' to check first.");
                    break;
                case "7" :
                    System.out.println("Exits, what more?");
                    break;
                default :
                    System.out.println("Returning back to main page.");
                    return;
            }
        }
    }
}