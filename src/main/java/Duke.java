import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Represents the personal assistant and contains the main method.
 */
public class Duke {

    private TaskList list;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Creates a duke object which stores data in the specified path .
     * @param s the absolute path of the file where the data is stored.
     */
    public Duke(String s){
        list = new TaskList();
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(s);
    }
   public void run() throws ParseException, IOException {
       Scanner scan = new Scanner(System.in);

       this.ui.Greet();
       File f = new File("/Users/sairo/OneDrive/Desktop/Duke/Data/Duke.txt");
       Scanner sca = new Scanner(f);
       while(sca.hasNext()){
           String dat = sca.nextLine();
           this.parser.readTask(dat,this.list);
       }
       while(scan.hasNextLine()){
           String a = scan.next();
           if(a.equals("bye")){
               this.ui.Exit();
               String s = "";
               for(Task t : this.list.taskList){
                   s = s + t.toString()+ "\n";
               }
               this.storage.writeFile(s);
               break;
           }
           else if(a.equals("list")){
               this.list.getList();
           }
           else if(a.equals("done")){
               int num = scan.nextInt();
               this.list.MarkAsDone(num-1);
               String c = scan.nextLine();
           }
           else if(a.equals("event")) {
               String b = scan.nextLine();
               this.list.readEvent(b);
           }
           else if(a.equals("deadline")) {
               String det = scan.nextLine();
               this.list.readDeadline(det);
           }
           else if (a.equals("todo")) {
               String todoDetails = scan.nextLine();
               this.list.readTodo(todoDetails);
           }
           else if(a.equals("delete")){
               int number = scan.nextInt();
               this.list.deleteTask(number);
           }
           else if(a.equals("find")){
               String required = scan.next();
               this.list.Find(required);
           }
           else{
               System.out.println("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
               String empt = scan.nextLine();
           }
       }
   }
    public static void main(String[] args) throws DukeException, IOException, ParseException {
       new Duke("/Users/sairo/OneDrive/Desktop/Duke/Data/Duke.txt").run();

    }
}
