


import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    public Duke(){}
    private void Greet(){
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    private void Echo(String a){
        System.out.println(a);
    }

    private void Exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) throws DukeException {
        Duke d = new Duke();
        Scanner scan = new Scanner(System.in);
        d.Greet();
        TaskList t = new TaskList();
        while(scan.hasNextLine()){
            String a = scan.next();
            if(a.equals("bye")){
                d.Exit();
                break;
            }
            else if(a.equals("list")){
                t.getList();
            }
            else if(a.equals("done")){
                int num = scan.nextInt();
                t.MarkAsDone(num-1);
                String c = scan.nextLine();
            }
            else if(a.equals("event")) {
                String b = scan.nextLine();
                if (b.length() == 0) {
                    System.out.println("\u2639" + " OOPS!!! the description of a event cannot be empty. ");
                } else {
                    int first = b.indexOf('/');
                    String desc = b.substring(0, first - 1);
                    String byTime = b.substring(first + 4);
                    Task t1 = new Event(desc, byTime);
                    t.Add(t1);
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
                    Task t1 = new Deadline(descr, byTime);
                    t.Add(t1);
                }
            }
            else if (a.equals("todo")) {
                String todoDetails = scan.nextLine();
                if (todoDetails.length() == 0)
                    System.out.println("\u2639" + " OOPS!!! the description of a todo cannot be empty. ");
                else {
                    Task t1 = new ToDo(todoDetails);
                    t.Add(t1);
                }
            }
            else if(a.equals("delete")){
                int number = scan.nextInt();
                t.deleteTask(number);
            }
            else
                System.out.println("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");

        }

    }
}
