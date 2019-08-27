import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Duke {

    private TaskList list;
    public Duke(){
        list = new TaskList();

    }
    private void Greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

    }
    public static void readTask(String s, TaskList t) throws ParseException {
        DateFormat df = new SimpleDateFormat("E, MMM dd yyyy HH:mm");

        if(s.contains("[T]")){
            if(s.contains( "["+"\u2713"+"]")){
                t.Add(new ToDo(s.substring(7), true));
            }
            else{
                t.Add(new ToDo(s.substring(7)));
            }
        }
        else if(s.contains("[E]")){

            int start = s.indexOf('(');
            String e = s.substring(7,start-1);
            String dl = s.substring(start+5, start+27);
            Date at = df.parse(dl);
            if(s.contains( "["+"\u2713"+"]")){
                t.Add(new Event(e,at, true));
            }
            else{
                t.Add(new Event(e,at));
            }
        }
        else{
            int sl = s.indexOf('/');
            String d = s.substring(7,sl-1);
            String by = s.substring(sl+4);
            if(s.contains( "["+"\u2713"+"]")){
                t.Add(new Deadline(d,by,true));
            }
            else
                t.Add(new Deadline(d,by));
        }
    }
    private void Echo(String a){
        System.out.println(a);
    }

    private void Exit() throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
    }
    private static void writeFile(String fileName, String content) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(content);
        fw.close();
    }
    public static void main(String[] args) throws DukeException, IOException, ParseException {
        Duke d = new Duke();
        Scanner scan = new Scanner(System.in);
        d.Greet();
        File f = new File("/Users/sairo/OneDrive/Desktop/Duke/Data/Duke.txt");
        Scanner sca = new Scanner(f);
        while(sca.hasNext()){
            String dat = sca.nextLine();
            readTask(dat,d.list);
        }
        while(scan.hasNextLine()){
            String a = scan.next();
            if(a.equals("bye")){
                d.Exit();
                String s = "";
                for(Task t : d.list.taskList){
                    s = s + t.toString()+ "\n";
                }
                writeFile("/Users/sairo/OneDrive/Desktop/Duke/data/Duke.txt",s);
                break;
            }
            else if(a.equals("list")){
                d.list.getList();
            }
            else if(a.equals("done")){
                int num = scan.nextInt();
                d.list.MarkAsDone(num-1);
                String c = scan.nextLine();
            }
            else if(a.equals("event")) {
                String b = scan.nextLine();
                if (b.length() == 0) {
                    System.out.println("\u2639" + " OOPS!!! the description of a event cannot be empty. ");
                } else {
                    int first = b.indexOf('/');
                    String desc = b.substring(0, first - 1);
                    String on = b.substring(first + 4);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date at = df.parse(on);
                    Task t1 = new Event(desc, at, false);
                    d.list.Add(t1);
                    d.list.addMessage();
                }
            }
            else if(a.equals("deadline")) {
                String det = scan.nextLine();
                if (det.length() == 0) {
                    System.out.println("\u2639" + " OOPS!!! the description of a deadline cannot be empty. ");
                } else {
                    int first = det.indexOf('/');
                    String descr = det.substring(0, first - 1);
                    String byTime = det.substring(first + 4);
                    Task t1 = new Deadline(descr, byTime, false);
                    d.list.Add(t1);
                    d.list.addMessage();
                }
            }
            else if (a.equals("todo")) {
                String todoDetails = scan.nextLine();
                if (todoDetails.length() == 0)
                    System.out.println("\u2639" + " OOPS!!! the description of a todo cannot be empty. ");
                else {
                    Task t1 = new ToDo(todoDetails, false);
                    d.list.Add(t1);
                    d.list.addMessage();
                }
            }
            else if(a.equals("delete")){
                int number = scan.nextInt();
                d.list.deleteTask(number);
            }
            else{
                System.out.println("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                String empt = scan.nextLine();
            }
        }

    }
}
