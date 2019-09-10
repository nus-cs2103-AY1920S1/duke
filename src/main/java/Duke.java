import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Duke {

    private Ui ui;
    public static List<Task> lst = new ArrayList<>();


    public Duke()  {
        this.ui = new Ui();
    }

    public void run() {

        ui.printWelcomeMessage();

        while (ui.hasInputs()) {

            String input = ui.readCommand();
            String[] tokens = input.split(" ");
            if (tokens[0].equals("bye")) {
                //printOneLine("Bye. Hope to see you again soon!");
                ui.printOneLine(new ExitCommand());
                break;
            } else if (tokens[0].equals("list")) {
                ui.printNumberList(lst);
            } else if (tokens[0].equals("done")) {
                try {
                    doTask(tokens[1]);
                } catch (NumberFormatException error) {
                    ui.printOneLine(new DukeException("Must be integer",DukeExceptionType.NOTINTEGER).getMessage());

                } catch (IllegalArgumentException error2) {
                    ui.printOneLine(new DukeException(error2.getMessage(),DukeExceptionType.GENERALMISTAKE).getMessage());
                } catch (IndexOutOfBoundsException error3) {
                    ui.printOneLine(new DukeException("No such task",DukeExceptionType.MISSINGTASK).getMessage());
                }
            } else if (tokens[0].equals("delete")) {
                try {
                    deleteTask(tokens[1]);
                } catch (NumberFormatException error) {
                    ui.printOneLine(new DukeException("Must be integer",DukeExceptionType.NOTINTEGER).getMessage());
                } catch (IndexOutOfBoundsException error3) {
                    ui.printOneLine(new DukeException("No such task",DukeExceptionType.MISSINGTASK).getMessage());

                }
            } else {
                createTask(tokens);
            }

        }

    }


    public static void main(String[] args) {
        new Duke().run();
    }

    public void doTask(String str) throws IllegalArgumentException, IndexOutOfBoundsException {
        int pos = Integer.parseInt(str)-1;
        Task task = lst.get(pos);
        boolean isDoneBefore = task.setDone();
        if (isDoneBefore) {
            throw new IllegalArgumentException("Task has already been done");
        }
        List<String> inst = List.of("Nice! I've marked this task as done: ",
                "  "+task.toString());
        ui.printInput(inst);
    }

    public void createTask(String [] tokens) {
        Task task = null;
        if (tokens[0].equals("todo")) {
            try {
                ui.checkValidLength(tokens);
                task = ToDo.createToDo(tokens);
            } catch (IllegalArgumentException error2){
                ui.printOneLine(new DukeException(error2.getMessage(),DukeExceptionType.GENERALMISTAKE).getMessage());
            }
        } else if (tokens[0].equals("deadline")) {
            try {
                ui.checkValidLength(tokens);
                if (!Arrays.asList(tokens).contains("/by")) {
                    throw new IllegalArgumentException("Missing deadline");
                } else {
                    task = Deadline.createDeadline(tokens);
                }
            } catch (IllegalArgumentException error2){
                ui.printOneLine(new DukeException(error2.getMessage(),DukeExceptionType.GENERALMISTAKE).getMessage());
            }
        } else if (tokens[0].equals("event")) {
            try {
                ui.checkValidLength(tokens);
                if (!Arrays.asList(tokens).contains("/at")) {
                    throw new IllegalArgumentException("Missing deadline");
                } else {
                    task = Event.createEvent(tokens);
                }
            } catch (IllegalArgumentException error2){
                ui.printOneLine(new DukeException(error2.getMessage(),DukeExceptionType.GENERALMISTAKE).getMessage());
            }
        } else {
            ui.printOneLine(new DukeException("Command doesn't exist",DukeExceptionType.INVALIDCOMMAND).getMessage());
        }

        if (task != null) {
            lst.add(task);
            ui.printInput(task, lst);
        }
    }

    public void deleteTask(String str) throws NumberFormatException, IndexOutOfBoundsException {
        int pos = Integer.parseInt(str)-1;
        Task task = lst.get(pos);
        lst.remove(task);
        ui.printDeletion(task, lst);
    }



   
}
