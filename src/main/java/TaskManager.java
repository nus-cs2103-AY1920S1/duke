import java.util.Scanner;
import java.util.ArrayList;

import java.io.PrintStream;

import java.nio.charset.StandardCharsets;

class TaskManager {
    private Scanner sc;
    private PrintStream ps;
    private ArrayList<Task> list;

    public TaskManager() {
        this.sc = new Scanner(System.in);
        this.ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        this.list = new ArrayList<>();
    }

    public void initializeTasks() {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ", 2);
            Action action = getAction(inputArr[0]);
            
            if(action == null) {
                generalError();
                System.out.println();
                continue;
            }

            // For 1 word type Actions
            if (inputArr.length == 1) {
                switch (action) {
                case LIST :
                    printList();
                    break;
                case BYE :
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
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

    private void todoAction(String taskString) {
        Todo todo = new Todo(taskString);
        list.add(todo);
        printTask(todo);
    }

    private void deadlineAction(String taskString, Action action) {
        String[] taskArr = taskString.split(" /by ", 2);
        if (taskArr.length == 1) {
            taskFormatError(action);
        } else {
            Deadline deadline = new Deadline(taskArr[0], taskArr[1]);
            list.add(deadline);
            printTask(deadline);
        }
    }

    private void eventAction(String taskString, Action action) {
        String[] taskArr = taskString.split(" /at ", 2);
        if (taskArr.length == 1) {
            taskFormatError(action);
        } else {
            Event event = new Event(taskArr[0], taskArr[1]);
            list.add(event);
            printTask(event);
        }
    }

    private void doneAction(Task task) {
        if (task != null) {
            if(task.isComplete()) {
                System.out.println("Task is already completed!");
            } else {
                task.setComplete(true);
                System.out.println("Nice! I've marked this task as done: ");
                ps.println("  " + task.toString());
            }
        } 
    }
    
    private void deleteAction(Task task) {
        if (task != null) {
            System.out.println("Noted. I've removed this task: ");
            ps.println("  " + task.toString());
            list.remove(task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    private Task getTask(String givenTask) {
        try {
            Integer.parseInt(givenTask);
        } catch (Exception e) {
            System.out.println("Oof. Done requires a number behind");
            return null;
        }

        Integer taskNum = Integer.parseInt(givenTask);
        try {
            list.get(taskNum - 1);
        } catch (Exception e) {
            System.out.println("Oof. The given task number is not found");
            return null;
        }

        return list.get(taskNum - 1);
    }

    private void printTask(Task task) {
        System.out.println("Got it. I've added this task: ");
        ps.println("  " + task.toString());
        System.out.println("Now you have " + this.list.size() +  " tasks in the list.");
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            if(this.list.get(i).isComplete()) {
                ps.println((i + 1) + ". " + this.list.get(i));
            } else {
                ps.println((i + 1) + ". " + this.list.get(i));
            }
        }
    }

    public Action getAction(String action) {
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
            return null;
        }
    }

    private void checkInputError(Action action) {
        switch (action) {
        case TODO :
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
            break;
        case DEADLINE :
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            break;
        case EVENT :
            System.out.println("OOPS!!! The description of a event cannot be empty.");
            break;
        case DONE :
            System.out.println("OOPS!!! The description of a done cannot be empty.");
            break;
        case DELETE :
            System.out.println("OOPS!!! The description of a delete cannot be empty.");
            break;
        default :
            // For anything else that is not a specified action
            generalError();
            break;
        }
    }

    private void taskFormatError(Action action) {
        switch (action) {
        case DEADLINE :
            System.out.println("Deadline requires a specified time. E.g. \'/by Sunday\'");
            break;
        case EVENT :
            System.out.println("Event requires a specified time. E.g. \'/at Mon 2-4pm\'");
            break;
        default :
            // Not supposed to happen
            throw new java.lang.Error("Task Error thrown was of: " + action + " type");
        }
    }

    private void generalError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}