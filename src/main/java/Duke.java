import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

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
        try {
            data = new WriteFile(path,false);
            ui = new Ui();
            Storage storage = new Storage(data,path,taskList);
            storage.initStorage();
            ui.link(taskList,storage);
            ui.showWelcome();
        } catch (DukeException ex) {
            ex.getMessage();
        } catch(ParseException ex) {
            System.out.println("wtf dude");
        } catch(
        FileNotFoundException ex) {
            System.out.println("I can't see");
        } catch(
        IOException ex) {
            System.out.println("That sign will stop me, cos I can't read!");
        }
    }
    public String getResponse(String inputOrig) {
        parser.scrubData();
        try {
            if (inputOrig.equals("vodka")) {
                return "You better have a bottle to give me Comrade." +
                        "Just look at the amount of chores I am helping you take note of!";
            } else if (inputOrig.equals("my name jeff")) {
                return "mai nam stahleen";
            } else if (inputOrig.equals("hey bb")) {
                return "Ay bb wan sum fuk";
            }
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
                String actualTask = parser.getNextCommand();
                String time = parser.getDoByDate();
                Task newTask = taskList.addDeadline(actualTask,time,0);
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
                return "Comrade, what rubbish are you mumbling? The only commands " +
                        "Soviets recognise are: find, todo, deadline, event, done and delete. " +
                        "If not, say bye and get on with your chores, Comrade!";
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        } catch (ParseException ex) {
            return "Comrade, give a proper date in the format dd/mm/yy HHMM";
        }

    }
}
