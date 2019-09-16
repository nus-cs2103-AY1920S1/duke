import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class GuiParser {

    static Scanner sc = new Scanner(System.in);
    private static UI ui;
    private GuiUI gui;
    private static Storage storage;
    private static int count;
    private Tasklist tasklist;

    /**
     * Parser constructor
     *
     * @param tasklist tasklist containing the list of tasks
     * @param gui the ui to be used
     * @param storage where the tasks will be stored
     *
     */

    public GuiParser(Tasklist tasklist, GuiUI gui, Storage storage) {
        this.tasklist = tasklist;
        this.gui = gui;
        this.storage = storage;


        if (tasklist.returnTasks().isEmpty()) {
            count = 0;
        } else {
            count = tasklist.size();
        }
    }

    /**
     * Reads the user input and converts it to instructions.
     *
     * @throws IOException  if the user input is wrong
     */

    public String guiParserRead(String command) throws IOException {
            if (command.equals("bye")) {
                return gui.bye();
            } else if (command.equals("list")) {
                return gui.listOut() + "\n" + tasklist.printlist();
            } else {
                String[] ls = command.split(" ");
                if (ls[0].equals("done")) {
                    String num = command.substring(5, 6);
                    return doneCommand(num);
                } else if (ls[0].equals("todo")) {
                    String[] td = command.split(" ");
                    try {
                        return todoCommand(td);
                    } catch (DukeException e){
                        System.out.println(e);
                    }
                } else if (ls[0].equals("event")) {
                    String[] eve = command.split(" ");
                    return eventCommand(eve);
                } else if (ls[0].equals("deadline")) {
                    String[] eve = command.split(" ");
                    return deadlineCommand(eve);
                } else if (ls[0].equals("delete")) {
                    String dnumber = ls[1];
                    return deleteCommand(dnumber);
                } else if (ls[0].equals("find")) {
                    ArrayList<Task> temp = tasklist.find(ls[1]);
                    String out = gui.find();
                    out = out + "\n" + tasklist.printlistfind(temp);
                    return out;
                } else {
                    try {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                    return "OOPS!!! I'm sorry, but I don't know what that means :-(";
                }
            }
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }


    /**
     * Returns String of the Date and Time in the user friendly format
     *
     * @param deadline deadline of event or deadline
     * @return String in user friendly format
     */

    public String getDateTime(String deadline) {
        String[] datetime = deadline.split(" ");
        String[] date = datetime[0].split("/");
        String time = datetime[1];
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate datef = LocalDate.parse(datetime[0], dateformatter);
        String dates = datef.format(DateTimeFormatter.ofPattern("LLLL uuuu"));
        String days = datef.format(DateTimeFormatter.ofPattern("d"));
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("Hmm");
        LocalTime timef = LocalTime.parse(time, timeformatter);
        String times = timef.format(DateTimeFormatter.ofPattern("h.mma"));
        if (day == 1) {
            days = days + "st";
        } else if (day == 2) {
            days = days + "nd";
        } else if (day == 3) {
            days = days + "rd";
        } else {
            days = days + "th";
        }
        String output = days + " of " + dates + ", " + times;
        return output;
    }

    public String doneCommand(String num) throws IOException {
        int res = Integer.parseInt(num);
        Task t = tasklist.get(res - 1);
        t.markAsDone();
        storage.saveFile(tasklist.returnTasks());
        return gui.done() + "\n" + t;
    }

    public String todoCommand(String[] td) throws IOException, DukeException {
        if (td.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String com = td[1];
        if (td.length > 1) {
            for (int i = 2; i < td.length; i ++) {
                com = com + " " + td[i];
            }
        } else {

        }
        tasklist.addTodo(new Todo(com));
        String out = gui.taskadded();
        out = out + "\n" + tasklist.returnTasks().get(count);
        count++;
        out = out + "\n" + gui.listcount(count);
        storage.saveFile(tasklist.returnTasks());
        return out;
    }

    public String eventCommand(String[] eve) throws IOException {
        String com = "";
        String eventdate = "";
        for (int i = 1; i < eve.length; i ++) {
            if (eve[i].equals("/at")) {
                eventdate = eve[i+1];
                for (int j = i+2; j < eve.length; j ++) {
                    eventdate = eventdate + " " + eve[j];
                }
                break;
            } else {
                if (com == "") {
                    com = eve[i];
                } else {
                    com = com + " " + eve[i];
                }
            }
        }
        tasklist.addEvent(new Event(com, getDateTime(eventdate)));
        String out = gui.taskadded();
        out = out + "\n" + tasklist.returnTasks().get(count);
        count++;
        out = out + "\n" + gui.listcount(count);
        storage.saveFile(tasklist.returnTasks());
        return out;
    }

    public String deadlineCommand(String[] eve) throws IOException {
        String com = "";
        String deadline = "";
        for (int i = 1; i < eve.length; i ++) {
            if (eve[i].equals("/by")) {
                deadline = eve[i+1];
                for (int j = i+2; j < eve.length; j ++) {
                    deadline = deadline + " " + eve[j];
                }
                break;
            } else {
                if (com == "") {
                    com = eve[i];
                } else {
                    com = com + " " + eve[i];
                }
            }
        }
        tasklist.addDeadline(new Deadline(com, getDateTime(deadline)));
        String out = gui.taskadded();
        out = out + "\n" + tasklist.returnTasks().get(count);
        count++;
        out = out + "\n" + gui.listcount(count);
        storage.saveFile(tasklist.returnTasks());
        return out;
    }

    public String deleteCommand(String dnumber) throws IOException {
        int dnum = Integer.parseInt(dnumber);
        String out = "Noted. I've removed this task:";
        assert tasklist != null : "tasklist cannot be empty";
        out = out + "\n" + tasklist.returnTasks().get(dnum-1);
        tasklist.delete(dnum-1);
        count--;
        out = out + "\n" + gui.listcount(count);
        storage.saveFile(tasklist.returnTasks());
        return out;
    }
}
