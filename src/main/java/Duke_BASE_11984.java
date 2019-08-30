import java.util.ArrayList;
import java.util.Scanner;

/*
 TODO: ADD EXCEPTION SUPPORT
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    private ArrayList<Task> taskArr = new ArrayList<>();
    private WriteFile data;
    public Duke(String path) {
        /**
         *  init 3 main components
         * @params String of path where tasks text file is
         *  @return none
         */
        data = new WriteFile(path,false);
        ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(data,path,taskList);
        storage.initStorage();
        ui.link(taskList,storage);
        ui.showWelcome();
    }
    private void run() {
        // TODO add DukeException for this
        /**
         *  main run method
         *  scans input and turns it
         *  into task: event, deadline or to do
         *  every print statement wrapped with
         *  2 solid lines
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if command unknown
         */
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                // list all tasks
                ui.list();
            } else if (input.equals("todo")) {
                String taskInfo = sc.nextLine();
                if (taskInfo.equals("")) {
                    System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                int sep = taskInfo.indexOf('/');
                // use sep to split string
                String actualTask = taskInfo.substring(0,sep);
                sep += 4; // put sep at space after /by
                String time = taskInfo.substring(sep);
                Task newTask = taskList.addTodo(actualTask);
                ui.addTask(newTask);
            } else if (input.equals("deadline")) {
                String taskInfo = sc.nextLine();
                if (taskInfo.equals("")) {
                    System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                int sep = taskInfo.indexOf('/');
                // use sep to split string
                String actualTask = taskInfo.substring(0,sep);
                sep += 4; // put sep at space after /by
                String time = taskInfo.substring(sep);
                Task newTask = taskList.addDeadline(actualTask,time);
                ui.addTask(newTask);
            } else if (input.equals("event")) {   // list command
                String taskInfo = sc.nextLine();
                if (taskInfo.equals("")) {
                    System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                int sep = taskInfo.indexOf('/');
                // use sep to split string
                String actualTask = taskInfo.substring(0,sep);
                sep += 4; // put sep at space after /by
                String time = taskInfo.substring(sep);
                Task newTask = taskList.addEvent(actualTask,time);
                ui.addTask(newTask);
            } else if (input.equals("done")) {   // list command
                int taskNum = sc.nextInt();
                Task doned = taskList.done(taskNum);
                ui.markDone(doned);
            } else if (input.equals("delete")) {
                int taskNum = sc.nextInt();
                Task deledTask = taskList.delete(taskNum);
                ui.delTask(deledTask);
            } else { // turn into exception
                // handle all other cases
                ui.printLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                ui.printLine();
            }
            input = sc.next();
        }
        ui.goodBye();
    }
    public static void main(String[] args) {
        Duke d = new Duke("C:\\Users\\Seb\\duke\\storage\\duke.txt");
        d.run();
    }
}
