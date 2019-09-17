import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Reads command and performs operations based on input or activity
 */
public class Parser {

    private Storage storage;
    private TaskList tasks;
    private String command;

    public Parser (Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }
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

    public void checkToDo (Scanner sc) throws Exception {
        String fulldescription = sc.nextLine();
        assert !fulldescription.isEmpty();
        if (!fulldescription.isEmpty()) {
            tasks.add(new Todo(fulldescription));
            storage.append(tasks.getLast());
        } else {
            throw new Exception();
        }
    }

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
