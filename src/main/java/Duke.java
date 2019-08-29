import java.util.ArrayList;
import java.util.Scanner;

/*
 TODO: ADD EXCEPTION SUPPORT
 */
public class Duke {
    // Classes used in Duke
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private WriteFile data;

    public Duke(String path) {
        /**
         *  init 3 main components, ui storage and tasklist
         *  then init storage: load tasks into tasklist
         *  then link ui and storage and tasklist
         *  then show welcome
         *  @params String of path where tasks text file is
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
         *  scans input and decides whether adding,
         *  changing, or listing tasks
         *  if change then call delete/done on tasklist
         *  if list then calls the ui to list tasks
         *  if add then turns input string
         *  into task: event, deadline or to do
         *  every add statement in tasklist
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if command unknown
         */
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {

            if (input.equals("list")) {  // list command
                // list all tasks
                ui.list();
            } else if (input.equals("todo")) {   // to do command
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
            } else if (input.equals("deadline")) { // deadline command
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
            } else if (input.equals("event")) {   // event command
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
            } else if (input.equals("done")) {   // mark done
                int taskNum = sc.nextInt();
                Task doned = taskList.done(taskNum);
                ui.markDone(doned);
            } else if (input.equals("delete")) { // delete task
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
