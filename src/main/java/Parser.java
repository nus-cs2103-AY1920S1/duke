import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class Parser {

    static Scanner sc = new Scanner(System.in);
    private static UI ui;
    private static Storage storage;
    private static int count;
    private Tasklist tasklist;

    /**
     * Parser constructor.
     *
     * @param tasklist tasklist containing the list of tasks
     * @param ui the ui to be used
     * @param storage where the tasks will be stored
     *
     */

    public Parser(Tasklist tasklist, UI ui, Storage storage) {
        this.tasklist = tasklist;
        this.ui = ui;
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

    public void parserRead() throws IOException {
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                UI.bye();
                break;
            } else if (command.equals("list")) {
                UI.printLine();
                UI.listOut();
                tasklist.listout();
                UI.printLineS();
            } else {
                String[] ls = command.split(" ");
                if (ls[0].equals("done")) {
                    String num = command.substring(5, 6);
                    int res = Integer.parseInt(num);
                    Task t = tasklist.get(res - 1);
                    t.markAsDone();
                    UI.printLine();
                    UI.done();
                    System.out.println(t);
                    UI.printLineS();
                    storage.saveFile(tasklist.returnTasks());
                } else if (ls[0].equals("todo")) {
                    String[] td = command.split(" ");
                    try {
                        if (td.length == 1) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        String com = td[1];
                        if (td.length > 1) {
                            for (int i = 2; i < td.length; i++) {
                                com = com + " " + td[i];
                            }
                        }
                        tasklist.addTodo(new Todo(com));
                        UI.printLine();
                        UI.taskadded();
                        System.out.println(tasklist.returnTasks().get(count));
                        count++;
                        UI.listcount(count);
                        UI.printLineS();
                        storage.saveFile(tasklist.returnTasks());
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                } else if (ls[0].equals("event")) {
                    String[] eve = command.split(" ");
                    String com = "";
                    String eventdate = "";
                    for (int i = 1; i < eve.length; i++) {
                        if (eve[i].equals("/at")) {
                            eventdate = eve[i + 1];
                            for (int j = i + 2; j < eve.length; j++) {
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
                    UI.printLine();
                    UI.taskadded();
                    System.out.println(tasklist.returnTasks().get(count));
                    count++;
                    UI.listcount(count);
                    UI.printLineS();
                    storage.saveFile(tasklist.returnTasks());
                } else if (ls[0].equals("deadline")) {
                    String[] eve = command.split(" ");
                    String com = "";
                    String deadline = "";
                    for (int i = 1; i < eve.length; i++) {
                        if (eve[i].equals("/by")) {
                            deadline = eve[i + 1];
                            for (int j = i + 2; j < eve.length; j++) {
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
                    UI.printLine();
                    UI.taskadded();
                    System.out.println(tasklist.returnTasks().get(count));
                    count++;
                    UI.listcount(count);
                    UI.printLineS();
                    storage.saveFile(tasklist.returnTasks());
                } else if (ls[0].equals("delete")) {
                    String dnumber = ls[1];
                    int dnum = Integer.parseInt(dnumber);
                    UI.printLine();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasklist.returnTasks().get(dnum - 1));
                    tasklist.delete(dnum - 1);
                    count--;
                    UI.listcount(count);
                    UI.printLineS();
                    storage.saveFile(tasklist.returnTasks());
                } else if (ls[0].equals("find")) {
                    ArrayList<Task> temp = tasklist.find(ls[1]);
                    UI.find();
                    tasklist.listfind(temp);
                    UI.printLineS();
                } else {
                    try {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    /**
     * Returns String of the Date and Time in the user friendly format.
     *
     * @param deadline deadline of event or deadline
     * @return String in user friendly format
     */

    public static String getDateTime(String deadline) {
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
}