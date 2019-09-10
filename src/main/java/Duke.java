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
    private Parser parser = new Parser();

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
    public String getResponse(String inputOrig) throws ParseException, DukeException {
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
        //parser.scrubData();
        parser.parse(inputOrig);
        String command = parser.getCommand();
        if (command.equals("bye")) {
            return ui.goodBye();
        } else if (command.equals("list")) {  // list command
            // list all tasks
            return ui.list();
        } else if (command.equals("todo")) {   // to do command
            String taskInfo = parser.getNextCommand();
            Task newTask = taskList.addTodo(taskInfo,0);
            return ui.addTask(newTask);
        } else if (command.equals("deadline")) { // deadline command
            String time = parser.getDoByDate();
            Task newTask = taskList.addDeadline(command,time,0);
            return ui.addTask(newTask);
        } else if (command.equals("event")) {   // event command

            String actualTask = parser.getNextCommand();

            String time = parser.getDoByDate();
            Task newTask = taskList.addEvent(actualTask,time,0);
            return ui.addTask(newTask);
        } else if (command.equals("done")) {   // mark done
            int taskNum = parser.getTaskNum();
            Task doneTask = taskList.done(taskNum);
            return ui.markDone(doneTask);
        } else if (command.equals("delete")) { // delete task
            int taskNum = parser.getTaskNum();
            Task delTask = taskList.delete(taskNum);
            return ui.delTask(delTask);
        } else if (command.equals("find")) { // turn into exception
            String keyWord = parser.getNextCommand();
            return ui.find(keyWord);
        } else {
            // handle all other cases
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
