import java.text.ParseException;
import java.util.Scanner;

/*
 TODO: ADD EXCEPTION SUPPORT
 */
public class Duke {
    // Classes used in Duke
    private Storage storage;
    private Ui ui;
    private TaskList taskList = new TaskList();
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
        Storage storage = new Storage(data,path,taskList);
        storage.initStorage();
        ui.link(taskList,storage);
        ui.showWelcome();
    }
    public String getResponse(String inputOrig) throws ParseException {
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
        Scanner sc = new Scanner(inputOrig);
        String input = sc.next();
        if (input.equals("bye")) {
            return ui.goodBye();
        } else if (input.equals("list")) {  // list command
            // list all tasks
            return ui.list();
        } else if (input.equals("todo")) {   // to do command
            String taskInfo = sc.nextLine();
            if (taskInfo.equals("")) {
                return " ☹ OOPS!!! The description of a todo cannot be empty.";
            }
            Task newTask = taskList.addTodo(taskInfo,0);
            return ui.addTask(newTask);
        } else if (input.equals("deadline")) { // deadline command
            String taskInfo = sc.nextLine();
            if (taskInfo.equals("")) {
                return " ☹ OOPS!!! The description of a todo cannot be empty.";
            }
            int seperator = taskInfo.indexOf('/');
            // use sep to split string
            String actualTask = taskInfo.substring(0,seperator);
            seperator += 4; // put sep at space after /by
            String time = taskInfo.substring(seperator);
            Task newTask = taskList.addDeadline(actualTask,time,0);
            return ui.addTask(newTask);
        } else if (input.equals("event")) {   // event command
            String taskInfo = sc.nextLine();
            if (taskInfo.equals("")) {
               return " ☹ OOPS!!! The description of a todo cannot be empty.";
            }
            int seperator = taskInfo.indexOf('/');
            // use sep to split string
            String actualTask = taskInfo.substring(0,seperator);
            seperator += 4; // put sep at space after /by
            String time = taskInfo.substring(seperator);
            Task newTask = taskList.addEvent(actualTask,time,0);
            return ui.addTask(newTask);
        } else if (input.equals("done")) {   // mark done
            int taskNum = sc.nextInt();
            Task doneTask = taskList.done(taskNum);
            return ui.markDone(doneTask);
        } else if (input.equals("delete")) { // delete task
            int taskNum = sc.nextInt();
            Task delTask = taskList.delete(taskNum);
            return ui.delTask(delTask);
        } else if (input.equals("find")) { // turn into exception
            String keyWord = sc.nextLine();
            return ui.find(keyWord);
        } else {
            // handle all other cases
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
