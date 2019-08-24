import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.PrintStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.nio.charset.StandardCharsets;

class TaskManager {
    private Scanner scanner;
    private PrintStream printer;
    private ArrayList<Task> list;
    private File tasksList;

    public TaskManager() throws IOException, ClassNotFoundException, DukeException {
        this.scanner = new Scanner(System.in);
        this.printer = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        this.tasksList = new File("TasksList.sav");
        if(!tasksList.exists()) {
            this.list = new ArrayList<Task>();
            tasksList.createNewFile();
            serializeList();
        } else {
            try {
                FileInputStream fileIn = new FileInputStream("TasksList.sav");
                ObjectInputStream in = new ObjectInputStream(fileIn);       
                Object ob = in.readObject();
                if(ob instanceof ArrayList<?>) {
                    this.list = (ArrayList) ob;
                }
                fileIn.close();
                in.close();
            } catch (IOException error) {
                this.list = new ArrayList<Task>();
                tasksList.createNewFile();
                serializeList();
                throw new DukeException("Error: Corrupted save file. "
                        + "I have rewrote the old save file with a new one. "
                        + "Please restart me again.");
            }
        }
    }

    public void initializeTasks() throws DukeException {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ", 2);
            Action action = getAction(inputArr[0]);

            // For 1 word type Actions
            if (inputArr.length == 1) {
                switch (action) {
                case LIST :
                    printList();
                    break;
                case BYE :
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default :
                    checkInputError(action);
                    break;
                }
            } else if (inputArr.length == 2) {
                // For 2 word type Actions (Or greater than 2 word)
                switch (action) {
                    case TODO :
                        todoAction(inputArr[1]);
                        break;
                    case DEADLINE :
                        deadlineAction(inputArr[1], action);
                        break;
                    case EVENT :
                        eventAction(inputArr[1], action);
                        break;
                    case DONE :
                        doneAction(getTask(inputArr[1]));
                        break;
                    case DELETE :
                        deleteAction(getTask(inputArr[1]));
                        break;
                    default :
                        generalError();
                        break;
                }
            }      
            // Print a empty line after every action
            System.out.println();
        }
    }

    private void todoAction(String taskString) throws DukeException {
        Todo todo = new Todo(taskString);
        list.add(todo);
        printTask(todo);
        serializeList();
    }

    private void deadlineAction(String taskString, Action action) throws DukeException {
        String[] taskArr = taskString.split(" /by ", 2);
        if (taskArr.length == 1) {
            taskFormatError(action);
        } else {
            Deadline deadline = new Deadline(taskArr[0], taskArr[1]);
            list.add(deadline);
            printTask(deadline);
            serializeList();
        }
    }

    private void eventAction(String taskString, Action action) throws DukeException {
        String[] taskArr = taskString.split(" /at ", 2);
        if (taskArr.length == 1) {
            taskFormatError(action);
        } else {
            Event event = new Event(taskArr[0], taskArr[1]);
            list.add(event);
            printTask(event);
            serializeList();
        }
    }

    private void doneAction(Task task) throws DukeException {
        if(task.isCompleted()) {
            throw new DukeException("Task is already completed!");
        } else {
            task.setCompleted(true);
            System.out.println("Nice! I've marked this task as done: ");
            printer.println("  " + task.toString());
            serializeList();
        }
    }
    
    private void deleteAction(Task task) throws DukeException {
        System.out.println("Noted. I've removed this task: ");
        printer.println("  " + task.toString());
        list.remove(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        serializeList();
    }

    private Task getTask(String givenTask) throws DukeException {
        try {
            Integer.parseInt(givenTask);
        } catch (Exception e) {
            throw new DukeException("Oof. Done requires a number behind");
        }

        Integer taskNum = Integer.parseInt(givenTask);
        try {
            list.get(taskNum - 1);
        } catch (Exception e) {
            throw new DukeException("Oof. The given task number is not found");
        }

        return list.get(taskNum - 1);
    }

    private void printTask(Task task) {
        System.out.println("Got it. I've added this task: ");
        printer.println("  " + task.toString());
        System.out.println("Now you have " + this.list.size() +  " tasks in the list.");
    }

    private void printList() {
        if(this.list.size() == 0) {
            System.out.println("Your list is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                if(this.list.get(i).isCompleted()) {
                    printer.println((i + 1) + ". " + this.list.get(i));
                } else {
                    printer.println((i + 1) + ". " + this.list.get(i));
                }
            }
        }
    }

    public Action getAction(String action) throws DukeException {
        switch (action) {
        case "list" :
            return Action.LIST;
        case "bye" :
            return Action.BYE;
        case "todo" :
            return Action.TODO;
        case "deadline" :
            return Action.DEADLINE;
        case "event" :
            return Action.EVENT;
        case "done" :
            return Action.DONE;
        case "delete" :
            return Action.DELETE;
        default :
            generalError();
            return null;
        }
    }

    private void checkInputError(Action action) throws DukeException {
        switch (action) {
        case TODO :
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        case DEADLINE :
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        case EVENT :
            throw new DukeException("OOPS!!! The description of a event cannot be empty."); 
        case DONE :
            throw new DukeException("OOPS!!! The description of a done cannot be empty.");
        case DELETE :
            throw new DukeException("OOPS!!! The description of a delete cannot be empty.");
        default :
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void taskFormatError(Action action) throws DukeException {
        switch (action) {
        case DEADLINE :
            throw new DukeException("Deadline requires a specified time. E.g. \'Task /by Sunday\'");
        case EVENT :
            throw new DukeException("Event requires a specified time. E.g. \'Task /at Mon 2-4pm\'");
        default :
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void generalError() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void serializeList() throws DukeException {
        try {
            FileOutputStream fileOut = new FileOutputStream("TasksList.sav");
            ObjectOutputStream out =  new ObjectOutputStream(fileOut);
            out.writeObject(this.list);
            out.close();
            fileOut.close();
        } catch (IOException error) {
            throw new DukeException("Unable to serialize the list to TasksList.sav. Error: " 
                    + error.getMessage());
        }
    }
}