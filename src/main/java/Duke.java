import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadtxt());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);


        while(sc.hasNextLine()){
            String s = sc.nextLine();
            String[] a = Parser.parse(s);
            switch (a[0]) {
                case "bye" :
                    ui.bye();
                    return;
                case "list" :
                    list(tasks.getlist());
                    break;
                case "done":
                    int n = Integer.parseInt(a[1]);
                    done(tasks.getlist(), n-1);
                    break;
                case "todo":
                    try{
                        todo(tasks.getlist(),s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "event":
                    try{
                        event(tasks.getlist(),s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "deadline":

                    try{
                        deadline(tasks.getlist(),s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    int m = Integer.parseInt(a[1]);
                    delete(tasks.getlist(), m-1);
                    break;


                default:
                    try{
                        other(s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

            }
        }
    }

    public static void main(String[] args) {
        File f = new File("data");
        f.mkdir();
        new Duke("data/tasks.txt").run();
    }

    public void todo(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 4) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            String td = s.substring(5);
            Task t = new Todo(td);
            list.add(t);
            ui.drawline();
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            ui.drawline();
            storage.addtask(tasks.getlist());
        }
    }
    public void deadline(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 8) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String deadlineDescription = s.substring(9, firstIndex);
            String deadlineBy = s.substring(secondIndex);
            Deadline d = new Deadline(deadlineDescription, deadlineBy);
            tasks.timeform(deadlineBy,d);
        }
    }
    public void event(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 5) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String eventDescription = s.substring(6, firstIndex);
            String eventAt = s.substring(secondIndex);
            Event event = new Event(eventDescription, eventAt);
            tasks.timeform(eventAt,event);



        }
    }
    public void other(String s) throws ErrorException{
        throw new ErrorException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public void delete(ArrayList<Task>list,int p){
        Task rt = list.get(p);
        ui.drawline();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     "+rt);
        list.remove(p);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        ui.drawline();
        storage.addtask(tasks.getlist());
    }

    public void list(ArrayList<Task>list){
        ui.drawline();
        System.out.println("     Here are the tasks in your list:");
        tasks.showlist();
        ui.drawline();
    }
    public void echo(ArrayList<Task>list,Task t){
        list.add(t);
        ui.drawline();
        System.out.println("     added: "+t.getName());
        ui.drawline();
    }
    public void done(ArrayList<Task>list,int k){
        ui.drawline();
        list.get(k).setDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     [✓]"+list.get(k).getName());
        ui.drawline();
        storage.addtask(tasks.getlist());
    }

}
