import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;


public class Storage {

    //public static ArrayList<Task> tasks;
    private String fileloc;
    private File tempFile;
    private Tasklist tasklist;

    public Storage(String fileloc) throws IOException {
        //ArrayList<Task> tasks = new ArrayList<Task>();
        Tasklist tasklist = new Tasklist();
        this.tasklist = tasklist;
        //this.tasks = tasks;
        this.fileloc = fileloc;
        tempFile = new File(fileloc);

        if (hasFile()) {
            readFile();
        } else {
            createFile();
        }
    }

    public Tasklist getTasks() {
        return tasklist;
    }

    public void saveFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(fileloc);
        String texttoadd = "";
        System.out.println("These will be saved");
        for (int i = 0; i < tasks.size(); i ++) {
            int num = i + 1;
            String textpart = num + ". " + tasks.get(i);
            if (i == 0) {
                texttoadd = textpart;
            } else {
                texttoadd = texttoadd + "\n" + textpart;
            }
        }
        try {
            fw.write(texttoadd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public boolean hasFile() {
        return tempFile.exists();
    }

    public void createFile() throws IOException {
        boolean exists = tempFile.exists();
        if (tempFile.createNewFile()) { //no file yet so create

        } else { // there is already a file so just print

        }
    }

    public void readFile() throws FileNotFoundException {
        ArrayList<String> filetasks = new ArrayList<String>();

        Scanner s = new Scanner(tempFile);
        while (s.hasNext()) {
            String t = s.nextLine();
            System.out.println(t);
            filetasks.add(t);
        }
        createTasks(filetasks);
    }

    public void createTasks(ArrayList<String> filetasks) {
        while (!filetasks.isEmpty()) {
            String tas = filetasks.remove(0);
            String[] splittedtas = tas.split("");
            if (splittedtas[4].equals("T")) {
                String word = "";
                for (int i = 10; i < splittedtas.length; i ++) {
                    word = word + splittedtas[i];
                }
                tasklist.addTodo(new Todo(word));
                if (isDone(splittedtas[7])) {
                    Task t = tasklist.returnTasks().get(tasklist.size()-1);
                    t.markAsDone();
                }
            } else if (splittedtas[4].equals("D")) {
                String word = "";
                String deadline = "";
                for (int i = 10; i < splittedtas.length; i ++) {
                    if (splittedtas[i].equals("(")) {
                        for (int j = i+5; j < splittedtas.length-1; j++) {
                            deadline = deadline + splittedtas[j];
                        }
                        break;
                    } else {
                        if (splittedtas[i+1].equals("(")) {

                        } else {
                            word = word + splittedtas[i];
                        }
                    }
                }
                tasklist.addDeadline(new Deadline(word, deadline));
                if (isDone(splittedtas[7])) {
                    Task t = tasklist.returnTasks().get(tasklist.size()-1);
                    t.markAsDone();
                }
            } else if (splittedtas[4].equals("E")) {
                String word = "";
                String date = "";
                for (int i = 10; i < splittedtas.length; i ++) {
                    if (splittedtas[i].equals("(")) {
                        for (int j = i+5; j < splittedtas.length-1; j++) {
                            date = date + splittedtas[j];
                        }
                        break;
                    } else {
                        if (splittedtas[i+1].equals("(")) {

                        } else {
                            word = word + splittedtas[i];
                        }
                    }
                }
                tasklist.addEvent(new Event(word, date));
                if (isDone(splittedtas[7])) {
                    Task t = tasklist.returnTasks().get(tasklist.size()-1);
                    t.markAsDone();
                }
            } else {

            }
        }
    }

    public static boolean isDone(String op) {
        return (op.equals("\u2713"));
    }
}