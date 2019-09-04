import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class Parser {

    static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasks;
    private static UI ui;
    private static Storage storage;
    private static int count;

    public Parser(ArrayList<Task> tasks,UI ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;


        if (tasks.isEmpty()) {
            count = 0;
        } else {
            count = tasks.size();
        }
    }

    public static void parserRead() throws IOException {
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                UI.printLine();
                UI.bye();
                UI.printLineS();
                break;
            } else if (command.equals("list")) {
                UI.printLine();
                UI.listOut();
                for (int i = 0; i < tasks.size(); i ++) {
                    int num = i + 1;
                    System.out.println(num + ". " + tasks.get(i));
                }
                UI.printLineS();
            } else {
                String[] ls = command.split(" ");
                if (ls[0].equals("done")) {
                    String num = command.substring(5, 6);
                    int res = Integer.parseInt(num);
                    Task t = tasks.get(res - 1);
                    t.markAsDone();
                    UI.printLine();
                    UI.done();
                    System.out.println(t);
                    UI.printLineS();
                    storage.saveFile(tasks);
                } else if (ls[0].equals("todo")) {
                    String[] td = command.split(" ");
                    try {
                        if (td.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        String com = td[1];
                        if (td.length > 1) {
                            for (int i = 2; i < td.length; i ++) {
                                com = com + " " + td[i];
                            }
                        } else {

                        }
                        tasks.add(new Todo(com));
                        UI.printLine();
                        UI.taskadded();
                        System.out.println(tasks.get(count));
                        count++;
                        UI.listcount(count);
                        UI.printLineS();
                        storage.saveFile(tasks);
                    } catch (DukeException e){
                        System.out.println(e);
                    }
                } else if (ls[0].equals("event")) {
                    String[] eve = command.split(" ");
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
                    tasks.add(new Event(com, getDateTime(eventdate)));
                    UI.printLine();
                    UI.taskadded();
                    System.out.println(tasks.get(count));
                    count++;
                    UI.listcount(count);
                    UI.printLineS();
                    storage.saveFile(tasks);
                } else if (ls[0].equals("deadline")) {
                    String[] eve = command.split(" ");
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
                    tasks.add(new Deadline(com, getDateTime(deadline)));
                    UI.printLine();
                    UI.taskadded();
                    System.out.println(tasks.get(count));
                    count++;
                    UI.listcount(count);
                    UI.printLineS();
                    storage.saveFile(tasks);
                } else if (ls[0].equals("delete")) {
                    String dnumber = ls[1];
                    int dnum = Integer.parseInt(dnumber);
                    UI.printLine();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(dnum-1));
                    tasks.remove(dnum-1);
                    count--;
                    UI.listcount(count);
                    UI.printLineS();
                    storage.saveFile(tasks);
                } else {
                    try {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

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