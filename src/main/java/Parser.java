import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Reads command and creates its respective Task object. Performs operations on TaskList. Stores the TaskList in a storage file.
 */
public class Parser {

    private Storage storage;
    private TaskList tasks;
    private String command;

    /**
     * Constructor of Parser.
     * @param storage storage object.
     * @param tasks TaskList object.
     */
    public Parser (Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Checks if command is done and marks task as done.
     * @param sc Scanner object.
     */
    public void checkDone (Scanner sc) {
        try {
            int taskNum = sc.nextInt();
            assert (taskNum != 0);
            tasks.taskDone(taskNum-1);
            System.out.println("IsDone?" + tasks.getList().get(taskNum-1).getIsDone());
            System.out.println("Nice! I've marked this task as done: \n" + tasks.taskPrint(taskNum-1));
            storage.update(tasks.getList());
        } catch (Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }

    /**
     * Checks if command is delete and removes task from TaskList.
     * @param sc Scanner object.
     */
    public void checkDelete (Scanner sc) {
        try {
            int numToDelete = sc.nextInt();
            assert (numToDelete != 0);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.taskPrint(numToDelete -1));
            System.out.println("Now you have " + (tasks.size() -1) + " tasks in the list. ");
            tasks.remove(numToDelete-1);
            storage.update(tasks.getList());
        } catch (Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }

    /**
     * Checks if command is Todo and creates a todo Object.
     * @param sc Scanner object.
     * @throws Exception if description is empty.
     */
    public void checkToDo (Scanner sc) throws Exception {
        String fullDescription = sc.nextLine();
        assert !fullDescription.isEmpty();
        if (!fullDescription.isEmpty()) {
            tasks.add(new Todo(fullDescription));
            storage.append(tasks.getLast());
        } else {
            throw new Exception();
        }
    }

    /**
     * Checks if command is deadline and creates a Deadline object.
     * @param sc Scanner object.
     * @throws Exception if description is empty.
     */
    public void checkDeadline (Scanner sc) throws Exception {
        String fullDescription = sc.nextLine();
        assert !(fullDescription == null);
        if (!fullDescription.isEmpty()) {
            String[] descriptionAndDate = fullDescription.split(" /by ");
            String description = descriptionAndDate[0];
            String by = descriptionAndDate[1];
            assert !(description == null);
            assert !(by == null);
            if(noClash(by)) {
                tasks.add(new Deadline(description, by)); //checks for no clash with another task in the list, if true then add Deadline to the TaskList
                storage.append(tasks.getLast());
            }
        } else {
            throw new Exception();
        }
    }

    /**
     * Checks if command is event and creates a Deadline object.
     * @param sc Scanner object.
     * @throws Exception if description is empty.
     */
    public void checkEvent (Scanner sc) throws Exception {
        String fullDescription = sc.nextLine();
        assert !(fullDescription == null);
        if (!fullDescription.isEmpty()) {
            String[] descriptionAndAt = fullDescription.split(" /at ");
            String description = descriptionAndAt[0];
            String at = descriptionAndAt[1];
            assert !(description == null);
            assert !(at == null);
            if(noClash(at)) {
                tasks.add(new Event(description, at)); //check for no clash with another task in the list, if true then add Event to the TaskList
                storage.append(tasks.getLast());
            }
        } else {
            throw new Exception();
        }
    }

    /**
     * Checks for any anomalies like no clash in timing.
     * @param timeAndDate timeAndDate of task object to be checked with the timeAndDate of other task objects.
     * @return
     */
    public boolean noClash(String timeAndDate) {
        ArrayList<Task> list = tasks.getList();
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) instanceof Deadline) {
                if(((Deadline) list.get(i)).getBy().equals(timeAndDate)) {
                    return false;
                }
            } else if(list.get(i) instanceof Event) {
                if(((Event) list.get(i)).getAt().equals(timeAndDate)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if command is todo, deadline or event.
     * @param sc Scanner object.
     * @param command type of task.
     */
    public void checkTask (Scanner sc, String command) {
        try {
            if(command.equals("todo")) {
                checkToDo(sc);
            } else if (command.equals("deadline")) {
                checkDeadline(sc);
            } else if (command.equals("event")) {
                checkEvent(sc);
            } else {
                throw new IllegalArgumentException();
            }
            System.out.println("Got it. I've added this task: ");
            System.out.println(" " + tasks.printLatest());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch(IllegalArgumentException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + "Enter <help> for a list of commands\n");
        } catch(Exception e) {
            System.out.println(command);
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Parser reads in command from user.
     * @param command commands like done, delete, list
     * @param sc
     */
    public void read (String command, Scanner sc) {
        this.command = command;
        if (command.equals("done")) {
            checkDone(sc);
        } else if(command.equals("delete")) {
            checkDelete(sc);
        } else if(command.equals("list")) {
            tasks.printForList();
        } else {
            checkTask(sc, this.command);
        }
    }
}
